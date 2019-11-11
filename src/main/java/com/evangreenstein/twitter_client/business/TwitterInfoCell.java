package com.evangreenstein.twitter_client.business;

import com.evangreenstein.twitter_client.data.TwitterInfo;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;

/**
 * ListCell for TwitterInfo This class represents the contents of an HBox that
 * contains tweet info.
 *
 * 
 */
public class TwitterInfoCell extends ListCell<TwitterInfo> {

    private final static Logger LOG = LoggerFactory.getLogger(TwitterInfoCell.class);
    
    private final TwitterEngine engine = new TwitterEngine();

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
     * This is what every tweet in a timeline looks like
     *
     * @param info
     * @return The node to be placed into the ListView
     */
    private Node getTwitterInfoCell(TwitterInfo info) {
        LOG.debug("getTwitterInfoCell");
        HBox node = new HBox();
        node.setSpacing(10);
        LOG.debug("URL: " + info.getImageURL());
        Image image = new Image(info.getImageURL(), 48, 48, true, false);
        ImageView imageView = new ImageView(image);
        
        Text name = new Text(info.getName());
        
        SimpleDateFormat simpleformat = new SimpleDateFormat("MMM dd hh:mm");
        Text date = new Text(simpleformat.format(info.getDateCreated()));
        
        Text text = new Text(info.getText());
        text.setWrappingWidth(400);
        
        Button commentBtn = new Button();
        Image commentImage = new Image("images/ic_comment.png", 20, 20, true, false);
        commentBtn.setGraphic(new ImageView(commentImage));
        commentBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, new ReplyHandler(info.getTweetID(), info.getHandle()));
        
        MenuButton retweetBtn = new MenuButton();
        Image retweetImage = new Image("images/ic_retweet.png", 20, 20, true, false);
        retweetBtn.setGraphic(new ImageView(retweetImage));
        MenuItem rt = new MenuItem("Retweet");
        MenuItem rtWMsg = new MenuItem("Retweet with message");
        rt.setOnAction( (event) -> {
            try {
                engine.retweet(info.getTweetID());
            } catch (TwitterException ex) {
                LOG.error("Can't get tweet id");
            }
        } );
        rtWMsg.setOnAction(new RetweetHandler(info.getTweetID(), info.getHandle()));
        retweetBtn.getItems().addAll(rt, rtWMsg);
        
        Button likeBtn = new Button();
        String url = info.isLiked() ? "images/ic_heart_full.png" : "images/ic_heart_empty.png";
        Image likeImage = new Image(url, 20, 20, true, false);
        likeBtn.setGraphic(new ImageView(likeImage)); 
        likeBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (info.isLiked()){
                try {
                    engine.unLikeTweet(info.getTweetID());
                } catch (TwitterException ex) {
                    LOG.error("Can't get tweet id");
                }
                likeBtn.setGraphic(new ImageView(new Image("images/ic_heart_empty.png", 20, 20, true, false)));
            }
            else {
                try {
                    engine.likeTweet(info.getTweetID());
                } catch (TwitterException ex) {
                    LOG.error("Can't get tweet id");
                }
                likeBtn.setGraphic(new ImageView(new Image("images/ic_heart_full.png", 20, 20, true, false)));
            }
        });
        
        HBox buttons = new HBox();
        buttons.setSpacing(100);
        buttons.getChildren().addAll(commentBtn, retweetBtn, likeBtn);
        

        HBox nameDate = new HBox();
        nameDate.getChildren().addAll(name, date);
        nameDate.setSpacing(10);
        
        Text id = new Text(""+info.getTweetID());
        LOG.debug("ID = : "+ info.getTweetID());
        id.setWrappingWidth(200);
        
        Button saveTweetBtn = new Button("Save Tweet");
        saveTweetBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, new SaveTweetHandler(saveTweetBtn, info));


        VBox vbox = new VBox();
        vbox.getChildren().addAll(nameDate, text, buttons, id);
        node.getChildren().addAll(imageView, vbox, saveTweetBtn);
        
        return node;
    }
}
