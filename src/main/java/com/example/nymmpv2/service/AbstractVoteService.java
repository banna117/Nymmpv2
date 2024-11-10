package com.example.nymmpv2.service;

import com.example.nymmpv2.model.Group;

import java.util.List;

public abstract class AbstractVoteService {

    // 투표 시작 로직
    public abstract VoteResponseDto startVote(VoteRequestDto voteRequestDto);

    // 사용자 응답 처리 로직
    public abstract void submitUserVote(UserVoteDto userVoteDto);

    // 실시간 결과 계산 로직
    public abstract VoteResultDto calculateRealTimeResults(int pollId);

}