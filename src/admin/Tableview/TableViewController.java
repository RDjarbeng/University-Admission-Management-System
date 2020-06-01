package admin.Tableview;

import AdmissionSystem.Database;
import admin.viewandedit.VnEController;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.StudentInfo;


public class TableViewController implements Initializable{

    @FXML
    private TableView<StudentInfo> tableView;

    @FXML
    private TableColumn<StudentInfo, String> colReceipt;
    @FXML
    private TableColumn<StudentInfo, String> colnLname;
    @FXML
    private TableColumn<StudentInfo, String> colnFname;
    @FXML
    private TableColumn<StudentInfo, String> colnMname;

    @FXML
    private Label tableViewTitle;

    @FXML
    private JFXTextArea txtDisplay; 

    public static int table=0;
    
    Connection connection;
    PreparedStatement pst;
    ResultSet rs, result;
    Database mydatabase;
    
    ObservableList<StudentInfo> oblist = FXCollections.observableArrayList();
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/viewandedit/VnE.fxml"));

    

    
    @FXML
    void handleViewRow(MouseEvent event) {
//      get selected student
        StudentInfo stinfo = tableView.getSelectionModel().getSelectedItem();

            if (stinfo == null) {
                System.out.println("Nothing selected");
            }
            else {

                try {
                    rs = mydatabase.getStudentFromDatabase(stinfo.getReceiptID());
                    System.out.println("Rows returned = "+ mydatabase.getNumRows(rs));


                if (rs.next()) {
                    System.out.println("inside while");
                     stinfo = new StudentInfo(
                            rs.getString(Database.STUDENT_RECEIPTID),
                            rs.getString(Database.STUDENT_LASTNAME),
                            rs.getString(Database.STUDENT_FIRSTNAME),
                            rs.getString(Database.STUDENT_MIDDLENAME),
                            rs.getString(Database.STUDENT_DOB),
                            rs.getString(Database.STUDENT_GENDER),
                            rs.getString(Database.STUDENT_NATIONALITY),
                            rs.getString(Database.STUDENT_EMAIL),
                            rs.getString(Database.STUDENT_RESIDENTIALADDRESS),
                            rs.getString(Database.STUDENT_POSTALADDRESS)
                    );
                    System.out.println("filling student details");

                     stinfo.setCourse(rs.getString(Database.FIRSTCHOICE));
                    stinfo.setHall(rs.getString(Database.FIRSTHALL));
                    stinfo.setStatus(rs.getString(Database.APP_STATUS));

                    System.out.println("Student status: "+stinfo.getStatus());

                    //oblist.add(stinfo );

                }else{throw  new SQLException("Could not fetch student");}
                } catch (SQLException throwables) {
                    System.out.println("Could not fetch student from DB");
                    throwables.printStackTrace();
                }
//                String a= stinfo.getPostalAddress();
//                String b=stinfo.getContact();
//                String c=stinfo.getCourse();
//                String d=stinfo.getEmail();
//                String e=stinfo.getDob();
//                String f=stinfo.getNationality();
//                String g="";//remember to delete
//                String h=stinfo.getFname();
//                String i=stinfo.getGender();
//                String j=stinfo.getHall();
//                String k=stinfo.getLname();
//                String l=stinfo.getMname();
//                String m=;
//                String n=
                
                
                
        try {
            System.out.println("Hello table controller 1");
            loader.load();
            
        } 
      catch (Exception ex) {
            ex.printStackTrace();
        }
       VnEController vneController = loader.getController();
        vneController.setData(
                stinfo.getReceiptID(),
                stinfo.getLname(),
                stinfo.getFname(),
                stinfo.getMname(),
                stinfo.getDob(),
                stinfo.getGender(),
                stinfo.getNationality(),
                stinfo.getEmail(),
                stinfo.getResAddress(),
                stinfo.getCourse(),
                stinfo.getHall(),
                stinfo.getStatus());
            Parent root1 = loader.getRoot();
           Stage stage = new Stage();
          stage.setScene(new Scene(root1));
          stage.show();
           System.out.println("Hello table controller 2");
                
            
               
            }

    }

    @Override
    public void initialize(URL location, ResourceBundle resource){
        
        try {
             mydatabase = new Database();


            //get students who have not yet been approved by the admin
            if(table == 1){
                rs = mydatabase.getPendingStudents();
                tableViewTitle.setText(Database.APP_STATUS_PENDING +" APPLICANTS");
            }else if(table == 2){
                rs = mydatabase.getAcceptedStudents();
                tableViewTitle.setText(Database.APP_STATUS_ACCEPT +" APPLICANTS");
            }else if(table == 3)
            {
                tableViewTitle.setText(Database.APP_STATUS_REJECT +" APPLICANTS");
                rs = mydatabase.getRejectedStudents();
            }


            while (rs.next()) {
            oblist.add(new StudentInfo(
                    rs.getString(Database.STUDENT_RECEIPTID),
                    rs.getString(Database.STUDENT_LASTNAME),
                    rs.getString(Database.STUDENT_FIRSTNAME)));
            }

        } catch (SQLException ex) {
            System.out.println("Database connectivity error!");
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
        colReceipt.setCellValueFactory(new PropertyValueFactory<>("receiptID"));
        colnLname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colnFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colnMname.setCellValueFactory(new PropertyValueFactory<>("mname"));







        tableView.setItems(oblist);
        
            
               }

    

}

