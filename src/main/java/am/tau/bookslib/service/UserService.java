package am.tau.bookslib.service;

import am.tau.bookslib.db.MySQLClient;
import am.tau.bookslib.model.Author;
import am.tau.bookslib.model.User;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    MySQLClient mySQLClient = new MySQLClient();

    public User findByUsername(String username) {

        return null;
    }

//    public Iterable<Author> getAll() throws SQLException {
//        List<Author> authors = new ArrayList<>();
//
//        PreparedStatement ps = mySQLClient.getPreparedStatement("SELECT * FROM author");
//        ResultSet rs = ps.executeQuery();
//
//        while (rs.next()) {
//            Author author = new Author();
//            author.setId(rs.getInt("id"));
//            author.setFirstName(rs.getString("fname"));
//            author.setLastName(rs.getString("lname"));
//            authors.add(author);
//        }
//
//        mySQLClient.closeAllAndDisconnect();
//
//        return authors;
//    }
//
//    public Author getById(int id) throws SQLException {
//        Author author = null;
//
//        PreparedStatement ps = mySQLClient.getPreparedStatement("SELECT * FROM author WHERE id = ?");
//        ps.setInt(1, id);
//        ResultSet rs = ps.executeQuery();
//
//        while (rs.next()) {
//            author = new Author();
//            author.setId(rs.getInt("id"));
//            author.setFirstName(rs.getString("fname"));
//            author.setLastName(rs.getString("lname"));
//        }
//
//        mySQLClient.closeAllAndDisconnect();
//
//        return author;
//    }
//
//    public void add(Author author) throws SQLException {
//        PreparedStatement ps = mySQLClient.getPreparedStatement("INSERT INTO author (fname, lname) VALUES (?, ?);");
//        ps.setString(1, author.getFirstName());
//        ps.setString(2, author.getLastName());
//        ps.execute();
//        mySQLClient.closeAllAndDisconnect();
//    }
//
//    public void update(Author author) throws SQLException {
//        PreparedStatement ps = mySQLClient.getPreparedStatement("UPDATE author SET fname = ?, lname = ? WHERE id = ?");
//        ps.setString(1, author.getFirstName());
//        ps.setString(2, author.getLastName());
//        ps.setInt(3, author.getId());
//        ps.execute();
//        mySQLClient.closeAllAndDisconnect();
//    }
//
//    public void delete(Integer id) throws SQLException {
//        PreparedStatement ps = mySQLClient.getPreparedStatement("DELETE FROM author WHERE id = ?");
//        ps.setInt(1, id);
//        ps.execute();
//        mySQLClient.closeAllAndDisconnect();
//    }
}
