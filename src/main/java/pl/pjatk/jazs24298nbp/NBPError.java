package pl.pjatk.jazs24298nbp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
@RequestMapping("/nbp/")
public class NBPError {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleExceptionNOTFOUND(RuntimeException runtimeException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("INVALID Query");
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<String> handleBadRequestException(HttpClientErrorException.BadRequest badRequest) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
    }

}
