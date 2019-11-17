/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.tests;

import com.evangreenstein.twitter_client.business.TwitterEngine;
import com.evangreenstein.twitter_client.data.StatusData;
import com.evangreenstein.twitter_client.data.TwitterInfo;
import com.evangreenstein.twitter_client.persistence.TweetDAO;
import com.evangreenstein.twitter_client.persistence.TweetDAOImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import twitter4j.Status;

/**
 *
 * @author evangreenstein
 */
public class TestDatabase {
    private final static Logger LOG = LoggerFactory.getLogger(TestDatabase.class);
    
    //Normally the connection variables are loaded from a properties file but since
    //this for a unit test i'm hard coding the values
    private final String url = "jdbc:mysql://localhost:3306/TWITTERTEST?serverTimezone=UTC";
    private final String user = "test";
    private final String password = "dawson";
    
    @Mock
    private Status statusMock;
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    
    @Before
    public void seedDatabase() {
        LOG.info("Seeding database");
        final String seedDataScript = loadAsString("createTestTwitterTables.sql");
        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            for (String statement : splitStatements(new StringReader(seedDataScript), ";")) {
                connection.prepareStatement(statement).execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed seeding database", e);
        }
    }
    
    @Ignore
    @Test
    public void testStoreTweet() throws IOException, ParseException{
        LOG.info("Test store tweet");
        when(statusMock.getId()).thenReturn(123456789L);
        
        when(statusMock.getUser().getName()).thenReturn("The User");
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = format.parse("2019-11-09 12:34:56");
        Date sqlDate = new Date(date.getTime());
        when(statusMock.getCreatedAt()).thenReturn(sqlDate);
        
        when(statusMock.getText()).thenReturn("aufegbegughvubvuewuo");
        
        when(statusMock.getUser().getProfileImageURL()).thenReturn("data:image/jpeg;base64,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");
        
        when(statusMock.isFavorited()).thenReturn(true);
        
        TwitterInfo info = new TwitterInfo(statusMock);
        TweetDAO tweetDao = new TweetDAOImpl("testDatabase.properties");
        
        int result = tweetDao.storeTweet(info);
        
        assertEquals(1, result);
    }
    
    @Ignore
    @Test
    public void testGetTweetById() throws IOException, ParseException{
        LOG.info("Test get tweet by id");
        TweetDAO tweetDao  = new TweetDAOImpl("testDatabase.properties");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = format.parse("2019-11-09 12:34:56");
        StatusData statusExpected = new StatusData(34532643, "Evan Greenstein", new java.sql.Date(date.getTime()), "Testing 1 2 3", true );
        
        StatusData statusResult = tweetDao.getTweetFromDB(34532643);
        
        assertEquals(statusExpected, statusResult);
        
    }
    
    @Ignore
    @Test
    public void testRetrieveSavedTweets() throws IOException, ParseException{
        LOG.info("Test retrieve saved tweets");
        TweetDAO tweetDao  = new TweetDAOImpl("testDatabase.properties");
        ObservableList<StatusData> savedTweetsExpected = FXCollections.observableArrayList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date1 = format.parse("2019-11-09 12:34:56");
        java.util.Date date2 = format.parse("2019-11-11 16:52:28");
        savedTweetsExpected.add( new StatusData(34532643, "Evan Greenstein", new java.sql.Date(date1.getTime()), "Testing 1 2 3", true ));
        savedTweetsExpected.add(new StatusData(49867839, "Mikey1891", new java.sql.Date(date2.getTime()), "Moose caboose", false ));
        
        ObservableList<StatusData> savedTweetsResult = tweetDao.retrieveSavedTweets();
        
        assertEquals(savedTweetsExpected, savedTweetsResult);
    }
    
    private String loadAsString(final String path) {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
                Scanner scanner = new Scanner(inputStream)) {
            return scanner.useDelimiter("\\A").next();
        } catch (IOException e) {
            throw new RuntimeException("Unable to close input stream.", e);
        }
    }

    private List<String> splitStatements(Reader reader, String statementDelimiter) {
        final BufferedReader bufferedReader = new BufferedReader(reader);
        final StringBuilder sqlStatement = new StringBuilder();
        final List<String> statements = new LinkedList<>();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || isComment(line)) {
                    continue;
                }
                sqlStatement.append(line);
                if (line.endsWith(statementDelimiter)) {
                    statements.add(sqlStatement.toString());
                    sqlStatement.setLength(0);
                }
            }
            return statements;
        } catch (IOException e) {
            throw new RuntimeException("Failed parsing sql", e);
        }
    }

    private boolean isComment(final String line) {
        return line.startsWith("--") || line.startsWith("//") || line.startsWith("/*");
    }
}
