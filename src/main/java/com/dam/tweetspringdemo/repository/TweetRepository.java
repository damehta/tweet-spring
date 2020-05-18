package com.dam.tweetspringdemo.repository;

import com.dam.tweetspringdemo.bean.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface TweetRepository extends MongoRepository<Tweet, UUID> {
    public ArrayList<Tweet> findByAuthorName(String author);

    ArrayList<Tweet> findAll();
    ArrayList<Tweet> findFirst100ByAuthorNameInOrderByPublishDateDesc(ArrayList<String> followingList);
}
