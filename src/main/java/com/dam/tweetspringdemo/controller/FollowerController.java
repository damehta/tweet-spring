package com.dam.tweetspringdemo.controller;

import com.dam.tweetspringdemo.bean.Follower;
import com.dam.tweetspringdemo.services.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;

@RestController
public class FollowerController  {
    @Autowired
    FollowerService followerService;

    @RequestMapping(value = "/follower", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Follower> addToFollowingList(@Valid @RequestBody Follower follower, BindingResult bindingResult)
            throws BadRequestException, InternalServerErrorException {

        if(bindingResult.hasErrors()) {
            throw new BadRequestException();
        }
        try{
            Follower updatedFollower = followerService.updateFollower(follower);
            return new ResponseEntity<Follower>(updatedFollower, HttpStatus.OK);
        }catch (Exception ex){
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<Follower> findUser(@RequestParam String userId)
        throws BadRequestException, InternalServerErrorException {
        try{
            return new ResponseEntity<Follower>(followerService.getUser(userId), HttpStatus.OK);
        } catch (Exception ex){
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
}
