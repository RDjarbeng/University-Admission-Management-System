/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.dashboard;

import AdmissionSystem.Database;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.StudentInfo;
import student.AdmissionStatus.AdmissionStatus;
import student.AdmissionStatus.AdmissionStatusController;

import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author kezia
 */
public class DashboardController implements Initializable {



    public static String user=" ";


    @FXML
    private Label studentDashboardLabel;

    @FXML
    private ImageView btnClose;
    @FXML
    private JFXButton btnRegister;
    @FXML
    private JFXButton btnTrackApp;

    boolean isRegistered;




    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        isRegistered = false;

        studentDashboardLabel.setText("Student Dashboard (" + user + ")");

        btnTrackApp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Parent root1;

//      get selected student
                StudentInfo stinfo = new StudentInfo();
                Database mydatabase = new Database();

                try {
                     isRegistered =mydatabase.fillStudentDetails(user, stinfo);


                } catch (SQLException throwables) {
                    System.out.println("Could not fetch student from DB");
                    throwables.printStackTrace();
                }

                if(isRegistered){
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/student/AdmissionStatus/AdmissionStatus.fxml"));

                        root1 =loader.load();
                        AdmissionStatusController vneController = loader.getController();
                        if(stinfo != null) {
                            vneController.setData(stinfo);
                        }
//                Parent root1 = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        stage.setTitle("Admission details ("+user+")");
                        ((Stage)((Node)(actionEvent.getSource())).getScene().getWindow()).hide();
                        stage.showAndWait();
                        ((Stage)((Node)(actionEvent.getSource())).getScene().getWindow()).show();

                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Register first!\n Before viewing admission status");
                }


            }
        });
    }


    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
        
    }

    @FXML
    private void handleRegisterButton(MouseEvent event) {
        StudentInfo stinfo = new StudentInfo();
        Database mydatabase = new Database();


        try {
            isRegistered =mydatabase.fillStudentDetails(user, stinfo);


        } catch (SQLException throwables) {
            System.out.println("Could not fetch student from DB");
            throwables.printStackTrace();
        }

        if(!isRegistered){
            try {

                FXMLLoader loading = new FXMLLoader(getClass().getResource("/student/register/RegisterUI.fxml"));
//            RegisterUIController.setUser(user);
//                     System.out.println("Hello dashboard controller1");
                Parent root1 = (Parent)loading.load();
//                     System.out.println("Hello dashboard controller34");

                Stage stage = new Stage();
//                     System.out.println("Hello dashboard controller41");

                //stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(root1));
                stage.setTitle("Register ");

                ((Stage)((Node)(event.getSource())).getScene().getWindow()).hide();
                stage.showAndWait();


                ((Stage)((Node)(event.getSource())).getScene().getWindow()).show();

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Your registration is complete\n View your details -> Track Admission");
        }

    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        DashboardController.user = user;
    }
}
