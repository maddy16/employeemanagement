/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.viremp.employeemanagement.MainApp;
import com.viremp.employeemanagement.db.DatabaseHandler;
import com.viremp.employeemanagement.forms.models.EmployeeAddForm;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
    private ComboBox<String> bloodGpCombo;

    public ComboBox<String> getBloodGpCombo() {
        return bloodGpCombo;
    }

    @FXML
    private ComboBox<String> leavesCombo;

    public ComboBox<String> getLeavesCombo() {
        return leavesCombo;
    }
    @FXML
    private ComboBox<String> maritalCombo;

    public ComboBox<String> getMaritalCombo() {
        return maritalCombo;
    }
    private EmployeeAddForm addForm;

    @FXML
    private JFXListView<String> intCourseList;

    public JFXListView<String> getIntCourseList() {
        return intCourseList;
    }

    @FXML
    private TextField fineNameField;

    @FXML
    private JFXButton deleteFineBtn;
    @FXML
    private JFXListView<String> finesList;

    @FXML
    private TextField intCourseField;


    @FXML
    private TextField extCourseField;

    @FXML
    private JFXListView<String> extCoursesList;

    @FXML
    private JFXButton deleteExtCourseBtn;

    public TextField getExtCourseField() {
        return extCourseField;
    }

    public JFXListView<String> getExtCoursesList() {
        return extCoursesList;
    }

    public JFXButton getDeleteExtCourseBtn() {
        return deleteExtCourseBtn;
    }

    public TextField getIntCourseField() {
        return intCourseField;
    }


    public JFXButton getDeleteIntCourseBtn() {
        return deleteIntCourseBtn;
    }

    @FXML
    private JFXButton deleteIntCourseBtn;

    public JFXListView<String> getFinesList() {
        return finesList;
    }

    public TextField getFineNameField() {
        return fineNameField;
    }

    public JFXButton getDeleteFineBtn() {
        return deleteFineBtn;
    }

    @FXML
    private ComboBox<String> rankCombo;

    public ComboBox<String> getRankCombo() {
        return rankCombo;
    }
    @FXML
    private ComboBox<String> relComboAddForm;

    @FXML
    private ComboBox<String> natComboAddForm;
    private ResourceBundle bundle;

    public ResourceBundle getBundle() {
        return bundle;
    }

    public ComboBox<String> getRelComboAddForm() {
        return relComboAddForm;
    }

    public ComboBox<String> getNatComboAddForm() {
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
    void addNewFineClicked(ActionEvent event) {
        addForm.addNewFine();
    }


    @FXML
    void setPicClicked(ActionEvent event) {
        addForm.chooseEmployeePic();
    }

    private void initAddForm() {
        addForm = new EmployeeAddForm(this);
    }

    @FXML
    void setFineImageClicked(ActionEvent event) {
        addForm.uploadFineImage();
    }

    @FXML
    void setIntCImageClicked(ActionEvent event) {
        addForm.uploadIntCourseImage();
    }
    @FXML
    void setExtCImageClicked(ActionEvent event) {
        addForm.uploadExtCourseImage();
    }

    @FXML
    void deleteFineClicked(ActionEvent event) {
        addForm.removeSelectedFine();
    }


    @FXML
    void keyTypedOnNationality(KeyEvent event) {
        addForm.checkEnteredNationality(natComboAddForm.editorProperty().get().getText());
    }

    @FXML
    void addEmpBtnClicked(ActionEvent event) {
        addForm.processFines();
    }

    @FXML
    void addIntCourse(ActionEvent event) {
        addForm.addNewIntCourse();
    }

    @FXML
    void addExtCourse(ActionEvent event) {
        addForm.addNewExtCourse();
    }

    @FXML
    void deleteIntCourse(ActionEvent event) {
        addForm.removeSelectedIntCourse();
    }

    @FXML
    void deleteExtCourse(ActionEvent event) {
        addForm.removeSelectedExtCourse();
    }

}
