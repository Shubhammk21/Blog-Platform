package com.BlogPlatform.Blog.Service;


import com.BlogPlatform.Blog.Exception.UserException;
import com.BlogPlatform.Blog.Model.User;
import com.BlogPlatform.Blog.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImply implements UserService{

    @Autowired
    private UserRepo ur;

    @Override
    public User createUser(User user) throws UserException {

        Optional<User> optionalUser= ur.findByEmail(user.getEmail());

        if (optionalUser.isEmpty()) return ur.save(user);
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

        user.setName(updatedUser.getName());
        user.setBioString(updatedUser.getBioString());

        return ur.save(user);
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
    public long getTotalUsers() {
        return ur.count();
    }

    @Override
    public List<User> getTop5ActiveUsers() {
        return ur.top5ActiveUSer();
    }
}
