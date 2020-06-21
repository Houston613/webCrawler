package model;
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

    public static void createDb() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 3: Execute a query
            logger.info("Creating table in given database...");
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS TESTABLE " +
                    "(id int IDENTITY NOT NULL PRIMARY KEY, name varchar(255))";
            stmt.executeUpdate(sql);
            logger.info("Created table in given database...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();

        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {

            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        logger.info("Goodbye connection!");
    }
    public void insertInDB(String url) {
        Connection conn = null;
        PreparedStatement insertPS;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 3: Create PS
            insertPS = conn.prepareStatement("INSERT INTO TESTABLE (name) values (?)");
            logger.info("PS was created..");

            //STEP 4: Update PS
            insertPS.setString(1,url);
            logger.info("PS was update..");

            //STEP 5: Add PS in Table
            insertPS.executeUpdate();
            insertPS.close();
            conn.close();
            logger.info("query correctly add");

        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();

        } finally {
            //finally block used to close resources
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        logger.info("Goodbye connection!");
    }

}