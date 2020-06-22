package model;
import controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class H2DB {
    private static final Logger logger = LogManager.getLogger();
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
    static final String USER = "sa";
    static final String PASS = "";

    private Connection connection(){
        Connection conn = null;
        // STEP 1: Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //STEP 2: Open a connection
        logger.info("Connecting to database...");
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    return conn;
    }

    public void createDb() {
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver

            //STEP 3: Execute a query
            logger.info("Creating table in given database...");
            stmt = connection().createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS TESTABLE " +
                    "(id int IDENTITY NOT NULL PRIMARY KEY, name varchar(255), text varchar(255))";
            stmt.executeUpdate(sql);
            logger.info("Created table in given database...");

            // STEP 4: Clean-up environment
            stmt.close();
            connection().close();

        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {

            } // nothing we can do
            try {
                if(connection()!=null) connection().close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        logger.info("Goodbye connection!");
    }
    public void insertInDB(String url) {
        PreparedStatement insertPS;

        try {
            //STEP 3: Create PS
            insertPS = connection().prepareStatement("INSERT INTO TESTABLE (name,text) values (?, ?)");
            logger.info("PS was created..");

            //STEP 4: Update PS
            insertPS.setString(1,url);
            insertPS.setString(2, Controller.getTextForDB());
            logger.info("PS was update..");
            //STEP 5: Add PS in Table
            insertPS.executeUpdate();
            insertPS.close();
            connection().close();
            logger.info("query correctly add");

        } catch(Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        finally {
            //finally block used to close resources
            try {
                if(connection()!=null) connection().close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        logger.info("Goodbye connection!");
    }

}