package com.webtech.socialBackendApi.controllers;

import com.webtech.socialBackendApi.models.Story;
import com.webtech.socialBackendApi.models.User;
import com.webtech.socialBackendApi.services.IStoryService;
import com.webtech.socialBackendApi.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StoryController {
    private final IStoryService storyService;
    private final IUserService userService;

    public StoryController(IStoryService storyService, IUserService userService) {
        this.storyService = storyService;
        this.userService = userService;
    }

    @PostMapping("/story")
    public ResponseEntity<Story> createStory(@RequestHeader("Authorization") String jwt, @RequestBody Story story){
        User logUser = userService.findUserByJwt(jwt);
        Story createdStory = storyService.createStory(story, logUser);
        return new ResponseEntity<>(createdStory, HttpStatus.CREATED);
    }

    @GetMapping("/story/user")
    public ResponseEntity<List<Story>> findStoryByUser(@RequestHeader("Authorization") String jwt){
        User logUser = userService.findUserByJwt(jwt);
        List<Story> stories = storyService.findStoryByUserId(logUser.getId());
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }
}
