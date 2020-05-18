package com.dam.tweetspringdemo.services;

import com.dam.tweetspringdemo.bean.Follower;
import com.dam.tweetspringdemo.repository.FollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class FollowerServiceImpl implements FollowerService {

    @Autowired
    FollowerRepository followerRepository;

    @Transactional
    public Follower updateFollower(Follower follower) {
        Follower existingFollwer = followerRepository.findByUserId(follower.getUserId());
        if(existingFollwer==null){
            follower.setId(UUID.randomUUID());
            return followerRepository.save(follower);
        }else{
            existingFollwer.setFollowers(follower.getFollowers());
            return followerRepository.save(existingFollwer);
        }
    }

    @Override
    public ArrayList<String> getListOfFollowing(String userId) {
        Follower existingFollower = followerRepository.findByUserId(userId);
        if(existingFollower != null){
            return existingFollower.getFollowers();
        }
        return null;
    }

    @Override
    public Follower getUser(String userId) {
        Follower existingFollower = followerRepository.findByUserId(userId);
        if(existingFollower != null){
            return existingFollower;
        }
        return null;
    }
}
