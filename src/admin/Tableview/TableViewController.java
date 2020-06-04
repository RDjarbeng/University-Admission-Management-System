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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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


    

    
    @FXML
    void handleViewRow(MouseEvent event) {
//      get selected student
        StudentInfo stinfo = tableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/viewandedit/VnE.fxml"));
            if (stinfo == null) {
                System.out.println("Nothing selected");
            }
            else {

                try {
                    System.out.println("fetching student");
//                    rs = mydatabase.getStudentFromDatabase(stinfo.getReceiptID());
                    mydatabase.fillStudentDetails(stinfo.getReceiptID(), stinfo);
                    //oblist.add(stinfo );

                } catch (SQLException throwables) {
                    System.out.println("Could not fetch student from DB");
                    throwables.printStackTrace();
                }
        try {
            System.out.println("Hello table controller 1");
            loader.load();
            VnEController vneController = loader.getController();
            vneController.setData(stinfo);
            Parent root1 = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("(Admin)Student details");
            ((Node)(event.getSource())).getScene().getWindow().hide();
            stage.show();



        }
      catch (Exception ex) {
            ex.printStackTrace();
        }

                
            
               
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
                tableViewTitle.setText(Database.APP_STATUS_ACCEPTED +" APPLICANTS");
            }else if(table == 3)
            {
                tableViewTitle.setText(Database.APP_STATUS_REJECT +" APPLICANTS");
                rs = mydatabase.getRejectedStudents();
            }


            while (rs.next()) {

                StudentInfo student = new StudentInfo(
                        rs.getString(Database.STUDENT_RECEIPTID),
                        rs.getString(Database.STUDENT_LASTNAME),
                        rs.getString(Database.STUDENT_FIRSTNAME));

                student.setDateCreated(rs.getTimestamp(Database.APP_DATE_CREATED).toString());

            oblist.add(student);

            }

        } catch (SQLException ex) {
            System.out.println("Database connectivity error!");
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
        colReceipt.setCellValueFactory(new PropertyValueFactory<>("receiptID"));
        colnLname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colnFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colnMname.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));


        tableView.setItems(oblist);
        try {
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    

}

