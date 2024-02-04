package com.paskhinproject.HomeWorkAstonProject;

import com.paskhinproject.HomeWorkAstonProject.dto.RegisterLoginUserDto;
import com.paskhinproject.HomeWorkAstonProject.models.AppUser;
import com.paskhinproject.HomeWorkAstonProject.repositories.AppUserRepository;
import com.paskhinproject.HomeWorkAstonProject.services.AppUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class HomeWorkAstonProjectApplicationTests {

    @Mock
    private AppUserRepository repository;

    @InjectMocks
    private AppUserServiceImpl userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAllUsers() {
        List<AppUser> list = new ArrayList<>();
        AppUser user1 = new AppUser("JohnDoe", "password");
        AppUser user2 = new AppUser("JohnDoe", "password");
        AppUser user3 = new AppUser("JohnDoe", "password");

        list.add(user1);
        list.add(user2);
        list.add(user3);

        when(repository.findAll()).thenReturn(list);
        List<AppUser> listTest = repository.findAll();
        assertEquals(3, listTest.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSaveUser() throws LoginException {
        AppUser user1 = new AppUser("JohnDoe", "password");

        repository.save(user1);
        verify(repository, times(1)).save(user1);
    }

    @Test
    void testUserCanBeDeleted() throws LoginException {
        AppUser user1 = new AppUser("JohnDoe", "password");

        repository.save(user1);
        verify(repository, times(1)).save(user1);

        repository.delete(user1);
        verify(repository,times(1)).delete(user1);
    }


}
