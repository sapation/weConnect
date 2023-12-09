package com.webtech.socialBackendApi.services.impl;

import com.webtech.socialBackendApi.models.Reel;
import com.webtech.socialBackendApi.models.User;
import com.webtech.socialBackendApi.repository.IReelRepository;
import com.webtech.socialBackendApi.services.IReelService;
import com.webtech.socialBackendApi.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReelServiceImpl implements IReelService {
    private final IReelRepository reelRepository;

    public ReelServiceImpl(IReelRepository reelRepository, IUserService userService) {
        this.reelRepository = reelRepository;
    }

    @Override
    public Reel createReel(Reel reel, User user) {
        Reel createdReel = new Reel();
        createdReel.setUser(user);
        createdReel.setTitle(reel.getTitle());
        createdReel.setVideo(reel.getVideo());
        return reelRepository.save(createdReel);
    }

    @Override
    public List<Reel> findAllReels() {
        return reelRepository.findAll();
    }

    @Override
    public List<Reel> findUsersReels(Integer userId) {
        return reelRepository.findByUserId(userId);
    }

    @Override
    public String deleteReel(Integer reelId, Integer userId) throws Exception {
        if (!reelRepository.existsById(reelId)) {
            throw new Exception("Reels with the id does not exist");
        }

        Reel reel = reelRepository.findById(reelId).get();
        if(reel.getUser().getId() != userId) {
            throw new Exception("you can only delete the reels you created");
        }
        reelRepository.deleteById(reelId);
        return "reel deleted successfully";
    }
}
