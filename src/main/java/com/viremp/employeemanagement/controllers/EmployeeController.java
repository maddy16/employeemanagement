/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.viremp.employeemanagement.MainApp;
import com.viremp.employeemanagement.db.DatabaseHandler;
import com.viremp.employeemanagement.forms.models.EmployeeAddForm;
import com.viremp.employeemanagement.models.Country;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Maddy
 */
public class EmployeeController implements Initializable {

    private DatabaseHandler db;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private ImageView newEmpImageView;
    @FXML
    private JFXButton newPicImgBtn;
    @FXML
    private JFXComboBox<String> bloodGpCombo;

    public JFXComboBox<String> getBloodGpCombo() {
        return bloodGpCombo;
    }
    @FXML
    private JFXComboBox<String> maritalCombo;

    public JFXComboBox<String> getMaritalCombo() {
        return maritalCombo;
    }
    private EmployeeAddForm addForm;

    @FXML
    private JFXComboBox<String> rankCombo;

    public JFXComboBox<String> getRankCombo() {
        return rankCombo;
    }
    @FXML
    private JFXComboBox<String> relComboAddForm;

    @FXML
    private JFXComboBox<String> natComboAddForm;
    private ResourceBundle bundle;

    public ResourceBundle getBundle() {
        return bundle;
    }

    public JFXComboBox<String> getRelComboAddForm() {
        return relComboAddForm;
    }

    public JFXComboBox<String> getNatComboAddForm() {
        return natComboAddForm;
    }

    public ImageView getNewEmpImageView() {
        return newEmpImageView;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bundle = rb;
        db = DatabaseHandler.getDatabaseHandler();

    }

    @FXML
    void addNewEmpClicked(ActionEvent event) throws IOException {
        MainApp.loadScene("/fxml/AddEmpForm.fxml", this, contentPane, bundle);
        initAddForm();
    }

    @FXML
    void setPicClicked(ActionEvent event) {
        addForm.chooseEmployeePic();
    }

    private void initAddForm() {
        addForm = new EmployeeAddForm(this);
    }

    @FXML
    void keyTypedOnNationality(KeyEvent event) {
        addForm.checkEnteredNationality(natComboAddForm.editorProperty().get().getText());
    }

}
