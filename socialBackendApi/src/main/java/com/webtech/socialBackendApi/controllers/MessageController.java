package com.webtech.socialBackendApi.controllers;

import com.webtech.socialBackendApi.models.Message;
import com.webtech.socialBackendApi.models.User;
import com.webtech.socialBackendApi.services.IMessageService;
import com.webtech.socialBackendApi.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {
    private final IMessageService messageService;
    private final IUserService userService;

    public MessageController(IMessageService messageService, IUserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/messages/chat/{chatId}")
    public ResponseEntity<Message> createMessage(@RequestHeader("Authorization") String jwt,
                                                 @RequestBody Message message,
                                                 @PathVariable("chatId") Integer chatId) {
        User user = userService.findUserByJwt(jwt);
        Message createMessage = messageService.createMessage(user,chatId, message);
        return  new ResponseEntity<>(createMessage, HttpStatus.CREATED);

    }

    @GetMapping("/messages/chat/{chatId}")
    public ResponseEntity<List<Message>> findChatsMessage(@PathVariable("chatId") Integer chatId) {
        List<Message> messages = messageService.findChatsMessages(chatId);
        return  new ResponseEntity<>(messages, HttpStatus.CREATED);

    }
}
