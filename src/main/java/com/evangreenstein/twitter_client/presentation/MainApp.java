/*
 * Containts only one class whose responsibilty is presentation
 */
package com.evangreenstein.twitter_client.presentation;

import com.evangreenstein.twitter_client.business.PropertiesManager;
import com.evangreenstein.twitter_client.controller.*;
import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.Paths.get;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Evan Greenstein
 */
public class MainApp extends Application {
    
    private final static Logger LOG = LoggerFactory.getLogger(MainApp.class);
    
    private Stage stage;
    private MainFXMLController mainCtrl;
    private PropertiesManager pm = new PropertiesManager();
    
    @Override
    public void start(Stage primaryStage) {
        
        this.stage = primaryStage;
        
        try {
            
            Scene mainScene = createMainScene();
            Scene propertiesScene = createPropertiesScene(mainScene);
            
            if (pm.loadProperties()){
                LOG.info("Entering main scene");
                this.stage.setScene(mainScene);
            }
            else{
                LOG.info("Entering properties scene");
                this.stage.setScene(propertiesScene);
                
            }
            
            stage.setTitle("Twitter Client");
            stage.show();
            
            
        } catch (IOException | IllegalStateException ex) {
            
            
        }

    }
    
    
    private Scene createMainScene() throws IOException{
        LOG.info("creating main scene");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainFXML.fxml")); 
        Parent root = loader.load();
        mainCtrl = loader.getController();
        Scene scene = new Scene(root);
        return scene;
    }
    
    private Scene createPropertiesScene(Scene scene2) throws IOException{
        LOG.info("creating properties scene and passing required variables into controller");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TwitterKeysFormFXML.fxml")); 
        Parent root = loader.load();
        LOG.info("getting controller ");
        TwitterKeysFormFXMLController controller = loader.getController();
        controller.setSceneStageController(scene2, stage, mainCtrl );
        Scene scene = new Scene(root);
        LOG.info("end");
        return scene;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
