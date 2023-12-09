package com.webtech.socialBackendApi.services.impl;

import com.webtech.socialBackendApi.models.Chat;
import com.webtech.socialBackendApi.models.User;
import com.webtech.socialBackendApi.repository.IChatRepository;
import com.webtech.socialBackendApi.services.IChatService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImpl implements IChatService {
    private final IChatRepository chatRepository;

    public ChatServiceImpl(IChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Chat createChat(User reqUser, User user) {
        Chat isExist = chatRepository.findChatByUsersId(user, reqUser);
        if (isExist != null) {
            return  isExist;
        }
        Chat chat = new Chat();
        chat.getUsers().add(user);
        chat.getUsers().add(reqUser);
        chat.setCreatedAt(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) {
        return chatRepository.findById(chatId).get();
    }

    @Override
    public List<Chat> findUserChat(Integer userId) {
        return chatRepository.findByUsersId(userId);
    }

    @Override
    public Chat save(Chat chat) {
        return chatRepository.save(chat);
    }
}
