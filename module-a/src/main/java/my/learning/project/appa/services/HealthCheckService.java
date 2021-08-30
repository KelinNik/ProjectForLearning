package my.learning.project.appa.services;

import lombok.extern.slf4j.Slf4j;
import my.learning.project.appa.clients.AppBHealthCheckClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HealthCheckService {

    private AppBHealthCheckClient appBHealthCheckClient;

    public HealthCheckService(AppBHealthCheckClient appBHealthCheckClient) {
        this.appBHealthCheckClient = appBHealthCheckClient;
    }

    @Value("${base.url}")
    String baseUrl;

    public boolean checkHealthStatus() {
        return appBHealthCheckClient.getHChStatusFromModuleB();
    }
}
