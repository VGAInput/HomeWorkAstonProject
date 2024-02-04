package com.paskhinproject.HomeWorkAstonProject.repositories;

import com.paskhinproject.HomeWorkAstonProject.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findByUsername(String username);

    AppUser findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);

    boolean existsByPassword(String password);
}
