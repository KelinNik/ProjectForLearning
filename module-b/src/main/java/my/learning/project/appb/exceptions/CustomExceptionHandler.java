package my.learning.project.appb.exceptions;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.UUID;

@Component
public class CustomExceptionHandler extends DefaultErrorAttributes {

    private static final Map<String, String> STATUS_MESSAGE_MAP = Map.of(
            "400", "Ошибка пользовательского запроса",
            "404", "Адрес не существует",
            "500", "Аккаунт не найден");

    @Override
    public Map<String, Object> getErrorAttributes(
            WebRequest webRequest, ErrorAttributeOptions options) {

        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        String errorMessage;
        switch (errorAttributes.get("status").toString()) {
            case "500":
                errorMessage = STATUS_MESSAGE_MAP.get("500");
                break;
            case "400":
                errorMessage = STATUS_MESSAGE_MAP.get("400");
                break;
            case "404":
                errorMessage = STATUS_MESSAGE_MAP.get("404");
                break;
            default:
                errorMessage = "";
        }

        errorAttributes.remove("path");
        errorAttributes.put("UUID", UUID.randomUUID());
        errorAttributes.put("errorMessage", errorMessage);

        return errorAttributes;
    }
}
