package com.dam.tweetspringdemo.bean;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Tweet {
    @Id
    public UUID id;
    @NotNull
    public String authorName;
    public String publishDate;
    @NotNull
    public String msg;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Tweet(UUID id, @NotNull String authorName, String publishDate, @NotNull String msg) {
        this.id = id;
        this.authorName = authorName;
        this.publishDate = publishDate;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
