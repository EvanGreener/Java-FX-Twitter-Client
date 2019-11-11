/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.business;

import com.evangreenstein.twitter_client.data.UserInfo;
import java.util.List;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 *
 * @author evangreenstein
 */
public class ConversationList {
    
    private final static Logger LOG = LoggerFactory.getLogger(ConversationList.class);

    private final ObservableList<UserInfo> list;

    private final TwitterEngine twitterEngine;
    
    public ConversationList(ObservableList<UserInfo> list){
        this.list = list;
        twitterEngine = new TwitterEngine();
        
    }
    
    
    public void fillConvosList() throws TwitterException{
        List<User> convos = twitterEngine.getConversationUsers();
        convos.forEach((user) -> {
            list.add(list.size(), new UserInfo(user));
        });
    }
}
