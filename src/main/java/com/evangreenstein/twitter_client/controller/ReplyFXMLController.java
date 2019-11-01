/**
 * Sample Skeleton for 'ReplyFXML.fxml' Controller Class
 */

package com.evangreenstein.twitter_client.controller;

import com.evangreenstein.twitter_client.business.TwitterEngine;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;

public class ReplyFXMLController {

    private final static Logger LOG = LoggerFactory.getLogger(ReplyFXMLController.class);

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="replyingToLbl"
    private Label replyingToLbl; // Value injected by FXMLLoader

    @FXML // fx:id="replyArea"
    private TextArea replyArea; // Value injected by FXMLLoader

    @FXML // fx:id="replyBtn"
    private Button replyBtn; // Value injected by FXMLLoader

    private final TwitterEngine engine = new TwitterEngine();
    private Stage stage;
    private long tweetId;
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public void setTweetInfo(long tweetId, String handle){
        this.tweetId = tweetId;
        
        replyingToLbl.setText(String.format("Replying to: @%s", handle ));
    }
    
    /**
     * Uses the twitter engine to reply to the tweet and then makes the reply
     * window disappear once the reply has been sent
     * 
     * @param event
     * @throws TwitterException 
     */
    @FXML
    void replyToTweet(ActionEvent event) throws TwitterException {
        engine.replyToTweet(replyArea.getText().trim() , tweetId);
        stage.hide();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert replyingToLbl != null : "fx:id=\"replyingToLbl\" was not injected: check your FXML file 'ReplyFXML.fxml'.";
        assert replyArea != null : "fx:id=\"replyArea\" was not injected: check your FXML file 'ReplyFXML.fxml'.";
        assert replyBtn != null : "fx:id=\"replyBtn\" was not injected: check your FXML file 'ReplyFXML.fxml'.";

        
    }
}
