package com.dam.tweetspringdemo.repository;

import com.dam.tweetspringdemo.bean.Follower;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FollowerRepository  extends MongoRepository<Follower, UUID> {
    public Follower findByUserId(String userId);
}
