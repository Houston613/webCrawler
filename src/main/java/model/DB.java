package model;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB{
    //конфиг для подключения
    //константа с названием таблицы

    public Connection getConnectDB() throws ClassNotFoundException, SQLException {
        StringBuilder connect = new StringBuilder();
        connect.append("jdbc:mysql://");
        connect.append("localhost:");
        connect.append("3306/");
        connect.append("MySql80?");
        connect.append("serverTimezone=UTC");
        Class.forName("com.mysql.jdbc.Driver");
        connectToDB = DriverManager.getConnection(connect.toString(),"root","D@rl1ng2773");
        return connectToDB;
    }

    Connection connectToDB;

    public void addToDB(String startURL) throws SQLException, ClassNotFoundException {
        String put = "INSERT INTO" + "URLS" + "URL" + "VALUES(?)";

        PreparedStatement preparedStatement = getConnectDB().prepareStatement(put);
        preparedStatement.setString(1, startURL);
        preparedStatement.executeUpdate();
    }

}