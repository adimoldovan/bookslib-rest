package am.tau.bookslib.service;

import am.tau.bookslib.db.MySQLClient;
import am.tau.bookslib.model.UserAccount;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserService {
    MySQLClient mySQLClient = new MySQLClient();

    public UserAccount findByUsername(String username) throws SQLException {
        UserAccount userAccount = null;

        PreparedStatement ps = mySQLClient.getPreparedStatement("SELECT * FROM user WHERE username LIKE ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            userAccount = new UserAccount();
            userAccount.setId(rs.getInt("id"));
            userAccount.setUsername(rs.getString("username"));
            userAccount.setPassword(rs.getString("password"));
        }

        mySQLClient.closeAllAndDisconnect();

        return userAccount;
    }

    public void add(UserAccount userAccount) throws SQLException {
        PreparedStatement ps = mySQLClient.getPreparedStatement("INSERT INTO user (username, password) VALUES (?, ?);");
        ps.setString(1, userAccount.getUsername());
        ps.setString(2, userAccount.getPassword());
        ps.execute();
        mySQLClient.closeAllAndDisconnect();
    }
}
