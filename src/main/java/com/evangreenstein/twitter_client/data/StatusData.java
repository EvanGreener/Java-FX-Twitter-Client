/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.data;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 * A slightly modified version of the TwitterInfo class. For database use
 * 
 */
public class StatusData {
    
    private LongProperty tweetIdProp;
    private StringProperty nameProp;
    private ObjectProperty dateCreatedProp;
    private StringProperty textProp;
    private ObjectProperty imageProp;
    private BooleanProperty isLikedProp;
   
    public StatusData(long tweetId, String name, Date dateCreated, String text, 
            byte[] imageData,  boolean isLiked) throws IOException{
        this.tweetIdProp = new SimpleLongProperty(tweetId);
        this.nameProp = new SimpleStringProperty(name);
        this.dateCreatedProp = new SimpleObjectProperty(dateCreated);
        this.textProp = new SimpleStringProperty(text);
        this.imageProp = new SimpleObjectProperty(convertBytesToImage(imageData));
        this.isLikedProp = new SimpleBooleanProperty(isLiked);
    }

    public StatusData(long tweetId, String name, Date dateCreated, String text, boolean isLiked) throws IOException{
        this.tweetIdProp = new SimpleLongProperty(tweetId);
        this.nameProp = new SimpleStringProperty(name);
        this.dateCreatedProp = new SimpleObjectProperty(dateCreated);
        this.textProp = new SimpleStringProperty(text);
        this.imageProp = new SimpleObjectProperty(null);
        this.isLikedProp = new SimpleBooleanProperty(isLiked);
    }
    
    public Date getDateCreated(){
        return (Date) dateCreatedProp.get();
    }
    
    public void setDateCreated(Date date){
        dateCreatedProp.set(date);
    }
    
    public ObjectProperty dateCreatedProp() {
        return dateCreatedProp;
    }
    
    
    public WritableImage getImage(){
        return (WritableImage) imageProp.get();
    }
    
    public void setImage(Image image){
        imageProp.set(image);
    }

    public ObjectProperty imageProp() {
        return imageProp;
    }

    public boolean getIsLiked(){
        return isLikedProp.get();
    }
    
    public void setIsLiked(boolean isLiked){
        isLikedProp.set(isLiked);
    }
    
    public BooleanProperty isLikedProp() {
        return isLikedProp;
    }

    public String getName(){
        return nameProp.get();
    }
    
    public void setName(String name){
        nameProp.set(name);
    }
    
    public StringProperty nameProp() {
        return nameProp;
    }

    public String getText(){
        return textProp.get();
    }
    
    public void setText(String text){
        textProp.set(text);
    }
    
    public StringProperty textProp() {
        return textProp;
    }

    public long getTweetId(){
        return tweetIdProp.get();
    }
    
    public void setTweetId(long tweetId){
        tweetIdProp.set(tweetId);
    }
    
    public LongProperty tweetIdProp() {
        return tweetIdProp;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof StatusData){
            StatusData statusData2 = (StatusData)o;
            return this.getTweetId() == statusData2.getTweetId() &&
                    this.getName().equals(statusData2.getName()) &&
                    this.getDateCreated().equals(statusData2.getDateCreated()) &&
                    this.getText().equals(statusData2.getText()) &&
                    this.getIsLiked() == statusData2.getIsLiked();
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.tweetIdProp);
        hash = 67 * hash + Objects.hashCode(this.nameProp);
        hash = 67 * hash + Objects.hashCode(this.dateCreatedProp);
        hash = 67 * hash + Objects.hashCode(this.textProp);
        hash = 67 * hash + Objects.hashCode(this.imageProp);
        hash = 67 * hash + Objects.hashCode(this.isLikedProp);
        return hash;
    }
    
    /**
     * 
     * Converts an array of bytes to an image
     * 
     * @param imageData
     * @return
     * @throws IOException 
     */
    private WritableImage convertBytesToImage(byte[] imageData) throws IOException {
        WritableImage writableImage = null;

        InputStream in = new ByteArrayInputStream(imageData);
        BufferedImage bufferedImage = ImageIO.read(in);

        if (bufferedImage != null) {
            writableImage = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
            PixelWriter pw = writableImage.getPixelWriter();
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    pw.setArgb(x, y, bufferedImage.getRGB(x, y));
                }
            }
        }
        return writableImage;
    }
    
    
    
}
