/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.business;

import com.evangreenstein.twitter_client.controller.ReplyFXMLController;
import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 */
public class ReplyHandler implements EventHandler<MouseEvent> {

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(ReplyHandler.class);
    private long tweetId;
    private String handle;
    
    public ReplyHandler(long tweetId, String handle){
        this.tweetId = tweetId;
        this.handle = handle;
    }
    
    /**
     * Creates a new stage with a text area and reply button. The tweet id and the 
     * handle is passed in the controller for later.
     * 
     * @param t 
     */
    @Override
    public void handle(MouseEvent t) {
        LOG.debug("Creating reply stage");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ReplyFXML.fxml"), 
                ResourceBundle.getBundle("MessagesBundle")); 
        Parent root;
        try {
            root = loader.load();
            ReplyFXMLController ctrl = loader.getController();
            ctrl.setTweetInfo(tweetId, handle);
            Stage stage = new Stage();
            ctrl.setStage(stage);
            Scene commentScene = new Scene(root);
            stage.setScene(commentScene);
            stage.setTitle("Reply To Tweet");
            stage.show();
        
        } catch (IOException ex) {
            LOG.error("Could not create the reply window");
        }
        
    }
    
}
