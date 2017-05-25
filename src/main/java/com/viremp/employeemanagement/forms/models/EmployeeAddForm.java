/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.forms.models;

import com.viremp.employeemanagement.controllers.EmployeeController;
import com.viremp.employeemanagement.controllers.MainSceneController;
import com.viremp.employeemanagement.db.DatabaseHandler;
//import com.viremp.employeemanagement.models.Certificate;
import com.viremp.employeemanagement.models.Country;
import com.viremp.employeemanagement.models.Course;
import com.viremp.employeemanagement.models.Fine;
import com.viremp.employeemanagement.models.Rank;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.StandardCopyOption.*;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.scene.control.ComboBox;
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
    private File fineImage;
    private File intCourseImage;
    private File extCourseImage;
    private List<Fine> fines;
//    private List<Certificate> certificates;
    private List<Course> intCourses;
    private List<Course> extCourses;
    public static final String FINES_IMAGE_PATH = ".userfiles/.fines/";
    public static final String CERTS_IMAGE_PATH = ".userfiles/.fines/";

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
        fillLeavesCombo();
        fillBloodGroupCombo();
        fineImage = null;
        fines = new ArrayList<>();
//        certificates = new ArrayList<>();
        intCourses = new ArrayList<>();
        extCourses = new ArrayList<>();
        for(int i=1;i<=6;i++){
            controller.getFinesList().getItems().add("Fine "+i);
        }
        for(int i=1;i<=6;i++){
            controller.getIntCourseList().getItems().add("Course "+i);
        }
        for(int i=1;i<=6;i++){
            controller.getExtCoursesList().getItems().add("Course "+i);
        }

    }

    private void fillRelationCombo(ComboBox<String> relComboAddForm) {
        ObservableList<String> items = relComboAddForm.getItems();
        items.clear();
        items.add(bundle.getString("fatherTxt"));
        items.add(bundle.getString("brotherTxt"));

        relComboAddForm.getSelectionModel().select(0);
    }

    private void fillLeavesCombo() {
        ObservableList<String> items = controller.getLeavesCombo().getItems();
        items.clear();
        items.add(bundle.getString("annualTxt"));
        items.add(bundle.getString("emergencyTxt"));
        items.add(bundle.getString("hajjTxt"));
        items.add(bundle.getString("umrahTxt"));
        items.add(bundle.getString("sickLeaveTxt"));
        items.add(bundle.getString("otherLeaveTxt"));
        controller.getLeavesCombo().getSelectionModel().select(0);
    }

    private void fillMaritalCombo() {
        ObservableList<String> items = controller.getMaritalCombo().getItems();
        items.clear();
        items.add("Single");
        items.add("Married");
        items.add("Divorced");

        controller.getMaritalCombo().getSelectionModel().select(0);
    }

    private void fillBloodGroupCombo() {
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

    private void fillNatCombo(ComboBox<String> natComboAddForm) {
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
        File file = getImageSelection();
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

    void fillRanksList() {
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

    public void uploadFineImage() {
        File file = getImageSelection();
        if (file != null) {
            try {
                fineImage = file;
//                BufferedImage bufferedImage = ImageIO.read(file);
//                Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void uploadIntCourseImage() {
        File file = getImageSelection();
        if (file != null) {
            try {
                intCourseImage = file;
//                BufferedImage bufferedImage = ImageIO.read(file);
//                Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void uploadExtCourseImage() {
        File file = getImageSelection();
        if (file != null) {
            try {
                extCourseImage = file;
//                BufferedImage bufferedImage = ImageIO.read(file);
//                Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addNewIntCourse() {
        String courseName = controller.getIntCourseField().getText();
        if (courseName.equals("")) {
            MainSceneController.instance.showError("Empty Course Name");
        } else {
            if (intCourseImage == null) {
                MainSceneController.instance.showError("Course Image not Selected");
            } else {

                boolean duplicate = false;
                for (Course course : intCourses) {
                    if (course.getCourseName().equalsIgnoreCase(courseName)) {
                        MainSceneController.instance.showError("Course Already Added.");
                        duplicate = true;
                        break;
                    }
                }
                if (!duplicate) {
                    Course course = new Course();
                    course.setCourseName(courseName);
                    course.setImageFile(intCourseImage);
                    intCourses.add(course);
                    intCourseImage = null;
                    controller.getIntCourseList().getItems().add(courseName);
                    controller.getDeleteIntCourseBtn().setDisable(false);
                    controller.getIntCourseField().setText("");
                }
            }
        }
    }

    public void addNewExtCourse() {
        String courseName = controller.getExtCourseField().getText();
        if (courseName.equals("")) {
            MainSceneController.instance.showError("Empty Course Name");
        } else {
            if (extCourseImage == null) {
                MainSceneController.instance.showError("Course Image not Selected");
            } else {

                boolean duplicate = false;
                for (Course course : extCourses) {
                    if (course.getCourseName().equalsIgnoreCase(courseName)) {
                        MainSceneController.instance.showError("Course Already Added.");
                        duplicate = true;
                        break;
                    }
                }
                if (!duplicate) {
                    Course course = new Course();
                    course.setCourseName(courseName);
                    course.setImageFile(extCourseImage);
                    extCourses.add(course);
                    extCourseImage = null;
                    controller.getExtCoursesList().getItems().add(courseName);
                    controller.getDeleteExtCourseBtn().setDisable(false);
                    controller.getExtCourseField().setText("");
                }
            }
        }

    }

    public void addNewFine() {
        String fineName = controller.getFineNameField().getText();
        if (fineName.equals("")) {
            MainSceneController.instance.showError("Empty Fine Name");
        } else {
            if (fineImage == null) {
                MainSceneController.instance.showError("Fine Image not Selected");
            } else {
                Fine fine = new Fine();
                fine.setImageFile(fineImage);
                fine.setFineName(fineName);
                fines.add(fine);
                controller.getFinesList().getItems().add(fineName);
                controller.getDeleteFineBtn().setDisable(false);
                fineImage = null;
                controller.getFineNameField().setText("");
            }
        }
    }

    public void removeSelectedIntCourse() {
        int selectedIndex = controller.getIntCourseList().getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            return;
        }
        intCourses.remove(selectedIndex);
        controller.getIntCourseList().getItems().remove(selectedIndex);
        if (controller.getIntCourseList().getItems().isEmpty()) {
            controller.getDeleteIntCourseBtn().setDisable(true);
        }
    }

    public void removeSelectedExtCourse() {
        int selectedIndex = controller.getExtCoursesList().getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            return;
        }
        extCourses.remove(selectedIndex);
        controller.getExtCoursesList().getItems().remove(selectedIndex);
        if (controller.getExtCoursesList().getItems().isEmpty()) {
            controller.getDeleteExtCourseBtn().setDisable(true);
        }
    }

    public void removeSelectedFine() {

        int selectedIndex = controller.getFinesList().getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            return;
        }
        fines.remove(selectedIndex);
        controller.getFinesList().getItems().remove(selectedIndex);
        if (controller.getFinesList().getItems().isEmpty()) {
            controller.getDeleteFineBtn().setDisable(true);
        }

    }

    public void processFines() {
        int fineId = 1;
        for (Fine fine : fines) {
            fine.setFineId(fineId);
            String copiedPath = copyFineFile(fine);
            if (copiedPath == null) {
                System.out.println("Cannot Copy File for fine " + fineId);
            } else {
                fine.setFineImage(copiedPath);
                fine.setEmployeeId(1);
                insertFineToDb(fine);
            }
            fineId++;
        }
    }

    public void processCertificates() {
//        int certId = 1;
//        for (Certificate cert : certificates) {
//            cert.setCertificateId(certId);
//            String copiedPath = copyCertFile(cert);
//            if (copiedPath == null) {
//                System.out.println("Cannot Copy File for Certificate " + certId);
//            } else {
//                cert.setCertificateImage(copiedPath);
//                cert.setEmployeeId(1);
//            }
//            certId++;
//        }

    }

    public void insertCertToDb(/*Certificate cert*/) {
        final Task task = new Task() {
            @Override
            protected Object call() {
//                try {
//                    return db.addNewCertificate(cert);
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
                return null;
            }
        };
        task.setOnSucceeded((event) -> {
            Object value = task.getValue();
            if (value == null) {
//                System.out.println("Unable to Insert Certificate with certId = " + cert.getCertificateId());
            } else {
                boolean inserted = (boolean) task.getValue();
                if (!inserted) {
//                    System.out.println("Unable to Insert Certificate with certId = " + cert.getCertificateId());
                }
            }
        });
        new Thread(task).start();
    }

    public void insertFineToDb(Fine fine) {

        final Task task = new Task() {
            @Override
            protected Object call() {
                try {
                    return db.addNewFine(fine);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        };
        task.setOnSucceeded((event) -> {
            Object value = task.getValue();
            if (value == null) {
                System.out.println("Unable to Insert Fine with fineId = " + fine.getFineId());
            } else {
                boolean inserted = (boolean) task.getValue();

                if (!inserted) {
                    System.out.println("Unable to Insert Fine with fineId = " + fine.getFineId());
                }
            }

        });
        new Thread(task).start();
    }

    public String copyFineFile(Fine fine) {
        int employeeId = 1;
        Path sourcePath = fine.getImageFile().toPath();
        String sDest = FINES_IMAGE_PATH + "fine_" + fine.getFineId() + "_" + employeeId + "." + getFileExtension(fine.getImageFile().getName());

        Path dest = Paths.get(sDest);

        try {
            Files.copy(sourcePath, dest, REPLACE_EXISTING);
            System.out.println(sDest);
            return sDest;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String copyCertFile(/*Certificate cert*/) {
//        int employeeId = 1;
//        Path sourcePath = cert.getImageFile().toPath();
//        String sDest = CERTS_IMAGE_PATH + "cert_" + cert.getCertificateId() + "_" + employeeId + "." + getFileExtension(cert.getImageFile().getName());
//
//        Path dest = Paths.get(sDest);
//
//        try {
//            Files.copy(sourcePath, dest, REPLACE_EXISTING);
//            System.out.println(sDest);
//            return sDest;
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        return null;
    }

    String getFileExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    File getImageSelection() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        return fileChooser.showOpenDialog(null);
    }
}
