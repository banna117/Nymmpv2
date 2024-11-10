package com.example.nymmpv2.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long groupId;

    @Column(length = 100, nullable = false, unique = true)
    private String groupName;

    @Column(length = 20, nullable = false,unique = true)
    private String groupHashCode;

}
