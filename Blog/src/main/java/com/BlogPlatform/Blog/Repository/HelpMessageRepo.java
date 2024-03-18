package com.BlogPlatform.Blog.Repository;

import com.BlogPlatform.Blog.Dto.Order;
import com.BlogPlatform.Blog.Model.HelpMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface HelpMessageRepo extends JpaRepository<HelpMessage, Integer> {

    @Query("Select hm from HelpMessage hm where userId>0 ORDER BY createdAt")
    public List<HelpMessage> userMessage();

    @Query("SELECT hm FROM HelpMessage hm WHERE userId < 1 ORDER BY createdAt")
    public List<HelpMessage> nonUserMessage();


}
