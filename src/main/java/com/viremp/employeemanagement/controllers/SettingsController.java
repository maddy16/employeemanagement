package com.viremp.employeemanagement.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.viremp.employeemanagement.MainApp;

/**
 * FXML Controller class
 *
 * @author Orthodox Computers
 */
public class SettingsController implements Initializable {

    @FXML
    private JFXComboBox<String> languageCombo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillLanguageCombo();
    }    
    void fillLanguageCombo(){
        languageCombo.getItems().add("English");
        languageCombo.getItems().add("عربى");
        if(com.viremp.employeemanagement.MainApp.englishSelected)
            languageCombo.getSelectionModel().select(0);
        else
            languageCombo.getSelectionModel().select(1);
    }
      @FXML
    void languageChanged(ActionEvent event) throws Exception {
        int selectedIndex = languageCombo.getSelectionModel().getSelectedIndex();
            MainApp.changeLocale(selectedIndex);
        
    }
    
}
