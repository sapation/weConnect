package com.webtech.socialBackendApi.services;

import com.webtech.socialBackendApi.models.Chat;
import com.webtech.socialBackendApi.models.Message;
import com.webtech.socialBackendApi.models.User;

import java.util.List;

public interface IMessageService {
    Message createMessage(User user, Integer chatId, Message message);
    List<Message> findChatsMessages(Integer chatId);
}
