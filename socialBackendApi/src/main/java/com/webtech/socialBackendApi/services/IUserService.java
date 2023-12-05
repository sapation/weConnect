package com.webtech.socialBackendApi.services;

import com.webtech.socialBackendApi.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User createUser(User user);

    User findUserByEmail(String email);

    List<User> getAllUsers();

    User findUserById(Integer userId);

    User followUser(Integer userId1, Integer userId2) throws Exception;

    boolean exist(Integer userId);

    User save(User user);

    User updateUser(Integer userId, User user);

    void deteleUser(Integer userId);

    List<User> searchUser(String query);
}
