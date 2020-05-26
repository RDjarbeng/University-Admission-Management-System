package admin.tableview;

import admin.viewandedit.VnEController;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class TableViewController implements Initializable{

    @FXML
    private TableView<StudentInfo> tableView;

    @FXML
    private TableColumn<StudentInfo, String> colPayment;
    @FXML
    private TableColumn<StudentInfo, String> colnLname;
    @FXML
    private TableColumn<StudentInfo, String> colnFname;
    @FXML
    private TableColumn<StudentInfo, String> colnMname;
    @FXML
    private TableColumn<StudentInfo, String> colnExt;
    @FXML
    private TableColumn<StudentInfo, String> colDob;
    @FXML
    private TableColumn<StudentInfo, String> colnGender;
    @FXML
    private TableColumn<StudentInfo, String> colnFather;
    @FXML
    private TableColumn<StudentInfo, String> colnMother;
    @FXML
    private TableColumn<StudentInfo, String> colnContact;
    @FXML
    private TableColumn<StudentInfo, String> colnEmail;
    @FXML
    private TableColumn<StudentInfo, String> colnAddress;
    @FXML
    private TableColumn<StudentInfo, String> colnCourse;
    @FXML
    private TableColumn<StudentInfo, String> colnHall;
    @FXML
    private TableColumn<StudentInfo, Blob> colnResults;
    @FXML
    private TableColumn<StudentInfo, Button> colnAction;
    @FXML
    private JFXTextArea txtDisplay; 
    
    
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    
    ObservableList<StudentInfo> oblist = FXCollections.observableArrayList();
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/viewandedit/VnE.fxml"));

    

    
    @FXML
    void handleViewRow(MouseEvent event) {

        StudentInfo stinfo = tableView.getSelectionModel().getSelectedItem();
            if (stinfo == null) {
                System.out.println("Nothing selected");
            }
            else { 
                String a= stinfo.getAddress();
                String b=stinfo.getContact();
                String c=stinfo.getCourse();
                String d=stinfo.getEmail();
                String e=stinfo.getDob();
                String f=stinfo.getExten();
                String g=stinfo.getFather();
                String h=stinfo.getFname();
                String i=stinfo.getGender();
                String j=stinfo.getHall();
                String k=stinfo.getLname();
                String l=stinfo.getMname();
                String m=stinfo.getMother();
                String n=stinfo.getPayment();
                
                
                
        try {
            System.out.println("Hello table controller 1");
            loader.load();
            
        } 
      catch (Exception ex) {
            ex.printStackTrace();
        }
       VnEController vneController = loader.getController();
        vneController.setData(n,k,h,l,f,e,i,g,m,b,d,a,c,j);
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
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/schoolsystem", "root", "");
            
            rs = connection.createStatement().executeQuery("select * from stregister");
            
            while (rs.next()) {
            oblist.add(new StudentInfo(rs.getString("payment"),  rs.getString("lname"), rs.getString("fname"), rs.getString("mname"),
            rs.getString("exten"), rs.getString("dob"), rs.getString("gender"), rs.getString("father"), rs.getString("mother"), rs.getString("contact"),
            rs.getString("email"), rs.getString("address"), rs.getString("course"), rs.getString("hall"), rs.getBlob(15)));
            }
            
            
            
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colnLname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colnFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colnMname.setCellValueFactory(new PropertyValueFactory<>("mname"));
        colnExt.setCellValueFactory(new PropertyValueFactory<>("colnExt"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colnFather.setCellValueFactory(new PropertyValueFactory<>("father"));
        colnMother.setCellValueFactory(new PropertyValueFactory<>("mother"));
        colnContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colnCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        colnHall.setCellValueFactory(new PropertyValueFactory<>("hall"));
        colnResults.setCellValueFactory(new PropertyValueFactory<>("results"));

        
  
        
        tableView.setItems(oblist);
        
            
               }

    

}

