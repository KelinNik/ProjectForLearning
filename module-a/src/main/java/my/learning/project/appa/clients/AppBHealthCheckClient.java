package my.learning.project.appa.clients;

import lombok.extern.slf4j.Slf4j;
import my.learning.project.appa.db.DataBaseConnection;
import my.learning.project.appa.entities.HealthCheckStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class AppBHealthCheckClient {

    @Value("${app.b.base.url}")
    private String baseUrl;

    private DataBaseConnection dataBaseConnection;

    public AppBHealthCheckClient(DataBaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    @Bean
    public boolean getHChStatusFromModuleB() {
        var restTemplate = new RestTemplate();
        try {
            var healthCheckStatus = restTemplate.getForObject(baseUrl + "healthcheckB", HealthCheckStatus.class);
            if (healthCheckStatus != null && dataBaseConnection.getConnectionStatuses()) {
                return healthCheckStatus.isStatus();
            }
        } catch (Exception e) {
            log.error("Ошибка во время запроса статуса ХЧ модуля Б", e);
            return false;
        }
        return false;
    }
}
