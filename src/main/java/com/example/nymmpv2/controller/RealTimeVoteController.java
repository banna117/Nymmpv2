package com.example.nymmpv2.controller;

import com.example.nymmpv2.dto.vote.*;
import com.example.nymmpv2.model.Group;
import com.example.nymmpv2.model.Room;
import com.example.nymmpv2.service.RealTimeVoteService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vote")
public class RealTimeVoteController {

    @Autowired
    private RealTimeVoteService voteService;

    @MessageMapping("/start-vote")
    @SendTo("/topic/vote")
    public VoteResponseDto startVote(VoteRequestDto voteRequest) {
        // 방장이 투표를 시작
        return voteService.startVote(voteRequest);
    }

    @MessageMapping("/submit-vote")
    @SendTo("/topic/vote-result")
    public VoteResultDto submitVote(UserVoteDto userVote) {
        // 참여자가 투표 응답 제출
        voteService.submitUserVote(userVote);
        return voteService.calculateRealTimeResults(userVote.getPollId());
    }

    // REST API 엔드포인트 - 방 참여
    @PostMapping("/join-room")
    public RoomJoinResponseDto joinRoom(@RequestBody RoomJoinRequestDto roomJoinRequestDto) {
        // 방에 참여 요청
        return voteService.joinRoom(roomJoinRequestDto.getRoomCode(),roomJoinRequestDto.getUser());

    }
    // WebSocket 엔드포인트 - 방 정보 업데이트 브로드캐스트
    @MessageMapping("/update-room/{roomCode}")
    @SendTo("/topic/room-updates/{roomCode}")
    public Room broadcastRoomUpdate(@DestinationVariable String roomCode) {
        Room updatedRoom = voteService.getRoomByCode(roomCode);
        return updatedRoom;
    }

    // REST API 엔드포인트 - 방 만들기
    @PostMapping("/create-room")
    public RoomCreationResponseDto makeRoom(@RequestBody RoomCreationRequestDto request) {
        // Room 만들기
        String roomCode = voteService.makeRoom(request.getGroup(), request.getMaster());

        // 방 생성 후 생성된 Room 가져오기
        // HOW?
        Room createdRoom = voteService.getRoomByCode(roomCode);

        // 응답 DTO 생성 후 반환
        return new RoomCreationResponseDto(
                createdRoom.getName(),
                createdRoom.getCode(),
                createdRoom.getStatus().name()
        );
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