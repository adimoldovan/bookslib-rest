package am.tau.bookslib.controller;

import am.tau.bookslib.exception.InvalidInputException;
import am.tau.bookslib.exception.NotFoundException;
import am.tau.bookslib.model.Author;
import am.tau.bookslib.model.SuccessResponse;
import am.tau.bookslib.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/author")
@Api(tags = "Author")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public void setService(AuthorService service) {
        this.authorService = service;
    }

    @ApiOperation(value = "View a list of available authors", response = Iterable.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Author> listAll() throws NotFoundException, SQLException {
        Iterable<Author> authors = authorService.getAll();
        if (!authors.iterator().hasNext()) {
            throw new NotFoundException("No authors found");
        }
        return authors;
    }

    @ApiOperation(value = "View a specific author", response = Author.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public Author show(@PathVariable Integer id) throws NotFoundException, SQLException {
        return getAuthorIfItExists(id);
    }

    @ApiOperation(value = "Create a new author")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity save(@RequestBody Author author) throws InvalidInputException, SQLException {
        validateAuthor(author);
        authorService.add(author);
        return new SuccessResponse().getResponse();
    }

    @ApiOperation(value = "Update an author")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Author author) throws InvalidInputException, NotFoundException, SQLException {
        Author storedAuthor = getAuthorIfItExists(id);
        validateAuthor(author);
        storedAuthor.setFirstName(author.getFirstName());
        storedAuthor.setLastName(author.getLastName());
        authorService.update(storedAuthor);
        return new SuccessResponse().getResponse();
    }

    @ApiOperation(value = "Delete an author")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id) throws NotFoundException, InvalidInputException, SQLException {
        getAuthorIfItExists(id);
        authorService.delete(id);
        return new SuccessResponse().getResponse();
    }

    private void validateAuthor(Author author) throws InvalidInputException {
        if (author.getFirstName() == null) {
            throw new InvalidInputException("Missing first name");
        }

        if (author.getLastName() == null) {
            throw new InvalidInputException("Missing last name");
        }

        if (author.getFirstName().length() < 1) {
            throw new InvalidInputException("The author first name should have at least 1 character");
        }

        if (author.getFirstName().length() > 45) {
            throw new InvalidInputException("The author first name MAX length is 45");
        }

        if (author.getLastName().length() < 1) {
            throw new InvalidInputException("The author last name should have at least 1 character");
        }

        if (author.getLastName().length() > 45) {
            throw new InvalidInputException("The author last name MAX length is 45");
        }
    }

    private Author getAuthorIfItExists(int id) throws NotFoundException, SQLException {
        Author author = authorService.getById(id);
        if (author == null) {
            throw new NotFoundException(String.format("Author with id %s was not found", id));
        }

        return author;
    }
}
