package com.webtech.socialBackendApi.services;

import com.webtech.socialBackendApi.models.Reel;
import com.webtech.socialBackendApi.models.User;

import java.util.List;

public interface IReelService {
    Reel createReel(Reel reel, User user);
    List<Reel> findAllReels();

    List<Reel> findUsersReels( Integer userId);
    String deleteReel(Integer reelId, Integer userId) throws Exception;

}
