package com.example.nymmpv2.model;

public class LiveVotePollKey {
    private Long pollid;
    private Long userid;
    public Long getPollid() {
        return pollid;
    }
    public void setPollid(Long pollid) {
        this.pollid = pollid;
    }
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public LiveVotePollKey(Long pollid, Long userid) {
        this.pollid = pollid;
        this.userid = userid;
    }
}

