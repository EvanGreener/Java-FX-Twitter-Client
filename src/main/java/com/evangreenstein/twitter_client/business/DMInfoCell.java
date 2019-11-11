/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.business;

import com.evangreenstein.twitter_client.data.DMInfo;
import java.util.logging.Level;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;

/**
 *
 * @author evangreenstein
 */
public class DMInfoCell extends ListCell<DMInfo> {
    private final static Logger LOG = LoggerFactory.getLogger(DMInfoCell.class);
    
    private final TwitterEngine engine = new TwitterEngine();
    
    @Override
    protected void updateItem(DMInfo item, boolean empty) {
        super.updateItem(item, empty);
        
        LOG.debug("updateItem");
        
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setGraphic(getDMInfoCell(item));
        }
    }
    
    private Node getDMInfoCell(DMInfo info){
        LOG.debug("getUserInfoCell");
        
        HBox node = new HBox();
        node.setSpacing(10);
        
        Image image;
        ImageView imageView = null;
        try {
            image = new Image(info.getSenderImageURL(), 20, 20, true, false);
            imageView = new ImageView(image);
        
        } catch (TwitterException ex) {
            java.util.logging.Logger.getLogger(DMInfoCell.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if (info.getSenderId() == engine.getId() ){
                node.setAlignment(Pos.BASELINE_RIGHT);
            }
            else {
                node.setAlignment(Pos.BASELINE_LEFT);
            }
        } catch (TwitterException ex) {
            LOG.error("Could not get user id");
        }
        
        Text message = new Text(info.getMessage());
        
        node.getChildren().addAll(imageView , message);
        
        return node;
    }
}
