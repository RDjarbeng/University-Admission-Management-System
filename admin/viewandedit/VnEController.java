/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.viewandedit;

import admin.tableview.StudentInfo;
import java.net.URL;
import java.sql.Blob;
import java.util.ResourceBundle;
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
    private Label lblPayment;
    @FXML
    private Label lblLname;
    @FXML
    private Label lblFname;
    @FXML
    private Label lblMname;
    @FXML
    private Label lblExten;
    @FXML
    private Label lblDob;
    @FXML
    private Label lblgender;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblFather;
    @FXML
    private Label lblMother;
    @FXML
    private Label lblCOntact;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblCourse;
    @FXML
    private Label lblHall;
    @FXML
    private Label lblResults;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TOO
    }    
    
    public void setData(String payment, String lname, String fname, String mname, String exten, String dob, String gender, String father, String mother, String contact, String email, String address, String course, String hall) //Blob results)
    {
        
        
        lblPayment.setText(payment);
        lblLname.setText(lname);
        lblFname.setText(fname);
        lblMname.setText(mname);
        lblExten.setText(exten);
        lblDob.setText(dob);
        lblgender.setText(gender);
        lblEmail.setText(email);
        lblFather.setText(father);
        lblMother.setText(mother);
        lblCOntact.setText(contact);
        lblAddress.setText(address);
        lblCourse.setText(course);
        lblHall.setText(hall);
    }

    public void setNewData(ObservableList<StudentInfo> selectedItems) {
    }
    
}
