package am.tau.bookslib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "genre")
@ApiModel
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated genre ID")
    private int id;

    @ApiModelProperty(notes = "The name of the genre", required = true, example = "Genre name")
    @Column(name = "name", unique = true, nullable = false, length = 45)
    private String name;

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
