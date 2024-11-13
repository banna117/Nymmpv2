package com.example.nymmpv2.dto.room;

import com.example.nymmpv2.model.Group;
import com.example.nymmpv2.model.User;


public class RoomCreationRequestDto {
    private Group group;
    private User master;

    // Getters and Setters
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }
}

