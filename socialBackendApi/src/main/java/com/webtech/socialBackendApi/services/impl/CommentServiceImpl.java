package com.webtech.socialBackendApi.services.impl;

import com.webtech.socialBackendApi.models.Comment;
import com.webtech.socialBackendApi.models.Post;
import com.webtech.socialBackendApi.models.User;
import com.webtech.socialBackendApi.repository.ICommentRepository;
import com.webtech.socialBackendApi.services.ICommentService;
import com.webtech.socialBackendApi.services.IPostService;
import com.webtech.socialBackendApi.services.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl implements ICommentService {
    private final IPostService postService;
    private final IUserService userService;
    private final ICommentRepository commentRepository;

    public CommentServiceImpl(IPostService postService, IUserService userService, ICommentRepository commentRepository) {
        this.postService = postService;
        this.userService = userService;
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        post.getComments().add(savedComment);
        postService.save(post);

        return savedComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        if(!commentRepository.existsById(commentId)){
            throw new Exception("Comment not found with the Id "+commentId);
        }
        return commentRepository.findById(commentId).get();
    }

    @Override
    public String deleteComment(Integer commentId, Integer userId) throws Exception {
        Comment comment = findCommentById(commentId);
        if (!Objects.equals(comment.getUser().getId(), userId)) {
            throw  new Exception("you can not someone comment");
        }
        commentRepository.deleteById(commentId);
        return "Comment deleted successfully";
    }

    @Override
    public List<Comment> findAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Comment comment = findCommentById(commentId);
        if(comment.getLiked().contains(user)){
            comment.getLiked().remove(user);
        } else {
            comment.getLiked().add(user);
        }
        return commentRepository.save(comment);
    }
}
