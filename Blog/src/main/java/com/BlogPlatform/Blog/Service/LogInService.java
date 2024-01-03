package com.BlogPlatform.Blog.Service;

import com.BlogPlatform.Blog.Dto.LogInDto;
import com.BlogPlatform.Blog.Exception.LogInException;
import com.BlogPlatform.Blog.Exception.UserException;
import com.BlogPlatform.Blog.Model.User;
import com.BlogPlatform.Blog.Model.UserSession;

public interface LogInService {

    public UserSession logInUser(LogInDto dto) throws LogInException, UserException;
    public String logOutUser(String key) throws LogInException;
    //public UserSession checkActiveStatus(String cusID)throws LogInException;

}
