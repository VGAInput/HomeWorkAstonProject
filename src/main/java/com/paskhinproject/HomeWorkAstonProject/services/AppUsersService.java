package com.paskhinproject.HomeWorkAstonProject.services;

import com.paskhinproject.HomeWorkAstonProject.dto.RegisterLoginUserDto;
import com.paskhinproject.HomeWorkAstonProject.models.AppUser;

import java.util.List;

public interface AppUsersService {

    void registerNewUser(RegisterLoginUserDto newUser);

    List<AppUser> getListOfAllUsers();

    boolean checkIfUsernameExists(String username);

    boolean login(String username, String password);
}
