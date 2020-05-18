package com.dam.tweetspringdemo.services;

import com.dam.tweetspringdemo.bean.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @@author darsh_mehta@yahoo.com
 * TweetService
 * Interface for Tweet. Post a tweet, get list of tweets.
 */
@Service
public interface TweetService {
    public Tweet postTweet(Tweet tweet);
    public ArrayList<Tweet> getLatestTweets();
    public Page<Tweet> getTweetsPage(Pageable pageable);
}
