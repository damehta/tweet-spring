package com.dam.tweetspringdemo.services;

import com.dam.tweetspringdemo.bean.Follower;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @@author darsh_mehta@yahoo.com
 * Interface for Following services: update follower and get list of following users
 */
@Service
public interface FollowerService {
    public Follower updateFollower(Follower follower);
    public ArrayList<String> getListOfFollowing(String userId);
    public Follower getUser(String userId);
}
