package com.viremp.employeemanagement.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.viremp.employeemanagement.MainApp;
import com.viremp.employeemanagement.db.DatabaseHandler;
import com.viremp.employeemanagement.models.Country;
import com.viremp.employeemanagement.models.Rank;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.Event;

/**
 * FXML Controller class
 *
 * @author Orthodox Computers
 */
public class SettingsController implements Initializable {

    private DatabaseHandler db;
    private List<Rank> allRanks;
    @FXML
    private JFXComboBox<String> languageCombo;

    @FXML
    private JFXTextField rankField;

    @FXML
    private JFXListView<String> ranksList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db = DatabaseHandler.getDatabaseHandler();
        initSettings();
    }

    private void initSettings() {
        fillLanguageCombo();
        fillRanksList();
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
        task.setOnSucceeded((event1) -> {
            List<Rank> ranks = (List<Rank>) task.getValue();
            if (ranks != null) {
                allRanks = ranks;
                ObservableList<String> items = ranksList.getItems();
                items.clear();

                ranks.forEach((rank) -> {
                    items.add(rank.getRankName());

                });
                ranksList.getSelectionModel().select(0);

            } else {
                MainSceneController.instance.showError("No Ranks to Show.");
            }
//                    MainScreenController.getController().setLoading(false);
        });
        new Thread(task).start();
    }

    void fillLanguageCombo() {
        languageCombo.getItems().add("English");
        languageCombo.getItems().add("عربى");
        if (com.viremp.employeemanagement.MainApp.englishSelected) {
            languageCombo.getSelectionModel().select(0);
        } else {
            languageCombo.getSelectionModel().select(1);
        }
    }

    @FXML
    void languageChanged(ActionEvent event) throws Exception {
        int selectedIndex = languageCombo.getSelectionModel().getSelectedIndex();
        MainApp.changeLocale(selectedIndex);

    }

    @FXML
    void addRankClicked(ActionEvent event) {
        String rankName = rankField.getText();
        if (rankName.equals("")) {
            MainSceneController.instance.showError("Empty Rank Name");
        } else {
            boolean duplicate = false;
            for (Rank rank : allRanks) {
                if (rankName.equalsIgnoreCase(rank.getRankName())) {
                    MainSceneController.instance.showError("Duplicate Rank");
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                final Task task = new Task() {
                    @Override
                    protected Object call() {
                        Object o = null;
                        try {
                            Rank rank = new Rank();
                            rank.setRankName(rankName);
                            o = db.addNewRank(rank);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        return o;
                    }
                };
                task.setOnSucceeded((Event event1) -> {
                    boolean added = (boolean) task.getValue();
                    if (added) {
                        fillRanksList();
                        MainSceneController.instance.showSuccess(rankName + " Rank added.");
                        rankField.setText("");
                    } else {
                        MainSceneController.instance.showError("Unable to Add Rank");
                    }
//                    MainScreenController.getController().setLoading(false);
                });
                new Thread(task).start();
            }
        }
    }

    @FXML
    void deleteRankClicked(ActionEvent event) {
        if(ranksList.getItems().isEmpty()) {
            MainSceneController.instance.showError("No Rank to Delete");
            return;
        }
        int selectedRankIndex = ranksList.getSelectionModel().getSelectedIndex();
        int rankId = allRanks.get(selectedRankIndex).getRankId();
        final Task task = new Task() {
            @Override
            protected Object call() {
                Object o = null;
                try {
                    o = db.deleteRank(rankId);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return o;
            }
        };
        task.setOnSucceeded((Event event1) -> {
            boolean deleted = (boolean) task.getValue();
            if (deleted) {
                fillRanksList();
                MainSceneController.instance.showSuccess("Rank Deleted");
            } else {
                MainSceneController.instance.showError("Unable to Delete Rank");
            }
//                    MainScreenController.getController().setLoading(false);
        });
        new Thread(task).start();

    }


}
