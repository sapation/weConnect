package com.webtech.socialBackendApi.services;

import com.webtech.socialBackendApi.models.Comment;

import java.util.List;

public interface ICommentService {
    Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;
    Comment findCommentById(Integer commentId) throws Exception;
    String deleteComment(Integer commentId, Integer userId) throws Exception;
    List<Comment> findAllComment();
    Comment likeComment(Integer commentId, Integer userId) throws Exception;
}
