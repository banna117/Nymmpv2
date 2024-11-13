package com.example.nymmpv2.dto.room;

// RoomCreationResponseDto.java
public class RoomCreationResponseDto {
    private String roomName;
    private String roomCode;
    private String status;

    public RoomCreationResponseDto(String roomName, String roomCode, String status) {
        this.roomName = roomName;
        this.roomCode = roomCode;
        this.status = status;
    }

    // Getters and Setters
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}