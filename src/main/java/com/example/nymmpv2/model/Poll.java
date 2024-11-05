package com.example.nymmpv2.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long poll_id;

    @OneToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "group_id",nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;




}
