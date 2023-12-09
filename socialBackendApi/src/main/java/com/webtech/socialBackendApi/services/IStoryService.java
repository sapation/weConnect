package com.webtech.socialBackendApi.services;

import com.webtech.socialBackendApi.models.Story;
import com.webtech.socialBackendApi.models.User;

import java.util.List;

public interface IStoryService {
    Story createStory(Story story, User user);
    List<Story> findStoryByUserId(Integer userId);
}
