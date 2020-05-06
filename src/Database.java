import java.sql.*;

/**
 * Contains functions to access database
 *
 * todo:
 * admin View to view all tables
 */
public class Database {

    final String DBURL ="jdbc:derby:universityDB;create=true";
    //final String DBURL ="jdbc:derby:universityDB;";
    Statement stmt;

    Connection conn;
    //constructor
    public Database(){
        try {
            conn = DriverManager.getConnection(DBURL);
             stmt = conn.createStatement();
            System.out.println("Connection established");

        }catch (SQLException ex){
            System.out.println("SQL Exception error: " +ex.getMessage());
        }

    }

    /**
     * @param query  string to be input in SQL select statement
     * @return ResultSet containing the Results
     * throws SQL exception
     */
    public ResultSet selectQuery(String query) throws SQLException{

        return stmt.executeQuery(query);

    }

    /**
     * closes the resources used to connect to the database
     */
    public  void closeConnection(){

        try {
            stmt.close();
            conn.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    /*
    * View all tables and their details
    * */
    public void adminView(){

    }
    //INITIALIZATION
    public void createTables(String sql) throws SQLException{
        stmt.execute(sql);
    }


    public static void main(String[] args) {
        new Database();
        System.out.println("Complete");

    }


};
