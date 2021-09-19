package my.learning.project.appb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Ни один аккаунт не найден")
public class EmptyAccountListException extends RuntimeException{
}
