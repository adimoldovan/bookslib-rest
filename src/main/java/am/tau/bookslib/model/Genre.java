package am.tau.bookslib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Genre {
    @ApiModelProperty(notes = "The database generated genre ID")
    private int id;

    @ApiModelProperty(notes = "The name of the genre", required = true, example = "Genre name")
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
