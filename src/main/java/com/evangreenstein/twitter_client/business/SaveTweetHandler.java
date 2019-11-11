/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.business;

import com.evangreenstein.twitter_client.data.TwitterInfo;
import com.evangreenstein.twitter_client.persistence.TweetDAO;
import com.evangreenstein.twitter_client.persistence.TweetDAOImpl;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import org.slf4j.LoggerFactory;

/**
 *
 * @author evangreenstein
 */
public class SaveTweetHandler implements EventHandler<MouseEvent>  {

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(SaveTweetHandler.class);
    
    private Button saveTweetBtn;
    private TwitterInfo info;
    
    public SaveTweetHandler(Button saveTweetBtn, TwitterInfo info){
        this.saveTweetBtn = saveTweetBtn;
        this.info = info;
    }
    
    

    @Override
    public void handle(MouseEvent t) {
        try {
            LOG.debug("Storing tweet to database");
            TweetDAO tweetDAO = new TweetDAOImpl("database.properties");
            tweetDAO.storeTweet(info);
            LOG.debug("Stored tweet sucessfully!");
            saveTweetBtn.setText("Tweet saved");
            saveTweetBtn.setDisable(true);
            
        } catch (IOException ex) {
            LOG.error("Could not load database properties file");
        }
        
        
        
    }
    
    
}
