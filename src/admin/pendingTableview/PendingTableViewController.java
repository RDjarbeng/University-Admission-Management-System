package admin.pendingTableview;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class PendingTableViewController implements Initializable{

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
    private JFXTextArea txtDisplay; 
    
    
    Connection connection;
    PreparedStatement pst;
    ResultSet rs, result;
    Database mydatabase;
    
    ObservableList<StudentInfo> oblist = FXCollections.observableArrayList();
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/viewandedit/VnE.fxml"));

    

    
    @FXML
    void handleViewRow(MouseEvent event) {

        StudentInfo stinfo = tableView.getSelectionModel().getSelectedItem();
            if (stinfo == null) {
                System.out.println("Nothing selected");
            }
            else {
                String fullDetails ="SELECT * "+
//                        Database.STUDENT_TABLE+"."+Database.STUDENT_RECEIPTID +", "+
//                        Database.STUDENT_LASTNAME+", "+
//                        Database.STUDENT_FIRSTNAME+", "+
//                        Database.STUDENT_MIDDLENAME+", "+
//                        Database.STUDENT_NATIONALITY+", "+
//                        Database.STUDENT_DOB+", "+
//                        Database.STUDENT_GENDER+", "+
//                        Database.STUDENT_POSTALADDRESS+", "+
//                        Database.STUDENT_RESIDENTIALADDRESS+", "+
//                        Database.STUDENT_EMAIL+", "+
//                        Database.FIRSTCHOICE+ ", "+
//                        Database.FIRSTHALL+" "+
                        " FROM "+Database.STUDENT_TABLE +", "+
                        Database.APPLICATION_TABLE +" "+
                        " WHERE "+
                        Database.STUDENT_TABLE+"." +Database.STUDENT_RECEIPTID+ " = ' "+stinfo.getReceiptID()
                        +" AND "+ Database.STUDENT_TABLE+"." +Database.STUDENT_RECEIPTID+ "' = "+
                        Database.APPLICATION_TABLE + "."+Database.APP_RECEIPTID


                        ;
                System.out.println("Details query: "+fullDetails);
                try {
                    rs = mydatabase.executeSelectQuery(fullDetails);


                while (rs.next()) {
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

                     stinfo.setCourse(rs.getString(Database.FIRSTCHOICE));
                    stinfo.setHall(rs.getString(Database.FIRSTHALL));

                    //oblist.add(stinfo );

                }
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
                stinfo.getPostalAddress(),
                stinfo.getCourse(),
                stinfo.getHall()
                );
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
            rs = mydatabase.getPendingStudents();

            while (rs.next()) {
            oblist.add(new StudentInfo(
                    rs.getString(Database.STUDENT_RECEIPTID),
                    rs.getString(Database.STUDENT_LASTNAME),
                    rs.getString(Database.STUDENT_FIRSTNAME)));
            }

        } catch (SQLException ex) {
            System.out.println("Database connectivity error!");
            Logger.getLogger(PendingTableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
        colReceipt.setCellValueFactory(new PropertyValueFactory<>("receiptID"));
        colnLname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colnFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colnMname.setCellValueFactory(new PropertyValueFactory<>("mname"));







        tableView.setItems(oblist);
        
            
               }

    

}

