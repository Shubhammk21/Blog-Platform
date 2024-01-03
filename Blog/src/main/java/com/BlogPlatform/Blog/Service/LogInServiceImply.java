package com.BlogPlatform.Blog.Service;

import com.BlogPlatform.Blog.Dto.LogInDto;
import com.BlogPlatform.Blog.Exception.LogInException;
import com.BlogPlatform.Blog.Exception.UserException;
import com.BlogPlatform.Blog.Model.User;
import com.BlogPlatform.Blog.Model.UserSession;
import com.BlogPlatform.Blog.Repository.UserRepo;
import com.BlogPlatform.Blog.Repository.UserSessionRepo;
import javax.transaction.Transactional;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LogInServiceImply implements LogInService{
    @Autowired
    private UserRepo uDao;

    @Autowired
    private UserSessionRepo rDao;

    @Override
    @Transactional
    public UserSession logInUser(LogInDto dto) throws LogInException, UserException {

        Optional<User> userWithEmail= uDao.findByEmail(dto.getUserName());
        Optional<User> userWithPhone= uDao.findByMobile(dto.getUserName());


        if(userWithPhone.isEmpty() && userWithEmail.isEmpty()){
            throw new LogInException("Username is invalid");
        }

        else if (userWithPhone.isPresent()){
            if (dto.getPassword().equals(userWithPhone.get().getPassword())){
                User user= userWithPhone.get();
                Optional<UserSession> checkAlreadyIn= rDao.findById(user.getUserId());

                if(checkAlreadyIn.isPresent()) {
                    throw new LogInException("Already Login");
                }else{
                    String key= RandomString.make(16);

                    return rDao.save(new UserSession(user.getMobile(), user.getPassword(), key, LocalDateTime.now()));
                }
            }else {
                throw new UserException("Incorrect Password");
            }
        }
        else {
            if (!dto.getPassword().equals(userWithEmail.get().getPassword())){
                throw new UserException("Incorrect Password");
            }else {
                User user= userWithEmail.get();
                Optional<UserSession> checkAlreadyIn= rDao.findById(user.getUserId());
                if(checkAlreadyIn.isPresent()) {
                    throw new LogInException("Already Login");
                }else{
                    String key= RandomString.make(16);

                    return rDao.save(new UserSession(user.getEmail(), user.getPassword(), key, LocalDateTime.now()));
                }
            }
        }

    }

    @Override
    @Transactional
    public String logOutUser(String key) throws LogInException {

        UserSession checkAlreadyIn= rDao.findByUuId(key).orElseThrow(()-> new LogInException("♣█☻ Key Error ☻█♣"));

        rDao.delete(checkAlreadyIn);
        return "Thank you ....";

    }
//    public UserSession checkActiveStatus(String cusID)throws LogInException {
//        return rDao.findById(cusID).orElseThrow(() -> new LogInException("No record present"));
//    }
}
