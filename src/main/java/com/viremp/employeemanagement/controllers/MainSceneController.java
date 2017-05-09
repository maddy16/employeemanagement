/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import com.viremp.employeemanagement.MainApp;

/**
 * FXML Controller class
 *
 * @author Orthodox Computers
 */
public class MainSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox sideBar;
    @FXML
    private AnchorPane contentPane;
    ResourceBundle bundle;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bundle = rb;
    }

    @FXML
    void settingsBtnClicked(ActionEvent event) throws IOException {
        toggleButton(event);
        resetContentArea();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SettingsScene.fxml"),bundle);
        Parent root = loader.load();
        fitParentAnchor(root);
        contentPane.getChildren().add(root);

    }
    
    void toggleButton(ActionEvent event) {
        resetSideButtons();
        JFXButton btn = (JFXButton) event.getSource();
        btn.setStyle("-fx-background-color: #2196F3;");
    }
    void resetSideButtons() {
        sideBar.getChildren().forEach((child) -> {
            child.setStyle("-fx-background-color:  #5C6BC0;");
        });
    }
    void resetContentArea() {
        contentPane.getChildren().clear();
    }
    void fitParentAnchor(Parent root) {
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
    }

}
