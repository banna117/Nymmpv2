package com.example.nymmpv2.dto.room;

import com.example.nymmpv2.model.Question;

public class AddQuestionRequestDto {
    private Question question;
    private String roomCode;

    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
    public String getRoomCode() {
        return roomCode;
    }
    public AddQuestionRequestDto(Question question, String roomCode) {
        this.question = question;
        this.roomCode = roomCode;
    }
}
