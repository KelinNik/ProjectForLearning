package my.learning.project.appa.services;

import lombok.extern.slf4j.Slf4j;
import my.learning.project.appa.clients.AppBHealthCheckClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HealthCheckService {

    private final AppBHealthCheckClient appBHealthCheckClient;

    public HealthCheckService(AppBHealthCheckClient appBHealthCheckClient) {
        this.appBHealthCheckClient = appBHealthCheckClient;
    }

    public boolean checkHealthStatus() {
        return appBHealthCheckClient.getHChStatusFromModuleB();
    }
}
