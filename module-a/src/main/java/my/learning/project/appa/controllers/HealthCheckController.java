package my.learning.project.appa.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import my.learning.project.appa.services.HealthCheckService;

@Controller
@Slf4j
public class HealthCheckController {

    private final HealthCheckService healthCheckService;

    public HealthCheckController(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("/healthcheck")
    public boolean getHealthCheck() {
        try {
            log.info("calling healthcheck method...");
            var status = healthCheckService.checkHealthStatus();
            log.info("Healthcheck status is = {}", status);
            return status;
        } catch (Exception ex) {
            log.error("Error from healthcheck service for A", ex);
            return false;
        }
    }
}
