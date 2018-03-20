package am.tau.bookslib.controller;

import am.tau.bookslib.exception.InvalidInputException;
import am.tau.bookslib.exception.UnauthorizedException;
import am.tau.bookslib.model.AccesToken;
import am.tau.bookslib.model.User;
import am.tau.bookslib.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
@Api(tags = "User")
public class UserController {

    private UserService userService;

    @Autowired
    public void setService(UserService service) {
        this.userService = service;
    }

    @ApiOperation(value = "Login, get authorization token")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public AccesToken login(@RequestBody User login) throws InvalidInputException, UnauthorizedException {
        String jwtToken = "";

        if (login.getUsername() == null || login.getPassword() == null) {
            throw new InvalidInputException("Please fill in username and password");
        }

        String email = login.getUsername();
        String password = login.getPassword();

//        User user = userService.findByUsername(email);
        User user = new User("user", "pass");

        if (user == null) {
            throw new UnauthorizedException("Invalid login. Please check your usernname and password.");
        }

        String pwd = user.getPassword();

        if (!password.equals(pwd)) {
            throw new UnauthorizedException("Invalid login. Please check your usernname and password.");
        }

        jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

        return new AccesToken(jwtToken);
    }
}
