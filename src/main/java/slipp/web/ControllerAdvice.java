package slipp.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import slipp.domain.UnAuthenticationException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(UnAuthenticationException.class)
    public ResponseEntity<String> unAuthenticationException(UnAuthenticationException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
