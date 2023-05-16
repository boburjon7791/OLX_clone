package com.company.server.services;

import com.company.server.db.database;
import com.company.server.models.Ad;
import com.company.server.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public Boolean addAd(User user, Ad ad) {
        if (user == null ||
                ad == null) {
            return null;
        }
        if (ad.getText() == null ||
                ad.getCategory_id() == null) {
            return null;
        }
        if (ad.getText().isBlank()) {
            return false;
        }
        boolean exist = false;
        for (User user1 : database.getUsers()) {
            if(user.getId().equals(user1.getId())){
                exist = true;
                break;
            }
        }
        if (!exist) {
            return false;
        }
        ad.setUser_id(user.getId());
        database.setAds(ad);
        return true;
    }

    /**
     * This method like or unlike for Ads
     * if return null user not found
     * else if return true this user liked to this ad
     * else if return false this user unliked to this ad
    * */
    public Boolean likeUnlikeAd(User user,Ad ad){
        if (user == null ||
                ad == null) {
            return null;
        }
        boolean likedUsers = ad.setLikedUsers(user.getId());
        if (likedUsers) {
            ad.setCout_of_like();
        }else {
            ad.removeCout_of_like();
        }
        return likedUsers;
    }
}
