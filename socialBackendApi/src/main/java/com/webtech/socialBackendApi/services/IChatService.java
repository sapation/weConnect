package com.webtech.socialBackendApi.services;

import com.webtech.socialBackendApi.models.Chat;
import com.webtech.socialBackendApi.models.User;

import java.util.List;

public interface IChatService {
    Chat createChat(User reqUser, User user);
    Chat findChatById(Integer chatId);
    List<Chat> findUserChat(Integer userId);
    Chat save(Chat chat);
}
