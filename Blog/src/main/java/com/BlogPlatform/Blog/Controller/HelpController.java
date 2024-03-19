package com.BlogPlatform.Blog.Controller;

import com.BlogPlatform.Blog.Exception.HelpMessageException;
import com.BlogPlatform.Blog.Exception.UserException;
import com.BlogPlatform.Blog.Model.HelpMessage;
import com.BlogPlatform.Blog.Service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelpController {

    @Autowired
    private HelpService hs;

    @PostMapping("/Feedback/hm")
    public ResponseEntity<HelpMessage> createMessagePost(@RequestBody HelpMessage hm) throws HelpMessageException {

        return new ResponseEntity<HelpMessage>(hs.saveMessage(hm), HttpStatus.CREATED);
    }
    @DeleteMapping("/Feedback/{adminKey}/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String adminKey, @PathVariable("id") Integer id) throws UserException {

        return new ResponseEntity<String>(hs.deleteMessage(adminKey, id), HttpStatus.OK);
    }
    @GetMapping("/Feedback/User/{adminKey}")
    public ResponseEntity<List<HelpMessage>> userMessageByOder(@PathVariable String adminKey) throws UserException {

        return new ResponseEntity<List<HelpMessage>>(hs.userMessageList(adminKey), HttpStatus.OK);
    }
    @GetMapping("/Feedback/NonUser/{adminKey}")
    public ResponseEntity<List<HelpMessage>> nonUserMessageByOder(@PathVariable String adminKey) throws UserException {

        return new ResponseEntity<List<HelpMessage>>(hs.nonUserMessageList(adminKey), HttpStatus.OK);
    }

    @GetMapping("/Feedback/All/{adminKey}")
    public ResponseEntity<List<HelpMessage>> AllMessageByOder(@PathVariable String adminKey) throws UserException {

        return new ResponseEntity<List<HelpMessage>>(hs.allMessageList(adminKey), HttpStatus.OK);
    }

}
