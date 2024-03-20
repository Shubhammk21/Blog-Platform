package com.BlogPlatform.Blog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer commentId;
    @Size(min = 2 ,message = "title character should be greater than 2")
    private String title;
    @Size(min = 10 ,message = "description character should be greater than 10")
    private String description;

    private LocalDate createdDate;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;
}
