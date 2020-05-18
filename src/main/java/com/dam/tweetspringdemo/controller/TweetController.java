package com.dam.tweetspringdemo.controller;

import com.dam.tweetspringdemo.bean.Tweet;
import com.dam.tweetspringdemo.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import java.util.ArrayList;

@RestController
public class TweetController {

    @Autowired
    TweetService tweetService;

    @RequestMapping(value = "/tweet", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Tweet> postTweet(@Valid @RequestBody Tweet tweet, BindingResult bindingResult)
            throws InternalServerErrorException, BadRequestException {
        if(bindingResult.hasErrors()){
            throw new BadRequestException("Author name or the message is/are missing in the request");
        }
        try{
            Tweet postTweet = tweetService.postTweet(tweet);
            return new ResponseEntity<Tweet>(postTweet, HttpStatus.CREATED);
        } catch (Exception ex){
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/feed", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ArrayList<Tweet>> getMyTweets() throws InternalServerErrorException {
        try{
            ArrayList<Tweet> tweetsList = tweetService.getLatestTweets();
            if(tweetsList == null || tweetsList.isEmpty()){
                return new ResponseEntity<ArrayList<Tweet>>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<ArrayList<Tweet>>(tweetsList, HttpStatus.OK);
            }
        }catch (Exception ex){
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/feed/{pageno}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<Tweet>> getMyTweets(@PathVariable("pageno") @Value("${pageno:0}") int pageno ) throws InternalServerErrorException {

        try{
            int pageSize = 2;

            Page<Tweet> tweetsList = tweetService.getTweetsPage(PageRequest.of(pageno, pageSize, Sort.by("publishDate").descending()));
            if(tweetsList == null || tweetsList.isEmpty()){
                return new ResponseEntity<Page<Tweet>>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<Page<Tweet>>(tweetsList, HttpStatus.OK);
            }
        }catch (Exception ex){
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
}
