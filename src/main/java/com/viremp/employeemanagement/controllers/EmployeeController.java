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
import com.viremp.employeemanagement.models.Employee;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maddy
 */
public class EmployeeController implements Initializable {

    private DatabaseHandler db;

    @FXML
    private TextField searchField;
    
    @FXML
    private JFXButton delEmpBtn;

    public JFXButton getDelEmpBtn() {
        return delEmpBtn;
    }
    

    @FXML
    private ComboBox<String> searchByCombo;

    @FXML
    private AnchorPane contentPane;
    @FXML
    private ImageView newEmpImageView;
    @FXML
    private JFXButton newPicImgBtn;

    @FXML
    private TextField svcNumField;
    @FXML
    private TextField nameField;

    @FXML
    private TextField cprField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField mobileField;

    @FXML
    private TextField relContactField;

    @FXML
    private DatePicker dojField;

    @FXML
    private DatePicker lastRankField;

    @FXML
    private JFXButton viewExtImagesBtn;

    @FXML
    private JFXButton viewIntImagesBtn;

    @FXML
    private JFXButton viewFineImagesBtn;

    public TextField getSvcNumField() {
        return svcNumField;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getCprField() {
        return cprField;
    }

    public TextField getAddressField() {
        return addressField;
    }

    public TextField getMobileField() {
        return mobileField;
    }

    public TextField getRelContactField() {
        return relContactField;
    }

    public DatePicker getDojField() {
        return dojField;
    }

    public DatePicker getLastRankField() {
        return lastRankField;
    }

    public TextField getDutyField() {
        return dutyField;
    }

    public TextField getLeaveDaysField() {
        return leaveDaysField;
    }

    @FXML
    private TextField dutyField;

    @FXML
    private TextField leaveDaysField;
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

    public JFXButton getViewExtImagesBtn() {
        return viewExtImagesBtn;
    }

    @FXML
    void viewFineImageClicked(ActionEvent event) {
        addForm.viewFineImage();
    }

    public JFXButton getViewIntImagesBtn() {
        return viewIntImagesBtn;
    }

    public JFXButton getViewFineImagesBtn() {
        return viewFineImagesBtn;
    }

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
    private Label addNewEmployeeLabel;
    
    @FXML
    private JFXButton editEmpBtn;
    
    

    public Label getAddNewEmployeeLabel() {
        return addNewEmployeeLabel;
    }
    @FXML
    private ComboBox<String> relComboAddForm;

    
    @FXML
    private JFXButton addEmpBtn;
    
    
    @FXML
    private JFXButton setFineImgBtn;

    @FXML
    private JFXButton addFineBtn;
    
    @FXML
    private JFXButton setIntCourseImageBtn;

    @FXML
    private JFXButton addIntCourseBtn;
    
    @FXML
    private JFXButton setExtCourseImageBtn;

    @FXML
    private JFXButton addExtCourseBtn;

    public JFXButton getSetFineImgBtn() {
        return setFineImgBtn;
    }

    public JFXButton getAddFineBtn() {
        return addFineBtn;
    }

    public JFXButton getSetIntCourseImageBtn() {
        return setIntCourseImageBtn;
    }

    public JFXButton getAddIntCourseBtn() {
        return addIntCourseBtn;
    }

    public JFXButton getSetExtCourseImageBtn() {
        return setExtCourseImageBtn;
    }

    public JFXButton getAddExtCourseBtn() {
        return addExtCourseBtn;
    }

    public JFXButton getNewPicImgBtn() {
        return newPicImgBtn;
    }

    public JFXButton getAddEmpBtn() {
        return addEmpBtn;
    }
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
    Stage dialogStage;

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
        addForm.processAddForm();
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

    @FXML
    void searchEmpClicked(ActionEvent event) {
        showSearchDialog();
    }

    @FXML
    void viewIntCourseImage(ActionEvent event) {
        addForm.viewIntCourseImage();
    }

    @FXML
    void viewExtCourseImage(ActionEvent event) {
        addForm.viewExtCourseImage();
    }

    void showSearchDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EmployeeController.class.getResource("/fxml/SearchQueryDialog.fxml"));
            loader.setController(this);
            loader.setResources(bundle);
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            dialogStage = new Stage();
            dialogStage.setTitle("Search Employee");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(MainApp.getMainStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.resizableProperty().setValue(Boolean.FALSE);
            searchByCombo.getItems().add("Service Number");
            searchByCombo.getItems().add("CPR Number");
            searchByCombo.getSelectionModel().select(0);

            // Set the person into the controller.
//            PersonEditDialogController controller = loader.getController();
//            controller.setDialogStage(dialogStage);
//            controller.setPerson(person);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void closeSearchDialog(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void searchClicked(ActionEvent event) {
        String query = searchField.getText();
        if (query.equals("")) {
            Alert alert = new Alert(AlertType.ERROR, "Empty Search Query.", ButtonType.OK);
            alert.showAndWait();
        } else {
            Task task = new Task() {
                @Override
                protected Object call() throws Exception {
                    if (searchByCombo.getSelectionModel().getSelectedIndex() == 0) {
                        return db.getByServiceNum(query);
                    } else {
                        return db.getByCPR(query);
                    }
                }
            };
            task.setOnSucceeded((evnt) -> {
                Object value = task.getValue();
                if (value == null) {
                    Alert alert = new Alert(AlertType.ERROR, "No Employee Found.", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    try {
                        Employee employee = (Employee) value;
                        closeSearchDialog(event);
                        MainApp.loadScene("/fxml/AddEmpForm.fxml", this, contentPane, bundle);
                        initAddForm();
                        addForm.setDataModel(employee);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            new Thread(task).start();
        }
    }
    
    @FXML
    void editEmpBtnClicked(ActionEvent event) {
        addForm.editForm();
    }
    
    @FXML
    void delEmpBtnClicked(ActionEvent event) {
        addForm.deleteEmp();
    }

    public JFXButton getEditEmpBtn() {
        return editEmpBtn;
    }

}
