package com.webtech.socialBackendApi.repository;

import com.webtech.socialBackendApi.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment, Integer> {
}
