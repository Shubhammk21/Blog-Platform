package com.BlogPlatform.Blog.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSession {
    @Id
    private String userId;
    private String password;
    @Column(unique = true)
    private String uuId;
    private LocalDateTime localDateTime;
    public boolean isAdministrator;
}
