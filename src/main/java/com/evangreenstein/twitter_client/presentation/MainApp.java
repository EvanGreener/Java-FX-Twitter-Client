/*
 * Contains the JavaFX Main class
 */
package com.evangreenstein.twitter_client.presentation;

import com.evangreenstein.twitter_client.business.PropertiesManager;
import com.evangreenstein.twitter_client.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Sets up all the scenes. Directs the user to the properties form if t
 * 
 */
public class MainApp extends Application {
    
    private final static Logger LOG = LoggerFactory.getLogger(MainApp.class);
    
    private Stage stage;
    private MainFXMLController mainCtrl;
    private final PropertiesManager pm = new PropertiesManager();
    
    /**
     * Directs the user to the properties form if the twitter4j properties
     * file isn't complete, otherwise it direct the user straight to the main
     * Twitter scene
     * @param primaryStage 
     */
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
    
    /**
     * Creates and returns the main twitter scene. Also gets a reference to the
     * main controller which is needed by the properties form controller.
     * 
     * @return The main scene
     * @throws IOException 
     */
    private Scene createMainScene() throws IOException{
        LOG.info("creating main scene");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainFXML.fxml"), 
                ResourceBundle.getBundle("MessagesBundle")); 
        Parent root = loader.load();
        mainCtrl = loader.getController();
        Scene scene = new Scene(root);
        return scene;
    }
    
    /**
     * Creates and returns the properties form scene. The properties scene controller 
     * has a method that passes in the main twitter scene, the stage and the twitter 
     * controller.
     * 
     * @param mainScene
     * @return The properties form scene
     * @throws IOException 
     */
    private Scene createPropertiesScene(Scene mainScene) throws IOException{
        LOG.info("creating properties scene and passing required variables into controller");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TwitterKeysFormFXML.fxml"),
            ResourceBundle.getBundle("MessagesBundle")); 
        Parent root = loader.load();
        LOG.info("getting controller ");
        TwitterKeysFormFXMLController controller = loader.getController();
        controller.setSceneStageController(mainScene, stage, mainCtrl );
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
