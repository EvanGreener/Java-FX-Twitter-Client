/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.persistence;

import com.evangreenstein.twitter_client.data.TwitterInfo;
import com.evangreenstein.twitter_client.data.StatusData;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.Paths.get;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Properties;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author evangreenstein
 */
public class TweetDAOImpl implements TweetDAO{

    private final static Logger LOG = LoggerFactory.getLogger(TweetDAOImpl.class);
    
    private String url;
    private String user;
    private String password;
    
    public TweetDAOImpl(String propsFileName) throws IOException{
        //Loading database properties
        Properties prop= new Properties();
        
        String rootDir= Paths.get("").toAbsolutePath().toString();
        Path dbProps = get(String.format("%s/src/main/resources", rootDir), propsFileName);
        
        if (Files.exists(dbProps)){
            try (InputStream propFileStream = Files.newInputStream(dbProps);){
                prop.load(propFileStream);
                
                url = prop.getProperty("Url");
                user = prop.getProperty("User");
                password = prop.getProperty("Password");
            }
        }
    }
    
    /**
     * 
     * Stores a tweet into the database
     * 
     * @param info
     * @return 
     */
    @Override
    public int storeTweet(TwitterInfo info) {
        String insertTweet = "INSERT INTO status (id, name, date_created, text, profile_image, is_liked)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
        int result = -1;
        
        try (Connection connection = DriverManager.getConnection(url, user,
                password);){
            
            PreparedStatement ps = connection.prepareStatement(insertTweet);
            ps.setLong(1, info.getTweetID());
            ps.setString(2, info.getName());
            ps.setDate(3, new java.sql.Date(info.getDateCreated().getTime()));
            ps.setString(4, info.getText());
            ps.setBytes(5, convertImageToBytes(info.getImageURL()));
            ps.setBoolean(6, info.isLiked());
            
            return ps.executeUpdate();
            
        }
        catch (SQLException ex) {
            LOG.error(ex.fillInStackTrace().toString());
            ex.printStackTrace();
        } catch (Exception ex) { 
            LOG.error(ex.fillInStackTrace().toString());
            ex.printStackTrace();
        }
        
        return result;
    }
      
    // Converts an image taken from an online URL into an array of bytes
    //
    // Code was taken from https://binarycoders.dev/2015/04/21/image-url-to-byte-array/
    private byte[] convertImageToBytes(String urlText) throws Exception {
        URL url = new URL(urlText);
         
        try (ByteArrayOutputStream output = new ByteArrayOutputStream();
                InputStream inputStream = url.openStream()) {
            int n = 0;
            byte [] buffer = new byte[ 1024 ];
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            return output.toByteArray();
        }
    }

    @Override
    public StatusData getTweetFromDB(long id) {
        String getTweetQuery = "SELECT * FROM status WHERE id = ?";
        
        StatusData status = null;
        
        try (Connection connection = DriverManager.getConnection(url, user,
                password);){
            
            PreparedStatement ps = connection.prepareStatement(getTweetQuery);
            ps.setLong(0, id);
            
            try (ResultSet rs = ps.executeQuery()){
                
                if (rs.next()){
                    status =  new StatusData(rs.getLong("id"), rs.getString("name"), rs.getDate("date_created"), 
                            rs.getString("text"), rs.getBytes("profile_image"),rs.getString("handle"),
                            rs.getBoolean("is_liked"));
                    return status;
                }
            } catch (IOException ex) {
                LOG.error(ex.getLocalizedMessage());
            }
            
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        return status;
    }

    @Override
    public ObservableList<StatusData> retrieveSavedTweets() {
        
        String getTweetQuery = "SELECT * FROM status";
        
        ObservableList<StatusData> savedTweets = FXCollections.observableArrayList();
        
        try (Connection connection = DriverManager.getConnection(url, user,
                password);){
            
            PreparedStatement ps = connection.prepareStatement(getTweetQuery);
            
            try (ResultSet rs = ps.executeQuery()){
                
                while (rs.next()){
                    savedTweets.add( new StatusData(rs.getLong("id"), rs.getString("name"), rs.getDate("date_created"), 
                            rs.getString("text"), rs.getBytes("profile_image"),rs.getString("handle"),
                            rs.getBoolean("is_liked"))) ;
                    
                }
            } catch (IOException ex) {
                LOG.error(ex.getLocalizedMessage());
            }
            
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        return savedTweets;
    }
    
    
}
