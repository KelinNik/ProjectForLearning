package my.learning.project.appa.controllers;

import lombok.extern.slf4j.Slf4j;
import my.learning.project.appa.entities.HealthCheckResponse;
import my.learning.project.appa.services.HealthCheckService;
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

    @GetMapping(value = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    HealthCheckResponse getHealthCheck() {
        try {
            boolean status;
            log.info("Calling healthcheck method...");
            status = healthCheckService.checkHealthStatus();
            log.info("Healthcheck status for module A is = {}", status);
            return new HealthCheckResponse(status);
        } catch (Exception e) {
            log.error("Error from healthcheck service", e);
            return new HealthCheckResponse(false);
        }
    }
}
