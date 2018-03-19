package am.tau.bookslib.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLClient.class.getName());
    private Connection conn;
    private PreparedStatement preparedStatement;

    private void disconnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeStatement() {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeAllAndDisconnect() {
        closeStatement();
        disconnect();
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        connect();
        preparedStatement = conn.prepareStatement(query);
        return preparedStatement;
    }

    private void connect() throws SQLException {
        String DB_HOST = "jdbc:mysql://localhost:3306/bookslib?useSSL=false";
        String DB_USER = "bookslibraryrest";
        String DB_PASSWORD = "bookslibraryrest";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        conn = DriverManager.getConnection(DB_HOST, DB_USER, DB_PASSWORD);
    }

    public Connection getConnection() throws SQLException {
        connect();
        return this.conn;
    }
}
