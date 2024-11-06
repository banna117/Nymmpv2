package com.example.nymmpv2.model;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Entity
@Data
public class UserResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long response_id;

    @OneToOne
    @JoinColumn(name = "poll_id",nullable = false)
    private Poll poll;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


    @Column(columnDefinition = "INT",nullable = false)
    private Integer option_type;

    @Column(columnDefinition = "TIMESTAMP",nullable = false)
    private Time created_at;

    @Column(columnDefinition = "TIMESTAMP",nullable = false)
    private Time expires_at;
}
