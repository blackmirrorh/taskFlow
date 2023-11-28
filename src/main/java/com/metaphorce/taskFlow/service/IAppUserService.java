package com.metaphorce.taskFlow.service;

import com.metaphorce.taskFlow.model.AppUser;
import com.metaphorce.taskFlow.repository.AppUserRepository;
import com.metaphorce.taskFlow.util.AppUserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IAppUserService implements AppUserService{

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser getUser(String userId) {
        return appUserRepository.findById(userId).orElseThrow(() -> new AppUserNotFoundException(userId));
    }

    @Override
    public void addNewUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public void deleteUser(String userId) {
        AppUser appUser = getUser(userId);
        appUserRepository.delete(appUser);
    }

    @Override
    public void updateUser(String userId, AppUser appUser) {
        AppUser oldUser = getUser(userId);
        appUser.setUsername(oldUser.getUsername());
        appUserRepository.save(appUser);
    }
}
