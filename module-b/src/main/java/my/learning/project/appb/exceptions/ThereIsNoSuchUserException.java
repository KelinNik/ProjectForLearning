package my.learning.project.appb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Аккаунт не найден")
public class ThereIsNoSuchUserException extends RuntimeException {
}