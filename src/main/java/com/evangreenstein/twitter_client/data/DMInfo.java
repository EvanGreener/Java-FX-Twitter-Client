/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 *
 * @author evangreenstein
 */
public class DMInfo {
    
    private final static Logger LOG = LoggerFactory.getLogger(DMInfo.class);
    private final Twitter twitter;
    
    private DirectMessage dm;
    
    public DMInfo(DirectMessage dm){
        this.dm = dm;
        twitter = TwitterFactory.getSingleton();
    }
    
    public long getRecipientId(){
        return dm.getRecipientId();
    }
    
    public long getSenderId(){
        return dm.getSenderId();
    }
    
    public String getMessage(){
        return dm.getText();
    }

    public String getSenderImageURL() throws TwitterException {
        return twitter.showUser(getSenderId()).getProfileImageURL();
    }
}
