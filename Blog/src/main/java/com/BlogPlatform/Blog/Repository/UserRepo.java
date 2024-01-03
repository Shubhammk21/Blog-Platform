package com.BlogPlatform.Blog.Repository;

import com.BlogPlatform.Blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
     public Optional<User> findByEmail(String email);
     public Optional<User> findByMobile(String num);
//     @Query("""
//             SELECT u.name, COUNT(p.user_id) AS post_count FROM USERS u LEFT JOIN POSTS p ON u.id = p.user_id\s
//             GROUP BY u.name\s
//             ORDER BY post_count DESC
//             LIMIT 5""")
     @Query("Select u from User u")
     public List<User> top5ActiveUSer();
}
