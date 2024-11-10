package com.example.nymmpv2.repository;

import com.example.nymmpv2.model.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {
    List<UserResponse> findByPoll_PollId(Long pollId);
    List<UserResponse> findByUser_UserId(Long userId);
}