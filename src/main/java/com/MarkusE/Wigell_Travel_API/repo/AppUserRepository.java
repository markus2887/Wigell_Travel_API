package com.MarkusE.Wigell_Travel_API.repo;

import com.MarkusE.Wigell_Travel_API.security.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
