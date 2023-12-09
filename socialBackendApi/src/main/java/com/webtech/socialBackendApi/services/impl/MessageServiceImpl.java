package com.webtech.socialBackendApi.services.impl;

import com.webtech.socialBackendApi.models.Chat;
import com.webtech.socialBackendApi.models.Message;
import com.webtech.socialBackendApi.models.User;
import com.webtech.socialBackendApi.repository.IMessageRepository;
import com.webtech.socialBackendApi.services.IChatService;
import com.webtech.socialBackendApi.services.IMessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {
    private final IMessageRepository messageRepository;
    private final IChatService chatService;

    public MessageServiceImpl(IMessageRepository messageRepository, IChatService chatService) {
        this.messageRepository = messageRepository;
        this.chatService = chatService;
    }

    @Override
    public Message createMessage(User user, Integer chatId, Message message) {
        Message createMessage = new Message();
        Chat chat = chatService.findChatById(chatId);

        createMessage.setChat(chat);
        createMessage.setContent(message.getContent());
        createMessage.setImage(message.getImage());
        createMessage.setUser(user);
        createMessage.setCreatedAt(LocalDateTime.now());

        Message savedMessage = messageRepository.save(createMessage);
        chat.getMessages().add(savedMessage);
        chatService.save(chat);
        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) {
        return messageRepository.findByChatId(chatId);
    }
}
