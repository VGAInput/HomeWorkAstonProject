package com.paskhinproject.HomeWorkAstonProject.dto;

import lombok.Data;

@Data
public class NewUserPasswordDto {
    String username;
    String oldPassword;
    String newPassword;
}
