/**
 * Sample Skeleton for 'RetweetFXML.fxml' Controller Class
 */

package com.evangreenstein.twitter_client.controller;

import com.evangreenstein.twitter_client.business.TwitterEngine;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import twitter4j.TwitterException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RetweetFXMLController {
    private final static Logger LOG = LoggerFactory.getLogger(RetweetFXMLController.class);

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="message"
    private TextArea message; // Value injected by FXMLLoader

    @FXML // fx:id="retweetBtn"
    private Button retweetBtn; // Value injected by FXMLLoader

    private Stage stage;
    private long tweetId;
    private String username;
    private final TwitterEngine engine = new TwitterEngine();
    
    public void setStage(Stage stage){
        this.stage= stage;
    }
    
    public void setTweetInfo(long tweetId, String username){
        this.tweetId = tweetId;
        this.username = username;
    }
    
    /**
     * Uses the twitter engine to retweet with a comment, then hides the retweet
     * stage
     * 
     * @param event
     * @throws TwitterException 
     */
    @FXML
    void retweetMessage(ActionEvent event) throws TwitterException {
        LOG.info("retweet button clicked");
        engine.retweet(tweetId, username, message.getText().trim());
        stage.hide();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert message != null : "fx:id=\"message\" was not injected: check your FXML file 'RetweetFXML.fxml'.";
        assert retweetBtn != null : "fx:id=\"retweetBtn\" was not injected: check your FXML file 'RetweetFXML.fxml'.";

    }
}
