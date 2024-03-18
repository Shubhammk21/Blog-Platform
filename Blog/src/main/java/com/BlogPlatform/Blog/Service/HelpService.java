package com.BlogPlatform.Blog.Service;
import com.BlogPlatform.Blog.Exception.HelpMessageException;
import com.BlogPlatform.Blog.Exception.UserException;
import com.BlogPlatform.Blog.Model.HelpMessage;


import java.util.List;

public interface HelpService {
    public HelpMessage saveMessage(HelpMessage hm)throws HelpMessageException;
    public String deleteMessage(String adminKey, Integer mId)throws UserException;
    public List<HelpMessage> userMessageList(String adminKey)throws UserException;
    public List<HelpMessage> nonUserMessageList(String adminKey)throws UserException;
    public List<HelpMessage> allMessageList(String adminKey);
}
