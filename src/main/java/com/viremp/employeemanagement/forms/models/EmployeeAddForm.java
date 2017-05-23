/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.forms.models;

import com.jfoenix.controls.JFXComboBox;
import com.viremp.employeemanagement.controllers.EmployeeController;
import com.viremp.employeemanagement.db.DatabaseHandler;
import com.viremp.employeemanagement.db.GenericDB;
import com.viremp.employeemanagement.models.Country;
import com.viremp.employeemanagement.models.Rank;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 *
 * @author Maddy
 */
public class EmployeeAddForm {

    public List<Country> allCountries;
    private final DatabaseHandler db;
    private final ResourceBundle bundle;
    private final EmployeeController controller;

    public EmployeeAddForm(EmployeeController controller) {
        db = DatabaseHandler.getDatabaseHandler();
        this.controller = controller;
        bundle = this.controller.getBundle();
        initForm();
    }

    private void initForm() {
        fillNatCombo(controller.getNatComboAddForm());
        fillRelationCombo(controller.getRelComboAddForm());
        fillRanksList();
        fillMaritalCombo();
        fillBloodGroupCombo();
    }

    private void fillRelationCombo(JFXComboBox<String> relComboAddForm) {
        ObservableList<String> items = relComboAddForm.getItems();
        items.clear();
        items.add(bundle.getString("fatherTxt"));
        items.add(bundle.getString("brotherTxt"));
        relComboAddForm.getSelectionModel().select(0);
    }
    private void fillMaritalCombo(){
        ObservableList<String> items = controller.getMaritalCombo().getItems();
        items.clear();
        items.add("Single");
        items.add("Married");
        items.add("Divorced");
        
        controller.getMaritalCombo().getSelectionModel().select(0);
    }
    
    private void fillBloodGroupCombo(){
        ObservableList<String> items = controller.getBloodGpCombo().getItems();
        items.clear();
        items.add("A+");
        items.add("A-");
        items.add("B+");
        items.add("B-");
        items.add("O+");
        items.add("O-");
        items.add("AB+");
        items.add("AB-");
        controller.getBloodGpCombo().getSelectionModel().select(0);
    }
    private void fillNatCombo(JFXComboBox<String> natComboAddForm) {
        final Task task = new Task() {
            @Override
            protected Object call() {
                Object o = null;
                try {
                    o = db.getAllCountries();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return o;
            }
        };
        task.setOnSucceeded((Event event1) -> {
            List<Country> countries = (List<Country>) task.getValue();
            if (countries != null) {
                allCountries = countries;
                ObservableList<String> items = natComboAddForm.getItems();
                items.clear();

                countries.forEach((country) -> {
                    items.add(country.getCountry());

                });
                natComboAddForm.getSelectionModel().select(0);

            } else {
                System.out.println("Countries List is Null");
            }
//                    MainScreenController.getController().setLoading(false);
        });
        new Thread(task).start();

    }

    public void chooseEmployeePic() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                controller.getNewEmpImageView().setImage(image);
//                newPicImgBtn.setText("Change Photo");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    void fillRanksList(){
        final Task task = new Task() {
            @Override
            protected Object call() {
                Object o = null;
                try {
                    o = db.getAllRanks();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return o;
            }
        };
        task.setOnSucceeded((Event event1) -> {
            List<Rank> ranks = (List<Rank>) task.getValue();
            if (ranks != null) {
//                allCountries = countries;
                ObservableList<String> items = controller.getRankCombo().getItems();
                items.clear();

                ranks.forEach((rank) -> {
                    items.add(rank.getRankName());

                });
                controller.getRankCombo().getSelectionModel().select(0);

            } else {
                System.out.println("Ranks List is Null");
            }
//                    MainScreenController.getController().setLoading(false);
        });
        new Thread(task).start();
    }
    public void checkEnteredNationality(String txt) {
        for (int i = 0; i < allCountries.size(); i++) {
            Country country = allCountries.get(i);
            if (country.getCountry().equalsIgnoreCase(txt)) {
                controller.getNatComboAddForm().getSelectionModel().select(i);
            }
        }
    }
}
