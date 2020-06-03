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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import student.dashboard.DashboardController;

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
    private JFXTextField txtStudentUsername;
    @FXML
    private JFXPasswordField txtStudentPassword;
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
    private Label userLoginStatusLabel;


    @FXML
    private Label adminLoginStatusLabel;

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
        
        
        String receipt = txtStudentUsername.getText();
        String pin = txtStudentPassword.getText();
        
        if (receipt.equals("") && pin.equals(""))
        {
            //System.out.println("Hello");
            userLoginStatusLabel.setText("Username/password field cannot be empty!");
            //JOptionPane.showMessageDialog(null, "Incorrect receipt or pin!");
            txtStudentUsername.requestFocus();

        }
        
        else {
            
            try {
                if(loginUser(receipt, pin)){
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/student/dashboard/Dashboard.fxml"));
                        DashboardController.setUser(resultSet.getString("username"));
                        System.out.println(resultSet.getString("username"));
//                     System.out.println("Hello loginpage controller1");
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage  stage = new Stage();
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(new Scene(root1));
                        stage.show();
                        System.out.println("Hello loginpage controller2222");
                        ((Node) (event.getSource())).getScene().getWindow().hide();


                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }


            } catch (SQLException ex) {
                Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
                userLoginStatusLabel.setText("Error connecting to server ");
            }
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtStudentUsername.requestFocus();

        //animate the ug logo
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
            String adminUsername = txtAdminUsername.getText();

            resultSet = fetchAdmin(adminUsername, adminPassword);


            if (resultSet.next())
            {
                //loginStatusLabel.setText("Login successful! ");
//                    JOptionPane.showMessageDialog(null, "Login Successful!");
                if(resultSet.getString("Username").equals(adminUsername) && resultSet.getString("Password").equals(adminPassword)) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin/dashboard/AdminDashboard.fxml"));
                    System.out.println("Hello adminpage");
                    Parent root1 = (Parent) fxmlLoader.load();

                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(new Scene(root1));
                    stage.show();

                    ((Node) (event.getSource())).getScene().getWindow().hide();
                }else{
//                System.out.println("invalid user "+adminUser+ " "+adminPassword);
                    adminLoginStatusLabel.setText("Invalid username or password!");

                    //JOptionPane.showMessageDialog(null, "Login Failed!");
                    //txtPaymentReceipt.setText("");//clear text field
                    txtAdminUsername.requestFocus();

                    txtAdminPassword.setText("");//clear passwordfield
                }
                }else{
//                System.out.println("invalid user "+adminUser+ " "+adminPassword);
                adminLoginStatusLabel.setText("Invalid username or password!");

                //JOptionPane.showMessageDialog(null, "Login Failed!");
                //txtPaymentReceipt.setText("");//clear text field
                txtAdminUsername.requestFocus();

                txtAdminPassword.setText("");//clear passwordfield
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


    /**
     * @param username
     * @param password
     * @return true if user exists in the database, false otherwise
     * @throws SQLException
     */
    private boolean loginUser(String username, String password) throws SQLException {
        userLoginStatusLabel.setText("Connecting...");
        Database database = new Database();

        preparedStatement = database.getPreparedStatement("select * from users where username=? and password=?");

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        resultSet = preparedStatement.executeQuery();


        if (resultSet.next())
        {
            if(resultSet.getString("Username").equals(username) && resultSet.getString("Password").equals(password)) {
                userLoginStatusLabel.setText("Login successful! ");
//                    JOptionPane.showMessageDialog(null, "Login Successful!");
                return  true;

            }else
            {
                userLoginStatusLabel.setText("Invalid username or password!");

                //JOptionPane.showMessageDialog(null, "Login Failed!");
                //txtPaymentReceipt.setText("");//clear text field
                txtStudentUsername.requestFocus();

                txtStudentPassword.setText("");//clear passwordfield

            }
        }
        else
        {
            userLoginStatusLabel.setText("Invalid username or password!");

            //JOptionPane.showMessageDialog(null, "Login Failed!");
            //txtPaymentReceipt.setText("");//clear text field
            txtStudentUsername.requestFocus();

            txtStudentPassword.setText("");//clear passwordfield
        }
        return false;
    }

    public ResultSet fetchAdmin(String user, String password) throws SQLException {
        Database myDatabase = new Database();
        preparedStatement = myDatabase.getConn().prepareStatement("select * from admin where username=? and " +
                "password=?");

        preparedStatement.setString(1, user);
        preparedStatement.setString(2, password);

        return preparedStatement.executeQuery();

    }








}
