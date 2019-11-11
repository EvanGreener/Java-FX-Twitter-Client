/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.business;

import com.evangreenstein.twitter_client.data.DMInfo;
import java.util.List;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.DirectMessage;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 *
 * @author evangreenstein
 */
public class SpecificConversation {
    private final static Logger LOG = LoggerFactory.getLogger(SpecificConversation.class);

    private final ObservableList<DMInfo> list;

    private final TwitterEngine twitterEngine;
    
    public SpecificConversation(ObservableList<DMInfo> list){
        this.list = list;
        twitterEngine = new TwitterEngine();
        
    }
    
    
    public void fillConvosList(long userid) throws TwitterException{
        List<DirectMessage> convo = twitterEngine.getConversation(userid);
        list.clear();
        convo.forEach((message) -> {
            list.add(list.size(), new DMInfo(message));
        });
    }
}
