package my.learning.project.appb.controllers;

import lombok.extern.slf4j.Slf4j;
import my.learning.project.appb.dto.HealthCheckResponse;
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
    public @ResponseBody HealthCheckResponse getHealthCheck() {
        try {
            log.info("Calling healthcheck method...");
            var status = healthCheckService.checkHealthStatus();
            log.info("Healthcheck status for module B is = {}", status);
            return new HealthCheckResponse(status);
        } catch (Exception ex) {
            log.error("Error from healthcheck service for B", ex);
            return new HealthCheckResponse(false);
        }
    }
}
