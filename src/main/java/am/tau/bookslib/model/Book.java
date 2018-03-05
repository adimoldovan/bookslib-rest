package am.tau.bookslib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class Book {
    @ApiModelProperty(notes = "The database generated ID")
    private int id;

    @ApiModelProperty(notes = "Book's name", required = true, example = "Thinking in Java")
    private String name;

    @ApiModelProperty(notes = "Publication date", required = true)
    private Date publicationDate;

    @ApiModelProperty(notes = "Book's author", required = true)
    private Author author;

    @ApiModelProperty(notes = "Book's genre", required = true)
    private Genre genre;

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
