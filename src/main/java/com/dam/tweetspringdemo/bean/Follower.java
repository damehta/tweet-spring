package com.dam.tweetspringdemo.bean;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Follower {
    @Id
    public UUID id; // Unique id for mongo db

    @NotNull
    public String userId;
    public ArrayList<String> followers = new ArrayList<String>();

    public Follower() {}

    public Follower(UUID id, @NotNull String userId, ArrayList<String> followers) {
        this.id = id;
        this.userId = userId;
        this.followers = followers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<String> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<String> followers) {
        this.followers = followers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Follower follower = (Follower) o;
        return id.equals(follower.id) &&
                userId.equals(follower.userId) &&
                followers.equals(follower.followers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, followers);
    }

    @Override
    public String toString() {
        return "Follower{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", followers=" + followers +
                '}';
    }
}
