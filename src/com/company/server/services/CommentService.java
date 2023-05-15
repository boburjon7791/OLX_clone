package com.company.server.services;

import com.company.server.db.database;
import com.company.server.models.Ad;
import com.company.server.models.Comment;
import com.company.server.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommentService {
    public void setComment(User user, Ad ad, String comment, UUID comment1) {
        if(user == null || comment == null ||
                comment.isBlank()){
            return;
        }
        database.getComments().forEach(comment2 -> {
            if(comment2.getId().equals(comment1)){
                comment2.setResponses(new Comment(comment,ad.getId()));
            }
        });
    }

    public void likeComment(User user, Comment comment) {
        if (user == null || comment == null) {
            return;
        }
        comment.setCout_of_comment_like(1);
    }

    public List<Comment> myAdComments(UUID user_id, UUID ad_id) {
        if (user_id == null || ad_id == null) {
            return null;
        }
        AtomicBoolean U = new AtomicBoolean(false);
        database.getUsers().forEach(user -> {
            if (user.getId().equals(user_id)) {
                U.set(true);
            }
        });
        if (!U.get()) {
            return null;
        }

        List<Comment> comments = new ArrayList<>();
        database.getComments().forEach(comment -> {
            if (comment.getAd_id().equals(ad_id)) {
                comments.add(comment);
            }
        });
        return comments;
    }

    public Boolean setComment(User user, Ad ad, String comment) {
        if (user == null || ad == null) {
            return null;
        }
        if (comment.isBlank()) {
            return false;
        }
        AtomicBoolean us= new AtomicBoolean(false);
        AtomicBoolean a= new AtomicBoolean(false);
        database.getUsers().forEach(user1 -> {
            if (user1.getId().equals(user.getId())){
                us.set(true);
            }
        });
        database.getAds().forEach(ad1 -> {
            if(ad1.getId().equals(ad.getId())){
                a.set(true);
            }
        });
        if(!(us.get() && a.get())){
            return false;
        }
        Comment addComment = new Comment(comment, UUID.randomUUID());
        database.setComments(addComment);
        addComment.setAd_id(ad.getId());
        return true;
    }
}
