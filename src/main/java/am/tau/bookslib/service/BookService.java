package am.tau.bookslib.service;

import am.tau.bookslib.db.MySQLClient;
import am.tau.bookslib.model.Author;
import am.tau.bookslib.model.Book;
import am.tau.bookslib.model.Genre;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    MySQLClient mySQLClient = new MySQLClient();

    public Iterable<Book> getAll() throws SQLException {
        List<Book> books = new ArrayList<>();

        PreparedStatement ps = mySQLClient.getPreparedStatement("SELECT b.id, b.name AS book_name, b.publication_date, a.id AS author_id, a.fname AS a_fname, a.lname AS a_lname, g.id AS genre_id, g.name as genre_name FROM book AS b LEFT OUTER JOIN author AS a ON b.author_id = a.id LEFT OUTER JOIN genre AS g on b.genre_id = g.id");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Author author = new Author();
            author.setId(rs.getInt("author_id"));
            author.setFirstName(rs.getString("a_fname"));
            author.setLastName(rs.getString("a_lname"));

            Genre genre = new Genre();
            genre.setId(rs.getInt("genre_id"));
            genre.setName(rs.getString("genre_name"));

            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("book_name"));
            book.setPublicationDate(rs.getDate("publication_date"));
            book.setAuthor(author);
            book.setGenre(genre);

            books.add(book);
        }

        mySQLClient.closeAllAndDisconnect();

        return books;
    }

    public Book getById(int id) throws SQLException {
        Book book = null;

        PreparedStatement ps = mySQLClient.getPreparedStatement("SELECT b.id, b.name AS book_name, b.publication_date, a.id AS author_id, a.fname AS a_fname, a.lname AS a_lname, g.id AS genre_id, g.name as genre_name FROM book AS b LEFT OUTER JOIN author AS a ON b.author_id = a.id LEFT OUTER JOIN genre AS g on b.genre_id = g.id WHERE b.id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Author author = new Author();
            author.setId(rs.getInt("author_id"));
            author.setFirstName(rs.getString("a_fname"));
            author.setLastName(rs.getString("a_lname"));

            Genre genre = new Genre();
            genre.setId(rs.getInt("genre_id"));
            genre.setName(rs.getString("genre_name"));

            book = new Book();
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("book_name"));
            book.setPublicationDate(rs.getDate("publication_date"));
            book.setAuthor(author);
            book.setGenre(genre);
        }

        mySQLClient.closeAllAndDisconnect();

        return book;
    }

    public void add(Book book) throws SQLException {
        PreparedStatement ps = mySQLClient.getPreparedStatement("INSERT INTO `book` (name, publication_date, author_id, genre_id) VALUES (?, ?, ?, ?)");
        ps.setString(1, book.getName());
        ps.setDate(2, new java.sql.Date(book.getPublicationDate().getTime()));
        ps.setInt(3, book.getAuthor().getId());
        ps.setInt(4, book.getGenre().getId());
        ps.execute();
        mySQLClient.closeAllAndDisconnect();
    }

    public void update(Book book) throws SQLException {
        PreparedStatement ps = mySQLClient.getPreparedStatement("UPDATE book SET name = ?, publication_date = ?, author_id = ?, genre_id = ? WHERE id = ?");
        ps.setString(1, book.getName());
        ps.setDate(2, new java.sql.Date(book.getPublicationDate().getTime()));
        ps.setInt(3, book.getAuthor().getId());
        ps.setInt(4, book.getGenre().getId());
        ps.setInt(5, book.getId());
        ps.execute();
        mySQLClient.closeAllAndDisconnect();
    }

    public void delete(Integer id) throws SQLException {
        PreparedStatement ps = mySQLClient.getPreparedStatement("DELETE FROM book WHERE id = ?");
        ps.setInt(1, id);
        ps.execute();
        mySQLClient.closeAllAndDisconnect();
    }
}
