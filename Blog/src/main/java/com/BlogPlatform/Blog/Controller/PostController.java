package com.BlogPlatform.Blog.Controller;

import com.BlogPlatform.Blog.Exception.LogInException;
import com.BlogPlatform.Blog.Exception.PostException;
import com.BlogPlatform.Blog.Model.Post;
import com.BlogPlatform.Blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService ps;

    //################################################## Admin #####################################################

    @PostMapping("/posts/{adminKey}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable String adminKey) throws PostException, LogInException {

        return new ResponseEntity<Post>(ps.createPost(post, adminKey), HttpStatus.CREATED);
    }

    @PutMapping("/posts/{id}/{adminKey}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Integer id, @RequestBody Post post, @PathVariable String adminKey) throws PostException, LogInException {

        return new ResponseEntity<Post>(ps.updatePost(id, post, adminKey), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}/{adminKey}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") Integer id, @PathVariable String adminKey) throws PostException, LogInException {

        return new ResponseEntity<Post>(ps.deletePost(id, adminKey), HttpStatus.OK);
    }

    //################################################## User #####################################################

    @PostMapping("/posts/{id}/like/{userKey}")
    public ResponseEntity<Integer> likePost(@PathVariable("id") Integer id, @PathVariable String userKey) throws PostException, LogInException {

        return new ResponseEntity<Integer>(ps.incrementLike(id, userKey), HttpStatus.OK);
    }

    @PostMapping("/posts/{id}/unlike/{userKey}")
    public ResponseEntity<Integer> unlikePost(@PathVariable("id") Integer id, @PathVariable String userKey) throws PostException, LogInException {

        return new ResponseEntity<>(ps.decrementLike(id, userKey), HttpStatus.OK);
    }

    //################################################## Free #####################################################

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Integer id) throws PostException {

        return new ResponseEntity<Post>(ps.getPost(id), HttpStatus.OK);
    }

    @GetMapping("/analytics/posts")
    public ResponseEntity<Long> getTotalPosts() {

        return new ResponseEntity<Long>(ps.totalNumberOfPosts(), HttpStatus.OK);
    }

    @GetMapping("/analytics/posts/top-liked")
    public ResponseEntity<List<Post>> getTopLikedPosts() {

        return new ResponseEntity<List<Post>>(ps.mostLikedPosts(), HttpStatus.OK);
    }

    @GetMapping("/posts/All")
    public ResponseEntity<List<Post>> getAllPost(){

        return new ResponseEntity<List<Post>>(ps.getAllPost(), HttpStatus.OK);
    }


}
