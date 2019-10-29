/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.business;

import com.evangreenstein.twitter_client.controller.ReplyFXMLController;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author evangreenstein
 */
public class ReplyHandler implements EventHandler<MouseEvent> {

    private long tweetId;
    private String handle;
    
    public ReplyHandler(long tweetId, String handle){
        this.tweetId = tweetId;
        this.handle = handle;
    }
    
    @Override
    public void handle(MouseEvent t) {
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
            Logger.getLogger(ReplyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
