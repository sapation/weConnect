package com.webtech.socialBackendApi.controllers;

import com.webtech.socialBackendApi.models.Reel;
import com.webtech.socialBackendApi.models.User;
import com.webtech.socialBackendApi.services.IReelService;
import com.webtech.socialBackendApi.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReelController {
    private final IReelService reelService;
    private final IUserService userService;

    public ReelController(IReelService reelService, IUserService userService) {
        this.reelService = reelService;
        this.userService = userService;
    }

    @PostMapping("/reels")
    public ResponseEntity<Reel> createReel(@RequestBody Reel reel,
                                           @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        Reel createdReel = reelService.createReel(reel, user);
        return new ResponseEntity<>(createdReel, HttpStatus.CREATED);
    }

    @GetMapping("/reels")
    public ResponseEntity<List<Reel>> findAllReel() {

        List<Reel> reels = reelService.findAllReels();
        return new ResponseEntity<>(reels, HttpStatus.OK);
    }

    @GetMapping("/reels/user/{userId}")
    public ResponseEntity<List<Reel>> findUsersReel(@PathVariable("userId") Integer userId) {

        List<Reel> reels = reelService.findUsersReels(userId);
        return new ResponseEntity<>(reels, HttpStatus.OK);
    }

    @DeleteMapping("/reels/{reelId}")
    public ResponseEntity<String> deleteReel(@PathVariable("reelId") Integer reelId,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        String message = reelService.deleteReel(reelId, user.getId());
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
