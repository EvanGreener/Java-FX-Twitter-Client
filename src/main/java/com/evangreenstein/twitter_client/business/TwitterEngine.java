
package com.evangreenstein.twitter_client.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.image.Image;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.DirectMessage;
import twitter4j.DirectMessageList;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StatusUpdate;
import twitter4j.TwitterFactory;
import twitter4j.User;

/**
 * The class that's responsible for interaction with the twitter4j library. 
 */
public class TwitterEngine {
    
    private final static Logger LOG = LoggerFactory.getLogger(TwitterEngine.class);
    
    public Twitter getTwitterInstance() {
        Twitter twitter = TwitterFactory.getSingleton();
        return twitter;

    }
    /**
     * Send a tweet
     *
     * @param tweet
     * @return
     * @throws TwitterException
     */
    public String createTweet(String tweet) throws TwitterException {
        LOG.debug("createTweet: " + tweet);
        Twitter twitter = getTwitterInstance();
        Status status = twitter.updateStatus(tweet);
        return status.getText();
    }
    
    /**
     * Get the timeline.
     *
     * @param page
     * @return
     * @throws TwitterException
     */
    public List<Status> getTimeLine(int page) throws TwitterException {
        LOG.debug("getTimeLine");
        Twitter twitter = getTwitterInstance();
        Paging paging = new Paging();
        paging.setCount(20);
        paging.setPage(page);
        List<Status> statuses = twitter.getHomeTimeline(paging);
        return statuses;
    }

    /**
     * Send direct message
     *
     * @param recipientName
     * @param msg
     * @return
     * @throws TwitterException
     */
    public String sendDirectMessage(String recipientName, String msg) throws TwitterException {
        LOG.debug("sendDirectMessage");
        Twitter twitter = getTwitterInstance();
        DirectMessage message = twitter.sendDirectMessage(recipientName, msg);
        return message.getText();
    }
    
    /**
     * Sends a direct message
     * 
     * @param userid
     * @param msg
     * @return
     * @throws TwitterException 
     */
    public String sendDirectMessage(long userid, String msg) throws TwitterException {
        LOG.debug("sendDirectMessage");
        Twitter twitter = getTwitterInstance();
        DirectMessage message = twitter.sendDirectMessage(userid, msg);
        return message.getText();
    }

    /**
     * Search for tweets with specific contents
     *
     * @param searchTerm
     * @return
     * @throws TwitterException
     */
    public List<Status> searchtweets(String searchTerm) throws TwitterException {
        LOG.debug("searchtweets");
        Twitter twitter = getTwitterInstance();
        Query query = new Query(searchTerm);
        QueryResult result = twitter.search(query);
        List<Status> statuses = result.getTweets();
        return statuses;
    }
    
    /**
     * Replies to a tweet. Updates users status in reply to someone else's status
     * 
     * @param reply
     * @param tweetId
     * @throws TwitterException 
     */
    public void replyToTweet(String reply, long tweetId) throws TwitterException{
        LOG.debug("replyToTweet");
        Twitter twitter = getTwitterInstance();
        StatusUpdate update = (new StatusUpdate(reply)).inReplyToStatusId(tweetId);
        twitter.updateStatus(update);
        
    }
    
    /**
     * Retrieves the tweets where you are mentioned.
     * 
     * @param page
     * @return
     * @throws TwitterException 
     */
    public List<Status> getMentions(int page) throws TwitterException{
        LOG.debug("getMentions");
        Twitter twitter = getTwitterInstance();
        Paging paging = new Paging();
        paging.setCount(20);
        paging.setPage(page);
        List<Status> statuses = twitter.getMentionsTimeline(paging);
        return statuses;
        
    }
    
    /** Retweets a tweet
     * 
     * @param tweetId
     * @throws TwitterException 
     */
    public void retweet(long tweetId) throws TwitterException{
        LOG.debug("Retweet without message");
        Twitter twitter = getTwitterInstance();
        twitter.retweetStatus(tweetId);
    }
    
    /**
     * Retweets a tweet with a message. A retweet with a message is simply a
     * normal tweet with the URL at the end.
     * 
     * @param tweetId
     * @param username
     * @param msg 
     */
    public void retweet(long tweetId, String username, String msg) throws TwitterException{
        LOG.debug("Retweet with message");
        Twitter twitter = getTwitterInstance();
        StatusUpdate update = (new StatusUpdate(
                String.format("%s https://twitter.com/%s/status/%s", msg, username, tweetId)
        ));
        twitter.updateStatus(update);
    }
    
    /**
     * Likes a tweet
     * 
     * @param tweetId
     * @throws TwitterException 
     */
    public void likeTweet(long tweetId) throws TwitterException{
        LOG.debug("like tweet");
        Twitter twitter = getTwitterInstance();
        twitter.createFavorite(tweetId);
    }
    
    /**
     * Unlikes a tweet
     * 
     * @param tweetId
     * @throws TwitterException 
     */
    public void unLikeTweet(long tweetId) throws TwitterException{
        LOG.debug("like tweet");
        Twitter twitter = getTwitterInstance();
        twitter.destroyFavorite(tweetId);
    }
    
    /**
     * The list of users the user had a conversation with in the last 30 days. 
     * 
     * @return The list of users 
     */
    public List<User> getConversationUsers() throws TwitterException{
        Twitter twitter = getTwitterInstance();
        
        long userID = twitter.getId();
        LOG.debug("user id: " + userID);
        
        List<User> convos = new ArrayList<>();
        List<Long> convosIDs = new ArrayList<>();
        String cursor = null;
        int count = 20;
        DirectMessageList messages;
        do{
            messages = cursor == null ? twitter.getDirectMessages(count) : twitter.getDirectMessages(count, cursor);
            for (DirectMessage message : messages) {
                long recepientID = message.getRecipientId();
                long senderID = message.getSenderId();
                
                if (recepientID != userID && !convosIDs.contains(recepientID)){
                    convosIDs.add(recepientID);
                    convos.add(twitter.showUser(recepientID));
                }
                else if (senderID != userID && !convosIDs.contains(senderID)){
                    convosIDs.add(senderID);
                    convos.add(twitter.showUser(senderID));
                }
            }
            
            cursor = messages.getNextCursor();
        } while (messages.size() > 0 && cursor != null);
        
        LOG.debug(convos.toString());
        
        return convos;
    }

    /**
     * Gets the conversation the user had with the other specified user. Only gets
     * the conversation up to 30 days ago.
     * 
     * @param id
     * @return The conversation 
     * @throws TwitterException 
     */
    public List<DirectMessage> getConversation(long id) throws TwitterException {
        Twitter twitter = getTwitterInstance();
        List<DirectMessage> convo = new ArrayList<>();
        String cursor = null;
        int count = 20;
        DirectMessageList messages;
        do{
            messages = cursor == null ? twitter.getDirectMessages(count) : twitter.getDirectMessages(count, cursor);
            for (DirectMessage message : messages) {
                long recepientID = message.getRecipientId();
                long senderID = message.getSenderId();
                
                //Adds a message to the conversation only if the user with 'id' was a
                //sender or recepient of message
                if (recepientID == id || senderID == id){
                    convo.add(message);
                }
            }
            
            cursor = messages.getNextCursor();
        } while (messages.size() > 0 && cursor != null);
        
        //Since the dms are newest first, we need to reverse it so the old ones show up at the top
        Collections.reverse(convo);
        
        return convo;
    }
    
    /**
     * 
     * @return The user's id
     * @throws twitter4j.TwitterException
     */
    public long getId() throws TwitterException{
        Twitter twitter = getTwitterInstance();
        return twitter.getId();
    }
    
    /**
     * 
     * @return the user's profile image
     * @throws TwitterException 
     */
    public Image getProfileImage() throws TwitterException{
        Twitter twitter = getTwitterInstance();
        return new Image(twitter.showUser(twitter.getId()).get400x400ProfileImageURL());
    }
    
    /**
     * 
     * @return the user's screen name
     * @throws TwitterException 
     */
    public String getTwitterName() throws TwitterException {
        Twitter twitter = getTwitterInstance();
        return twitter.showUser(twitter.getId()).getName();
    }
    
    /**
     * 
     * @return the user's handle
     * @throws TwitterException 
     */
    public String getTwitterHandle() throws TwitterException {
        Twitter twitter = getTwitterInstance();
        return twitter.showUser(twitter.getId()).getScreenName();
    }
    
    /**
     * 
     * @return the user's description
     * @throws TwitterException 
     */
    public String getDesc() throws TwitterException {
        Twitter twitter = getTwitterInstance();
        return twitter.showUser(twitter.getId()).getDescription();
    }
    
}
