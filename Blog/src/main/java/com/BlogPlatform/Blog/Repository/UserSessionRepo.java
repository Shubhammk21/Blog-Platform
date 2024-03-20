package com.BlogPlatform.Blog.Repository;

import com.BlogPlatform.Blog.Model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSessionRepo extends JpaRepository<UserSession, Integer> {

    public Optional<UserSession> findByUuId(String key);
    public Optional<UserSession> findByUserId(String key);


}
