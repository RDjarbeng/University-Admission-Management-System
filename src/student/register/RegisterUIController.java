/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.register;

import AdmissionSystem.Database;
import dbconnection.DBConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author kezia
 */
public class RegisterUIController implements Initializable {

    @FXML
    private TextField txtSurname;

    @FXML
    private TextField txtFname;

    @FXML
    private TextField txtMName;

    @FXML
    private TextField txtExt;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtPops;

    @FXML
    private TextField txtMoms;

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
    private TextField txtDOB;
    
    private Database myDatabase;
    private PreparedStatement pst;
    @FXML
    private TextField txtReceipt;
    @FXML
    private ListView listview;
    @FXML
    private Button btnChPdf;

    private FileInputStream fis;
    
    String newPath;
    String s;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //myDatabase = DBConnection.dbconnect();
       
    }    


     
    @FXML
    private void handleRegSubmit(ActionEvent event) {
        String receipt = txtReceipt.getText();
     String lname = txtSurname.getText();
     String fname = txtFname.getText();
     String mname = txtMName.getText();
     //String ext = txtExt.getText();
     String dob = txtDOB.getText();
     String gender = txtGender.getText();
     String father = txtPops.getText();
//     String mother = txtMoms.getText();
     String num = txtNum.getText();
     String email = txtEmail.getText();
     String address = txtAdd.getText();
     String prog = txtProg.getText();
     String hall = txtHall.getText();
    
     try { 
         
            Class.forName("com.mysql.jdbc.Driver");
            myDatabase = new Database();


            if(newPath != null){
                File resultPDF = new File(newPath);
                fis = new FileInputStream(resultPDF);
            }



            pst = myDatabase.getPreparedStatement("insert into student (ReceiptID, LastName, FirstName, MiddleName, " +
                    "dob, " +
                    "gender, " +
                    " nationality, phoneNumber, email, ResidentialAddress) values(?,?,?,?,?,?,?,?,?,?");

            pst.setString(1, receipt);
            pst.setString(2, lname);
            pst.setString(3, fname);
            pst.setString(4, mname);
            pst.setString(5, dob);
            pst.setString(6, gender);
            pst.setString(7, father);
            //pst.setString(9, mother);
            pst.setString(8, num);
            pst.setString(9, email);
            pst.setString(10, address);
            //pst.setBlob(11, fis);
            System.out.println("SAVED!!!!");

            int i = pst.executeUpdate();
            
            if (i ==1) {
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
            
            }
       
        } 
        catch (SQLException | FileNotFoundException ex){
            ex.printStackTrace();        
        } catch (ClassNotFoundException e) {
         e.printStackTrace();
     }


    }

    @FXML
    private void handlePdfChoose(ActionEvent event) throws IOException {
        
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
        new ExtensionFilter("PDF File", "*.pdf"));
        File selectedFile = fc.showOpenDialog(null);
        s = selectedFile.getAbsolutePath();
        newPath = s.replace('\\', '/');
        
        
        if (selectedFile != null) {
        listview.getItems().add(selectedFile.getName());
        }
        else {
        JOptionPane.showMessageDialog(null, "Invalid File Format! upload only pdf's");

        }
        
        
    }
    
    
    
    
    
    
    }

     
    
    

