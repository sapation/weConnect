package com.webtech.socialBackendApi.services;

import com.webtech.socialBackendApi.models.Post;

import java.util.List;

public interface IPostService {
    Post createPost(Post post, Integer userId) throws Exception;
    String deletePost(Integer postId, Integer userId) throws Exception;

    List<Post> findPostByUserId(Integer userId) throws Exception;
    Post findPostById(Integer postId) throws Exception;
    List<Post> findAllPost();
    Post savedPost(Integer postId, Integer userId) throws Exception;
    Post likePost(Integer postId, Integer userId) throws Exception;


}
