package AdmissionSystem;

import model.StudentInfo;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author rDjarbeng
 * Contains functions to access database(eg: retrive selected applicants)
 * Contains constants for database table names and columns
 * Need to include sql libraries to run
 *
 */
public class Database {

    //Derby
    //final String DB_URL = "jdbc:derby:universityDB;create=true";
    //final String DB_URL = "jdbc:derby:universityDB;";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/universitydb";
    public static final String uname="root";
    String pass ="";

    public static final String STUDENT_TABLE = "STUDENT";
    public static final String STUDENT_RECEIPTID = "RECEIPTID";
    public static final String STUDENT_LASTNAME = "LastName";
    public static final String STUDENT_FIRSTNAME = "FirstName";
    public static final String STUDENT_MIDDLENAME = "MiddleName";
    public static final String STUDENT_DOB = "DOB";
    public static final String STUDENT_GENDER = "Gender";
    public static final String STUDENT_NATIONALITY = "Nationality";
    public static final String STUDENT_PHONENUMBER = "PHONENUMBER";
    public static final String STUDENT_RESIDENTIALADDRESS = "RESIDENTIALADDRESS";
    public static final String STUDENT_POSTALADDRESS = "POSTALADDRESS";
    public static final String STUDENT_EMAIL = "EMAIL";



    public static final String APPLICATION_TABLE = "APPLICATION";
    public static final String APP_RECEIPTID = "RECEIPTID";
    public static final String APP_STATUS = "STATUS";
    public static final String FIRSTCHOICE = "FIRSTCHOICEOFSTUDY";
    public static final String SECONDCHOICE = "SecondChoiceOfStudy";
    public static final String FIRSTHALL = "FirstHallOfResidence";
    public static final String SECONDHALL = "SecondHallOfResidence";
    public static final String THIRDHALL = "ThirdHallOfResidence";
    public static final String RESULTS = "Results";
    public static final String APP_STATUS_REJECT = "REJECTED";
    public static final String APP_STATUS_ACCEPTED = "ACCEPTED";
    public static final String APP_STATUS_PENDING = "PENDING";
    public static final String APP_DATE_CREATED = "DATECREATED";
    public static final String APP_HALLASSIGNED = "HALLASSIGNED";
    public static final String APP_HALLASSIGNED_NOHALLASSIGNED = "NO HALL ASSIGNED";





    private String[][] tableData;
    private String [] columnNames;

    //final String DBURL ="jdbc:derby:universityDB;";
    Statement stmt;

    public Connection getConn() {
        return conn;
    }

    private Connection conn;
    PreparedStatement preparedStatement;

    //constructor
    public Database() {
        //Class.forName("com.mysql.jdbc.Driver");
            getDatabaseConnection();
            System.out.println("Connection established");

    }

    public PreparedStatement getPreparedStatement(String statement) throws SQLException {
        preparedStatement = conn.prepareStatement(statement);
        return preparedStatement;
    }

    public String [] getApplicantNames() throws SQLException {
        ResultSet result = stmt.executeQuery("Select ReceiptID, lastName"+"" +
                " from Student");


        int rowCount = getNumRows(result);
        String [] studentNames = new String[rowCount];

        for(int i= 0; i<rowCount; i++){
            studentNames[i] = result.getString("ReceiptID") +" "+ result.getString("LastName");
            result.next();
        }
        return studentNames;

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

    public ResultSet getStudentFromDatabase(String id)throws SQLException {
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
                Database.STUDENT_TABLE+"." +Database.STUDENT_RECEIPTID+ " = '"+ id
                +"' AND "+ Database.STUDENT_TABLE+"." +Database.STUDENT_RECEIPTID+ " = "+
                Database.APPLICATION_TABLE + "."+Database.APP_RECEIPTID
                ;

        return executeSelectQuery(fullDetails);

    }

    public ResultSet getRejectedStudents() throws SQLException {

        String select ="select * from "+Database.STUDENT_TABLE +", "+Database.APPLICATION_TABLE
                + " WHERE "+Database.STUDENT_TABLE+"."+Database.STUDENT_RECEIPTID+" = "+
                Database.APPLICATION_TABLE+"."+Database.APP_RECEIPTID
                +" AND "+ Database.APPLICATION_TABLE +"." +Database.APP_STATUS+ " = " +
                "'REJECTED' "
                ;
        System.out.println("Query rejected students: "+select);
         return executeSelectQuery(select);
    }

    public ResultSet getAcceptedStudents() throws SQLException {

        String select ="select * from "+Database.STUDENT_TABLE +", "+Database.APPLICATION_TABLE
                + " WHERE "+Database.STUDENT_TABLE+"."+Database.STUDENT_RECEIPTID+" = "+
                Database.APPLICATION_TABLE+"."+Database.APP_RECEIPTID
                +" AND "+ Database.APPLICATION_TABLE +"." +Database.APP_STATUS+ " = " +
                "'ACCEPTED' "
                ;
        System.out.println("Query accepted students: "+select);
        return executeSelectQuery(select);
    }

    public ResultSet getPendingStudents() throws SQLException {

        String select ="select * from "+Database.STUDENT_TABLE +", "+Database.APPLICATION_TABLE
                + " WHERE "+Database.STUDENT_TABLE+"."+Database.STUDENT_RECEIPTID+" = "+
                Database.APPLICATION_TABLE+"."+Database.APP_RECEIPTID
                +" AND "+ Database.APPLICATION_TABLE +"." +Database.APP_STATUS+ " = " +
                "'PENDING' "
                ;
        System.out.println("Query pending students: "+select);
        return executeSelectQuery(select);
    }



    /**
     * @param query string to be input in SQL select statement
     *  Populates an array of column names and fills a 2D array in a format suitable for
     *      use in a table
     * run getColumnNames(), getTableData() after execution
     * throws SQL exception
     */
    public void selectAsTableQuery(String query) throws SQLException {

        ResultSet result = stmt.executeQuery(query);
        int numRows =0;
        int numCols =0;

         numRows = getNumRows(result);

        ResultSetMetaData meta = result.getMetaData();
        numCols = meta.getColumnCount();
        System.out.println("Number of rows="+ numRows);
        System.out.println("Number of columns="+ numCols);

        columnNames = new String[numCols];


        for(int i =0; i< numCols; i++){
            //get column name
            columnNames[i] = meta.getColumnLabel(i+1);
        }

        tableData = new String[numRows][numCols];

        for(int i =0; i<numRows; i++)
        {
            //get rows for every column as array of Strings

            tableData[i] = arrayOfStrings(result, numCols);
            result.next();

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
//    public void createTables(String sql) throws SQLException {
//        stmt.execute(sql);
//    }



    /**
     * @param md
     * @throws SQLException
     *intended for the admin
     * */
//    public void displayTableInfo(ResultSetMetaData md) throws SQLException {
//        int col = md.getColumnCount();
//        System.out.println("Number of Column : " + col);
//        System.out.println("Columns Name: ");
//        for (int i = 1; i <= col; i++) {
//            String col_name = md.getColumnName(i);
//            System.out.println(col_name + " " + md.getColumnTypeName(i));
//        }
//    }

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
            conn = DriverManager.getConnection(DB_URL, uname, pass);
            //conn.setAutoCommit(false);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (Exception ex) {
            System.out.println("AdmissionSystem.Database Connectivity error ");
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
        res.absolute(0);
        return numRows;
    }

    public boolean fillStudentDetails(String user, StudentInfo student) throws SQLException {

        ResultSet rs = getStudentFromDatabase(user);
        //System.out.println("Rows returned = " + mydatabase.getNumRows(rs));

        
        if (rs.next()) {
                    student.setReceiptID(rs.getString(Database.STUDENT_RECEIPTID));
                    student.setLname(rs.getString(Database.STUDENT_LASTNAME));
                    student.setFname(rs.getString(Database.STUDENT_FIRSTNAME));
            student.setMname(rs.getString(Database.STUDENT_MIDDLENAME));
            student.setDob(rs.getString(Database.STUDENT_DOB));
            student.setGender(rs.getString(Database.STUDENT_GENDER));
            student.setNationality(rs.getString(Database.STUDENT_NATIONALITY));
            student.setEmail(rs.getString(Database.STUDENT_EMAIL));
            student.setResAddress(rs.getString(Database.STUDENT_RESIDENTIALADDRESS));
                    student.setPostalAddress(rs.getString(Database.STUDENT_POSTALADDRESS));

            student.setCourse(rs.getString(Database.FIRSTCHOICE));
            student.setHall(rs.getString(Database.FIRSTHALL));
            student.setStatus(rs.getString(Database.APP_STATUS));
            student.setAssignedHall(rs.getString(Database.APP_HALLASSIGNED));
            rs.close();
            return  true;
        }else {
            System.out.println("Error creating student");
            rs.close();
            return false;
        }

        
    }



    /**
     * @param resultSet resultSet to return strings from
     * @param numCols rows in the result set
     * @return desired String array
     * @throws SQLException
     */
    public String[] arrayOfStrings(ResultSet resultSet, int numCols) {
        String data[] = new String[numCols];
        for(int index =0 ; index<numCols; index++)
        {
            //store String values of interest in the array
            try {
                data[index] = resultSet.getString(index+1);
                System.out.println("index"+index+"numcols= "+ numCols);
                //go to next row in the result set

            } catch (SQLException throwables) {
                System.out.println("In array of Strings");throwables.printStackTrace();
            }


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

    public void updateStudentStatus(String adminStatement, String id, String hall) throws SQLException {
        String accept ="UPDATE "+ Database.APPLICATION_TABLE+" SET "+Database.APP_STATUS +"= ?" +
                ", "+Database.APP_HALLASSIGNED +"= ?" +
                "WHERE " +
                "Application" +
                ". "+Database.APP_RECEIPTID+" =?";

        System.out.println("Update Query :"+ accept);

        PreparedStatement updateStudent = getPreparedStatement(accept);
        updateStudent.setString(1, adminStatement);
        updateStudent.setString(2, hall);
        updateStudent.setString(3, id);
        updateStudent.executeUpdate();

    }

    public void executeQuery(String userStatement) throws SQLException {
        stmt.executeQuery(userStatement);
    }

    public void execute(String userStatement) throws SQLException {
        stmt.execute(userStatement);
    }

    public void executeUpdateQuery(String userStatement)throws  SQLException{
        stmt.executeUpdate(userStatement);
    }



    /**
     * Prints in the console the columns metadata, based in the Arraylist of
     * tables passed as parameter.
     *
     * @param tables
     * @throws SQLException
     */
    public static void getColumnsMetadata(ArrayList tables)
            throws SQLException {
        ResultSet rs = null;
        // Print the columns properties of the actual table


    }

    /**
     * @param dbmd DatabaseMetadata variable
     * @param myTables Arraylist containing the table names
     * recommend use after executing getTableNames to print out the tables
     * @throws SQLException
     */
    public void printTableDetails(DatabaseMetaData dbmd, ArrayList<String> myTables) throws SQLException {
        int i=1;
        for (String actualTable : myTables) {
            ResultSet rs = dbmd.getColumns(null, null, actualTable, null);
            System.out.println("\n"+"("+i+")"+"["+(actualTable).toUpperCase()+"]");
            while (rs.next()) {
                System.out.println(rs.getString("COLUMN_NAME") + " "
                        + rs.getString("TYPE_NAME") + " "
                        + rs.getString("COLUMN_SIZE"));
            }
            System.out.println("_____________________________");
            i++;
        }
    }

    /**
     * @param dbmd DatabaseMetadata variable
     * @return Arraylist containing the table names
     * @throws SQLException
     */
    public ArrayList<String> getTableNames(DatabaseMetaData dbmd) throws SQLException{

        String table[]={"TABLE"};

        ResultSet rs= dbmd.getTables(null,null,null,table);


        ArrayList<String> listofTable = new ArrayList<String>();
        System.out.println("create array");


        while(rs.next()){
            listofTable.add(rs.getString(3));

        }


        return listofTable;
    }





    public static void main(String[] args) {
        Database mydatabase = new Database();


        try {

//            ResultSet         result = mydatabase.executeSelectQuery("Select * from STUDENT");
//            ResultSetMetaData md     = result.getMetaData();
//            mydatabase.displayTableInfo(md);

            DatabaseMetaData dbmd=mydatabase.conn.getMetaData();

//            mydatabase.executeUpdateQuery(
//                    "Insert into users(username, password)"+
//                            "values('kofi', '123')"
//            );
            System.out.println("inserted values");

            //print table names
            ArrayList<String> myTables =mydatabase.getTableNames(dbmd);
            System.out.println("Tables");
            for(String table: myTables){
                System.out.println(table);
            }
                System.out.println("\n");
            mydatabase.printTableDetails(dbmd, myTables);

            for(String table: myTables){
                ResultSet rs = dbmd.getPrimaryKeys (null, null, table );
                while (rs.next()){
                    System.out.println("Table name: "+rs.getString("TABLE_NAME"));
                    System.out.println("Column name: "+rs.getString("COLUMN_NAME"));
                    System.out.println("Catalog name: "+rs.getString("TABLE_CAT"));
                    System.out.println("Primary key sequence: "+rs.getString("KEY_SEQ"));
                    System.out.println("Primary key name: "+rs.getString("PK_NAME"));
                    System.out.println(" ");
                }
            }



        } catch (SQLException ex) {
            System.out.println("Sql Exception: \n" + ex.getMessage());
        }
        System.out.println("Complete");

    }

    public boolean getStudentDocument(String receiptID) throws SQLException {
        ResultSet resultSet = null;
        String selection = "SELECT * FROM "+ APPLICATION_TABLE +" WHERE "+APP_RECEIPTID+" =?";

        preparedStatement = conn.prepareStatement(selection);
        preparedStatement.setString(1, receiptID);
        resultSet = preparedStatement.executeQuery();

        try{
            if (resultSet.next()){
                FileOutputStream fileOutputStream = null;
                String filename = "C:\\Users\\use\\Desktop\\AdminDocs\\"+receiptID+" results.pdf";

                File file= new File(filename);
                fileOutputStream = new FileOutputStream(filename);
                InputStream inputStream = resultSet.getBinaryStream(RESULTS);

                byte[] buffer = new byte[1024];
                while (inputStream.read(buffer)>0){
                    fileOutputStream.write(buffer);
                }
                fileOutputStream.close();
                inputStream.close();
                System.out.println("File created at: \n"+filename);
                return true;
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to fully create file "+receiptID);
            e.printStackTrace();
        }

        return  false;
    }


    //end of class
};



//
//Tables
//        ADMIN
//        ADMITTEDSTUDENTS
//        APPLICATION
//        APPLICATIONSTATUS
//        STUDENT
//        USERS
//
//
//
//        (1)[ADMIN]
//        ADMINNUMBER INTEGER 10
//        USERNAME VARCHAR 25
//        PASSWORD VARCHAR 35
//        _____________________________
//
//        (2)[ADMITTEDSTUDENTS]
//        STUDENTID CHAR 8
//        APPLICATIONID CHAR 10
//        RECEIPTID CHAR 10
//        COURSEOFSTUDY VARCHAR 25
//        HALLOFRESIDENCE VARCHAR 35
//        _____________________________
//
//        (3)[APPLICATION]
//        APPLICATIONID CHAR 10
//        RECEIPTID CHAR 10
//        FIRSTCHOICEOFSTUDY VARCHAR 25
//        SECONDCHOICEOFSTUDY VARCHAR 25
//        THIRDCHOICEOFSTUDY VARCHAR 25
//        FIRSTHALLOFRESIDENCE VARCHAR 25
//        SECONDHALLOFRESIDENCE VARCHAR 25
//        THIRDHALLOFRESIDENCE VARCHAR 25
//        RESULTS VARCHAR 25
//        _____________________________
//
//        (4)[APPLICATIONSTATUS]
//        APPLICATIONNUMBER INTEGER 10
//        APPLICATIONID CHAR 10
//        ADMISSIONSTATUS VARCHAR 15
//        _____________________________
//
//        (5)[STUDENT]
//        RECEIPTID CHAR 10
//        LASTNAME VARCHAR 25
//        FIRSTNAME VARCHAR 25
//        MIDDLEINITIAL VARCHAR 25
//        DOB DATE 10
//        NATIONALITY VARCHAR 25
//        PHONENUMBER VARCHAR 15
//        RESIDENTIALADDRESS VARCHAR 50
//        POSTALADDRESS VARCHAR 50
//        _____________________________
//
//        (6)[USERS]
//        USERNAME CHAR 10
//        PASSWORD VARCHAR 25
//        _____________________________

//Admin database commands to create table

//        String createT = "CREATE TABLE Admin ( "+
//                "AdminNumber INT,"+
//                "USERNAME varchar(25),"+
//                "PASSWORD varchar(35),"+
//                " PRIMARY KEY(ADMINNumber)"+
//                ")"
//                ;

        /*
        Create table users(
username char(10),
password varchar(25)
)
ALTER TABLE users
 ADD CONSTRAINT MyPrimaryKey PRIMARY KEY(username)

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

//Create table users(
//        username VARCHAR(25),
//        password VARCHAR(25)
//        )

//Driver Name: Apache Derby Embedded JDBC Driver
//        Driver Version: 10.14.2.0 - (1828579)
//        UserName: APP
//        AdmissionSystem.Database Product Name: Apache Derby
//        AdmissionSystem.Database Product Version: 10.14.2.0 - (1828579)