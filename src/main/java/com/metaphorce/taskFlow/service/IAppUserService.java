package com.metaphorce.taskFlow.service;

import com.metaphorce.taskFlow.model.AppUser;
import com.metaphorce.taskFlow.repository.AppUserRepository;
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
        return appUserRepository.findById(userId).orElse(null);
    }

    @Override
    public void addNewUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public void deleteUser(String userId) {
        AppUser appUser = appUserRepository.findById(userId).orElse(null);
        if(null != appUser){
            appUserRepository.delete(appUser);
        }
        else {
            System.out.println("Not found");
        }
    }

    @Override
    public void updateUser(String userId, AppUser appUser) {
        AppUser oldAppUser = appUserRepository.findById(userId).orElse(null);
        if(null != oldAppUser){
            appUser.setUsername(oldAppUser.getUsername());
            appUserRepository.save(appUser);
        }
        else {
            System.out.println("Not found");
        }
    }
}
