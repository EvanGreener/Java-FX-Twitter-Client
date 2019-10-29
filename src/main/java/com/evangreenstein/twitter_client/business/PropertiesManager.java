
package com.evangreenstein.twitter_client.business;

import com.evangreenstein.twitter_client.data.T4jPropertiesFXBean;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.Paths.get;
import static java.nio.file.Files.newOutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The properties manager is responsible for loading the twitter4j properties file if there is one
 * and for generating   
 * 
 */
public class PropertiesManager {
    
    private final static Logger LOG = LoggerFactory.getLogger(PropertiesManager.class);
    
    /**
     * If the properties file exists in the src/main/resources/ directory, it will 
     * load the file otherwise false will be returned.
     * 
     * @return true if the properties file exists, false otherwise
     * @throws IOException 
     */
    public boolean loadProperties() throws IOException{
        LOG.info("Loading Properties");
        Properties prop= new Properties();
        
        String rootDir= Paths.get("").toAbsolutePath().toString();
        Path twitter4j = get(String.format("%s/src/main/resources", rootDir), "twitter4j.properties");
        
        boolean exists = false;
        if (Files.exists(twitter4j)){
            try (InputStream propFileStream = Files.newInputStream(twitter4j);){
                prop.load(propFileStream);

            }
            exists = true;
        }
        return exists;
    }
    
    /**
     * Creates the properties file and writes the keys and their values into the file
     * 
     * @param t4j
     * @throws IOException 
     */
    public void writeProperties(final T4jPropertiesFXBean t4j) throws IOException {
        
        Properties prop= new Properties();            

        prop.setProperty("oauth.consumerKey", t4j.getCKey());
        prop.setProperty("oauth.consumerSecret", t4j.getCSecret());
        prop.setProperty("oauth.accessToken", t4j.getAToken() );
        prop.setProperty("oauth.accessTokenSecret", t4j.getATSecret());

        String rootDir= Paths.get("").toAbsolutePath().toString();
        LOG.debug(rootDir);

        Path twitter4j = get(String.format("%s/src/main/resources", rootDir), "twitter4j.properties");

        try (OutputStream propFileStream = newOutputStream(twitter4j)){
            prop.store(propFileStream, "Twitter4j properties");
        }
    }
}
