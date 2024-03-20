package com.BlogPlatform.Blog.Repository;

import com.BlogPlatform.Blog.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    @Query("Select p from Post p")
    public List<Post> findTop5Likes();

    @Query("Select p from Post p")
    public List<Post> findAllPost();

}
