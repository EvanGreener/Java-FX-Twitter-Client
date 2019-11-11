/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client.tests;

import com.evangreenstein.twitter_client.business.TwitterEngine;
import com.evangreenstein.twitter_client.persistence.TweetDAO;
import com.evangreenstein.twitter_client.persistence.TweetDAOImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 *
 * @author evangreenstein
 */
public class TestDatabase {
    private final static Logger LOG = LoggerFactory.getLogger(TestDatabase.class);
    
    //Normally the connection variables are loaded from a properties file but since
    //this for a unit test i'm hard coding the values
    private final String url = "jdbc:mysql://localhost:3306/TWITTER?serverTimezone=UTC";
    private final String user = "evang";
    private final String password = "dawson";
    private final TweetDAO tweetDAO;
    private final Twitter twitter;
    
    public TestDatabase() throws IOException{
        tweetDAO = new TweetDAOImpl("testDatabase.properties");
        twitter = TwitterFactory.getSingleton();
                
    }
    
    @Before
    public void seedDatabase() {
        LOG.info("Seeding");
        final String seedDataScript = loadAsString("createTestTwitterDBs.sql");
        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            for (String statement : splitStatements(new StringReader(seedDataScript), ";")) {
                connection.prepareStatement(statement).execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed seeding database", e);
        }
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
    
    @Ignore
    @Test
    public void testStoreTweet(){
        fail("Not implemented yet");
    }
    
    @Ignore
    @Test
    public void testGetTweetById(){
        fail("Not implemented yet");
    }
    
    @Ignore
    @Test
    public void testRetrieveSavedTweets(){
        fail("Not implemented yet");
    }
    
    
}
