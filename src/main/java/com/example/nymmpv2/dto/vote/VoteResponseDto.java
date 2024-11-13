package com.example.nymmpv2.dto.vote;

import java.util.Map;

public class VoteResponseDto {
    private String roomCode;
    private int questionId;
    private String message;

    public String getRoomCode() {
        return roomCode;
    }
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public VoteResponseDto(String roomCode, int questionId, String message) {
        this.roomCode = roomCode;
        this.questionId = questionId;
        this.message = message;

    }
}