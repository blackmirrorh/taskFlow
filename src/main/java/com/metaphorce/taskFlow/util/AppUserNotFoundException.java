package com.metaphorce.taskFlow.util;

public class AppUserNotFoundException extends RuntimeException{

    public AppUserNotFoundException(String id){
        super(String.format("The user with the id " + id + " was not found"));
    }
}
