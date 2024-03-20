package com.BlogPlatform.Blog.Service;


import com.BlogPlatform.Blog.Exception.LogInException;
import com.BlogPlatform.Blog.Exception.PostException;
import com.BlogPlatform.Blog.Model.Post;
import com.BlogPlatform.Blog.Model.User;
import com.BlogPlatform.Blog.Model.UserSession;
import com.BlogPlatform.Blog.Repository.PostRepo;
import com.BlogPlatform.Blog.Repository.UserRepo;
import com.BlogPlatform.Blog.Repository.UserSessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImply implements PostService{
    @Autowired
    private PostRepo pr;

    @Autowired
    private UserRepo ur;
    @Autowired
    private UserSessionRepo usp;

    @Override
    public Post createPost(Post post, String adminKey) throws PostException, LogInException {

        Optional<UserSession> us= usp.findByUuId(adminKey);

        if (us.isPresent() && us.get().isAdministrator){
            User user = ur.findById(post.getUser().getUserId())
                    .orElseThrow(() -> new PostException("(︺︹︶) No user their with "+ post.getUser().getUserId()+" (︺︹︶)"));
            post.setUser(user);
            post.setCreated_at(LocalDateTime.now());
            return pr.save(post);

        }else {
            throw new LogInException("(︺︹︶) Only Admin have right to Post\"");
        }

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
    public Post updatePost(int id, Post post, String adminKey) throws PostException, LogInException {

        Optional<UserSession> us= usp.findByUuId(adminKey);

        if (us.isPresent() && us.get().isAdministrator){

            Post existingPost = pr.findById(id)
                    .orElseThrow(() -> new PostException("(︺︹︶) No Post their with "+ id+" (︺︹︶)"));

            // update post fields

            existingPost.setDescription(post.getDescription());
            existingPost.setTitle(post.getTitle());
            existingPost.setImg(post.getImg());

            // set updated_at timestamp
            existingPost.setUpdated_at(LocalDateTime.now());

            // save updated post to database
            return pr.save(existingPost);

        }else {
            throw new LogInException("(︺︹︶) Only Admin have right to Post\"");
        }
        // check if post exists
    }

    @Override
    public Post deletePost(int id, String adminKey) throws PostException, LogInException {

        Optional<UserSession> us= usp.findByUuId(adminKey);

        if (us.isPresent() && us.get().isAdministrator){

            Post post = pr.findById(id)
                    .orElseThrow(() -> new PostException("(︺︹︶) No Post their with "+ id+" (︺︹︶)"));
            // delete post from database
            pr.delete(post);
            return post;

        }else {
            throw new LogInException("(︺︹︶) Only Admin have right to Post (︺︹︶)");
        }

    }

    @Override
    public int incrementLike(int post_id, String userKey) throws PostException, LogInException {

        Optional<UserSession> us= usp.findByUuId(userKey);

        if (us.isPresent()){

            Post post = pr.findById(post_id)
                    .orElseThrow(() -> new PostException("(︺︹︶) No Post their with "+ post_id+" (︺︹︶)"));
            int count= post.getLikes() + 1;
            post.setLikes(count);
            pr.save(post);

            return count;

        }else {
            throw new LogInException("(︺︹︶) Login to use this feature (︺︹︶)");
        }
    }

    @Override
    public int decrementLike(int post_id, String userKey) throws PostException, LogInException {

        Optional<UserSession> us= usp.findByUuId(userKey);

        if (us.isPresent()){

            Post post = pr.findById(post_id)
                    .orElseThrow(() -> new PostException("(︺︹︶) No Post their with "+ post_id+" (︺︹︶)"));
            int count= Math.max(0, post.getLikes() - 1);
            post.setLikes(count);
            pr.save(post);

            return count;
        }else {
            throw new LogInException("(︺︹︶) Login to use this feature (︺︹︶)");
        }
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
