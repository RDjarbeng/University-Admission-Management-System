/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.register;

import AdmissionSystem.Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Student;
import model.StudentInfo;
import student.dashboard.DashboardController;

import javax.swing.JOptionPane;


/**
 * FXML Controller class
 * @author Richard
 * @author kezia
 */
public class RegisterUIController implements Initializable {
    @FXML
    private Label registerTitle;

    @FXML
    private TextField txtSurname;

    @FXML
    private TextField txtFname;

    @FXML
    private TextField txtMName;

    @FXML
    private TextField txtNationality;

    @FXML
    private TextField txtDOB;

    @FXML
    private TextField txtNum;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextArea txtAdd;

    @FXML
    private TextField txtProg;

    @FXML
    private TextField txtHall;

    @FXML
    private Button btnSubmitReg;

    @FXML
    private TextField txtReceipt;

    @FXML
    private ListView<String> listview;

    @FXML
    private Button btnChPdf;

    @FXML
    private JFXRadioButton genderMaleRButton;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton genderFemaleCheck;

    @FXML
    private Label registerStatusLabel;


    //backend
    private Database myDatabase;
    private PreparedStatement pst;

    private FileInputStream fis;
    
    String newPath;
    String s_file;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //myDatabase = DBConnection.dbconnect();

        registerTitle.setText("Registration ("+ DashboardController.getUser()+")");
        txtReceipt.setText(DashboardController.getUser());
        registerStatusLabel.setText("");
       
    }    


     
    @FXML
    private void handleRegSubmit(ActionEvent event) {

        String receipt = txtReceipt.getText();
     String lname = txtSurname.getText();
     String fname = txtFname.getText();
     String mname = txtMName.getText();
     String nationality = txtNationality.getText();
     String dob = txtDOB.getText();
        String gender;
     if(genderMaleRButton.isSelected()) {
         gender = "MALE";
     } else {
         gender = "FEMALE";
     }
     String phoneNum = txtNum.getText();
     String email = txtEmail.getText();
     String address = txtAdd.getText();
     String course = txtProg.getText();
     String hall = txtHall.getText();

     try {
//            Class.forName("com.mysql.jdbc.Driver");


            if(!(lname.isEmpty() || fname.isEmpty() || dob.isEmpty() || nationality.isEmpty() || phoneNum.isEmpty())){
                if(!newPath.isEmpty()){

                    //if file has been selected
                    myDatabase = new Database();
                    StudentInfo applicant = new StudentInfo();

                    //STUDENT TABLE
                    applicant.setReceiptID(receipt);
                    applicant.setLname(lname);
                    applicant.setFname(fname);
                    applicant.setMname(mname);
                    applicant.setDob(dob);
                    applicant.setNationality(nationality);
                    applicant.setGender(gender);
                    applicant.setPhoneNumber(phoneNum);
                    applicant.setEmail(email);
                    applicant.setResAddress(address);
                    //APPLICATION TABLE
                    applicant.setCourse(course);
                    applicant.setHall(hall);

                    int i =0;
                    myDatabase.insertStudent( applicant);

                    //todo
                    //move this to Database.java
                    String insert ="INSERT INTO "+Database.APPLICATION_TABLE +
                            "(RECEIPTID,"+
                            Database.FIRSTCHOICE+", "+
                            Database.RESULTS+") "+
                            "VALUES(?,?,?)";
                    System.out.println("Insert: "+insert);

                    File resultPDF = new File(newPath);
                    fis = new FileInputStream(resultPDF);
                    System.out.println("FIle size :" +fis.getChannel().size());

                    pst = myDatabase.getConn().prepareStatement(insert);
                    pst.setString(1, receipt);
                    pst.setString(2, course);
                    pst.setBinaryStream(3,fis,fis.available());

                     i =pst.executeUpdate();



                    System.out.println("Student Registered!");



                    if (i ==1) {
                        registerStatusLabel.setText("Registration Successful");
                        JOptionPane.showMessageDialog(null, "Registration Successful");

                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/student/dashboard/Dashboard.fxml"));
                            System.out.println("Hello loginpage controller1");
                            Parent root1 = (Parent)fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.initStyle(StageStyle.TRANSPARENT);
                            stage.setScene(new Scene(root1));
                            stage.show();
                            System.out.println("Hello loginpage controller2222");
                            ((Node)(event.getSource())).getScene().getWindow().hide();


                        }
                        catch(Exception e){
                            e.printStackTrace();

                        }
                        finally {
                            pst.close();
                        }

                    }else {
                        registerStatusLabel.setText("Something went wrong");
                    }

                }else{
                    registerStatusLabel.setText("No file to upload");
                }

            }else{
                registerStatusLabel.setText( "Fields marked * are required");
            }



       
        } 
        catch (SQLException ex){
            ex.printStackTrace();
            registerStatusLabel.setText("Something went wrong");
        } catch (IOException e) {
         e.printStackTrace();
         registerStatusLabel.setText("File Error!");
     }


    }

    @FXML
    private void handlePdfChoose(ActionEvent event)  {


        
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
        new ExtensionFilter("PDF File", "*.pdf"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile.exists()) {
        s_file = selectedFile.getAbsolutePath();
        newPath = s_file.replace('\\', '/');
        System.out.println("FILE: "+s_file);
        System.out.println("New FILE: "+newPath);
        System.out.println("file size: "+ selectedFile.length());
        listview.getItems().add(selectedFile.getName());
        }
        else {
        registerStatusLabel.setText( "Invalid File Format. Upload only pdf's");

        }
        
        
    }
    
    
    

    
    
    }

     
    
    

