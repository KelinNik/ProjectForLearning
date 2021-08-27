package my.learning.project.appa.clients;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import my.learning.project.appa.entities.HealthCheckStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@PropertySource("classpath:env.properties")
@Data
@Component
public class AppBHealthCheckClient {

    @Value("${base.url}")
    String baseUrl;

    @Bean
    public boolean getHChStatusFromModuleB() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            HealthCheckStatus healthCheckStatus = restTemplate.getForObject(baseUrl + "healthcheckB", HealthCheckStatus.class);
            return healthCheckStatus.status;
        } catch (Exception e) {
            log.error("Exception from module B", e);
        }
        return false;
    }
}
