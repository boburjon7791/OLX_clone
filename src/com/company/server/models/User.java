package com.company.server.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private final UUID id = UUID.randomUUID();
    private String firstName;
    private String phoneNumber;
    private String password;
    private final List<UUID> likedAds = new ArrayList<>();

    public User() {}

    public User(String firstName, String phoneNumber, String password) {
        this.firstName = firstName;
        this.phoneNumber = "+" + phoneNumber;
        this.password = password;
    }
    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = "+" + phoneNumber;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        if (!getId().equals(user.getId())) return false;
        return getPhoneNumber().equals(user.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getPhoneNumber().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
