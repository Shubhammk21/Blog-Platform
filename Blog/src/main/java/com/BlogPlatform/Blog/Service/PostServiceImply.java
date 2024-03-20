package com.BlogPlatform.Blog.Service;


import com.BlogPlatform.Blog.Exception.PostException;
import com.BlogPlatform.Blog.Model.Post;
import com.BlogPlatform.Blog.Model.User;
import com.BlogPlatform.Blog.Repository.PostRepo;
import com.BlogPlatform.Blog.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImply implements PostService{
    @Autowired
    private PostRepo pr;

    @Autowired
    private UserRepo ur;

    @Override
    public Post createPost(Post post) throws PostException {
        User user = ur.findById(post.getUser().getUserId())
                .orElseThrow(() -> new PostException("(︺︹︶) No user their with "+ post.getUser().getUserId()+" (︺︹︶)"));
        post.setUser(user);
        post.setCreated_at(LocalDateTime.now());
        return pr.save(post);
    }

    @Override
    public Post getPost(int id) throws PostException {
        return pr.findById(id)
                .orElseThrow(() -> new PostException("(︺︹︶) No Post their with "+ id+" (︺︹︶)"));
    }
    @Override
    public List<Post> getAllPost() {
        return pr.findAllPost();
    }

    @Override
    public Post updatePost(int id, Post post) throws PostException {
        // check if post exists
        Post existingPost = pr.findById(id)
                .orElseThrow(() -> new PostException("(︺︹︶) No Post their with "+ id+" (︺︹︶)"));

        // update post fields

        existingPost.setDescription(post.getDescription());
        existingPost.setDescription(post.getDescription());

        // set updated_at timestamp
        existingPost.setUpdated_at(LocalDateTime.now());

        // save updated post to database
        return pr.save(existingPost);
    }

    @Override
    public Post deletePost(int id) throws PostException {
        Post post = pr.findById(id)
                .orElseThrow(() -> new PostException("(︺︹︶) No Post their with "+ id+" (︺︹︶)"));
        // delete post from database
        pr.delete(post);
        return post;
    }

    @Override
    public int incrementLike(int post_id) throws PostException {

        Post post = pr.findById(post_id)
                .orElseThrow(() -> new PostException("(︺︹︶) No Post their with "+ post_id+" (︺︹︶)"));
        int count= post.getLikes() + 1;
        post.setLikes(count);
        pr.save(post);

        return count;
    }

    @Override
    public int decrementLike(int post_id) throws PostException {

        Post post = pr.findById(post_id)
                .orElseThrow(() -> new PostException("(︺︹︶) No Post their with "+ post_id+" (︺︹︶)"));
        int count= Math.max(0, post.getLikes() - 1);
        post.setLikes(count);
        pr.save(post);

        return count;
    }

    @Override
    public long totalNumberOfPosts() {

        return pr.count();
    }

    @Override
    public List<Post> mostLikedPosts() {
        return pr.findTop5Likes();
    }
}
