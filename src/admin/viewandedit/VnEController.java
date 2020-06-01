/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.viewandedit;

import admin.pendingTableview.StudentInfo;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author kezia
 */
public class VnEController implements Initializable {

    @FXML
    private Label lblReceipt;

    @FXML
    private Label lblLname;

    @FXML
    private Label lblFname;

    @FXML
    private Label lblMname;

    @FXML
    private Label lblNationality;

    @FXML
    private Label lblDob;

    @FXML
    private Label lblgender;

    @FXML
    private Label lblEmail;



    @FXML
    private Label lblResidentialAddress;

    @FXML
    private Label lblCourse;

    @FXML
    private Label lblHall;

    @FXML
    private Label lblResults;

    @FXML
    private JFXButton btnAccept;

    @FXML
    private JFXButton btnDecline;

    @FXML
    private Label lblStatus;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TOO
    }    
    
    public void setData(String receiptID, String lname, String fname, String mname, String dob, String gender,
                        String nationality,
                         String email, String resAddress, String posAddress, String course, String hall) //Blob results)
    {
        
        
        lblReceipt.setText(receiptID);
        lblLname.setText(lname);
        lblFname.setText(fname);
        lblMname.setText(mname);
        lblDob.setText(dob);
        lblgender.setText(gender);
        lblNationality.setText(nationality);
        lblEmail.setText(email);

        lblResidentialAddress.setText(resAddress);
//        lblPostalAddress.setText(posAddress);
        lblCourse.setText(course);
        lblHall.setText(hall);
    }

    public void setNewData(ObservableList<StudentInfo> selectedItems) {
    }



}
