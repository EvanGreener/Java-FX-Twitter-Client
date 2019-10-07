/**
 * Sample Skeleton for 'TwitterKeysFormFXML.fxml' Controller Class
 */
package com.evangreenstein.twitter_client.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.Paths.get;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static java.nio.file.Files.newOutputStream;
import java.nio.file.Paths;


public class TwitterKeysFormFXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cKeyField"
    private TextField cKeyField; // Value injected by FXMLLoader

    @FXML // fx:id="cSecretField"
    private TextField cSecretField; // Value injected by FXMLLoader

    @FXML // fx:id="aTokenField"
    private TextField aTokenField; // Value injected by FXMLLoader

    @FXML // fx:id="aTSecretzfield"
    private TextField aTSecretzfield; // Value injected by FXMLLoader

    @FXML // fx:id="generatePropBtn"
    private Button generatePropBtn; // Value injected by FXMLLoader

    @FXML // fx:id="errorMsgLbl"
    private Label errorMsgLbl; // Value injected by FXMLLoader
    
    private Scene nextScene;
    private Stage stage;
    private MainFXMLController mainController;
    
    
    public void setSceneStageController(Scene scene, Stage stage, MainFXMLController mainController){
        this.nextScene = scene;
        this.stage = stage;
        this.mainController = mainController;
    }
    
    @FXML
    void genTwitterProps(ActionEvent event) throws IOException {
        if ("".equals(cKeyField.getText()) || "".equals(cSecretField.getText()) 
                || "".equals(aTokenField.getText()) || "".equals(aTSecretzfield.getText())){
            
            errorMsgLbl.setText("At least one of the fields has not been entered. Please enter all of them.");
        }
        else{
            
            Properties prop = new Properties();
            
            prop.setProperty("oauth.consumerKey", cKeyField.getText());
            prop.setProperty("oauth.consumerSecret", cSecretField.getText());
            prop.setProperty("oauth.accessToken", aTokenField.getText());
            prop.setProperty("oauth.accessTokenSecret", aTSecretzfield.getText());
            
            String rootDir= Paths.get("").toAbsolutePath().toString();
            
            Path twitter4j = get(rootDir + "/src/main/resources", "twitter4j.properties");
            
            try (OutputStream propFileStream = newOutputStream(twitter4j)){
                prop.store(propFileStream, "Twitter4j properties");
            }
            
            stage.setScene(nextScene);
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cKeyField != null : "fx:id=\"cKeyField\" was not injected: check your FXML file 'TwitterKeysFormFXML.fxml'.";
        assert cSecretField != null : "fx:id=\"cSecretField\" was not injected: check your FXML file 'TwitterKeysFormFXML.fxml'.";
        assert aTokenField != null : "fx:id=\"aTokenField\" was not injected: check your FXML file 'TwitterKeysFormFXML.fxml'.";
        assert aTSecretzfield != null : "fx:id=\"aTSecretzfield\" was not injected: check your FXML file 'TwitterKeysFormFXML.fxml'.";
        assert generatePropBtn != null : "fx:id=\"generatePropBtn\" was not injected: check your FXML file 'TwitterKeysFormFXML.fxml'.";
        assert errorMsgLbl != null : "fx:id=\"errorMsgLbl\" was not injected: check your FXML file 'TwitterKeysFormFXML.fxml'.";

    }
}
