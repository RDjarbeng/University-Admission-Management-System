/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.viewandedit;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import AdmissionSystem.Database;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.StudentInfo;

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
    private JFXButton btnReject;

    @FXML
    private Label lblStatus;

    Database updateDatabase;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TOO
        btnAccept.setOnAction(actionEvent ->{
                    System.out.println("accepted");
                    updateDatabase = new Database();
                    try {
                        //update the student information
                        updateDatabase.updateStudentStatus(Database.APP_STATUS_ACCEPT, lblReceipt.getText());
                        //update the UI

                        ResultSet  resultSet= updateDatabase.getStudentFromDatabase(lblReceipt.getText());

                        lblStatus.setText((resultSet.next()?resultSet.getString(Database.APP_STATUS):
                                                   ("Error...")));

                        updateDatabase.closeConnection();
                    } catch (SQLException throwables) {
                        System.out.println("Problem updating student details");
                        throwables.printStackTrace();
                    }
                }
                );

        btnReject.setOnAction(actionEvent ->{
                    System.out.println("Rejected");
                    updateDatabase = new Database();
                    try {
                        //update the student information
                        updateDatabase.updateStudentStatus(Database.APP_STATUS_REJECT, lblReceipt.getText());
                        //update the UI
                        lblStatus.setText(Database.APP_STATUS_REJECT);

                        updateDatabase.closeConnection();

                    } catch (SQLException throwables) {
                        System.out.println("Problem updating student details");
                        throwables.printStackTrace();
                    }
                }
        );
    }    
    
    public void setData(String receiptID, String lname, String fname, String mname, String dob, String gender,
                        String nationality,
                        String email, String resAddress, String course, String hall, String stinfoStatus) //Blob results)
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
        lblCourse.setText(course);
        lblHall.setText(hall);
        lblStatus.setText(stinfoStatus);

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
