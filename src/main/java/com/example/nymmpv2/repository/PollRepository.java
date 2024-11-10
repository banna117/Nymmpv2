package com.example.nymmpv2.repository;

import com.example.nymmpv2.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Long> {

    List<Poll> findByGroup_GroupId(Long groupId);
    //특정 시간 이후에 만료되는 투표 검색
    List<Poll> findByExpiresAtAfter(java.time.LocalDateTime now);
}