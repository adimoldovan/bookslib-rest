package am.tau.bookslib.controller;

import am.tau.bookslib.exception.InvalidInputException;
import am.tau.bookslib.exception.NotFoundException;
import am.tau.bookslib.model.Book;
import am.tau.bookslib.model.SuccessResponse;
import am.tau.bookslib.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/book")
@Api(tags = "Book")
public class BookController {

    private BookService bookService;

    @Autowired
    public void setService(BookService service) {
        this.bookService = service;
    }

    @ApiOperation(value = "View a list of available books", response = Iterable.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Book> listAll() throws NotFoundException, SQLException {
        Iterable<Book> books = bookService.getAll();
        if (!books.iterator().hasNext()) {
            throw new NotFoundException("No book found");
        }
        return books;
    }

    @ApiOperation(value = "View a specific book", response = Book.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public Book show(@PathVariable Integer id) throws NotFoundException, SQLException {
        return getBookIfItExists(id);
    }

    @ApiOperation(value = "Create a new book")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity save(@RequestBody Book book) throws InvalidInputException, SQLException {
        validateBook(book);
        bookService.add(book);
        return new SuccessResponse().getResponse();
    }

    @ApiOperation(value = "Update a book")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Book book) throws InvalidInputException, NotFoundException, SQLException {
        Book storedBook = getBookIfItExists(id);
        validateBook(book);
        storedBook.setName(book.getName());
        storedBook.setPublicationDate(book.getPublicationDate());
        storedBook.setGenre(book.getGenre());
        storedBook.setAuthor(book.getAuthor());
        bookService.update(storedBook);
        return new SuccessResponse().getResponse();
    }

    @ApiOperation(value = "Delete a book")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id) throws NotFoundException, InvalidInputException, SQLException {
        getBookIfItExists(id);
        bookService.delete(id);
        return new SuccessResponse().getResponse();
    }

    private void validateBook(Book book) throws InvalidInputException {
        if (book.getName() == null) {
            throw new InvalidInputException("Missing book name");
        }

        if (book.getPublicationDate() == null) {
            throw new InvalidInputException("Missing publication date");
        }

        if (book.getName().length() < 1) {
            throw new InvalidInputException("The book name should have at least 1 character");
        }

        if (book.getName().length() > 45) {
            throw new InvalidInputException("The book name MAX length is 45");
        }

        if (book.getPublicationDate() == null) {
            throw new InvalidInputException("Missing publication date");
        }

        if (book.getAuthor() == null) {
            throw new InvalidInputException("Missing author");
        }

        if (book.getGenre() == null) {
            throw new InvalidInputException("Missing genre");
        }
    }

    private Book getBookIfItExists(int id) throws NotFoundException, SQLException {
        Book book = bookService.getById(id);
        if (book == null) {
            throw new NotFoundException(String.format("Book with id %s was not found", id));
        }

        return book;
    }
}
