package am.tau.bookslib.service;

import am.tau.bookslib.db.MySQLClient;
import am.tau.bookslib.model.Genre;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {
    MySQLClient mySQLClient = new MySQLClient();

    public Iterable<Genre> getAll() throws SQLException {
        List<Genre> genres = new ArrayList<>();

        PreparedStatement ps = mySQLClient.getPreparedStatement("SELECT * FROM genre");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Genre genre = new Genre();
            genre.setId(rs.getInt("id"));
            genre.setName(rs.getString("name"));
            genres.add(genre);
        }

        mySQLClient.closeAllAndDisconnect();
        return genres;
    }

    public Genre getById(int id) throws SQLException {
        Genre genre = null;

        PreparedStatement ps = mySQLClient.getPreparedStatement("SELECT * FROM genre WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            genre = new Genre();
            genre.setId(rs.getInt("id"));
            genre.setName(rs.getString("name"));
        }

        mySQLClient.closeAllAndDisconnect();
        return genre;
    }

    public void add(Genre genre) throws SQLException {
        PreparedStatement ps = mySQLClient.getPreparedStatement("INSERT INTO genre (name) VALUES (?)");
        ps.setString(1, genre.getName());
        ps.execute();
        mySQLClient.closeAllAndDisconnect();
    }

    public void update(Genre genre) throws SQLException {
        PreparedStatement ps = mySQLClient.getPreparedStatement("UPDATE genre SET name = ? WHERE id = ?");
        ps.setString(1, genre.getName());
        ps.setInt(2, genre.getId());
        ps.execute();
        mySQLClient.closeAllAndDisconnect();
    }

    public void delete(Integer id) throws SQLException {
        PreparedStatement ps = mySQLClient.getPreparedStatement("DELETE FROM genre WHERE id = ?");
        ps.setInt(1, id);
        ps.execute();
        mySQLClient.closeAllAndDisconnect();
    }
}
