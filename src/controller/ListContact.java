package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ContactUsers;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

/*
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
*/

/**
 *This class is used to manage the FXML ListContact file.
 *
 * @author  CyberDine
 * @since   2021-04-11
 */
public class ListContact implements Initializable {
    /**
     * Definition of global variables.
     */
    public Integer idUsers;
    public String  varLastName;
    /**
     * Name for the composants interface.
     */
    @FXML
    private Button printContactAllButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button button;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField telContactUser;
    @FXML
    private TextField nameFirstNameContactUser;
    @FXML
    private TextField emailContactUser;
    @FXML
    private TextField sexeContactUser;
    @FXML
    private Button saveModify;
    @FXML
    private Button windowShow;
    @FXML
    private Label label;
    @FXML
    private javafx.scene.control.TableView<Person> TableView;// = new TableView<Person>();
    @FXML
    private TableColumn<Integer, Integer> numberCol;
    @FXML private TableColumn<String, String>  telCol ;
    @FXML private TableColumn<String, String> nameFisrtNamecol;
    @FXML private TableColumn<String, String> emailCol ;
    @FXML private TableColumn<String, String> sexeCol ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Instance the class ContactUsers.
        ContactUsers contactUsers = new ContactUsers();
        try {
            ResultSet answerDataBase = contactUsers.selectContactTable();
            //Create a ObservableList.
            final ObservableList<Person> data = FXCollections.observableArrayList();

            //Extract the data form the contacts_users table.
            while(answerDataBase.next()){
                //Retrieve by column name.
                int id              = answerDataBase.getInt("id");
                String telephone    = answerDataBase.getString("telephone");
                String nom_prenoms  = answerDataBase.getString("nom_prenoms");
                String email        = answerDataBase.getString("email");
                String sexe         = answerDataBase.getString("sexe");

                //Add content in the Observablelist.
                data.add(new Person(id,telephone,nom_prenoms,email,sexe));
            }
            //Column for the data list.
            numberCol.setMinWidth(50);
            telCol.setMinWidth(100);
            nameFisrtNamecol.setMinWidth(100);
            emailCol.setMinWidth(200);
            sexeCol.setMinWidth(50);
            //Save data in the TableView.
            TableView.getItems().setAll(data);
            //Multiple select in the TableView.
            //TableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            //Close DataBase.
            answerDataBase.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     *This method allows you to return to the welcome window.
     */
    public void previousButtonLogin(ActionEvent actionEvent) {
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
    /**
     * This method prints all contacts.
     */
    public void printAllContact(ActionEvent actionEvent) throws SQLException {
        /*
        System.out.println("IMPRIMER TOUT LES CONTACTS");

        //Instance the class ContactUsers.
        ContactUsers contactUsers = new ContactUsers();
        ResultSet answerDataBase = contactUsers.selectContactTable();
        final String src = "../../resource/acteDeResidence.pdf";
        //final String src = "acte_residence.pdf";
        final String dest = "exitDocuments/" + "ListeDesContacts" + ".pdf";
        //Initialize PDF document
        PdfDocument pdfDoc = null;
        while(answerDataBase.next()){
            int id              = answerDataBase.getInt   ("id");
            String telephone    = answerDataBase.getString("telephone");
            String nom_prenoms  = answerDataBase.getString("nom_prenoms");
            String email        = answerDataBase.getString("email");
            String sexe         = answerDataBase.getString("sexe");

            try {
                pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //assert pdfDoc != null;
            PdfCanvas canvas = null;
            if (pdfDoc != null) {
                canvas = new PdfCanvas(pdfDoc.getFirstPage());
                //Nom et prenom de la personne
                try {
                    canvas.beginText().setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), 10)
                            .moveText(250, 363)
                            .showText(String.valueOf(id))
                            .endText();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        //assert pdfDoc != null;
        if (pdfDoc != null) {
            pdfDoc.close();
        }
        //assert pdfDoc != null;
        //pdfDoc.close();
        //Information Alert Dialogs.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("TERMINE DOCUMENT GENERER AVEC SUCCESS");
        alert.showAndWait();
         */
    }
    /**
     * Creation of the person class.
     */
    public static class Person {
        //Declaration of attributes.
        private final SimpleIntegerProperty numberId;
        private final SimpleStringProperty tel;
        private final SimpleStringProperty nameFirstName;
        private final SimpleStringProperty email;
        private final SimpleStringProperty sexeUsers;
        /**
         * This method is the constructor of the Person class.
         *
         * @param numberUser this parameter retrieves the username  for the user.
         * @param telephoneUser this parameter retrieves the contact for the user .
         * @param nameFirstNameUser this parameter retrieves the name and firstName for the user.
         * @param emailUser this parameter retrieves the name and emailfor the user.
         * @param sexeUser this parameter retrieves the name and sexe for the user.
         */
        private Person(Integer numberUser, String telephoneUser, String nameFirstNameUser, String emailUser,String sexeUser) {
            this.numberId           = new SimpleIntegerProperty(numberUser);
            this.tel                = new SimpleStringProperty(telephoneUser);
            this.nameFirstName      = new SimpleStringProperty(nameFirstNameUser);
            this.email              = new SimpleStringProperty(emailUser);
            this.sexeUsers          = new SimpleStringProperty(sexeUser);
        }
        public String getTel() {
            return tel.get();
        }

        public void setTel(String fName) {
            tel.set(String.valueOf(Integer.parseInt(fName)));
        }

        public String getNameFirstName() {
            return nameFirstName.get();
        }

        public void setnNmeFirstName(String fName) {
            nameFirstName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }

        public int getLastNameCol1Number() {
            return numberId.get();
        }

        public SimpleIntegerProperty lastNameCol1NumberProperty() {
            return numberId;
        }

        public String getSexeUsers() {
            return sexeUsers.get();
        }

        public SimpleStringProperty sexeUsersProperty() {
            return sexeUsers;
        }
    }
}