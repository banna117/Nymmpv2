package com.example.nymmpv2.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
@Table(name = "`groups`")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long groupId;

    @Column(length = 100, nullable = false, unique = true)
    private String groupName;

    @Column(length = 20, nullable = false,unique = true)
    private String groupHashCode;

    @Builder
    public Group(Long groupId, String groupName,String groupHashCode) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupHashCode = groupHashCode;
    }
}
