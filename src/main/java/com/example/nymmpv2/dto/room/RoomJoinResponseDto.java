package com.example.nymmpv2.dto.room;

import com.example.nymmpv2.model.Room;

public class RoomJoinResponseDto {
    private Room room;
    private String result;
    public void setRoom(Room room) {
        this.room = room;
    }
    public void setResult(String result){
        this.result = result;
    }
    public RoomJoinResponseDto(Room room, String result) {
        this.room = room;
        this.result = result;
    }

}
