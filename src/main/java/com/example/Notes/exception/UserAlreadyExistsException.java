package com.example.Notes.exception;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(){
        super("User Already Exists");
    }
}
