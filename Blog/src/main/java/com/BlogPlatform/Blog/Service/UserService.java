package com.BlogPlatform.Blog.Service;



import com.BlogPlatform.Blog.Exception.LogInException;
import com.BlogPlatform.Blog.Exception.UserException;
import com.BlogPlatform.Blog.Model.User;
import com.BlogPlatform.Blog.Model.UserSession;

import java.util.List;


public interface UserService {
    public UserSession createUser(User user) throws UserException;
    public User getUser(int id) throws UserException;
    public User updateUser(int id,User updatedUser) throws UserException;
    public User deleteUser(int id) throws UserException;
    public long getTotalUsers(String adminKey) throws LogInException;
    public List<User> getTop5ActiveUsers();
}


