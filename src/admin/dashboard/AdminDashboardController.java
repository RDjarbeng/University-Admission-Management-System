/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dashboard;

import AdmissionSystem.Database;
import admin.Tableview.TableViewController;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author kezia
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private JFXButton btnViewPending;
    @FXML
    private JFXButton btnViewSelectedApplicants;
    @FXML
    private JFXButton btnViewRejectedApplicants;
    @FXML
    private ImageView btnClose;

    public static int rejected = 0;
    public static int pending = 0;
    public static int accepted = 0;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Database myDatabase = new Database();


        try {
            accepted = myDatabase.getNumAcceptedStudents();
            pending = myDatabase.getNumPendingStudents();
            rejected = myDatabase.getNumRejectedStudents();

            myDatabase.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        btnViewPending.setText("View pending applicants ("+pending+")");
        btnViewRejectedApplicants.setText("View rejected applicants ("+rejected+")");
        btnViewSelectedApplicants.setText("View selected applicants ("+accepted+")");
    }

    @FXML
    private void handleViewPendingApplicants(ActionEvent event) {
        try {
            TableViewController.table =1;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin/Tableview/TableView.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            //stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            stage.setTitle("View Applicants");
            //stage.setFullScreen(true);
            if(pending >0) {
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }

            stage.show();


        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void handleViewAcceptedApplicants(ActionEvent event) {
        try {
            TableViewController.table =2;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin/Tableview/TableView.fxml"));



            Parent root = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            //stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            //stage.setFullScreen(true);

            if(accepted >0) {
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            stage.show();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void handleViewRejectedApplicants(ActionEvent event) {
        try {
            TableViewController.table =3;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin/Tableview/TableView.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            //stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            if(rejected >0) {
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            stage.show();
//            fxmlLoader = new FXMLLoader(getClass().getResource("/admin/dashboard/AdminDashboard.fxml"));
//
//            Parent root1 = (Parent) fxmlLoader.load();
//            stage = new Stage();
//            stage.initStyle(StageStyle.TRANSPARENT);
//            stage.setScene(new Scene(root1));
//            stage.show();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleCloseButton(MouseEvent event) {
        System.exit(0);
    }
    
}
