package com.company.server.services;

import com.company.server.db.database;
import com.company.server.models.Ad;
import com.company.server.models.User;

import java.util.ArrayList;
import java.util.List;

public class AdService {
    public List<Ad> showMyAds(User user) {
        if(user == null){
            return null;
        }
        List<Ad> myAds = new ArrayList<>();
        database.getAds().forEach(ad -> {
            if(ad.getUser_id().equals(user.getId())){
                myAds.add(ad);
            }
        });
        return myAds;
    }
}
