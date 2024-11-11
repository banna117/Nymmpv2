package com.example.nymmpv2.dto.vote;

import com.example.nymmpv2.model.Group;
import com.example.nymmpv2.model.User;

public class RoomJoinRequestDto {
    private User user;
    private String roomCode;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {

        this.user = user;
    }
    public String getRoomCode() {
        return this.roomCode;
    }
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public RoomJoinRequestDto(User user, String roomCode) {
        this.user = user;
        this.roomCode = roomCode;
    }
}
