package com.example.nymmpv2.model;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

@Entity
@Data
public class UserResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long responseId;

    @ManyToOne
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(columnDefinition = "INT", nullable = false)
    private Integer selectedOption;


    @Convert(converter = JsonToStringConverter.class)
    private Map<String, Object> responseDetails;


}
