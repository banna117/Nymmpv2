package com.example.nymmpv2.controller;

import com.example.nymmpv2.dto.vote.RoomCreationRequestDto;
import com.example.nymmpv2.dto.vote.RoomCreationResponseDto;
import com.example.nymmpv2.model.Room;
import com.example.nymmpv2.service.RealTimeVoteService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vote")
public class RealTimeVoteController {

    @Autowired
    private RealTimeVoteService voteService;

    @MessageMapping("/start-vote")
    @SendTo("/topic/vote")
    public VoteResponseDto startVote(VoteRequestDto voteRequest) {
        // 방장이 투표를 시작
        return voteService.startVote(voteRequest);
    }

    @MessageMapping("/submit-vote")
    @SendTo("/topic/vote-result")
    public VoteResultDto submitVote(UserVoteDto userVote) {
        // 참여자가 투표 응답 제출
        voteService.submitUserVote(userVote);
        return voteService.calculateRealTimeResults(userVote.getPollId());
    }

    // REST API 엔드포인트 - 방 만들기
    @PostMapping("/create-room")
    public RoomCreationResponseDto makeRoom(@RequestBody RoomCreationRequestDto request) {
        // Room 만들기
        voteService.makeRoom(request.getGroup(), request.getMaster());

        // 방 생성 후 생성된 Room 가져오기
        Room createdRoom = voteService.getRoomByGroup(request.getGroup());

        // 응답 DTO 생성 후 반환
        return new RoomCreationResponseDto(
                createdRoom.getName(),
                createdRoom.getCode(),
                createdRoom.getStatus().name()
        );
    }
}