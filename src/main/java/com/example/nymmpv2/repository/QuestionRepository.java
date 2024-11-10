package com.example.nymmpv2.repository;

import com.example.nymmpv2.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByGroup_GroupId(Long groupId);
    List<Question> findByMadeBy_UserId(Long userId);
}