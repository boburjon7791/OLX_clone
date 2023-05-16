package com.company.server.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Comment {
    private final UUID id = UUID.randomUUID();
    private String comment_text;
    private UUID elon_id = UUID.randomUUID();
    private Integer cout_of_comment_like = 0;
    private final List<Comment> responses = new ArrayList<>();

    public Comment(String comment_text, UUID elon_id) {
        this.comment_text = comment_text;
        this.elon_id = elon_id;
    }

    public UUID getId() {
        return id;
    }

    public List<Comment> getResponses() {
        return responses;
    }
    public void setResponses(Comment comment){
        this.responses.add(comment);
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public UUID getAd_id() {
        return elon_id;
    }

    public void setAd_id(UUID elon_id) {
        this.elon_id = elon_id;
    }

    public Integer getCout_of_comment_like() {
        return cout_of_comment_like;
    }

    public void setCout_of_comment_like() {
        this.cout_of_comment_like ++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;

        return getId().equals(comment.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment_text='" + comment_text + '\'' +
                ", elon_id=" + elon_id +
                ", cout_of_comment_like=" + cout_of_comment_like +
                '}';
    }
}
