package com.BlogPlatform.Blog.Model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.BlogPlatform.Blog.Dto.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    @Size(min = 2 ,message = "name character should be greater than 2")
    private String fistName;

    @Size(min = 2 ,message = "name character should be greater than 2")
    private String lastName;
    @Email(message = "Email is mandatory")
    private String email;
    @Size(min = 10,max = 10 ,message = "mobile number should be 10 digit")
    private String mobile;

    //	(timestamp, automatically set when the user is created)
    private LocalDateTime created_at;

    @Size(min = 1, max = 200, message = "string, 0-200 characters")
    private String bioString;

    private String password;

    private String Dob;

    private Gender gender;

    public boolean isAdministrator;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    // (foreign key referencing the User model)
    private List<Post> posts= new ArrayList<>();

}
