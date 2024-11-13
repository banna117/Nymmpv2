package com.example.nymmpv2.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long poll_id;

    // question 의 형식 확정하기
    @OneToOne
    @JoinColumn(name = "questionId", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "groupId",nullable = false)
    private Group group;

    //투표 옵션 타입 (1: 전체, 2: Yes/No, 3: 랜덤 4명)
    @Column(columnDefinition = "TINYINT", nullable = false)
    private Integer optionType;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP",nullable = false)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP",nullable = false)
    private LocalDateTime expiresAt;
}





}
