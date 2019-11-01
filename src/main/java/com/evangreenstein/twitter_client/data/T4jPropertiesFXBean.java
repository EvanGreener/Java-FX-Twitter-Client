/*
 * Contains classes whose responsibility is storing data
 */
package com.evangreenstein.twitter_client.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The twitter4J properties FX bean. Used for storing input from the properties form
 */
public class T4jPropertiesFXBean {
    
    private final StringProperty cKey;
    private final StringProperty cSecret;
    private final StringProperty aToken;
    private final StringProperty aTSecret;
    
    
    public T4jPropertiesFXBean(final String cKey, final String cSecret, final String aToken, final String aTSecret ){
        this.cKey = new SimpleStringProperty(cKey);
        this.cSecret = new SimpleStringProperty(cSecret);
        this.aToken = new SimpleStringProperty(aToken);
        this.aTSecret = new SimpleStringProperty(aTSecret);
    }

    public T4jPropertiesFXBean() {
        this("", "", "", "");
    }
    
    public String getCKey(){
        return cKey.get();
    }
    
    public String getCSecret(){
        return cSecret.get();
    }
    
    public String getAToken(){
        return aToken.get();
    }
    
    public String getATSecret(){
        return aTSecret.get();
    }
    
    public StringProperty cKeyProp(){
        return cKey;
    }
    
    public StringProperty cSecretProp(){
        return cSecret;
    }
    
    public StringProperty aTokenProp(){
        return aToken;
    }
    
    public StringProperty aTSecretProp(){
        return aTSecret;
    }
    
    @Override
    public String toString(){
        return String.format("cKey: %s, cSecret: %s, aToken: %s, aTSecret %s", cKey, cSecret, aToken, aTSecret);
    }
}
