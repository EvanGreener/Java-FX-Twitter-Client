/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.business;

import com.evangreenstein.twitter_client.data.TwitterInfo;
import java.util.List;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;

/**
 * Responsible for managing the search results timeline
 */
public class SearchManager {
    private final static Logger LOG = LoggerFactory.getLogger(SearchManager.class);

    private final ObservableList<TwitterInfo> list;

    private final TwitterEngine twitterEngine;


    /**
     * Non-default constructor initializes instance variables.
     *
     * @param list
     */
    public SearchManager(ObservableList<TwitterInfo> list) {
        twitterEngine = new TwitterEngine();
        this.list = list;
        
    }

    /**
     * Always clears the list before filling it with the search results
     *
     * @param searchTerm
     * @throws Exception
     */
    public void fillTimeLine(String searchTerm) throws Exception {
        LOG.debug("Clearing and filling the search results timeline");
        List<Status> searchResults = twitterEngine.searchtweets(searchTerm);
        list.clear();
        searchResults.forEach((status) -> {
            list.add(list.size(), new TwitterInfo(status));
        });
       
    }
    
}
