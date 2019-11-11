/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.business;

import com.evangreenstein.twitter_client.data.UserInfo;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author evangreenstein
 */
public class UserInfoCell extends ListCell<UserInfo> {
    
    private final static Logger LOG = LoggerFactory.getLogger(UserInfoCell.class);
    
    @Override
    protected void updateItem(UserInfo item, boolean empty) {
        super.updateItem(item, empty);
        
        LOG.debug("updateItem");
        
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setGraphic(getUserInfoCell(item));
        }
    }
    
    private Node getUserInfoCell(UserInfo info){
        LOG.debug("getUserInfoCell");
        
        HBox node = new HBox();
        node.setSpacing(10);
        
        Image image = new Image(info.getImageURL(), 48, 48, true, false);
        ImageView imageView = new ImageView(image);
        
        Text screenName = new Text(info.getName());
        
        node.getChildren().addAll(imageView, screenName);
        
        return node;
    }
}
