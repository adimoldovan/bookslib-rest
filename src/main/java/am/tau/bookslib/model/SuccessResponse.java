package am.tau.bookslib.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SuccessResponse {
    private String message;

    public SuccessResponse() {
        this.message = "Success!";
    }

    public SuccessResponse(String message) {
        this.message = message;
    }

    public ResponseEntity getResponse() {
        return new ResponseEntity<>(new Response(this.message, HttpStatus.OK), HttpStatus.OK);
    }
}
