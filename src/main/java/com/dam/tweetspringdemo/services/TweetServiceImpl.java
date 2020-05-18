package com.dam.tweetspringdemo.services;

import com.dam.tweetspringdemo.bean.Tweet;
import com.dam.tweetspringdemo.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class TweetServiceImpl implements TweetService {
    @Autowired
    FollowerService followerService;

    @Autowired
    TweetRepository tweetRepository;

    @Transactional
    public Tweet postTweet(Tweet tweet) {
        tweet.setId(UUID.randomUUID());
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        tweet.setPublishDate(myDateObj.format(myFormatObj));
        return tweetRepository.save(tweet);
    }

    @Override
    public ArrayList<Tweet> getLatestTweets() {
        String followerUserId = getFollowerUserId();
        ArrayList<String> followingList = followerService.getListOfFollowing(followerUserId);
        ArrayList<Tweet> feed = new ArrayList<>();
        if(followingList == null){
            followingList = new ArrayList<>();
        }
        followingList.add(followerUserId);
        feed = tweetRepository.findFirst100ByAuthorNameInOrderByPublishDateDesc(followingList);
        return feed;
    }

    private String getFollowerUserId(){
//        TODO: Implement getFollowerName
        return "admin";
    }

    @Override
    public Page<Tweet> getTweetsPage(Pageable pageable) {
        /*@RequestMapping(value="INSERT YOUR LINK", method=RequestMethod.GET)
    public List<Profile> getAll(int page) {
        Pageable pageable = new PageRequest(page, 5); //get 5 profiles on a page
        Page<Profile> page = repo.findAll(pageable);
        return Lists.newArrayList(page);*/
//        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC));
        Page<Tweet> pg = tweetRepository.findAll(pageable);
        return pg;
    }
}
