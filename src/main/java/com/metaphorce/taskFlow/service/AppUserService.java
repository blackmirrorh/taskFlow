package com.metaphorce.taskFlow.service;

import com.metaphorce.taskFlow.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AppUserService {

    List<AppUser> getAllUsers();

    AppUser getUser(String userId);

    void addNewUser(AppUser appUser);

    void deleteUser(String userId);

    void updateUser(String userId, AppUser appUser);
}
