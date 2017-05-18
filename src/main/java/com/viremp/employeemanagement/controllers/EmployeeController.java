/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.controllers;

import com.jfoenix.controls.JFXButton;
import com.viremp.employeemanagement.MainApp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Maddy
 */
public class EmployeeController implements Initializable {

    @FXML
    private AnchorPane contentPane;
    @FXML
    private ImageView newEmpImageView;
    @FXML
    private JFXButton newPicImgBtn;
    ResourceBundle bundle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bundle = rb;
    }

    @FXML
    void addNewEmpClicked(ActionEvent event) throws IOException {
        MainApp.loadScene("/fxml/AddEmpForm.fxml", this, contentPane, bundle);
    }

    @FXML
    void setPicClicked(ActionEvent event) {
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
                newEmpImageView.setImage(image);
//                newPicImgBtn.setText("Change Photo");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
