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
import javafx.concurrent.Task;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Orthodox Computers
 */
public class MainSceneController implements Initializable {

    public static MainSceneController instance;
    @FXML
    private VBox sideBar;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private Label error;
    ResourceBundle bundle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bundle = rb;
        instance = this;
    }

    public void loadEmployeesView() throws IOException{
        MainApp.loadScene("/fxml/EmployeeScene.fxml", null, contentPane, bundle);
    }
    @FXML
    void settingsBtnClicked(ActionEvent event) throws IOException {
        toggleButton(event);
        MainApp.loadScene("/fxml/SettingsScene.fxml", null, contentPane, bundle);
    }

    @FXML
    void employeesBtnClicked(ActionEvent event) throws IOException{
        toggleButton(event);
        loadEmployeesView();
    }

    void toggleButton(ActionEvent event) {
        resetSideButtons();
        JFXButton btn = (JFXButton) event.getSource();
        btn.setStyle("-fx-background-color: #4CAF50;");
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

    public void showError(String msg) {
        error.getStyleClass().remove("success");
        error.getStyleClass().add("error");
        error.setText(msg);
        error.setVisible(true);
    }

    public void showSuccess(String msg) {
        error.getStyleClass().add("success");
        error.getStyleClass().remove("error");
        error.setText(msg);
        error.setVisible(true);
        final Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                Thread.sleep(1000);
                return null;
            }
            
        };
        task.setOnSucceeded((event) -> {
            error.setVisible(false);
            
        });
        new Thread(task).start();
    }

    
    public void hideError() {
        error.setText("");
        error.setVisible(false);
    }

}
