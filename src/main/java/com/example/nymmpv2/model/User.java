package com.example.nymmpv2.model;

import lombok.*;
import jakarta.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long userId;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 45, nullable = false)
    private String username;

    @Column(length = 20, nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(columnDefinition = "BIGINT")
    private Long kakaoId;

    @Builder
    public User(String email, String username, String password, Group group, Long kakaoId) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.group = group;
        this.kakaoId = kakaoId;
    }

    public void assignGroup(Group group) {
        this.group = group;
    }

}
