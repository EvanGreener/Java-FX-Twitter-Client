/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.persistence;

import com.evangreenstein.twitter_client.data.TwitterInfo;
import com.evangreenstein.twitter_client.data.StatusData;
import javafx.collections.ObservableList;

/**
 *
 * @author evangreenstein
 */
public interface TweetDAO {
    int storeTweet(TwitterInfo info);
    StatusData getTweetFromDB(long id);
    ObservableList<StatusData> retrieveSavedTweets();
}
