/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evangreenstein.twitter_client;

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
import static java.nio.file.Paths.get;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Evan Greenstein
 */
public class MainApp extends Application {
    
    
    private Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        
        this.stage = primaryStage;
        
        try {
            
            Scene mainScene = createMainScene();
            Scene propertiesScene = createPropertiesScene();
                    
            Properties prop = new Properties();
            Path twitter4j = get("/src/main/resources", "twitter4j.properties");
            if (Files.exists(twitter4j)){
                
                this.stage.setScene(mainScene);
                
                try (InputStream propFileStream = Files.newInputStream(twitter4j);){
                    prop.load(propFileStream);
                    
                }
            }
            else{
                this.stage.setScene(propertiesScene);
                
            }
            
            stage.setTitle("Twitter Client");
            stage.show();
            
            
        } catch (IOException | IllegalStateException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            // See code samples for displaying an Alert box if an exception is thrown
        }

    }
    
    
    private Scene createMainScene() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainFXML.fxml")); 
        Parent root = loader.load();
        MainFXMLController controller = loader.getController();
        Scene scene = new Scene(root);
        return scene;
    }
    
    private Scene createPropertiesScene() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TwitterKeysFormFXML.fxml")); 
        Parent root = loader.load();
        MainFXMLController controller = loader.getController();
        Scene scene = new Scene(root);
        return scene;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
