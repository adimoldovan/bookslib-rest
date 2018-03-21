package am.tau.bookslib.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/")
@ApiIgnore
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    String index() {
        return "<h1>Books library API</h1><p>Nothing to do here. You may want to check the documentation. Click <a href=\"swagger-ui.html\">here</a></p>";
    }
}
