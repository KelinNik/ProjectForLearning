package sm.services;

import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {

    public HealthCheckService() {
    }

    public boolean checkHealthStatus() {
        return true;
    }
}
