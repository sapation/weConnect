package com.webtech.socialBackendApi.repository;

import com.webtech.socialBackendApi.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByChatId(Integer chatId);
}
