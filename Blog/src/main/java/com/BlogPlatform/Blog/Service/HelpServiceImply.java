package com.BlogPlatform.Blog.Service;

import com.BlogPlatform.Blog.Exception.HelpMessageException;
import com.BlogPlatform.Blog.Exception.UserException;
import com.BlogPlatform.Blog.Model.HelpMessage;
import com.BlogPlatform.Blog.Model.UserSession;
import com.BlogPlatform.Blog.Repository.HelpMessageRepo;
import com.BlogPlatform.Blog.Repository.UserSessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HelpServiceImply implements HelpService{

    @Autowired
    private HelpMessageRepo hmr;
    @Autowired
    private UserSessionRepo usr;
    @Override
    public HelpMessage saveMessage(HelpMessage hm) throws HelpMessageException {
        hm.setCreatedAt(LocalDateTime.now());
        return hmr.save(hm);
    }

    @Override
    public String deleteMessage(String adminKey, Integer mId) throws UserException {

        Optional<UserSession> us= usr.findByUuId(adminKey);

        if(us.isPresent() && us.get().isAdministrator){
            hmr.deleteById(mId);
            return "Successful";
        }else {
            throw new UserException("(︺︹︶) User Not authorized +\" (︺︹︶)");
        }
    }

    @Override
    public List<HelpMessage> userMessageList(String adminKey) throws UserException {

        Optional<UserSession> us= usr.findByUuId(adminKey);

        if(us.isPresent() && us.get().isAdministrator){
            return hmr.userMessage();
        }else {
            throw new UserException("(︺︹︶) User Not authorized +\" (︺︹︶)");
        }
    }

    @Override
    public List<HelpMessage> nonUserMessageList(String adminKey) throws UserException {

        Optional<UserSession> us= usr.findByUuId(adminKey);

        if(us.isPresent() && us.get().isAdministrator){
            return hmr.nonUserMessage();
        }else {
            throw new UserException("(︺︹︶) User Not authorized +\" (︺︹︶)");
        }
    }

    @Override
    public List<HelpMessage> allMessageList(String adminKey) {
        return hmr.findAll(Sort.by(Sort.Direction.ASC, "createdAt"));
    }
}
