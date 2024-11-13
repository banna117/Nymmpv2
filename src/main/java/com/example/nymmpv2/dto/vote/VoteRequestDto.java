package com.example.nymmpv2.dto.vote;

public class VoteRequestDto {
    private String roomCode;         // 투표가 진행될 방의 Code
    private int questionId;        // 질문의 ID
    private int userId;            // 투표하는 사용자의 ID
    private int selectedOption;    // 사용자가 투표한 것.

    // Getters and Setters
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }
}