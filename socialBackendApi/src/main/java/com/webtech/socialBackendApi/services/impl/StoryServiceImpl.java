package com.webtech.socialBackendApi.services.impl;

import com.webtech.socialBackendApi.models.Story;
import com.webtech.socialBackendApi.models.User;
import com.webtech.socialBackendApi.repository.IStoryRepository;
import com.webtech.socialBackendApi.services.IStoryService;
import com.webtech.socialBackendApi.services.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImpl implements IStoryService {
    private final IStoryRepository storyRepository;

    public StoryServiceImpl(IStoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public Story createStory(Story story, User user) {
        Story createdStory = new Story();
        createdStory.setCaption(story.getCaption());
        createdStory.setImage(story.getImage());
        createdStory.setUser(user);
        createdStory.setCreatedAt(LocalDateTime.now());

        return storyRepository.save(createdStory);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) {
        return storyRepository.findByUserId(userId);
    }
}
