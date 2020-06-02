/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.AdmissionStatus;

import AdmissionSystem.Database;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.StudentInfo;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author kezia
 */
public class AdmissionStatusController implements Initializable {

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
    private JFXButton btnReject;

    @FXML
    private Label lblStatus;


    @FXML
    private JFXTextArea statusTextArea;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }    
    
    public void setData(String receiptID, String lname, String fname, String mname, String dob, String gender,
                        String nationality,
                        String email, String resAddress, String course, String hall, String stinfoStatus) //Blob results)
    {
        
        
//        lblReceipt.setText(receiptID);
//        lblLname.setText(lname);
//        lblFname.setText(fname);
//        lblMname.setText(mname);
//        lblDob.setText(dob);
//        lblgender.setText(gender);
//        lblNationality.setText(nationality);
//        lblEmail.setText(email);
//
//        lblResidentialAddress.setText(resAddress);
//        lblCourse.setText(course);
//        lblHall.setText(hall);
        lblStatus.setText(stinfoStatus);

        String status ="Receipt ID: "+receiptID+" \n"+
        "Last name: "+lname+" \n"+
        "First name: "+fname+" \n"+
                "Middle name: "+mname+" \n"+
                "DOB: "+dob+" \n"+
                "Gender: "+gender+" \n"+
                "Nationality: "+nationality+" \n"+
                "Email: "+email+" \n"+
                "Address: "+resAddress+" \n"+
                "_________________________"+" \n";
        if(stinfoStatus.equals(Database.APP_STATUS_ACCEPTED)){
            status +="Course : "+course+" \n"+
                     "Hall: "+hall+" \n"+
                     "Admission status: "+stinfoStatus+" \n";
        }else{
            status +="Course : "+course+" \n"+
                    "Hall: "+" \n"+
                    "Admission status: "+stinfoStatus+" \n";

            lblCourse.setText(" ");
            lblHall.setText(" ");
        }
                ;

        statusTextArea.setText(status);




    }



    public void setNewData(ObservableList<StudentInfo> selectedItems) {
    }


    public void setData(StudentInfo stinfo) {

        lblReceipt.setText(stinfo.getReceiptID());
        lblLname.setText(stinfo.getLname());
        lblFname.setText(stinfo.getFname());
        lblMname.setText(stinfo.getMname());
        lblDob.setText(stinfo.getDob());
        lblgender.setText(stinfo.getGender());
        lblNationality.setText(stinfo.getNationality());
        lblEmail.setText(stinfo.getEmail());
        lblResidentialAddress.setText(stinfo.getResAddress());
        lblCourse.setText(stinfo.getCourse());
        lblHall.setText(stinfo.getHall());
        lblStatus.setText(stinfo.getStatus());
    }
}
