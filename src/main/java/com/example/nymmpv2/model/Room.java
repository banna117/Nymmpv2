package com.example.nymmpv2.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Room {
    private String name;                // 방 이름
    private String code;                // 방 코드 (고유 식별자)
    private RoomStatus status;          // 방 상태 (voting, waiting, done)
    private User master;                // 방장
    private List<User> participants;    // 참여 인원들
    private List<Poll> polls;   // 방에 설정된 질문들
    private Poll currPoll;     // 현재 진행 중인 질문
    // <pollid, userid> , UserResponse
    private Map<LiveVotePollKey, UserResponse> responses; // 유저별 답변 관리

    // 초기화
    public Room(String name, String code, User master) {
        this.name = name;
        this.code = code;
        this.status = RoomStatus.WAITING; // 초기 상태는 대기
        this.master = master;
        this.participants = new ArrayList<>();
        this.polls = new ArrayList<>();
        this.responses = new HashMap<>();
    }

    // 참여자 추가
    public boolean addParticipant(User user) {
         return participants.add(user);
    }

    // 질문 추가
    public boolean addPoll(Poll poll) {
        return polls.add(poll);
    }

    // 현재 질문 설정
    public void setCurrentPoll(Poll poll) {
        this.currPoll = poll;
    }

    // 상태 변경
    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    // 유저 응답 저장
    public void addResponse(User user, UserResponse response) {
        responses.put(user, response);
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getCode() { return code; }
    public RoomStatus getStatus() { return status; }
    public User getMaster() { return master; }
    public List<User> getParticipants() { return participants; }
    public List<Poll> getPolls() { return polls; }
    public Poll getCurrPoll() { return currPoll; }
    public Map<LiveVotePollKey, UserResponse> getResponses() { return responses; }
}
