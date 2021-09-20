package my.learning.project.appb.controllers;

import lombok.extern.slf4j.Slf4j;
import my.learning.project.appb.entities.HealthCheckResponse;
import my.learning.project.appb.services.HealthCheckService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class HealthCheckController {

    private final HealthCheckService healthCheckService;

    public HealthCheckController(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    @GetMapping(value = "/healthcheckB", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    HealthCheckResponse getHealthCheckB() {
        try {
            log.debug("Вызов метода healthcheck...");
            var accountsFromDB = healthCheckService.getAccountsFromDB();
            log.debug("Для модуля В статус healthcheck = {}", accountsFromDB);
            return new HealthCheckResponse(accountsFromDB);
        } catch (Exception ex) {
            log.error("Ошибка из сервиса healthcheck для модуля B", ex);
            return new HealthCheckResponse(false);
        }
    }
}
