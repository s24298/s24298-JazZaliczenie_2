package pl.pjatk.jazs24298nbp;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {

        HttpStatus httpStatus = determineHttpStatus(ex);
        return new ResponseEntity<>("ERROR", httpStatus);
    }
    private HttpStatus determineHttpStatus(Exception ex) {
        if (ex instanceof HttpClientErrorException) {
            return (HttpStatus) ((HttpClientErrorException) ex).getStatusCode();
        } else if (ex instanceof HttpServerErrorException) {
            return (HttpStatus) ((HttpServerErrorException) ex).getStatusCode();
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}