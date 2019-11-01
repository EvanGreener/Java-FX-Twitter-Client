/**
 * Contains Java FX controller classes
 */
package com.evangreenstein.twitter_client.controller;

import com.evangreenstein.twitter_client.business.PropertiesManager;
import com.evangreenstein.twitter_client.data.T4jPropertiesFXBean;
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
import javafx.beans.binding.Bindings;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterKeysFormFXMLController {
    
    private final static Logger LOG = LoggerFactory.getLogger(TwitterKeysFormFXMLController.class);

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
    private TextField aTSecretField; // Value injected by FXMLLoader

    @FXML // fx:id="generatePropBtn"
    private Button generatePropBtn; // Value injected by FXMLLoader

    @FXML // fx:id="errorMsgLbl"
    private Label errorMsgLbl; // Value injected by FXMLLoader
    
    private Scene nextScene;
    private Stage stage;
    private MainFXMLController mainController;
    private PropertiesManager pm = new PropertiesManager();
    private T4jPropertiesFXBean t4jPropsBean;
    
    public TwitterKeysFormFXMLController(){
        t4jPropsBean = new T4jPropertiesFXBean();
    }
    
    /**
     * By passing a reference to the stage object, the next scene and it's controller, 
     * this controller can switch to the next scene once the user properly submits the form
     * 
     * @param scene
     * @param stage
     * @param mainController 
     */
    public void setSceneStageController(Scene scene, Stage stage, MainFXMLController mainController){
        this.nextScene = scene;
        this.stage = stage;
        this.mainController = mainController;
    }
    
    /**
     * This event is triggered when the user presses the "Connect to twitter button".
     * It will only generate the properties file once the user has entered all the 4
     * keys. Then it switches to the next scene
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    void genTwitterProps(ActionEvent event) throws IOException {
        if (cKeyField.getText().isBlank() || cSecretField.getText().isBlank()
                || aTokenField.getText().isBlank()|| aTSecretField.getText().isBlank() ){
            LOG.debug("At least one of the fields not entered");
            
            errorMsgLbl.setText("At least one of the fields has not been entered. Please enter all of them.");
        }
        else{
            LOG.debug("Writing the properties file");
            pm.writeProperties(t4jPropsBean);
            stage.setScene(nextScene);
        }
    }

    /**
     * Initializes form and binds the form's fields to the keys of the properties FX bean 
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cKeyField != null : "fx:id=\"cKeyField\" was not injected: check your FXML file 'TwitterKeysFormFXML.fxml'.";
        assert cSecretField != null : "fx:id=\"cSecretField\" was not injected: check your FXML file 'TwitterKeysFormFXML.fxml'.";
        assert aTokenField != null : "fx:id=\"aTokenField\" was not injected: check your FXML file 'TwitterKeysFormFXML.fxml'.";
        assert aTSecretField != null : "fx:id=\"aTSecretzfield\" was not injected: check your FXML file 'TwitterKeysFormFXML.fxml'.";
        assert generatePropBtn != null : "fx:id=\"generatePropBtn\" was not injected: check your FXML file 'TwitterKeysFormFXML.fxml'.";
        assert errorMsgLbl != null : "fx:id=\"errorMsgLbl\" was not injected: check your FXML file 'TwitterKeysFormFXML.fxml'.";
        
        t4jPropsBean = new T4jPropertiesFXBean();

        Bindings.bindBidirectional(cKeyField.textProperty(), t4jPropsBean.cKeyProp());
        Bindings.bindBidirectional(cSecretField.textProperty(), t4jPropsBean.cSecretProp());
        Bindings.bindBidirectional(aTokenField.textProperty(), t4jPropsBean.aTokenProp());
        Bindings.bindBidirectional(aTSecretField.textProperty(), t4jPropsBean.aTSecretProp());

        
        
        
    }
}
