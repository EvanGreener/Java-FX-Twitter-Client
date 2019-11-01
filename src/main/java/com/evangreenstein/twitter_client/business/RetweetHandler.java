/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.business;

import com.evangreenstein.twitter_client.controller.ReplyFXMLController;
import com.evangreenstein.twitter_client.controller.RetweetFXMLController;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 1734025
 */
public class RetweetHandler implements EventHandler<ActionEvent> {
    
    private final static Logger LOG = LoggerFactory.getLogger(RetweetHandler.class);

    private final long tweetId;
    private final String username;
    
    public RetweetHandler(long tweetId, String username) {
        this.tweetId = tweetId;
        this.username = username;
    }

    /**
     * Creates a new stage with a text area and retweet button. The tweet id and the 
     * handle is passed in the controller for later.
     * 
     * @param t 
     */
    @Override
    public void handle(ActionEvent t) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RetweetFXML.fxml"), 
                ResourceBundle.getBundle("MessagesBundle")); 
        Parent root;
        try {
            root = loader.load();
            RetweetFXMLController ctrl = loader.getController();
            ctrl.setTweetInfo(tweetId, username);
            Stage stage = new Stage();
            ctrl.setStage(stage);
            Scene retweetMessageScene = new Scene(root);
            stage.setScene(retweetMessageScene);
            stage.setTitle("Retweet with message");
            stage.show();
        
        } catch (IOException ex) {
            
        }
    }
    
}
