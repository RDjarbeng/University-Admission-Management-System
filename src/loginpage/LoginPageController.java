/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginpage;

import AdmissionSystem.Database;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.swing.JOptionPane;

import static java.lang.Math.random;

/**
 * FXML Controller class
 *
 * @author kezia
 */
public class LoginPageController implements Initializable {
    
    @SuppressWarnings("unsused")
    private LoginPage loginpage;

    @FXML
    private Pane paneStudent;
    @FXML
    private JFXTextField txtPaymentReceipt;
    @FXML
    private JFXPasswordField txtPin;
    @FXML
    private JFXButton btnStudentLogin;
    @FXML
    private JFXButton btnAdminLoginSwitch;
    @FXML
    private ImageView btnClose;
    @FXML
    private Pane paneAdmin;
    @FXML
    private JFXTextField txtAdminUsername;
    @FXML
    private JFXPasswordField txtAdminPassword;
    @FXML
    private JFXButton btnAdminLogin;
    @FXML
    private JFXButton btnStudentLoginSwitch;
    @FXML
    private ImageView btnBack;
    @FXML
    private Label lblPinIncorrect;
    @FXML
    private Label loginStatusLabel;

    @FXML
    private ImageView loginLogo;


    /**
     * Initializes the controller class.
     */
    

    @FXML
    private void handleLoginSwitchAction(ActionEvent event) {
        if (event.getSource().equals(btnStudentLoginSwitch)) {

            paneStudent.toFront();
        }

        if (event.getSource().equals(btnAdminLoginSwitch)) {
            paneAdmin.toFront();
        }

    }

    @FXML
    private void handleMovementToggle(MouseEvent event) {

        if (event.getSource() == btnClose) {
            System.exit(0);
        }

        if (event.getSource() == btnBack) {
            paneStudent.toFront();

        }
    }
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    
    @FXML 
    private void handleStudentLogin(MouseEvent event) {
        
        
        String receipt = txtPaymentReceipt.getText();
        String pin = txtPin.getText();
        
        if (receipt.equals("") && pin.equals(""))
        {
            //System.out.println("Hello");
            loginStatusLabel.setText("Username/password field cannot be empty!");
            //JOptionPane.showMessageDialog(null, "Incorrect receipt or pin!");
            txtPaymentReceipt.requestFocus();

        }
        
        else {
            
            try {
                loginStatusLabel.setText("Connecting...");
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/universitydb", "root", "");
                preparedStatement = connection.prepareStatement("select * from users where username=? and password=?");
                
                preparedStatement.setString(1, receipt);
                preparedStatement.setString(2, pin);
                
                resultSet = preparedStatement.executeQuery();
                
                
                if (resultSet.next())
                {
                    loginStatusLabel.setText("Login successful! ");
//                    JOptionPane.showMessageDialog(null, "Login Successful!");
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
                }
                else 
                {
                    loginStatusLabel.setText("Invalid username or password!");

                    //JOptionPane.showMessageDialog(null, "Login Failed!");
                        //txtPaymentReceipt.setText("");//clear text field
                        txtPaymentReceipt.requestFocus();

                        txtPin.setText("");//clear passwordfield
                }
            }
            catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
                loginStatusLabel.setText("Error connecting to server ");
            }
        }
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


        //animate the UG badge
        fadeAnimation();

    }

    private void fadeAnimation() {
        //Animate logo
        Timeline timeline = new Timeline();

        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0
                        new KeyValue(loginLogo.opacityProperty(), 0)

                ),
                new KeyFrame( Duration.seconds(4), // set end position at 3s
                        new KeyValue(loginLogo.opacityProperty(), 1)
                )
        );
        // play  animation
        timeline.play();
    }


    @FXML
    private void handleAdminLogin(ActionEvent event) {
        
        try {
            String adminPassword =txtAdminPassword.getText();
            String adminUser = txtAdminUsername.getText();

            resultSet =validateAdmin(adminUser, adminPassword);


            if (resultSet.next())
            {
                //loginStatusLabel.setText("Login successful! ");
//                    JOptionPane.showMessageDialog(null, "Login Successful!");

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin/dashboard/AdminDashboard.fxml"));
                System.out.println("Hello adminpage");
                Parent root1 = (Parent)fxmlLoader.load();

                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(root1));
                stage.show();

                ((Node)(event.getSource())).getScene().getWindow().hide();
                }else{
                System.out.println("invalid user "+adminUser+ " "+adminPassword);
            }

                    
                    }
        catch (SQLException e){
            System.out.println("Database connectivity error");
            e.printStackTrace();
        }
        
                    catch(Exception e){
                        e.printStackTrace();

                    }


    }
    public ResultSet validateAdmin(String user, String password) throws SQLException {
        Database myDatabase = new Database();
        preparedStatement = myDatabase.getConn().prepareStatement("select * from admin where username=? and " +
                "password=?");

        preparedStatement.setString(1, user);
        preparedStatement.setString(2, password);

        return preparedStatement.executeQuery();

    }








}
