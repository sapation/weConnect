package com.webtech.socialBackendApi.controllers;

import com.webtech.socialBackendApi.models.Chat;
import com.webtech.socialBackendApi.models.User;
import com.webtech.socialBackendApi.request.CreateChatRequest;
import com.webtech.socialBackendApi.services.IChatService;
import com.webtech.socialBackendApi.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatController {
    private final IChatService chatService;
    private final IUserService userService;
    public ChatController(IChatService chatService, IUserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }
     @PostMapping("/chats")
     public ResponseEntity<Chat> createChat(@RequestHeader("Authorization") String jwt, @RequestBody  CreateChatRequest req){
        User reqUser = userService.findUserByJwt(jwt);
        User user2= userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(reqUser, user2);
        return new ResponseEntity<>(chat, HttpStatus.CREATED);
     }

    @GetMapping("/chats")
    public ResponseEntity<List<Chat>> findUserChat(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        List<Chat> chats = chatService.findUserChat(user.getId());
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @GetMapping("/chats/{chatId}")
    public ResponseEntity<Chat> findChatById(@PathVariable("chatId") Integer chatId){
        Chat chat = chatService.findChatById(chatId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
}
