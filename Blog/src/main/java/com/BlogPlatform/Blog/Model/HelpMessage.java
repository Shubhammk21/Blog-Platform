package com.BlogPlatform.Blog.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HelpMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int messageId;

    @Size(min = 2 ,message = "name character should be greater than 2")
    private String firstName;

    @Size(min = 2 ,message = "name character should be greater than 2")
    private String lastName;
    @Email(message = "Email is mandatory")
    private String email;
    @Size(min = 10,max = 10 ,message = "mobile number should be 10 digit")
    private String mobile;

    @Size(min = 1, max = 200, message = "string, 0-200 characters")
    private String message;

    private LocalDateTime createdAt;
    private int userId;
}
