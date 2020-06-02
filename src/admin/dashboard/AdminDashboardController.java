/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dashboard;

import admin.Tableview.TableViewController;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kezia
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private JFXButton btnViewandEdit;
    @FXML
    private JFXButton btnViewSelectedApplicants;
    @FXML
    private JFXButton btnViewRejectedApplicants;
    @FXML
    private ImageView btnClose;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleViewandEdit(ActionEvent event) {
        try {
            TableViewController.table =1;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin/Tableview/TableView.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            //stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            stage.setTitle("View Applicantsf");
            //stage.setFullScreen(true);
            stage.show();
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void handleViewSelectedApplicants(ActionEvent event) {
        try {
            TableViewController.table =2;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin/Tableview/TableView.fxml"));



            Parent root = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            //stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            //stage.setFullScreen(true);
            stage.show();
            //((Node)(event.getSource())).getScene().getWindow().hide();
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
            //stage.setFullScreen(true);
            stage.show();
            //((Node)(event.getSource())).getScene().getWindow().hide();
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
