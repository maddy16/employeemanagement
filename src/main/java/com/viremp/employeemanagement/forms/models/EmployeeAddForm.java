/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.forms.models;

import com.jfoenix.controls.JFXListView;
import com.viremp.employeemanagement.controllers.EmployeeController;
import com.viremp.employeemanagement.controllers.MainSceneController;
import com.viremp.employeemanagement.db.DatabaseHandler;
//import com.viremp.employeemanagement.models.Certificate;
import com.viremp.employeemanagement.models.Country;
import com.viremp.employeemanagement.models.Course;
import com.viremp.employeemanagement.models.Employee;
import com.viremp.employeemanagement.models.Fine;
import com.viremp.employeemanagement.models.Rank;
import java.awt.Desktop;
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
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    private int rankIdSelected;
    private int countryIdSelected;
    private int employeeId;
    private File fineImage;
    private File intCourseImage;
    private File extCourseImage;
    private File profilePicImage;
    private List<Fine> fines;
    private List<Course> intCourses;
    private List<Course> extCourses;
    private List<Rank> allRanks;
    private Employee employeeToProcess;
    public static final String PROFILE_IMAGE_PATH = ".userfiles/.pics/";
    public static final String FINES_IMAGE_PATH = ".userfiles/.fines/";
    public static final String INT_IMAGE_PATH = ".userfiles/.int/";
    public static final String EXT_IMAGE_PATH = ".userfiles/.ext/";

    boolean editMode = false;
    boolean readOnly = true;

    public EmployeeAddForm(EmployeeController controller) {
        db = DatabaseHandler.getDatabaseHandler();
        this.controller = controller;
        bundle = this.controller.getBundle();
        initForm();
    }

    public void editForm() {
        if (readOnly) {
            gotoEditMode();
        } else {
            processUpdateForm();
        }

    }

    void gotoEditMode() {
        toggleReadOnly(false);
        controller.getEditEmpBtn().setText("Save");
        controller.getDelEmpBtn().setText("Cancel");
    }

    void gotoViewMode() {
        toggleReadOnly(true);
        controller.getEditEmpBtn().setText("Edit Record");
        controller.getDelEmpBtn().setText("Delete Record");
    }

    public void deleteEmp() {
        if (readOnly) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Sure want to delete Employee Record?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    db.deleteAllFines(employeeId);
                    db.deleteAllIntCourses(employeeId);
                    db.deleteAllExtCourses(employeeId);
                    db.deleteEmployee(employeeId);
                    alert = new Alert(Alert.AlertType.INFORMATION, "Employee Record Deleted.", ButtonType.OK);
                    alert.showAndWait();
                    MainSceneController.instance.loadEmployeesView();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            setDataModel(employeeToProcess);
            gotoViewMode();
        }
    }

    void populateComboBoxes() {
        fillNatCombo(controller.getNatComboAddForm());
        fillRelationCombo(controller.getRelComboAddForm());
        fillRanksList();
        fillMaritalCombo();
        fillLeavesCombo();
        fillBloodGroupCombo();
    }

    void toggleReadOnly(boolean toggle) {
        readOnly = toggle;
        controller.getSvcNumField().setEditable(!toggle);
        controller.getRankCombo().setDisable(toggle);
        controller.getNameField().setEditable(!toggle);
        controller.getCprField().setEditable(!toggle);
        controller.getAddressField().setEditable(!toggle);
        controller.getNatComboAddForm().setDisable(toggle);
        controller.getMobileField().setEditable(!toggle);
        controller.getRelContactField().setEditable(!toggle);
        controller.getRelComboAddForm().setDisable(toggle);
        controller.getBloodGpCombo().setDisable(toggle);
        controller.getDojField().setDisable(toggle);
        controller.getLastRankField().setDisable(toggle);
        controller.getDutyField().setEditable(!toggle);
        controller.getLeaveDaysField().setEditable(!toggle);
        controller.getLeavesCombo().setDisable(toggle);
        controller.getMaritalCombo().setDisable(toggle);
        controller.getNewPicImgBtn().setDisable(toggle);
        controller.getAddFineBtn().setDisable(toggle);
        controller.getAddIntCourseBtn().setDisable(toggle);
        controller.getAddExtCourseBtn().setDisable(toggle);
        controller.getSetExtCourseImageBtn().setDisable(toggle);
        controller.getSetFineImgBtn().setDisable(toggle);
        controller.getSetIntCourseImageBtn().setDisable(toggle);
        controller.getFineNameField().setEditable(!toggle);
        controller.getIntCourseField().setEditable(!toggle);
        controller.getExtCourseField().setEditable(!toggle);
        if(controller.getFinesList().getItems().size()>0)
            controller.getDeleteFineBtn().setDisable(toggle);
        if(controller.getIntCourseList().getItems().size()>0)
            controller.getDeleteIntCourseBtn().setDisable(toggle);
        if(controller.getExtCoursesList().getItems().size()>0)
            controller.getDeleteExtCourseBtn().setDisable(toggle);

    }

    public void setDataModel(Employee employee) {
        employeeToProcess = employee;
        editMode = true;
        
        controller.getAddEmpBtn().setVisible(false);
        controller.getEditEmpBtn().setVisible(true);
        controller.getDelEmpBtn().setVisible(true);
        controller.getAddNewEmployeeLabel().setText(bundle.getString("employeeDetailsLabel"));
        controller.getSvcNumField().setText(employee.getSvcNum());
        employeeId = employee.getEmployeeId();
        for (int i = 0; i < allRanks.size(); i++) {
            if (allRanks.get(i).getRankId() == employee.getRankId()) {
                controller.getRankCombo().getSelectionModel().select(i);
                break;
            }
        }
        controller.getNameField().setText(employee.getEmpName());
        controller.getCprField().setText(employee.getCprNum());
        controller.getAddressField().setText(employee.getAddress());
        for (int i = 0; i < allCountries.size(); i++) {
            if (allCountries.get(i).getCountryId() == employee.getCountryId()) {
                controller.getNatComboAddForm().getSelectionModel().select(i);
                break;
            }
        }
        controller.getMobileField().setText(employee.getMobile());
        controller.getRelContactField().setText(employee.getRelContact());
        controller.getRelComboAddForm().getSelectionModel().select(employee.getRelRelation());
        controller.getBloodGpCombo().getSelectionModel().select(employee.getBloodGroup());
        controller.getDojField().setValue(LocalDate.parse(employee.getDoj()));
        controller.getLastRankField().setValue(LocalDate.parse(employee.getLastRankDate()));
        controller.getDutyField().setText(employee.getDuty());
        controller.getLeaveDaysField().setText(employee.getLeaveDays());
        controller.getLeavesCombo().getSelectionModel().select(employee.getLeaveType());
        controller.getMaritalCombo().getSelectionModel().select(employee.getMaritalStatus());
        File file = new File(employee.getEmpPic());
        profilePicImage = file;
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            controller.getNewEmpImageView().setImage(image);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        controller.getViewExtImagesBtn().setVisible(true);
        controller.getViewIntImagesBtn().setVisible(true);
        controller.getViewFineImagesBtn().setVisible(true);
        try {
            List<Fine> allFines = db.getAllFines(employee.getEmployeeId());

            if (allFines != null && allFines.size() > 0) {
                fines = allFines;
                controller.getViewFineImagesBtn().setDisable(false);
                controller.getFinesList().getItems().clear();
                controller.getDeleteFineBtn().setDisable(false);
                for (Fine fine : allFines) {
                    controller.getFinesList().getItems().add(fine.getFineName());
                    fine.setImageFile(new File(fine.getFineImage()));
                }
            }
            List<Course> allIntCourses = db.getAllInteriorCourses(employee.getEmployeeId());

            if (allIntCourses != null && allIntCourses.size() > 0) {
                intCourses = allIntCourses;
                
                controller.getViewIntImagesBtn().setDisable(false);
                controller.getIntCourseList().getItems().clear();
                controller.getDeleteIntCourseBtn().setDisable(false);
                for (Course course : allIntCourses) {
                    controller.getIntCourseList().getItems().add(course.getCourseName());
                    course.setImageFile(new File(course.getCourseImage()));
                }
            }
            List<Course> allExtCourses = db.getAllExteriorCourses(employee.getEmployeeId());

            if (allExtCourses != null && allExtCourses.size() > 0) {
                extCourses = allExtCourses;
                controller.getViewExtImagesBtn().setDisable(false);
                controller.getExtCoursesList().getItems().clear();
                controller.getDeleteExtCourseBtn().setDisable(false);
                for (Course course : allExtCourses) {
                    controller.getExtCoursesList().getItems().add(course.getCourseName());
                    course.setImageFile(new File(course.getCourseImage()));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        toggleReadOnly(true);

    }

    public void viewIntCourseImage() {
        viewImage(controller.getIntCourseList(), 1);
    }

    public void viewExtCourseImage() {
        viewImage(controller.getExtCoursesList(), 2);
    }

    public void viewFineImage() {
        viewImage(controller.getFinesList(), 0);
    }

    private void viewImage(JFXListView<String> listView, int cat) {
        try {

            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex == -1) {
                return;
            }
            Object ob = null;
            switch (cat) {
                case 0:
                    ob = fines.get(selectedIndex);
                    break;
                case 1:
                    ob = intCourses.get(selectedIndex);
                    break;
                case 2:
                    ob = extCourses.get(selectedIndex);
            }
            File file = null;
            if (ob instanceof Fine) {
                file = new File(((Fine) ob).getFineImage());
            } else if (ob instanceof Course) {
                file = new File(((Course) ob).getCourseImage());
            }
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void initObjects() {
        fineImage = null;
        intCourseImage = null;
        extCourseImage = null;
        profilePicImage = null;
        fines = new ArrayList<>();
        intCourses = new ArrayList<>();
        extCourses = new ArrayList<>();
        allRanks = new ArrayList<>();
    }

    private void initForm() {
        initObjects();
        populateComboBoxes();

        createDirectories();
//        for (int i = 1; i <= 6; i++) {
//            controller.getFinesList().getItems().add("Fine " + i);
//        }
//        for (int i = 1; i <= 6; i++) {
//            controller.getIntCourseList().getItems().add("Course " + i);
//        }
//        for (int i = 1; i <= 6; i++) {
//            controller.getExtCoursesList().getItems().add("Course " + i);
//        }

    }

    void createDirectories() {
        File file = new File(INT_IMAGE_PATH);
        file.mkdirs();
        file = new File(EXT_IMAGE_PATH);
        file.mkdirs();
        file = new File(FINES_IMAGE_PATH);
        file.mkdirs();
        file = new File(PROFILE_IMAGE_PATH);
        file.mkdirs();
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
        try {
            List<Country> countries = db.getAllCountries();
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void chooseEmployeePic() {
        File file = getImageSelection();
        if (file != null) {
            try {
                profilePicImage = file;
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
        try {
            List<Rank> ranks = db.getAllRanks();
            if (ranks != null) {
//                allCountries = countries;
                ObservableList<String> items = controller.getRankCombo().getItems();
                items.clear();
                allRanks = ranks;
                ranks.forEach((rank) -> {
                    items.add(rank.getRankName());

                });
                controller.getRankCombo().getSelectionModel().select(0);

            } else {
                System.out.println("Ranks List is Null");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

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

    void processExtCourses() {
        int courseId = 1;
        for (Course course : extCourses) {
            course.setCourseId(courseId);
            String copiedPath = copyExtCourseFile(course);
            if (copiedPath == null) {
                System.out.println("Cannot Copy File for Exterior Course: " + courseId);
            } else {
                try {
                    course.setCourseImage(copiedPath);
                    course.setEmployeeId(employeeId);
                    insertExtCourseToDb(course);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            courseId++;
        }
    }

    void processIntCourses() {
        int courseId = 1;
        for (Course course : intCourses) {
            course.setCourseId(courseId);
            String copiedPath = copyIntCourseFile(course);
            if (copiedPath == null) {
                System.out.println("Cannot Copy File for Interior Course: " + courseId);
            } else {
                try {
                    course.setCourseImage(copiedPath);
                    course.setEmployeeId(employeeId);
                    insertIntCourseToDb(course);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            courseId++;
        }
    }

    void processFines() {
        int fineId = 1;
        for (Fine fine : fines) {
            fine.setFineId(fineId);
            String copiedPath = copyFineFile(fine);
            if (copiedPath == null) {
                System.out.println("Cannot Copy File for fine " + fineId);
            } else {
                try {
                    fine.setFineImage(copiedPath);
                    fine.setEmployeeId(employeeId);
                    insertFineToDb(fine);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            fineId++;
        }
    }

    public void insertFineToDb(Fine fine) throws SQLException {
        boolean inserted = db.addNewFine(fine);
        if (!inserted) {
            System.out.println("Unable to Insert Fine with fineId = " + fine.getFineId());
        }
    }

    public void insertIntCourseToDb(Course course) throws SQLException {
        boolean inserted = db.addNewIntCourse(course);
        if (!inserted) {
            System.out.println("Unable to Insert Interior Course with Id = " + course.getCourseId());
        }
    }

    public void insertExtCourseToDb(Course course) throws SQLException {
        boolean inserted = db.addNewExtCourse(course);
        if (!inserted) {
            System.out.println("Unable to Insert Exterior Course with Id = " + course.getCourseId());
        }
    }

    public String copyFineFile(Fine fine) {
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

    public String copyIntCourseFile(Course course) {
        Path sourcePath = course.getImageFile().toPath();
        String sDest = INT_IMAGE_PATH + "int_" + course.getCourseId() + "_" + employeeId + "." + getFileExtension(course.getImageFile().getName());
        Path dest = Paths.get(sDest);
        try {
            Files.copy(sourcePath, dest, REPLACE_EXISTING);
            return sDest;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String copyExtCourseFile(Course course) {
        Path sourcePath = course.getImageFile().toPath();
        String sDest = EXT_IMAGE_PATH + "ext_" + course.getCourseId() + "_" + employeeId + "." + getFileExtension(course.getImageFile().getName());
        Path dest = Paths.get(sDest);
        try {
            Files.copy(sourcePath, dest, REPLACE_EXISTING);
            return sDest;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String copyProfilePic() {
        Path sourcePath = profilePicImage.toPath();
        String sDest = PROFILE_IMAGE_PATH + "pic_" + employeeId + "." + getFileExtension(profilePicImage.getName());
        Path dest = Paths.get(sDest);
        try {
            Files.copy(sourcePath, dest, REPLACE_EXISTING);
            return sDest;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private boolean selectRank() {
        boolean rankSelected = false;
        int index = controller.getRankCombo().getSelectionModel().getSelectedIndex();
        String selectedName = controller.getRankCombo().getSelectionModel().getSelectedItem();
        if (controller.getRankCombo().getItems().get(index).equalsIgnoreCase(selectedName)) {
            rankIdSelected = allRanks.get(index).getRankId();
            rankSelected = true;
        } else {
            if (selectedName.equals("")) {
                MainSceneController.instance.showError("Rank not Selected");
            } else {
                Rank rank = new Rank();
                rank.setRankName(controller.getRankCombo().getSelectionModel().getSelectedItem());
                try {
                    int lastInsertId = db.addNewRank(rank);
                    if (lastInsertId > 0) {
                        rankIdSelected = lastInsertId;
                        rankSelected = true;
                    } else {
                        MainSceneController.instance.showError("Invalid Rank");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return rankSelected;
    }

    boolean selectNationality() {
        boolean nationSelected = false;
        int index = controller.getNatComboAddForm().getSelectionModel().getSelectedIndex();
        String selectedName = controller.getNatComboAddForm().getSelectionModel().getSelectedItem();
        if (controller.getNatComboAddForm().getItems().get(index).equalsIgnoreCase(selectedName)) {
            countryIdSelected = allCountries.get(index).getCountryId();
            nationSelected = true;
        } else {
            if (selectedName.equals("")) {
                MainSceneController.instance.showError("Nationality Not Mentioned");
            } else {
                Country country = new Country();
                country.setCountry(controller.getNatComboAddForm().getSelectionModel().getSelectedItem());
                try {
                    int lastInsertId = db.addNewCountry(country);
                    if (lastInsertId > 0) {
                        countryIdSelected = lastInsertId;
                        nationSelected = true;
                    } else {
                        MainSceneController.instance.showError("Invalid Nationality Value");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    MainSceneController.instance.showError(ex.getMessage());
                }
            }
        }
        return nationSelected;
    }

    public void processUpdateForm() {
        if (selectRank()) {
            if (selectNationality()) {
                if (formValid()) {
                    try {
                        Employee employee = getDataModel();
                        employee.setEmployeeId(employeeId);
                        String profilePicPath = copyProfilePic();
                        if (profilePicPath == null) {
                            System.out.println("Error copying profile picture");
                            MainSceneController.instance.showError("Unable to Update Employee Details");
                            return;
                        }
                        employee.setEmpPic(profilePicPath);
                        boolean updated = db.updateEmployee(employee);
                        if (updated) {
                            reProcessEntities(employeeId);
                            MainSceneController.instance.showSuccess("Employee Details Saved.");
                            gotoViewMode();
                        } else {
                            MainSceneController.instance.showError("Unable to Update Employee Details");
                        }
                    } catch (SQLIntegrityConstraintViolationException ex) {
                        if (ex.getMessage().contains("svc")) {
                            MainSceneController.instance.showError("Duplicate Entry for Service Number");
                        } else {
                            MainSceneController.instance.showError("Duplicate Entry for CPR Number");
                        }
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

    }

    void reProcessEntities(int employeeId) throws SQLException {
        db.deleteAllFines(employeeId);
        db.deleteAllIntCourses(employeeId);
        db.deleteAllExtCourses(employeeId);
        processFines();
        processIntCourses();
        processExtCourses();

    }

    public void processAddForm() {

        if (selectRank()) {
            if (selectNationality()) {
                if (formValid()) {
                    try {
                        Employee employee = getDataModel();
                        employeeId = db.addNewEmployee(employee);
                        employee.setEmployeeId(employeeId);
                        String profilePicPath = copyProfilePic();
                        if (profilePicPath == null) {
                            System.out.println("Error copying profile picture");
                            MainSceneController.instance.showError("Unable to Register Employee");
                            return;
                        }
                        employee.setEmpPic(profilePicPath);
                        db.updateEmployee(employee);
                        processFines();
                        processIntCourses();
                        processExtCourses();
                        MainSceneController.instance.showSuccess("Employee Registered.");
                        resetForm();

                    } catch (SQLIntegrityConstraintViolationException ex) {
                        if (ex.getMessage().contains("svc")) {
                            MainSceneController.instance.showError("Duplicate Entry for Service Number");
                        } else {
                            MainSceneController.instance.showError("Duplicate Entry for CPR Number");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        MainSceneController.instance.showError("Unable to Register Employee");

                    }
                }
            }
        }
    }

    void resetForm() {
        controller.getSvcNumField().setText("");
        controller.getNameField().setText("");
        controller.getCprField().setText("");
        controller.getAddressField().setText("");
        controller.getMobileField().setText("");
        controller.getRelContactField().setText("");
        controller.getDojField().setValue(null);
        controller.getLastRankField().setValue(null);
        controller.getDutyField().setText("");
        initObjects();

    }

    boolean formValid() {
        String txt = controller.getSvcNumField().getText();
        if (txt.equals("")) {
            MainSceneController.instance.showError("Please Enter Service Number");
            return false;
        }
        txt = controller.getNameField().getText();
        if (txt.equals("")) {
            MainSceneController.instance.showError("Please Enter Employee Name");
            return false;
        }
        txt = controller.getCprField().getText();
        if (txt.equals("")) {
            MainSceneController.instance.showError("Please Enter CPR Number");
            return false;
        }
        txt = controller.getAddressField().getText();
        if (txt.equals("")) {
            MainSceneController.instance.showError("Please Enter Address");
            return false;
        }
        txt = controller.getMobileField().getText();
        if (txt.equals("")) {
            MainSceneController.instance.showError("Please Enter Mobile Number");
            return false;
        }
        txt = controller.getRelContactField().getText();
        if (txt.equals("")) {
            MainSceneController.instance.showError("Please Enter Contact Number of Relative");
            return false;
        }
        LocalDate dValue = controller.getDojField().getValue();
        if (dValue == null) {
            MainSceneController.instance.showError("Please Select Date of Joining");
            return false;
        }
        dValue = controller.getLastRankField().getValue();
        if (dValue == null) {
            MainSceneController.instance.showError("Please Select Last Rank Date");
            return false;
        }
        txt = controller.getDutyField().getText();
        if (txt.equals("")) {
            MainSceneController.instance.showError("Please Enter Duty");
            return false;
        }
        if (profilePicImage == null) {
            MainSceneController.instance.showError("Please Select Picture of Employee");
            return false;
        }
        return true;

    }

    public Employee getDataModel() {
        Employee employee = new Employee();
        employee.setSvcNum(controller.getSvcNumField().getText());
        employee.setRankId(rankIdSelected);
        employee.setEmpName(controller.getNameField().getText());
        employee.setCprNum(controller.getCprField().getText());
        employee.setAddress(controller.getAddressField().getText());
        employee.setCountryId(countryIdSelected);
        employee.setMobile(controller.getMobileField().getText());
        employee.setRelContact(controller.getRelContactField().getText());
        employee.setRelRelation(controller.getRelComboAddForm().getSelectionModel().getSelectedItem());
        employee.setBloodGroup(controller.getBloodGpCombo().getSelectionModel().getSelectedItem());
        employee.setDoj(controller.getDojField().getValue().toString());
        employee.setLastRankDate(controller.getLastRankField().getValue().toString());
        employee.setDuty(controller.getDutyField().getText());
        employee.setLeaveDays(controller.getLeaveDaysField().getText());
        employee.setLeaveType(controller.getLeavesCombo().getSelectionModel().getSelectedItem());
        employee.setMaritalStatus(controller.getMaritalCombo().getSelectionModel().getSelectedItem());

//        employee.setEmpPic(profilePicPath);
        System.out.println(employee);
        return employee;
    }

    private String getFileExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    private File getImageSelection() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        return fileChooser.showOpenDialog(null);
    }

}
