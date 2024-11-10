package com.example.nymmpv2.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String questionText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User made_by;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId",nullable = false)
    private Group group;

}
