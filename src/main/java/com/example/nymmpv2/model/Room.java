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
    private List<Question> questions;   // 방에 설정된 질문들
    private Question currQuestion;      // 현재 진행 중인 질문
    private Map<User, UserResponse> responses; // 유저별 답변 관리

    public Room(String name, String code, User master) {
        this.name = name;
        this.code = code;
        this.status = RoomStatus.WAITING; // 초기 상태는 대기
        this.master = master;
        this.participants = new ArrayList<>();
        this.questions = new ArrayList<>();
        this.responses = new HashMap<>();
    }

    // 참여자 추가
    public void addParticipant(User user) {
        participants.add(user);
    }

    // 질문 추가
    public boolean addQuestion(Question question) {
        return questions.add(question);
    }

    // 현재 질문 설정
    public void setCurrentQuestion(Question question) {
        this.currQuestion = question;
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
    public List<Question> getQuestions() { return questions; }
    public Question getCurrQuestion() { return currQuestion; }
    public Map<User, UserResponse> getResponses() { return responses; }
}
