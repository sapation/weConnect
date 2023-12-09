package com.webtech.socialBackendApi.controllers;

import com.webtech.socialBackendApi.models.Comment;
import com.webtech.socialBackendApi.models.User;
import com.webtech.socialBackendApi.services.ICommentService;
import com.webtech.socialBackendApi.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final ICommentService commentService;
    private final IUserService userService;

    public CommentController(ICommentService commentService, IUserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/comments/post/{postId}")
    public ResponseEntity<Comment> createComment(
            @RequestBody Comment comment,
            @PathVariable("postId") Integer postId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User logUser = userService.findUserByJwt(jwt);
        Comment savedComment = commentService.createComment(comment,postId,logUser.getId());
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments =  commentService.findAllComment();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<Comment> findCommentById(@PathVariable("commentId") Integer commentId) throws Exception {
        Comment comment = commentService.findCommentById(commentId);
        return  new ResponseEntity<>(comment, HttpStatus.OK);
    }
    @PutMapping("/comments/like/{commentId}")
    public Comment likeComment(
            @PathVariable("commentId") Integer commentId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User logUser = userService.findUserByJwt(jwt);
        return commentService.likeComment(commentId,logUser.getId());
    }
}
