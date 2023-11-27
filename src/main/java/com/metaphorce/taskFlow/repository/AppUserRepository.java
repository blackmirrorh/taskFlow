package com.metaphorce.taskFlow.repository;

import com.metaphorce.taskFlow.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
}
