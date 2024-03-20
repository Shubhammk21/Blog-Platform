package com.BlogPlatform.Blog.Service;


import com.BlogPlatform.Blog.Exception.LogInException;
import com.BlogPlatform.Blog.Exception.UserException;
import com.BlogPlatform.Blog.Model.User;
import com.BlogPlatform.Blog.Model.UserSession;
import com.BlogPlatform.Blog.Repository.UserRepo;
import com.BlogPlatform.Blog.Repository.UserSessionRepo;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImply implements UserService{

    @Autowired
    private UserRepo ur;
    @Autowired
    private UserSessionRepo usr;

    @Override
    @Transactional
    public UserSession createUser(User user) throws UserException {

        Optional<User> optionalUser= ur.findByEmail(user.getEmail());

        user.setAdministrator(false);
        user.setCreated_at(LocalDateTime.now());
        if (optionalUser.isEmpty()) {

            String key= RandomString.make(16);
            ur.save(user);
            return usr.save(new UserSession(user.getUserId(), user.getMobile(), user.getPassword(), key, LocalDateTime.now(), user.isAdministrator));

        }
        else throw new UserException("(︺︹︶) Already email used (︺︹︶)");
    }

    @Override
    public User getUser(int id) throws UserException {

        return ur.findById(id)
                .orElseThrow(() -> new UserException("(︺︹︶) No user their with "+ id+" (︺︹︶)"));
    }

    @Override
    public User updateUser(int id, User updatedUser) throws UserException {

        User user = ur.findById(id)
                .orElseThrow(() -> new UserException("(︺︹︶) No user their with "+ id+" (︺︹︶)"));

        // user.setName(updatedUser.getName());
        //user.setBioString(updatedUser.getBioString());

        return ur.save(updatedUser);
    }

    @Override
    public User deleteUser(int id) throws UserException {

        Optional<User> optionalUser= ur.findById(id);

        if (optionalUser.isEmpty()) throw new UserException("(︺︹︶) No user their with "+ id+" (︺︹︶)");
        else{
            ur.delete(optionalUser.get());
            return optionalUser.get();
        }

    }

    @Override
    public long getTotalUsers(String adminKey) throws LogInException {

        Optional<UserSession> us= usr.findByUuId(adminKey);

        if (us.isPresent() && us.get().isAdministrator){

            return ur.count();

        }else {
            throw new LogInException("(︺︹︶) Only admin use this feature (︺︹︶)");
        }
    }

    @Override
    public List<User> getTop5ActiveUsers() {
        return ur.top5ActiveUSer();
    }
}
