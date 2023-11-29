package com.metaphorce.taskFlow.controller;

import com.metaphorce.taskFlow.model.AppUser;
import com.metaphorce.taskFlow.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/all-users")
    public List<AppUser> getAllUsers(){
        return appUserService.getAllUsers();
    }

    @GetMapping("{userId}")
    public AppUser getUser(@PathVariable String userId){
        return appUserService.getUser(userId);
    }

    @PostMapping("/new-user")
    public void addNewUser(@RequestBody @Valid AppUser appUser){
        appUserService.addNewUser(appUser);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable String userId){
        appUserService.deleteUser(userId);
    }

    @PutMapping("{userId}")
    public void updateUser(@PathVariable String userId, @RequestBody @Valid AppUser appUser){
        appUserService.updateUser(userId, appUser);
    }
}
