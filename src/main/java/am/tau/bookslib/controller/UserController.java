package am.tau.bookslib.controller;

import am.tau.bookslib.exception.InvalidInputException;
import am.tau.bookslib.exception.ResourceAlreadyExistsException;
import am.tau.bookslib.model.SuccessResponse;
import am.tau.bookslib.model.UserAccount;
import am.tau.bookslib.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/user")
@Api(tags = "User")
public class UserController {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setService(UserService service, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = service;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @ApiOperation(value = "Create a new user account")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity addNewAccount(@RequestBody UserAccount user) throws SQLException {
        validateUser(user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.add(user);
        return new SuccessResponse().getResponse();
    }

    private void validateUser(UserAccount user) throws SQLException {
        if (user.getUsername() == null) {
            throw new InvalidInputException("Missing username");
        }

        if (user.getPassword() == null) {
            throw new InvalidInputException("Missing password");
        }

        if (user.getUsername().length() < 3) {
            throw new InvalidInputException("The username should have at least 3 characters");
        }

        if (user.getUsername().length() > 45) {
            throw new InvalidInputException("The usernamename MAX length is 45 characters");
        }

        if (user.getPassword().length() < 8) {
            throw new InvalidInputException("The password should have at least 8 characters");
        }

        UserAccount existingAccount = userService.findByUsername(user.getUsername());

        if (existingAccount != null) {
            throw new ResourceAlreadyExistsException("Username already exists");
        }
    }
}
