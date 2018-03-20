package am.tau.bookslib.controller;

import am.tau.bookslib.exception.InvalidInputException;
import am.tau.bookslib.exception.NotFoundException;
import am.tau.bookslib.model.Genre;
import am.tau.bookslib.model.SuccessResponse;
import am.tau.bookslib.service.GenreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/genre")
@Api(tags = "Genre")
public class GenreController {

    private GenreService genreService;

    @Autowired
    public void setService(GenreService genreService) {
        this.genreService = genreService;
    }

    @ApiOperation(value = "View a list of available genres", response = Iterable.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Genre> listAll() throws NotFoundException, SQLException {
        Iterable<Genre> genres = genreService.getAll();
        if (!genres.iterator().hasNext()) {
            throw new NotFoundException("No genres found");
        }
        return genres;
    }

    @ApiOperation(value = "View a specific genre", response = Genre.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public Genre show(@PathVariable Integer id) throws NotFoundException, SQLException {
        return getGenreIfItExists(id);
    }

    @ApiOperation(value = "Create a new genre")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity save(@RequestBody Genre genre) throws InvalidInputException, SQLException {
        validateGenre(genre);
        genreService.add(genre);
        return new SuccessResponse().getResponse();
    }

    @ApiOperation(value = "Update a genre")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Genre genre) throws InvalidInputException, NotFoundException, SQLException {
        Genre storedGenre = getGenreIfItExists(id);
        validateGenre(genre);
        storedGenre.setName(genre.getName());
        genreService.update(storedGenre);
        return new SuccessResponse().getResponse();
    }

    @ApiOperation(value = "Delete a genre")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id) throws NotFoundException, SQLException {
        getGenreIfItExists(id);
        genreService.delete(id);
        return new SuccessResponse().getResponse();
    }

    private void validateGenre(Genre genre) throws InvalidInputException {
        if (genre.getName() == null) {
            throw new InvalidInputException("Missing genre name");
        }

        if (genre.getName().length() < 1) {
            throw new InvalidInputException("The genre name should have at least 1 character");
        }

        if (genre.getName().length() > 45) {
            throw new InvalidInputException("The genre name MAX length is 45");
        }
    }

    private Genre getGenreIfItExists(int categoryId) throws NotFoundException, SQLException {
        Genre genre = genreService.getById(categoryId);
        if (genre == null) {
            throw new NotFoundException("Genre was not found");
        }

        return genre;
    }
}
