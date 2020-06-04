/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.viewandedit;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import AdmissionSystem.Database;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private JFXButton btnDownloadResults;


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
    private JFXComboBox<String> comboBoxAssignHall;


    @FXML
    private JFXCheckBox checkboxHallAssigned;

    @FXML
    private Label viewStatusLabel;

    private StudentInfo student;

    Database updateDatabase;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

         viewStatusLabel.setText("");

        btnAccept.setOnAction(actionEvent ->{

                    updateDatabase = new Database();
                    try {
                        //update the student information
                        String studentHall= comboBoxAssignHall.getSelectionModel().getSelectedItem();//get Hall assigned
                        student.setAssignedHall(studentHall);
                        updateDatabase.updateSelectedStudentStatus(Database.APP_STATUS_ACCEPTED, student);
                        //update the UI

                        updateDatabase.fillStudentDetails(lblReceipt.getText(), student);
                        setData(student);
                        viewStatusLabel.setText("Student status changed to "+Database.APP_STATUS_ACCEPTED);



                        updateDatabase.closeConnection();
                    } catch (SQLException throwables) {
//                        System.out.println("Problem updating student details");
                        viewStatusLabel.setText("Problem updating student details");
                        throwables.printStackTrace();
                    }
                }
        );

        btnReject.setOnAction(actionEvent ->{
                    System.out.println("Rejected");
                    updateDatabase = new Database();
                    try {

                        //this should not modify hall information
//                        String studentHall= student.getAssignedHall();
//
//                        if(!checkboxHallAssigned.isSelected())
//                            studentHall=comboBoxAssignHall.getSelectionModel().getSelectedItem();

                        //update the student information
                        updateDatabase.updateRejectedStudentStatus(Database.APP_STATUS_REJECT, lblReceipt.getText());
                        //update the UI
//                        initialize(url, rb);
                        lblStatus.setText(Database.APP_STATUS_REJECT);
                        viewStatusLabel.setText("Student status changed to "+Database.APP_STATUS_REJECT);

                        updateDatabase.closeConnection();

                    } catch (SQLException throwables) {
//                        System.out.println("Problem updating student details");
                        viewStatusLabel.setText("Problem updating student details");
                        throwables.printStackTrace();
                    }
                }
        );

        btnDownloadResults.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateDatabase = new Database();
                try {
                    //download the student results
                    String filePath =updateDatabase.getStudentDocument(lblReceipt.getText());
                    updateDatabase.closeConnection();
                    viewStatusLabel.setText("File downloaded at "+filePath);

                } catch (SQLException throwables) {
//                    System.out.println("Problem downloading student details");
                    viewStatusLabel.setText("Problem downloading student details");
                    throwables.printStackTrace();
                }
            }
        });
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


        comboBoxAssignHall.setItems(FXCollections.observableArrayList(getHalls()));
        comboBoxAssignHall.getSelectionModel().select(0);



    }

    public  ArrayList getHalls(){
        ArrayList<String> Halls = new ArrayList<>();
        Halls.add("No hall assigned");
        Halls.add("Akuafo Hall");
        Halls.add("Legon Hall");
        Halls.add("African Union Hall(PENTAGON)");



        if(lblgender.getText().toUpperCase().trim().equals("MALE")){
            Halls.add("Commonwealth Hall");
        }else{
            Halls.add("Volta Hall");
        }

        return  Halls;
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

        if(!stinfo.getAssignedHall().toUpperCase().equals(Database.APP_HALLASSIGNED_NOHALLASSIGNED)) {
            lblHall.setText(stinfo.getAssignedHall());
        } else {
            lblHall.setText(stinfo.getHall());
        }


        lblStatus.setText(stinfo.getStatus());
        comboBoxAssignHall.setItems(FXCollections.observableArrayList(getHalls()));
        comboBoxAssignHall.getSelectionModel().select(0);

        if(!stinfo.getAssignedHall().toUpperCase().equals(Database.APP_HALLASSIGNED_NOHALLASSIGNED)){
            //if a hall has already been assigned
            checkboxHallAssigned.setSelected(true);
            comboBoxAssignHall.getSelectionModel().select(stinfo.getAssignedHall());
            System.out.println(stinfo.getAssignedHall());
        }
        student = stinfo;
    }
}
