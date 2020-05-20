/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.register;

import dbconnection.DBConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    
    private Connection connection1;
    private PreparedStatement pst;
    @FXML
    private TextField txtReceipt;
    @FXML
    private ListView listview;
    @FXML
    private Button btnChPdf;
    
    String newPath;
    String s;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connection1 = DBConnection.dbconnect();
       
    }    


     
    @FXML
    private void handleRegSubmit(ActionEvent event) {
        
     String payment = txtReceipt.getText();   
     String lname = txtSurname.getText();
     String fname = txtFname.getText();
     String mname = txtMName.getText();
     String ext = txtExt.getText();
     String dob = txtDOB.getText();
     String gender = txtGender.getText();
     String father = txtPops.getText();
     String mother = txtMoms.getText();
     String num = txtNum.getText();
     String email = txtEmail.getText();
     String address = txtAdd.getText();
     String prog = txtProg.getText();
     String hall = txtHall.getText();
    
     try { 
         
            Class.forName("com.mysql.jdbc.Driver");
            connection1 = DriverManager.getConnection("jdbc:mysql://localhost/schoolsystem", "root", "");
            
            File resultPDF = new File(newPath);
            FileInputStream fis = new FileInputStream(resultPDF);

            pst = connection1.prepareStatement("insert into stregister(payment, lname, fname, mname, exten, dob, gender, father, mother, contact, email, address, course, hall, results)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, payment);
            pst.setString(2, lname);
            pst.setString(3, fname);
            pst.setString(4, mname);
            pst.setString(5, ext);
            pst.setString(6, dob);
            pst.setString(7, gender);
            pst.setString(8, father);
            pst.setString(9, mother);
            pst.setString(10, num);
            pst.setString(11, email);
            pst.setString(12, address);
            pst.setString(13, prog);
            pst.setString(14, hall);
            pst.setBlob(15, fis);
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
        catch (Exception ex){
            ex.printStackTrace();        
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
        JOptionPane.showMessageDialog(null, "Invalid File Format!");

        }
        
        
    }
    
    
    
    
    
    
    }

     
    
    

