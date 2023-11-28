package com.metaphorce.taskFlow.service;

import com.metaphorce.taskFlow.model.*;
import com.metaphorce.taskFlow.repository.AppUserRepository;
import com.metaphorce.taskFlow.repository.TaskRepository;
import com.metaphorce.taskFlow.util.AppUserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AppUserServiceTest {

    @Mock
    AppUserRepository appUserRepository;

    @InjectMocks
    AppUserService appUserService = new IAppUserService();

    List<AppUser> appUserList = new ArrayList<>();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        appUserList.add(new AppUser("aladmin123", "Luis Gerardo Ramirez Cabrera", "qwerty", "CobraKai", Role.Manager.toString()));
        appUserList.add(new AppUser("elLider99", "Juanito Perez", "abc123", "CobraKai", Role.Lead.toString()));
        appUserList.add(new AppUser("elDeveloper007", "Panchito Lopez", "esteesmip4ss", "CobraKai", Role.Developer.toString()));
    }

    @Test
    public void testGetAllUsers(){
        when(appUserRepository.findAll()).thenReturn(appUserList);
        List<AppUser> appUsersFromService = appUserService.getAllUsers();
        assertEquals(appUserList.get(0).getUsername(), appUsersFromService.get(0).getUsername());
        assertEquals(appUserList.get(1).getUsername(), appUsersFromService.get(1).getUsername());
        assertEquals(appUserList.get(2).getUsername(), appUsersFromService.get(2).getUsername());
    }

    @Test
    public void testGetUser(){
        String username = "billyMili";
        AppUser appUser = new AppUser(username, "Bill Gates", "microsoft", "Microsoft", Role.Manager.toString());
        when(appUserRepository.findById(username)).thenReturn(Optional.of(appUser));
        AppUser appUserFromService = appUserService.getUser(username);
        assertEquals(appUser.getUsername(), appUserFromService.getUsername());
        assertEquals(appUser.getName(), appUserFromService.getName());
        assertEquals(appUser.getPassword(), appUserFromService.getPassword());
        assertEquals(appUser.getTeam(), appUserFromService.getTeam());
        assertEquals(appUser.getRole(), appUserFromService.getRole());
    }

    @Test
    public void testGetUserNotFound(){
        String username = "billyMili";
        when(appUserRepository.findById(username)).thenThrow(new AppUserNotFoundException(username));
        assertThrows(AppUserNotFoundException.class, ()-> appUserService.getUser(username));
    }

    @Test
    public void testAddNewUser(){
        String username = "billyMili";
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setName("Bill Gates");
        appUser.setPassword("microsoft");
        appUser.setTeam("Microsoft");
        appUser.setRole(Role.Manager);
        when(appUserRepository.findById(username)).thenReturn(Optional.of(appUser));
        appUserService.addNewUser(appUser);
        assertEquals(appUser.getUsername(), appUserService.getUser(username).getUsername());
    }

    @Test
    public void testDeleteUser(){
        String username = "billyMili";
        AppUser appUser = new AppUser(username, "Bill Gates", "microsoft", "Microsoft", Role.Manager.toString());
        when(appUserRepository.findById(username)).thenReturn(Optional.of(appUser));
        appUserService.deleteUser(username);
        when(appUserRepository.findById(username)).thenReturn(Optional.empty());
        assertThrows(AppUserNotFoundException.class, ()-> appUserService.getUser(username));
    }

    @Test
    public void testUpdateUser(){
        String username = "billyMili";
        AppUser appUser = new AppUser(username, "Bill Gates", "microsoft", "Microsoft", Role.Manager.toString());
        when(appUserRepository.findById(username)).thenReturn(Optional.of(appUser));
        appUserService.updateUser(username, appUser);
        assertEquals(appUser.getUsername(), appUserService.getUser(username).getUsername());
    }

    @Test
    public void testUpdateUserNotFound(){
        String username = "billyMili";
        AppUser appUser = new AppUser(username, "Bill Gates", "microsoft", "Microsoft", Role.Manager.toString());
        when(appUserRepository.findById(username)).thenThrow(new AppUserNotFoundException(username));
        assertThrows(AppUserNotFoundException.class, ()-> appUserService.updateUser(username, appUser));
    }
}
