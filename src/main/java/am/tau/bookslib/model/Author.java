package am.tau.bookslib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Author {
    @ApiModelProperty(notes = "The database generated ID")
    private int id;

    @ApiModelProperty(notes = "Author's first name", required = true, example = "Author first name")
    private String firstName;

    @ApiModelProperty(notes = "Author's last name", required = true, example = "Author last name")
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
