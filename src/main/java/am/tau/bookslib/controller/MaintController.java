package am.tau.bookslib.controller;

import am.tau.bookslib.model.SuccessResponse;
import am.tau.bookslib.model.UserAccount;
import am.tau.bookslib.service.MaintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/maint")
@Api(tags = "Maintenence")
public class MaintController {
    private MaintService maintService;
    private UserController userController;

    @Autowired
    public void setService(MaintService service, UserController userController) {
        this.maintService = service;
        this.userController = userController;
    }

    @ApiOperation(value = "Drops and recreates the entire DB with default data", produces = "application/json")
    @RequestMapping(value = "/full", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity recreateDB() throws SQLException, IOException {
        maintService.fullMaint();
        userController.addNewAccount(new UserAccount("defaultuser", "defaultuser"));
        return new SuccessResponse().getResponse();
    }
}
