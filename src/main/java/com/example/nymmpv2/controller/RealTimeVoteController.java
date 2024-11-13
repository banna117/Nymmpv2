package com.example.nymmpv2.controller;

import com.example.nymmpv2.dto.room.*;
import com.example.nymmpv2.dto.vote.VoteRequestDto;
import com.example.nymmpv2.dto.vote.VoteResponseDto;
import com.example.nymmpv2.model.Poll;
import com.example.nymmpv2.model.Question;
import com.example.nymmpv2.model.Room;
import com.example.nymmpv2.service.RealTimeVoteService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/vote")
public class RealTimeVoteController {

    @Autowired
    private RealTimeVoteService voteService;

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // REST API 엔드포인트 - 방 만들기
    @PostMapping("/create-room")
    public RoomCreationResponseDto makeRoom(@RequestBody RoomCreationRequestDto request) {
        // Room 만들기
        String roomCode = voteService.makeRoom(request.getGroup(), request.getMaster());

        // 방 생성 후 생성된 Room 가져오기
        // HOW?
        Room createdRoom = voteService.getRoomByCode(roomCode);
        return new RoomCreationResponseDto(
                createdRoom.getName(),
                createdRoom.getCode(),
                createdRoom.getStatus().name()
        );
    }
    // REST API 엔드포인트 - 방 참여
    @PostMapping("/join-room")
    public RoomJoinResponseDto joinRoom(@RequestBody RoomJoinRequestDto roomJoinRequestDto) {
        // 방에 참여 요청
        return voteService.joinRoom(roomJoinRequestDto.getRoomCode(),roomJoinRequestDto.getUser());

    }
    // 시작
    @MessageMapping("/start-vote")
    @SendTo("/topic/vote")
    public VoteResponseDto startVote(VoteRequestDto voteRequest) {
        // 방장이 투표를 시작
        VoteResponseDto response = voteService.startVote(voteRequest);

        // 현재 방의 질문 리스트를 가져옴
        List<Poll> polls = voteService.getPolls(voteRequest.getRoomCode());

        // 15초마다 각 질문을 순차적으로 보내는 스케줄러 설정
        scheduler.scheduleAtFixedRate(new QuestionSender(polls, voteRequest.getRoomCode()), 0, 15, TimeUnit.SECONDS);

        return response;
    }
    //
    private class QuestionSender implements Runnable {
        private final List<Poll> polls;
        private final String roomCode;
        private int currentIndex = 0;

        public QuestionSender(List<Poll> polls, String roomCode) {
            this.polls = polls;
            this.roomCode = roomCode;
        }

        @Override
        public void run() {
            //계속 보내도 되는지 상태를 확인할 것
            // room 의 상태가 바뀐다면 종료해야함.
            if (currentIndex < polls.size()) {
                Poll currentPoll = polls.get(currentIndex);
                //currentQuestion 보내기
                sendQuestionToRoom(currentQuestion, roomCode);
                currentIndex++;
            } else {
                // 모든 질문 보낸 후 종료
                scheduler.shutdown();
            }
        }
    }

    // Method to send a question to all clients in the room
    @SendTo("/topic/question/{roomCode}")
    public Question sendQuestionToRoom(Question question, String roomCode) {
        return question;
    }

    @MessageMapping("/submit-vote")
    @SendTo("/topic/vote-result")
    public VoteResultDto submitVote(UserVoteDto userVote) {
        // 참여자가 투표 응답 제출
        voteService.submitUserVote(userVote);
        return voteService.calculateRealTimeResults(userVote.getPollId());
    }


    // WebSocket 엔드포인트 - 방 정보 업데이트 브로드캐스트
    @MessageMapping("/update-room/{roomCode}")
    @SendTo("/topic/room-updates/{roomCode}")
    public Room broadcastRoomUpdate(@DestinationVariable String roomCode) {
        Room updatedRoom = voteService.getRoomByCode(roomCode);
        return updatedRoom;
    }


    //질문 추가
    @PostMapping("/add-question")
    public String addQuestion(@RequestBody AddQuestionRequestDto request){
        if(voteService.addQuestion(request.getRoomCode(), request.getQuestion()).equals("Added question")){
            broadcastRoomUpdate(request.getRoomCode());
            return "Added question";
        }

    }
}