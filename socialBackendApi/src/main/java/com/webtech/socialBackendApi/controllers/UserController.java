package com.webtech.socialBackendApi.controllers;

import com.webtech.socialBackendApi.models.User;
import com.webtech.socialBackendApi.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final IUserService _userService;

    public UserController(IUserService userService) {
        this._userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = _userService.createUser(user);
        return  new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return _userService.getAllUsers();
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Integer userId){
        User findUser = _userService.findUserById(userId);
        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    @PutMapping("/api/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Integer userId,@RequestBody User user){
        if(!_userService.exist(userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User updatedUser = _userService.updateUser(userId, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") Integer userId) {
        _userService.deteleUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/api/users/follow/{userId1}/{userId2}")
    public User followUserHandler(
            @PathVariable("userId1") Integer userId1,
            @PathVariable("userId2") Integer userId2) throws Exception {
        return _userService.followUser(userId1, userId2);
    }

    @GetMapping("/api/users/search")
    public List<User> searchUserHandler(@RequestParam("query") String query) {
        return _userService.searchUser(query);
    }
}
