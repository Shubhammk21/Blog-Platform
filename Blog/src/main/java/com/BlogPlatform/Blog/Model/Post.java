package com.BlogPlatform.Blog.Model;


import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.links.Link;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // (unique identifier)
    private Integer postId;

    @Size(min = 2 ,message = "title character should be greater than 2")
    private String title;

    @Lob
    @Size(min = 10 ,message = "description character should be greater than 10")
    private String description;

    @Lob
    private String img;

    @CreationTimestamp
    //	(timestamp, automatically set when the post is created)
    private LocalDateTime created_at;

    @UpdateTimestamp
    //	(timestamp, automatically updated when the post is updated)
    private LocalDateTime updated_at;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments= new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "userId")
    // (foreign key referencing the User model)
    private User user;

    //	(integer, non-negative)
    private int likes;

}