package my.learning.project.appb.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ThereIsNoSuchUserException.class)
    public ResponseEntity<ErrorMessage> handleThereIsNoSuchUserException() {
        return new ResponseEntity<>(new ErrorMessage(false, "Аккаунт не найден"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyAccountListException.class)
    public ResponseEntity<ErrorMessage> handleEmptyAccountListException() {
        return new ResponseEntity<>(new ErrorMessage(false, "Ни один аккаунт не найден"),
                HttpStatus.I_AM_A_TEAPOT);
    }

    @Data
    @AllArgsConstructor
    private static class ErrorMessage {
        private Boolean success;
        private String message;
    }
}
