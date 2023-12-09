package com.webtech.socialBackendApi.repository;

import com.webtech.socialBackendApi.models.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStoryRepository extends JpaRepository<Story, Integer> {
    List<Story> findByUserId(Integer userId);
}
