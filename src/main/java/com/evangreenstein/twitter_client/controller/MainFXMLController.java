
package com.evangreenstein.twitter_client.controller;

import com.evangreenstein.twitter_client.business.ConversationList;
import com.evangreenstein.twitter_client.data.DMInfo;
import com.evangreenstein.twitter_client.business.DMInfoCell;
import com.evangreenstein.twitter_client.business.MentionsManager;
import com.evangreenstein.twitter_client.business.SearchManager;
import com.evangreenstein.twitter_client.business.SpecificConversation;
import com.evangreenstein.twitter_client.business.TimelineManager;
import com.evangreenstein.twitter_client.business.TwitterEngine;
import com.evangreenstein.twitter_client.data.TwitterInfo;
import com.evangreenstein.twitter_client.business.TwitterInfoCell;
import com.evangreenstein.twitter_client.data.UserInfo;
import com.evangreenstein.twitter_client.business.UserInfoCell;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;

/**
 * The controller class for the main twitter client.
 * 
 * @author evangreenstein
 */
public class MainFXMLController {
    
    private final static Logger LOG = LoggerFactory.getLogger(MainFXMLController.class);


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
    private Button notificationsBtn; // Value injected by FXMLLoader

    @FXML // fx:id="helpBtn"
    private Button helpBtn; // Value injected by FXMLLoader

    @FXML // fx:id="windowsStackPane"
    private StackPane windowsStackPane; // Value injected by FXMLLoader

    @FXML // fx:id="messagesWindow"
    private HBox messagesWindow; // Value injected by FXMLLoader

    @FXML // fx:id="convosList"
    private ListView<UserInfo> convosList; // Value injected by FXMLLoader

    @FXML // fx:id="messageField"
    private TextField messageField; // Value injected by FXMLLoader

    @FXML // fx:id="sendBtn"
    private Button sendBtn; // Value injected by FXMLLoader

    @FXML // fx:id="specificConvoList"
    private ListView<DMInfo> specificConvoList; // Value injected by FXMLLoader

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
    private ListView<TwitterInfo> searchResults; // Value injected by FXMLLoader

    @FXML // fx:id="homeWindow"
    private VBox homeWindow; // Value injected by FXMLLoader

    @FXML // fx:id="userImageHome"
    private ImageView userImageHome; // Value injected by FXMLLoader

    @FXML // fx:id="statusMsg"
    private TextArea statusMsg; // Value injected by FXMLLoader

    @FXML // fx:id="tweetBtn"
    private Button tweetBtn; // Value injected by FXMLLoader

    @FXML // fx:id="userTimeline"
    private ListView<TwitterInfo> userTimeline; // Value injected by FXMLLoader

    @FXML // fx:id="notificationsWindow"
    private VBox notificationsWindow; // Value injected by FXMLLoader

    @FXML // fx:id="notificationsList"
    private ListView<TwitterInfo> notificationsList; // Value injected by FXMLLoader

    @FXML // fx:id="nextTweetsBtn"
    private Button nextTweetsBtn; // Value injected by FXMLLoader


    private final TwitterEngine twitterEngine = new TwitterEngine();
    private TimelineManager timelineManager;
    private MentionsManager mentionsManager;
    private SearchManager searchManager;
    private SpecificConversation specificConversationManager;
    private long selectedUser;
    
    @FXML
    void followUnfollowUser(ActionEvent event) {

    }

    @FXML
    void isOverCharacterLimit(KeyEvent event) {

    }

    /**
     * Retrieves the tweets that match the search term. 
     * 
     * @param event
     * @throws TwitterException
     * @throws Exception 
     */
    @FXML
    void search(ActionEvent event) throws TwitterException, Exception {
        searchManager.fillTimeLine(searchBar.getText());
        searchBar.clear();
        
    }

    /**
     * Sends a direct message. Unfortunately the client has to be restarted to see the effect.
     * 
     * @param event
     * @throws TwitterException 
     */
    @FXML
    void sendDirectMessage(ActionEvent event) throws TwitterException {
        LOG.debug("Send direct message. Restart client to see message in conversation");
        twitterEngine.sendDirectMessage(selectedUser, messageField.getText());
        messageField.clear();
    }

    /**
     * Sends a tweet
     * 
     * @param event
     * @throws TwitterException 
     */
    @FXML
    void sendTweet(ActionEvent event) throws TwitterException {
        String tweet = statusMsg.getText();
        statusMsg.clear();
        twitterEngine.createTweet(tweet);
    }

    @FXML
    void showHelpScreen(ActionEvent event) {
        LOG.debug("Creating reply stage");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HelpFXML.fxml"), 
                ResourceBundle.getBundle("MessagesBundle")); 
        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            Scene helpScene = new Scene(root);
            stage.setScene(helpScene);
            stage.setTitle("Help");
            stage.show();
        
        } catch (IOException ex) {
            LOG.error("Could not create the reply window");
        }
    }
    
    /**
     * The event that fills the timeline with 20 more tweets
     * 
     * @param event 
     */
    @FXML
    void showNextTweets(ActionEvent event) {
        try {
            timelineManager.fillTimeLine();
        } catch (Exception e) {
            LOG.error("Unable to add to timeline");
        }

    }

    /**
     * This event handler is attached to all the sidebar buttons. When a sidebar button
     * is pressed, it brings the corresponding window to the foreground. 
     * 
     * I realize that attaching all the buttons to one event handler was not the cleanest 
     * way of approaching this.
     * 
     * @param event 
     */
    @FXML
    void showWindow(ActionEvent event) {
        
        EventTarget target = event.getTarget();
        if (target instanceof Button){
            Button sideBarBtn = (Button) target;
            String id = sideBarBtn.getId();
            LOG.debug(id);

            //Checks the first part before Btn and which one that corresponds to.
            //E.g. homeBtn matches "^home\w*"
            if (Pattern.matches("^home\\w*", id)){
                homeWindow.toFront();
            }
            else if (Pattern.matches("^explore\\w*", id)){
                exploreWindow.toFront();
            }
            else if (Pattern.matches("^messages\\w*", id)){
                messagesWindow.toFront();
            }
            else if (Pattern.matches("^profile\\w*", id)) {
                profileWindow.toFront();
            }
            else if (Pattern.matches("^notifications\\w*", id)){
                notificationsWindow.toFront();
            }
        }
        
    }
    
    @FXML
    void showConversation(MouseEvent event) throws TwitterException{
        LOG.debug("showConversation");
        UserInfo info = convosList.getSelectionModel().getSelectedItem();
        specificConversationManager.fillConvosList(info.getId());
        twitterNameMessagesLbl.setText(info.getName());
        selectedUser = info.getId();
        
    }
    
    /**
     * Initializes the listviews for the home timeline, the mentions timeline and 
     * the search result tweets. 
     * 
     * @throws Exception 
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws Exception {
        assert sideBarVbox != null : "fx:id=\"sideBarVbox\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert homebtn != null : "fx:id=\"homebtn\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert exploreBtn != null : "fx:id=\"exploreBtn\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert messagesBtn != null : "fx:id=\"messagesBtn\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert profileBtn != null : "fx:id=\"profileBtn\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert notificationsBtn != null : "fx:id=\"notificationsBtn\" was not injected: check your FXML file 'MainFXML.fxml'.";
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
        assert nextTweetsBtn != null : "fx:id=\"nextTweetsBtn\" was not injected: check your FXML file 'MainFXML.fxml'.";
        
        ObservableList<TwitterInfo> timeline = FXCollections.observableArrayList();
        userTimeline.setItems(timeline);
        userTimeline.setCellFactory(p -> new TwitterInfoCell());
        timelineManager = new TimelineManager(userTimeline.getItems());
        timelineManager.fillTimeLine();
        
        ObservableList<TwitterInfo> mentions = FXCollections.observableArrayList();
        notificationsList.setItems(mentions);
        notificationsList.setCellFactory(p -> new TwitterInfoCell());
        mentionsManager = new MentionsManager(notificationsList.getItems());
        mentionsManager.fillTimeLine();
        
        ObservableList<TwitterInfo> results = FXCollections.observableArrayList();
        searchResults.setItems(results);
        searchResults.setCellFactory(p -> new TwitterInfoCell());
        searchManager = new SearchManager(searchResults.getItems());
        
        ObservableList<UserInfo> convos = FXCollections.observableArrayList();
        convosList.setItems(convos);
        convosList.setCellFactory(p -> new UserInfoCell());
        ConversationList convosListManager = new ConversationList(convosList.getItems());
        convosListManager.fillConvosList();
        
        ObservableList<DMInfo> specificConvo = FXCollections.observableArrayList();
        specificConvoList.setItems(specificConvo);
        specificConvoList.setCellFactory(p -> new DMInfoCell());
        specificConversationManager = new SpecificConversation(specificConvoList.getItems());
        
        
        userImageHome.setImage(twitterEngine.getProfileImage());
        
        profileHeader.setText(twitterEngine.getTwitterName() + " - Profile");
        userProfileImage.setImage(twitterEngine.getProfileImage());
        twitterNameProfileLbl.setText(twitterEngine.getTwitterName());
        twitterHandleLbl.setText(twitterEngine.getTwitterHandle());
        profileDescLbl.setText(twitterEngine.getDesc());
    }
}
