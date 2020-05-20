package admin.dashboard;

import dbconnection.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminDashController implements Initializable{

    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, String> colnName;


    @FXML
    private TableColumn<Person, String> colnCourse;
    
    ObservableList<Person> oblist = FXCollections.observableArrayList();
    
    Connection connection;
    ResultSet rs;

    
    @Override
    public void initialize(URL location, ResourceBundle resource){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/school", "root", "");
            
            rs = connection.createStatement().executeQuery("select * from record");
            
            while (rs.next()) {
            oblist.add(new Person(rs.getString("name"),  rs.getString("course")));
            }
            
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
        colnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colnCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        
        tableView.setItems(oblist);
        

    }

}
