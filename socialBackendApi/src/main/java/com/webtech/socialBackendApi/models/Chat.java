package com.webtech.socialBackendApi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String chat_name;
    private String chat_image;
    @ManyToMany
    private List<User> users = new ArrayList<>();

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages = new ArrayList<>();

}
