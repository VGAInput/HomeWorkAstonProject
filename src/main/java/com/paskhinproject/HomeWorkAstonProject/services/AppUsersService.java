package com.paskhinproject.HomeWorkAstonProject.services;

import com.paskhinproject.HomeWorkAstonProject.dto.NewUserPasswordDto;
import com.paskhinproject.HomeWorkAstonProject.dto.RegisterLoginUserDto;
import com.paskhinproject.HomeWorkAstonProject.models.AppUser;

import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import java.util.List;

public interface AppUsersService {

    void deleteUser(int id);

    void registerNewUser(RegisterLoginUserDto newUser) throws LoginException;

    boolean changePassword(NewUserPasswordDto newUserPasswordDto) throws IllegalStateException;

    List<AppUser> getListOfAllUsers();

    boolean checkIfUsernameExists(String username);

    boolean login(String username, String password) throws FailedLoginException;
}
