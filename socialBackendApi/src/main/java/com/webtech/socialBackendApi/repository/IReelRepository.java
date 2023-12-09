package com.webtech.socialBackendApi.repository;

import com.webtech.socialBackendApi.models.Reel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReelRepository extends JpaRepository<Reel, Integer> {
    List<Reel> findByUserId(Integer userId);
}
