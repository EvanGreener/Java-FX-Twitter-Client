/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.User;

/**
 *
 * @author evangreenstein
 */
public class UserInfo {
    
    private final static Logger LOG = LoggerFactory.getLogger(UserInfo.class);
    
    private final User user;
    
    public UserInfo(User user){
        this.user = user;
    }
    
    public String getName(){
        return user.getName();
    }
    
    public String getImageURL(){
        return user.getProfileImageURL();
    }
    
    public long getId(){
        return user.getId();
    }
}
