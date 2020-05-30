package model;
import com.sun.jdi.event.StepEvent;
import controller.Controller;
import javafx.css.Match;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class H2DB {

    public Connection getConnectionToDB() throws ClassNotFoundException, SQLException {
        Connection dbConnection;
            Class.forName("org.h2.Driver");
            dbConnection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;"
                    +"INIT=CREATE SCHEMA IF NOT EXISTS URLS");
            return dbConnection;
    }
    private String regex(String url){
        //для того чтобы создать корректный query приходится убирать все знаки в запросах
        Pattern http = Pattern.compile("https?://");
        Matcher matcher = http.matcher(url);
        url = matcher.replaceAll("");
        Pattern text = Pattern.compile("\\W+");
        Matcher matcher1 = text.matcher(url);
        return url = matcher1.replaceAll("");
    }

    public void createDBTable(String nameOfTable){
        try (Connection connection = getConnectionToDB()) {
            PreparedStatement createPreparedStatement;
            String createQuery =
                    "CREATE TABLE " +regex(nameOfTable)+ " (id int primary key, name varchar(255))";
            //запрос на создание таблицы
            System.out.println("valid table query");
            createPreparedStatement = connection.prepareStatement(createQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
            connection.commit();
            System.out.println("table created");
            //установка соединения и добавления изменений
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertInDB(String nameOfTable, String url,int iter) {
        try (Connection connection = getConnectionToDB()) {
            connection.setAutoCommit(false);
            PreparedStatement insertPreparedStatement;
            String insertQuery = "INSERT INTO "+regex(nameOfTable) +"(id, name) values("+iter+","+regex(url)+")";
            System.out.println("valid request for addition");
            //запрос на добавлеие в таблицу

            insertPreparedStatement = connection.prepareStatement(insertQuery);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
            connection.commit();
            System.out.println("query correctly add");
            //установка соединения и добавления изменений

        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

