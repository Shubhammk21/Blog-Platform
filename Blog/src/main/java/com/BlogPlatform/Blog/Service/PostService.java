package com.BlogPlatform.Blog.Service;

import com.BlogPlatform.Blog.Exception.LogInException;
import com.BlogPlatform.Blog.Exception.PostException;
import com.BlogPlatform.Blog.Model.Post;

import java.util.List;

public interface PostService {
    public Post createPost(Post post, String adminKey) throws PostException, LogInException;
    public Post getPost(int id) throws PostException;
    public Post updatePost(int id,Post post, String adminKey) throws PostException, LogInException;
    public Post deletePost(int id, String adminKey) throws PostException, LogInException;
    public int incrementLike(int post_id, String userKey) throws PostException,LogInException;
    public int decrementLike(int post_id, String userKey) throws PostException, LogInException;
    public long totalNumberOfPosts();
    public List<Post> mostLikedPosts();

    public List<Post> getAllPost();
}
//POST /posts: Create a new post. The request should include the user_id.
//GET /posts/{id}: Retrieve a post by id.
//PUT /posts/{id}: Update a post's content by id.
//DELETE /posts/{id}: Delete a post by id.
//POST /posts/{id}/like: Increment the like count of a post by id.
//POST /posts/{id}/unlike: Decrement the like count of a post by id. The count
//should not go below 0.
//GET /analytics/posts: Retrieve the total number of posts.
//GET /analytics/posts/top-liked: Retrieve the top 5 most liked posts.
