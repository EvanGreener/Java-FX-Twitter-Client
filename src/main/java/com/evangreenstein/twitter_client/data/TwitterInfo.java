package com.evangreenstein.twitter_client.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;

/**
 * Twitter information for ListCell
 * This class defines which members of the Status object you wish to display.
 * 
 */
public class TwitterInfo {
    
    private final static Logger LOG = LoggerFactory.getLogger(TwitterInfo.class);
    
    private final Status status;

    public TwitterInfo(Status status) {
        this.status = status;
    }

    public String getName() {
        return status.getUser().getName();
    }
    
    public Date getDateCreated() {
        return status.getCreatedAt();
    }

    public String getText(){
        return status.getText();
    }

    public String getImageURL(){
        return status.getUser().getProfileImageURL();
    }
    
    public String getHandle() {
      return status.getUser().getScreenName();
    }
    
    public long getTweetID() {
        return status.getId();
    }
    
    public boolean isLiked(){
        return status.isFavorited();
    }
    
}
