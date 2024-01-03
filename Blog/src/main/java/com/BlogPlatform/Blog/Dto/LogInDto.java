package com.BlogPlatform.Blog.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogInDto {

    @NotNull(message = "userid cannot be null")
    private String userName;

    @NotNull(message = "password cannot be null")
    private String password;

}
