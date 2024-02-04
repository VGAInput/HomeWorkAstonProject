package com.paskhinproject.HomeWorkAstonProject.services;

import com.paskhinproject.HomeWorkAstonProject.dto.NewUserPasswordDto;
import com.paskhinproject.HomeWorkAstonProject.dto.RegisterLoginUserDto;
import com.paskhinproject.HomeWorkAstonProject.models.AppUser;
import com.paskhinproject.HomeWorkAstonProject.repositories.AppUserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUsersService {

    private final AppUserRepository appUserRepository;


    @PostConstruct
    public void init() {
        AppUser admin = new AppUser(1, "admin", "admin");
        appUserRepository.save(admin);
    }

    @Override
    public void deleteUser(int id) {
        appUserRepository.findById(id).ifPresent(appUserRepository::delete);
    }

    public AppUser updateUser(AppUser user) throws Exception {
        if (appUserRepository.existsById(user.getId())) {
            return appUserRepository.save(user);
        } else {
            throw new Exception("Can't update - user not found.");
        }
    }

    @Override
    public void registerNewUser(RegisterLoginUserDto newUser) throws LoginException {
        if (checkIfUsernameExists(newUser.getUsername())) throw new LoginException();
        else {
            AppUser newAppUser = new AppUser(newUser.getUsername(), newUser.getPassword());
            appUserRepository.save(newAppUser);
        }
    }

    @Override
    public boolean changePassword(NewUserPasswordDto newUserPasswordDto) throws IllegalStateException {
        if (checkIfUsernameExists(newUserPasswordDto.getUsername())) {
            AppUser user = appUserRepository.findByUsername(newUserPasswordDto.getUsername());
            if (!user.getPassword().equals(newUserPasswordDto.getOldPassword())) {
                throw new IllegalStateException();
            }
            user.setPassword(newUserPasswordDto.getNewPassword());
            appUserRepository.save(user);
            return true;
        } else return false;
    }


    @Override
    public List<AppUser> getListOfAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public boolean checkIfUsernameExists(String username) {
        return appUserRepository.findByUsername(username) != null;
    }

    @Override
    public boolean login(String username, String password) throws FailedLoginException {

        if (appUserRepository.findByUsernameAndPassword(username, password) != null) return true;
        else {
            throw new FailedLoginException();
        }
    }


}
