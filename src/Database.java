import java.sql.*;

/**
 * Contains functions to access database
 * Need to include derby libraries to run
 *
 * todo:
 * add foreign keys and auto increment values
 * admin View to view all tables
 */
public class Database {

    final String DB_URL ="jdbc:derby:universityDB;create=true";
    //final String DBURL ="jdbc:derby:universityDB;";
    Statement stmt;

    Connection conn;
    //constructor
    public Database(){
        try {
            getDatabaseConnection();
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
        Database mydatabase =new Database();


        try {

            ResultSet result =mydatabase.selectQuery("Select * from STUDENT");
            ResultSetMetaData md = result.getMetaData();
            mydatabase.displayTableInfo(md);


        }catch (SQLException ex){
            System.out.println("Create table failed \n" +ex.getMessage());
        }
        System.out.println("Complete");

    }

    public void displayTableInfo(ResultSetMetaData md) throws SQLException{
        int col = md.getColumnCount();
        System.out.println("Number of Column : "+ col);
        System.out.println("Columns Name: ");
        for (int i = 1; i <= col; i++){
            String col_name = md.getColumnName(i);
            System.out.println(col_name +" "+ md.getColumnTypeName(i));
        }
    }

    private void getDatabaseConnection()
 {
         try
         {
             // Create a connection to the database.
             conn = DriverManager.getConnection(DB_URL);
             }
         catch (Exception ex)
         {
             System.out.println("Database Connectivity error ");
             ex.printStackTrace();
             System.exit(0);
             }
         }


};

//Admin database commands

//        String createT = "CREATE TABLE Admin ( "+
//                "AdminNumber INT,"+
//                "USERNAME varchar(25),"+
//                "PASSWORD varchar(35),"+
//                " PRIMARY KEY(ADMINNumber)"+
//                ")"
//                ;

        /*
        String createT = "CREATE TABLE admittedStudents ( "+
                "StudentID char(8),"+
                "ApplicationID char(10),"+
                "ReceiptID char(10),"+
                "CourseOfStudy varchar(25),"+
                "HallOfResidence varchar(35),"+
                " PRIMARY KEY(StudentID)"+
                ")"
                ;
        */

//        String createT = "CREATE TABLE APPLICATIONSTATUS ( "+
//                "APPLICATIONNUMBER INT NOT NULL,"+
//                "ApplicationID CHAR(10),"+
//                "AdmissionStatus varchar(15),"+
//                " PRIMARY KEY(APPLICATIONNUMBER)"+
//                ")"

//        "LastName VARCHAR(25),"+
//                "FirstName VARCHAR(25),"+
//                "MiddleInitial VARCHAR(25),"+
//                "DOB DATE,"+
//                "Nationality VARCHAR(25),"+
//                "PhoneNumber varchar(15),"+
//                "ResidentialAddress VARCHAR(50),"+
//                "PostalAddress VARCHAR(50)"+