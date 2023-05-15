package com.company.server.services;

import com.company.server.db.database;
import com.company.server.models.Ad;
import com.company.server.models.Comment;
import com.company.server.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {
    public List<Ad> showAds(User user) {
        if(user == null){
            return null;
        }
        List<Ad> otherAds = new ArrayList<>();
        database.getAds().forEach(ad -> {
            if(!ad.getUser_id().equals(user.getId())){
                otherAds.add(ad);
            }
        });
        return otherAds;
    }

    public User showUser(UUID userId) {
        if (userId == null) {
            return null;
        }
        for (User user : database.getUsers()) {
            if (user.getId().equals(userId)){
                return user;
            }
        }
        return null;
    }

    public List<Comment> showAdComments(Ad ad) {
        if (ad == null) {
            return null;
        }
        List<Comment> comments = new ArrayList<>();
        database.getComments().forEach(comment -> {
            if (comment.getAd_id().equals(ad.getId())){
                comments.add(comment);
            }
        });
        return comments;
    }


}
