/**
 * Sample Skeleton for 'TwitterKeysFormFXML.fxml' Controller Class
 */
package com.evangreenstein.twitter_client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    
    @FXML
    void genTwitterProps(ActionEvent event) {
        if ("".equals(cKeyField.getText()) || "".equals(cSecretField.getText()) 
                || "".equals(aTokenField.getText()) || "".equals(aTSecretzfield.getText())){
            
            
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
