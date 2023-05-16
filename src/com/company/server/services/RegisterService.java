package com.company.server.services;

import com.company.server.db.database;
import com.company.server.models.User;

import java.util.concurrent.atomic.AtomicBoolean;

public class RegisterService {
    public Boolean registerService(User user) {
        if (user == null) {
            return null;
        }
        if (user.getFirstName() == null ||
                user.getFirstName().isBlank()) {
            return false;
        }
        if (user.getPhoneNumber() == null ||
                user.getPhoneNumber().isBlank()) {
            return false;
        }
        if (user.getPassword() == null ||
                user.getPassword().isBlank()) {
            return false;
        }
        AtomicBoolean exist = new AtomicBoolean(false);
        database.getUsers().forEach(user1 -> {
            if (!user1.equals(user) ||
            user1.hashCode() == user.hashCode()) {
                exist.set(true);
            }
        });
        if (exist.get()) {
            return false;
        }
        database.getUsers().add(user);
        return true;
    }
}
