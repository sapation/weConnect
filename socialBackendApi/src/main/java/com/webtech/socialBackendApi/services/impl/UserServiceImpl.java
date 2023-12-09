package com.webtech.socialBackendApi.services.impl;

import com.webtech.socialBackendApi.config.JwtProvider;
import com.webtech.socialBackendApi.models.User;
import com.webtech.socialBackendApi.repository.IUserRepository;
import com.webtech.socialBackendApi.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository _userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this._userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return _userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return _userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return _userRepository.findAll();
    }

    @Override
    public User findUserById(Integer userId) {
        return _userRepository.findById(userId).get();
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        _userRepository.save(user1);
        _userRepository.save(user2);

        return user1;
    }

    @Override
    public boolean exist(Integer userId) {
        return _userRepository.existsById(userId);
    }

    @Override
    public User save(User user) {
        return _userRepository.save(user);
    }

    @Override
    public User updateUser(Integer userId, User user) {
        return _userRepository.findById(userId).map(existingUser -> {
            Optional.ofNullable(user.getFirstName()).ifPresent(existingUser::setFirstName);
            Optional.ofNullable(user.getLastName()).ifPresent(existingUser::setLastName);
            Optional.ofNullable(user.getEmail()).ifPresent(existingUser::setEmail);
            Optional.ofNullable(user.getPassword()).ifPresent(existingUser::setPassword);
            Optional.ofNullable(user.getGender()).ifPresent(existingUser::setGender);
            return _userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User does not exist"));
    }

    @Override
    public void deteleUser(Integer userId) {
        _userRepository.deleteById(userId);
    }

    @Override
    public List<User> searchUser(String query) {
        return _userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        return _userRepository.findByEmail(email);
    }
}
