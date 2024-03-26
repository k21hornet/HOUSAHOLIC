package com.project.housaholic.repository;

import com.project.housaholic.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthRepository extends JpaRepository<AppUser, String> {
    @Query("SELECT u FROM AppUser u WHERE u.email = :email")
    AppUser findByEmail(String email);
}
