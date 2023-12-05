package com.webtech.socialBackendApi.controllers;

import com.webtech.socialBackendApi.models.Post;
import com.webtech.socialBackendApi.response.ApiResponse;
import com.webtech.socialBackendApi.services.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Post> createPost(@PathVariable("userId") Integer userId,
                                           @RequestBody Post post) throws Exception {
        Post createdPost = postService.createPost(post, userId);

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId,
                                                  @PathVariable("userId") Integer userId) throws Exception {
        String message = postService.deletePost(postId, userId);
        ApiResponse response = new ApiResponse(message, true);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post>findPostByIdHandler(@PathVariable("postId") Integer postId)
            throws Exception {
        Post post = postService.findPostById(postId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUserPostsHandler(@PathVariable("userId") Integer userId)
            throws Exception {
        List<Post> posts = postService.findPostByUserId(userId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPost() {
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable("postId") Integer postId,
                                                 @PathVariable("userId")Integer userId)
            throws Exception {
        Post savedPost = postService.savedPost(postId, userId);
        return  new ResponseEntity<>(savedPost, HttpStatus.ACCEPTED);
    }

    @PutMapping("/posts/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likedPostHandler(@PathVariable("postId") Integer postId,
                                                 @PathVariable("userId")Integer userId)
            throws Exception {
        Post likePost = postService.likePost(postId, userId);
        return  new ResponseEntity<>(likePost, HttpStatus.ACCEPTED);
    }
}
