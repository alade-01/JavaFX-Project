package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ContactUsers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 *This class is used to manage the FXML saveCoontact file.
 *
 * @author  CyberDine
 * @since   2021-04-11
 */
public class SaveContact {
    /**
     * Definition of global variables.
     */
    String homme;
    String femme;
    int boolInteger = 3;

    String userSexeSave;

    @FXML private CheckBox chexBoxHomme;
    @FXML private CheckBox chexBoxFemme;
    /**
     * Name for the composants interface.
     */
    @FXML
    private Button previousButtonWindowSaveContact;
    @FXML
    private TextField userContact;
    @FXML
    private TextField userName;
    @FXML
    private TextField userEmail;
    @FXML
    private TextField userSexe;
    @FXML
    private Button enregistrerContact;
    /**
     * This method allows you to select the male check box.
     */
    public void chexBoxHommeMethod(ActionEvent actionEvent) {
        if(chexBoxHomme.isSelected()){
            chexBoxFemme.setSelected(false);
            homme = "HOMME";
            boolInteger = 1;
        }else {
            boolInteger = 3;
        }
    }
    /**
     * This method allows you to select the woman check box.
     */
    public void chexBoxFemmeMethod(ActionEvent actionEvent) {
        if(chexBoxFemme.isSelected()){
            chexBoxHomme.setSelected(false);
            femme = "FEMME";
            boolInteger = 0;
        }else {
            boolInteger = 3;
        }
    }
    /**
     * This method save the contacts in the contacts_users table.
     */
    public void enregistrerNewContact(ActionEvent actionEvent) throws SQLException, IOException {
        //Retrieves the information entered.
        String userContactSave = userContact.getText().trim();
        String userNameSave    = userName.getText().toUpperCase().trim();
        String userEmailSave   = userEmail.getText().toLowerCase().trim();
        //Checking the boolean value.

        //String userSexeSave    = userSexe.getText().toUpperCase().trim();
        //Check if the information is empty.
        if (userContactSave.isEmpty()){
            //Warning Alert Dialogs.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter phone number.");
            alert.showAndWait();
        }else if (userNameSave.isEmpty()){
            //Warning Alert Dialogs.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the contact name.");
            alert.showAndWait();
        }else if (userEmailSave.isEmpty()){
            //Warning Alert Dialogs.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the e-maîl adresse.");
            alert.showAndWait();
        } else if (boolInteger == 3){
            //Warning Alert Dialogs.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please choose the gender.");
            alert.showAndWait();
        } else {
            if (boolInteger == 1){
                userSexeSave    = homme;
            }else if(boolInteger == 0){
                userSexeSave    = femme;
            }
            boolean  intUserContact = userContactSave.matches("-?\\d+") ;
            boolean test = userNameSave.equals(toString());
            System.out.println(test);
            if(intUserContact){
                //Covert userContact variable string to variable int.
                //Integer userContactSaveTable =  Integer.parseInt(userContact.getText().trim());

                //Instance the class ContacUserst from package model.
                ContactUsers saveContactUser = new ContactUsers();
                //Return the information at the method saveContactTable from the class ContacUserst.
                boolean boolDataBase = saveContactUser.saveContactTable("+225 "+userContactSave,userNameSave,userEmailSave,userSexeSave);
                if (boolDataBase){
                    //Error Alert Dialogs.
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Failure");
                    alert.setHeaderText(null);
                    alert.setContentText("Registration failed.");
                    alert.showAndWait();
                }else {
                    //Information Alert Dialogs.
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Registration successfully completed.");
                    alert.showAndWait();

                    //Clear TextField from the saveContact window.
                    userContact.setText("");
                    userName.setText("");
                    userEmail.setText("");
                    chexBoxHomme.setSelected(false);
                    chexBoxFemme.setSelected(false);
                    //userSexe.setText("");
                }
            }else{
                //Warning Alert Dialogs.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Phone : please enter numbers only.");
                alert.showAndWait();
            }
        }
    }
    /**
     *This method returns to the dashboard window.
     */
    public void previousWindowSaveContact(ActionEvent actionEvent) {
        //Load file FXML for the dashboard.
        Parent loadFileFXMLDashboard = null;
        try {
            loadFileFXMLDashboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboard.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert loadFileFXMLDashboard != null;
        Scene dashboardWindowScene = new Scene(loadFileFXMLDashboard);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(dashboardWindowScene);
        app_stage.centerOnScreen();
        app_stage.show();
    }
}