package model;
import com.sun.jdi.event.StepEvent;
import controller.Controller;
import javafx.css.Match;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class H2DB {
    private String nameOfTable;
    public H2DB(String nameOfTable) {
        this.nameOfTable = nameOfTable;
    }

    public Connection getConnectionToDB() throws ClassNotFoundException, SQLException {
        Connection dbConnection;
            Class.forName("org.h2.Driver");
            dbConnection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;"
                    +"INIT=CREATE SCHEMA IF NOT EXISTS URLS");
            return dbConnection;
    }
    private String regex(){
        Pattern http = Pattern.compile("http://");
        Matcher matcher = http.matcher(nameOfTable);
        nameOfTable = matcher.replaceAll("");
        Pattern text = Pattern.compile("\\W+");
        Matcher matcher1 = text.matcher(nameOfTable);
        return nameOfTable = matcher1.replaceAll("");
    }

    public void createDBTable(){
        try (Connection connection = getConnectionToDB()) {
            PreparedStatement createPreparedStatement;

            String createQuery =
                    "CREATE TABLE " +regex()+ " (id int primary key, name varchar(255))";
            //запрос на создание таблицы

            createPreparedStatement = connection.prepareStatement(createQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
            connection.commit();
            //установка соединения и добавления изменений
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertInDB(String url,int iter) {
        try (Connection connection = getConnectionToDB()) {
            connection.setAutoCommit(false);
            PreparedStatement insertPreparedStatement;
            String insertQuery = "INSERT INTO "+ regex() +"(id, name) values("+iter+","+url+")";
            //запрос на добавлеие в таблицу

            insertPreparedStatement = connection.prepareStatement(insertQuery);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
            connection.commit();
            //установка соединения и добавления изменений

        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

