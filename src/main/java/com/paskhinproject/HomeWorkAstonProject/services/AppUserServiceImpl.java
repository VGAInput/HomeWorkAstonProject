package com.paskhinproject.HomeWorkAstonProject.services;

import com.paskhinproject.HomeWorkAstonProject.dto.NewUserPasswordDto;
import com.paskhinproject.HomeWorkAstonProject.dto.RegisterLoginUserDto;
import com.paskhinproject.HomeWorkAstonProject.models.AppUser;
import com.paskhinproject.HomeWorkAstonProject.repositories.AppUserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUsersService {

    private final AppUserRepository appUserRepository;


    @PostConstruct
    public void createAdmin() {
        AppUser admin = new AppUser(1, "admin", "admin");
        appUserRepository.save(admin);
    }

    @Override
    public void registerNewUser(RegisterLoginUserDto newUser) {
        AppUser newAppUser = new AppUser(newUser.getUsername(), newUser.getPassword());
        appUserRepository.save(newAppUser);
    }

    public boolean changePassword(NewUserPasswordDto newUserPasswordDto) {
        if (checkIfUsernameExists(newUserPasswordDto.getUsername())) {
            AppUser user = appUserRepository.findByUsername(newUserPasswordDto.getUsername());
            if (!user.getPassword().equals(newUserPasswordDto.getOldPassword())) return false;
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
    public boolean login(String username, String password) {
        return appUserRepository.findByUsernameAndPassword(username, password) != null;
    }


}
