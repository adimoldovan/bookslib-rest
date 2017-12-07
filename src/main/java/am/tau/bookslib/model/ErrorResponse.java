package am.tau.bookslib.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;

    public ErrorResponse(HttpStatus httpStatus) {
        this.message = "Error!";
        this.httpStatus = httpStatus;
    }

    public ErrorResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ResponseEntity getResponse() {
        return new ResponseEntity<>(new Response(this.message, this.httpStatus), this.httpStatus);
    }
}
