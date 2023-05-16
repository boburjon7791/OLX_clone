package com.company.server.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Ad {
    private final UUID id = UUID.randomUUID();
    private String text;
    private UUID category_id;
    private UUID user_id;
    private Integer cout_of_like = 0;
    private final Set<UUID> likedUsers = new HashSet<>();

    public Ad(String text, UUID category_id, UUID user_id) {
        this.text = text;
        this.category_id = category_id;
        this.user_id = user_id;
    }

    public Set<UUID> getLikedUsers() {
        return likedUsers;
    }
    public boolean setLikedUsers(UUID user_id){
        return likedUsers.add(user_id);
    }
    public void removeLikedUsers(UUID user_id){
        likedUsers.remove(user_id);
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getCategory_id() {
        return category_id;
    }

    public void setCategory_id(UUID category_id) {
        this.category_id = category_id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public Integer getCout_of_like() {
        return cout_of_like;
    }

    public void setCout_of_like() {
        this.cout_of_like ++;
    }

    public void removeCout_of_like() {
        this.cout_of_like--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ad elon)) return false;

        if (!getId().equals(elon.getId())) return false;
        return getUser_id().equals(elon.getUser_id());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getUser_id().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", category_id=" + category_id +
                ", user_id=" + user_id +
                ", cout_of_like=" + cout_of_like +
                '}';
    }
}
