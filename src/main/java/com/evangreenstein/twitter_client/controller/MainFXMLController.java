/**
 * Sample Skeleton for 'MainFXML.fxml' Controller Class
 */

package com.evangreenstein.twitter_client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainFXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="sideBarVbox"
    private VBox sideBarVbox; // Value injected by FXMLLoader

    @FXML // fx:id="homebtn"
    private Button homebtn; // Value injected by FXMLLoader

    @FXML // fx:id="exploreBtn"
    private Button exploreBtn; // Value injected by FXMLLoader

    @FXML // fx:id="messagesBtn"
    private Button messagesBtn; // Value injected by FXMLLoader

    @FXML // fx:id="profileBtn"
    private Button profileBtn; // Value injected by FXMLLoader

    @FXML // fx:id="profileBtn1"
    private Button profileBtn1; // Value injected by FXMLLoader

    @FXML // fx:id="helpBtn"
    private Button helpBtn; // Value injected by FXMLLoader

    @FXML // fx:id="windowsStackPane"
    private StackPane windowsStackPane; // Value injected by FXMLLoader

    @FXML // fx:id="messagesWindow"
    private HBox messagesWindow; // Value injected by FXMLLoader

    @FXML // fx:id="convosList"
    private ListView<?> convosList; // Value injected by FXMLLoader

    @FXML // fx:id="messageField"
    private TextField messageField; // Value injected by FXMLLoader

    @FXML // fx:id="sendBtn"
    private Button sendBtn; // Value injected by FXMLLoader

    @FXML // fx:id="specificConvoList"
    private ListView<?> specificConvoList; // Value injected by FXMLLoader

    @FXML // fx:id="twitterNameMessagesLbl"
    private Label twitterNameMessagesLbl; // Value injected by FXMLLoader

    @FXML // fx:id="profileWindow"
    private VBox profileWindow; // Value injected by FXMLLoader

    @FXML // fx:id="profileHeader"
    private Label profileHeader; // Value injected by FXMLLoader

    @FXML // fx:id="userProfileImage"
    private ImageView userProfileImage; // Value injected by FXMLLoader

    @FXML // fx:id="followUnfollowBtn"
    private Button followUnfollowBtn; // Value injected by FXMLLoader

    @FXML // fx:id="twitterNameProfileLbl"
    private Label twitterNameProfileLbl; // Value injected by FXMLLoader

    @FXML // fx:id="twitterHandleLbl"
    private Label twitterHandleLbl; // Value injected by FXMLLoader

    @FXML // fx:id="profileDescLbl"
    private Label profileDescLbl; // Value injected by FXMLLoader

    @FXML // fx:id="userProfileTimeline"
    private ListView<?> userProfileTimeline; // Value injected by FXMLLoader

    @FXML // fx:id="exploreWindow"
    private VBox exploreWindow; // Value injected by FXMLLoader

    @FXML // fx:id="searchBar"
    private TextField searchBar; // Value injected by FXMLLoader

    @FXML // fx:id="searchResults"
    private ListView<?> searchResults; // Value injected by FXMLLoader

    @FXML // fx:id="homeWindow"
    private VBox homeWindow; // Value injected by FXMLLoader

    @FXML // fx:id="userImageHome"
    private ImageView userImageHome; // Value injected by FXMLLoader

    @FXML // fx:id="statusMsg"
    private TextArea statusMsg; // Value injected by FXMLLoader

    @FXML // fx:id="tweetBtn"
    private Button tweetBtn; // Value injected by FXMLLoader

    @FXML // fx:id="userTimeline"
    private ListView<?> userTimeline; // Value injected by FXMLLoader

    @FXML // fx:id="notificationsWindow"
    private VBox notificationsWindow; // Value injected by FXMLLoader

    @FXML // fx:id="notificationsList"
    private ListView<?> notificationsList; // Value injected by FXMLLoader

    @FXML
    void followUnfollowUser(ActionEvent event) {

    }

    @FXML
    void isOverCharacterLimit(KeyEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void sendDirectMessage(ActionEvent event) {

    }

    @FXML
    void sendTweet(ActionEvent event) {

    }

    @FXML
    void showHelpScreen(ActionEvent event) {

    }

    @FXML
    void showWindow(ActionEvent event) {
        
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert sideBarVbox != null : "fx:id=\"sideBarVbox\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert homebtn != null : "fx:id=\"homebtn\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert exploreBtn != null : "fx:id=\"exploreBtn\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert messagesBtn != null : "fx:id=\"messagesBtn\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert profileBtn != null : "fx:id=\"profileBtn\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert profileBtn1 != null : "fx:id=\"profileBtn1\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert helpBtn != null : "fx:id=\"helpBtn\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert windowsStackPane != null : "fx:id=\"windowsStackPane\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert messagesWindow != null : "fx:id=\"messagesWindow\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert convosList != null : "fx:id=\"convosList\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert messageField != null : "fx:id=\"messageField\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert sendBtn != null : "fx:id=\"sendBtn\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert specificConvoList != null : "fx:id=\"specificConvoList\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert twitterNameMessagesLbl != null : "fx:id=\"twitterNameMessagesLbl\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert profileWindow != null : "fx:id=\"profileWindow\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert profileHeader != null : "fx:id=\"profileHeader\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert userProfileImage != null : "fx:id=\"userProfileImage\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert followUnfollowBtn != null : "fx:id=\"followUnfollowBtn\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert twitterNameProfileLbl != null : "fx:id=\"twitterNameProfileLbl\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert twitterHandleLbl != null : "fx:id=\"twitterHandleLbl\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert profileDescLbl != null : "fx:id=\"profileDescLbl\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert userProfileTimeline != null : "fx:id=\"userProfileTimeline\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert exploreWindow != null : "fx:id=\"exploreWindow\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert searchBar != null : "fx:id=\"searchBar\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert searchResults != null : "fx:id=\"searchResults\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert homeWindow != null : "fx:id=\"homeWindow\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert userImageHome != null : "fx:id=\"userImageHome\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert statusMsg != null : "fx:id=\"statusMsg\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert tweetBtn != null : "fx:id=\"tweetBtn\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert userTimeline != null : "fx:id=\"userTimeline\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert notificationsWindow != null : "fx:id=\"notificationsWindow\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert notificationsList != null : "fx:id=\"notificationsList\" was not injected: check your FXML file 'MainFXML.fxml'.";

    }
}
