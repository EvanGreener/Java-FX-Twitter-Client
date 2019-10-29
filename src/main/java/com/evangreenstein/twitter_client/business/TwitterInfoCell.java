package com.evangreenstein.twitter_client.business;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ListCell for TwitterInfo This class represents the contents of an HBox that
 * contains tweet info.
 *
 * 
 */
public class TwitterInfoCell extends ListCell<TwitterInfo> {

    private final static Logger LOG = LoggerFactory.getLogger(TwitterInfoCell.class);

    /**
     * This method is called when ever cells need to be updated
     *
     * @param item
     * @param empty
     */
    @Override
    protected void updateItem(TwitterInfo item, boolean empty) {
        super.updateItem(item, empty);

        LOG.debug("updateItem");

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setGraphic(getTwitterInfoCell(item));
        }
    }

    /**
     * This method determines what the cell will look like. Here is where you
     * can add buttons or any additional information
     *
     * @param info
     * @return The node to be placed into the ListView
     */
    private Node getTwitterInfoCell(TwitterInfo info) {
        LOG.debug("getTwitterInfoCell");
        HBox node = new HBox();
        node.setSpacing(10);
        
        Image image = new Image(info.getImageURL(), 48, 48, true, false);
        ImageView imageView = new ImageView(image);
        
        Text name = new Text(info.getName());
        name.setWrappingWidth(450);
        
        Text text = new Text(info.getText());
        text.setWrappingWidth(450);
        
        Button commentBtn = new Button();
        Image commentImage = new Image("images/ic_comment.png", 20, 20, true, false);
        commentBtn.setGraphic(new ImageView(commentImage));
        commentBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, new ReplyHandler(info.getTweetID(), info.getHandle()));
        
        Button retweetBtn = new Button();
        Image retweetImage = new Image("images/ic_retweet.png", 20, 20, true, false);
        retweetBtn.setGraphic(new ImageView(retweetImage));
        
        Button likeBtn = new Button();
        Image likeImage = new Image("images/ic_heart_empty.png", 20, 20, true, false);
        likeBtn.setGraphic(new ImageView(likeImage)); 
        
        HBox buttons = new HBox();
        buttons.setSpacing(100);
        buttons.getChildren().addAll(commentBtn, retweetBtn, likeBtn);
        
        Text count = new Text(""+info.getTweetID());
        LOG.debug("ID = : "+ info.getTweetID());
        count.setWrappingWidth(450);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(name, text, buttons, count);
        node.getChildren().addAll(imageView, vbox);
        
        return node;
    }
}
