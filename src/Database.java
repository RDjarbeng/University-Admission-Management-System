import javax.xml.transform.Result;
import java.sql.*;

/**
 * Contains functions to access database
 * Need to include derby libraries to run
 * <p>
 * todo:
 * add foreign keys and auto increment values
 * admin View to view all tables
 */
public class Database {

    final String DB_URL = "jdbc:derby:universityDB;create=true";

    private String[][] tableData;
    private String [] columnNames;

    //final String DBURL ="jdbc:derby:universityDB;";
    Statement stmt;

    Connection conn;

    //constructor
    public Database() {
            getDatabaseConnection();
            System.out.println("Connection established");

    }



    /**
     * closes the resources used to connect to the database
     */
    public void closeConnection() throws SQLException {


            stmt.close();
            conn.close();

    }

    /**
     * @param query string to be input in SQL select statement
     * @return ResultSet containing the Results
     * throws SQL exception
     */
    public ResultSet executeSelectQuery(String query) throws SQLException {

        return stmt.executeQuery(query);

    }

    /**
     * @param query string to be input in SQL select statement
     * run getColumnNames, getTableData after execution
     * throws SQL exception
     */
    public void selectQuery(String query) throws SQLException {

        ResultSet result = stmt.executeQuery(query);

        int numRows = getNumRows(result);
        ResultSetMetaData meta = result.getMetaData();

        columnNames = new String[meta.getColumnCount()];

        for(int i =0; i< meta.getColumnCount(); i++){
            //get column name
            columnNames[i] = meta.getColumnLabel(i+1);
        }

        tableData = new String[numRows][meta.getColumnCount()];

        for(int col =0; col<numRows; col++)
        {
            //get rows for every column as array of Strings
            tableData[col] = arrayOfStrings(result, numRows, col+1);
        }

    }

    //setters and getters

    public String[][] getTableData() {
        return tableData;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    //INITIALIZATION
    public void createTables(String sql) throws SQLException {
        stmt.execute(sql);
    }



    /**
     * @param md
     * @throws SQLException
     *intended for the admin
     * */
    public void displayTableInfo(ResultSetMetaData md) throws SQLException {
        int col = md.getColumnCount();
        System.out.println("Number of Column : " + col);
        System.out.println("Columns Name: ");
        for (int i = 1; i <= col; i++) {
            String col_name = md.getColumnName(i);
            System.out.println(col_name + " " + md.getColumnTypeName(i));
        }
    }

    /**
     * Create connection to the database - JDBC
     * //assignes a statement object to the stmt variable
     * Closes the program if no connectivity is established
     * todo
     * display error message instead of quitting application
     */
    private void getDatabaseConnection() {
        try {
            // Create a connection to the database.
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (Exception ex) {
            System.out.println("Database Connectivity error ");
            ex.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * @param res the result set who's rows are to be counted
     * @return the number of rows
     * @throws SQLException
     */
    public int getNumRows(ResultSet res) throws SQLException{
        res.last();
        int numRows = res.getRow();
        res.first();
        return numRows;
    }



    /**
     * @param resultSet resultSet to return strings from
     * @param numRows rows in the result set
     * @param colPosition  integer position of column in the result set to be changed to String array
     *                     (columns start from 1)
     * @return desired String array
     * @throws SQLException
     */
    public String[] arrayOfStrings(ResultSet resultSet, int numRows, int colPosition) throws SQLException{
        String data[] = new String[numRows];
        for(int index =0 ; index<numRows; index++)
        {
            //store String values of interest in the array
            data[index] = resultSet.getString(colPosition);

            //go to next row in the result set
            resultSet.next();
        }
        return data;
    }

    /**
     * @param resultSet resultSet to return strings from
     * @param numRows rows in the result set
     * @param colPosition  String Description of column in the result set to be changed to String array
     * @return desired String array
     * @throws SQLException
     */
    public String[] arrayOfStrings(ResultSet resultSet, int numRows, String colPosition) throws SQLException{
        String data[] = new String[numRows];
        for(int index =0 ; index<numRows; index++)
        {
            //store String values of interest in the array
            data[index] = resultSet.getString(colPosition);

            //go to next row in the result set
            resultSet.next();
        }
        return data;
    }

    public static void main(String[] args) {
        Database mydatabase = new Database();


        try {

            ResultSet         result = mydatabase.executeSelectQuery("Select * from STUDENT");
            ResultSetMetaData md     = result.getMetaData();
            mydatabase.displayTableInfo(md);


        } catch (SQLException ex) {
            System.out.println("Create table failed \n" + ex.getMessage());
        }
        System.out.println("Complete");

    }

    public void executeQuery(String userStatement) throws SQLException {
        stmt.executeQuery(userStatement);
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