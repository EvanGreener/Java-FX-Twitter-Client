/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.business;

import java.util.List;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;

/**
 * Responsible for managing the notifications/mentions timeline
 */
public class MentionsManager {
    private final static Logger LOG = LoggerFactory.getLogger(MentionsManager.class);

    private final ObservableList<TwitterInfo> list;

    private final TwitterEngine twitterEngine;

    private int page;
    

    /**
     * Non-default constructor initializes instance variables.
     *
     * @param list
     */
    public MentionsManager(ObservableList<TwitterInfo> list) {
        twitterEngine = new TwitterEngine();
        this.list = list;
        page = 1;
    }

    /**
     * Add new Status objects to the ObservableList. Additions occur at the end
     * of the list.
     *
     * @throws Exception
     */
    public void fillTimeLine() throws Exception {
        LOG.debug("Filling the mentions timeline in notifications");
        List<Status> mentions = twitterEngine.getMentions(page);
        mentions.forEach((status) -> {
            list.add(list.size(), new TwitterInfo(status));
        });
        page += 1;
    }
    
    
}
