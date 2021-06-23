package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 *This class is used to manage the FXML home file.
 *
 * @author  CyberDine
 * @since   2021-04-11
 */
public class Home {
    /**
     * Name for the composants interface.
     */
    @FXML
    private Button closeWindowHomeButton;
    @FXML
    private Button startWindowLogin;
    /**
     * This method display the login window.
     */
    public void showWindowLogin(ActionEvent actionEvent) throws IOException {
        //Load file FXML for the login.
        Parent loadFileFXMLLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/login.fxml")));
        Scene loginWindowScene = new Scene(loadFileFXMLLogin);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(loginWindowScene);
        app_stage.centerOnScreen();
        app_stage.show();
        //app_stage.hide();
    }
    /**
     * This method close home window.
     */
    public void closeWindowHome(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setContentText("Êtes-vous sûre de vouloir quitter le logiciel ?");
        ButtonType okButton = new ButtonType("oui", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("non", ButtonBar.ButtonData.NO);
        ButtonType cancelButton = new ButtonType("annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
        alert.setY(230);
        alert.setX(500);
        alert.showAndWait().ifPresent(type -> {
            //Close window home.
            if (type == okButton) { // do something
                //Get a handle to the stage.
                Stage stage = (Stage) closeWindowHomeButton.getScene().getWindow();
                //Do what you have to do.
                stage.close();
            }
            //We stay on the window home.
            else if (type == noButton) { // do something
                //Load file FXML for the home.
                Parent loadFileFXMLHome = null;
                try {
                    loadFileFXMLHome = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/home.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert loadFileFXMLHome != null;
                Scene homeWindowScene = new Scene(loadFileFXMLHome);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.setScene(homeWindowScene);
                app_stage.show();
                //app_stage.hide();
            }
            //We stay on the window home.
            else { // do something
                //Load file FXML for the home.
                Parent loadFileFXMLHome = null;
                try {
                    loadFileFXMLHome = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/home.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert loadFileFXMLHome != null;
                Scene homeWindowScene = new Scene(loadFileFXMLHome);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.setScene(homeWindowScene);
                app_stage.show();
            }
        });
    }
}