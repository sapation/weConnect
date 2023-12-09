package com.webtech.socialBackendApi.repository;

import com.webtech.socialBackendApi.models.Chat;
import com.webtech.socialBackendApi.models.Reel;
import com.webtech.socialBackendApi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IChatRepository extends JpaRepository<Chat, Integer> {
    List<Chat> findByUsersId(Integer userId);
    @Query("select c from Chat c where :user Member of c.users And :reqUser Member of c.users")
    Chat findChatByUsersId(@Param("user") User user,@Param("reqUser") User reqUser);
}
