package my.learning.project.appa.controllers;

import lombok.extern.slf4j.Slf4j;
import my.learning.project.appa.entities.HealthCheckResponse;
import my.learning.project.appa.entities.HealthCheckStatus;
import my.learning.project.appa.services.HealthCheckService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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
            boolean status = false;
            log.info("Calling healthcheck method...");
            if (getHChStatusFromModuleB()) {
                status = healthCheckService.checkHealthStatus();
                log.info("Healthcheck status for module A is = {}", status);
            }
            return new HealthCheckResponse(status);
        } catch (Exception e) {
            log.error("Error from healthcheck service for A", e);
            return new HealthCheckResponse(false);
        }
    }

    private boolean getHChStatusFromModuleB() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            log.info("Calling healthcheck method from module B...");
            HealthCheckStatus healthCheckStatus = restTemplate.getForObject("http://localhost:8082/healthcheckB", HealthCheckStatus.class);
            return healthCheckStatus.status;
        } catch (Exception e) {
            log.error("Exception from module B");
        }
        return false;
    }
}
