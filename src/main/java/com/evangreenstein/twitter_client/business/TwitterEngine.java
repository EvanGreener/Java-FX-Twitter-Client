
package com.evangreenstein.twitter_client.business;

import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StatusUpdate;
import twitter4j.TwitterFactory;

/**
 * The class that's responsible for interaction with the twitter4j library. 
 */
public class TwitterEngine {
    
    private final static Logger LOG = LoggerFactory.getLogger(TwitterEngine.class);
    
    public Twitter getTwitterinstance() {
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
        Twitter twitter = getTwitterinstance();
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
        Twitter twitter = getTwitterinstance();
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
        Twitter twitter = getTwitterinstance();
        DirectMessage message = twitter.sendDirectMessage(recipientName, msg);
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
        Twitter twitter = getTwitterinstance();
        Query query = new Query(searchTerm);
        QueryResult result = twitter.search(query);
        List<Status> statuses = result.getTweets();
        return statuses;
    }
    
    /**
     * Replies to a tweet 
     * 
     * @param reply
     * @param tweetId
     * @throws TwitterException 
     */
    public void replyToTweet(String reply, long tweetId) throws TwitterException{
        LOG.debug("replyToTweet");
        Twitter twitter = getTwitterinstance();
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
        Twitter twitter = getTwitterinstance();
        Paging paging = new Paging();
        paging.setCount(20);
        paging.setPage(page);
        List<Status> statuses = twitter.getMentionsTimeline(paging);
        return statuses;
    }
}
