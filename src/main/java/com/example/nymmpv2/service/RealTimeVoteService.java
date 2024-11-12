package com.example.nymmpv2.service;

import com.example.nymmpv2.dto.vote.RoomJoinResponseDto;
import com.example.nymmpv2.model.Group;

import com.example.nymmpv2.model.Question;
import com.example.nymmpv2.model.Room;
import com.example.nymmpv2.model.User;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


//TODO: 인원, 방 제거 로직 생각하기
@Service
public class RealTimeVoteService extends AbstractVoteService {

    // 현재 활성화된 그룹 <groupcode, Room> 모아놓기
    private final Map<String, Room> liveGroups = new HashMap<>();

    // Room 검색
    public Room getRoomByCode(String roomCode) {return liveGroups.get(roomCode);}

    // 방 만들기
    public String makeRoom(Group group, User master) {
        // 방 이름 기본값 설정: Username의 그룹
        String roomName = master.getUsername() + "의 그룹";
        // 고유 방 코드 생성
        // TODO: roomCode 생성방식 정하기
        String roomCode = UUID.randomUUID().toString();

        // Room 객체 생성
        Room room = new Room(roomName, roomCode, master);

        // 방 초기화: master를 참가자로 추가하고 방장 설정
        room.addParticipant(master);

        // 방을 liveGroups 맵에 추가
        liveGroups.put(roomCode, room);
        return roomCode;
    }
    // 방 참여 메소드
    public RoomJoinResponseDto joinRoom(String roomCode, User participant) {
        Room room = liveGroups.get(roomCode);
        if (room == null) {
            return new RoomJoinResponseDto(null, "Can't join Room");
        }
        room.addParticipant(participant);
        return new RoomJoinResponseDto(room,"joined room");
    }
    //질문 추가 메소드
    public String addQuestion(String roomCode,Question question){
        Room room = liveGroups.get(roomCode);
        if (room == null) {
            return "Cannot get Room by code";
        }
        if(room.addQuestion(question)){
            return "Added question";
        }
        return "Cannot add question to Room";
    }

    @Override
    public VoteResponseDto startVote(VoteRequestDto voteRequestDto) {
        // room 내부의
        Room room = getRoomByGroup(voteRequestDto.getGroup());
        List<Question> questions = room.getQuestions();

        for (Question question : questions) {

        }
        for (getRoomByGroup().getQuestions())
        VoteResponseDto response = new VoteResponseDto();
        response.setPollId(voteRequestDto.getPollId());
        response.setMessage("Vote started successfully!");
        return response;
    }

    @Override
    public void submitUserVote(UserVoteDto userVoteDto) {
        // 사용자의 응답을 저장하고 결과 업데이트 로직
        int pollId = userVoteDto.getPollId();
        VoteResultDto result = voteResults.getOrDefault(pollId, new VoteResultDto());
        result.updateResult(userVoteDto.getSelectedOption());
        voteResults.put(pollId, result);
    }

    @Override
    public VoteResultDto calculateRealTimeResults(int pollId) {
        // 현재 투표 결과 계산
        return voteResults.get(pollId);
    }
}