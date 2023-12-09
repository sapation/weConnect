package com.webtech.socialBackendApi.services.impl;

import com.webtech.socialBackendApi.models.Post;
import com.webtech.socialBackendApi.models.User;
import com.webtech.socialBackendApi.repository.IPostRepository;
import com.webtech.socialBackendApi.services.IPostService;
import com.webtech.socialBackendApi.services.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {
    private final IPostRepository _postRepository;
    private final IUserService _userService;

    public PostServiceImpl(IPostRepository _postRepository,IUserService _userService) {
        this._postRepository = _postRepository;
        this._userService = _userService;
    }

    @Override
    public Post createPost(Post post, Integer userId) throws Exception {
         User user =  _userService.findUserById(userId);
         post.setUser(user);
         post.setCreatedAt(LocalDateTime.now());
         return _postRepository.save(post);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = _userService.findUserById(userId);
        if (post.getUser().getId() != user.getId()) {
            throw new Exception("you can not delete another user post");
        }
        _postRepository.deleteById(postId);
        return "Post delete successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) throws Exception {
        return _postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> user =  _postRepository.findById(postId);
        if (user.isEmpty()){
            throw new Exception("Post not found with id"+postId);
        }

        return user.get();
    }

    @Override
    public List<Post> findAllPost() {
        return _postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = _userService.findUserById(userId);
        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }else {
            user.getSavedPost().add(post);
        }
        _userService.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = _userService.findUserById(userId);
        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }

        return _postRepository.save(post);
    }

    @Override
    public void save(Post post) {
        _postRepository.save(post);
    }
}
