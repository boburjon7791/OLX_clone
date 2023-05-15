package com.company.server.services;

import com.company.server.db.database;
import com.company.server.models.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AuthService {
    public User login(String phoneNumber, String password){
        AtomicReference<User> User = new AtomicReference<>(new User());
        List<com.company.server.models.User> userList = database.getUsers();
        for (com.company.server.models.User user : userList) {
            if(user.getPhoneNumber().equals("+"+phoneNumber)
            && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
