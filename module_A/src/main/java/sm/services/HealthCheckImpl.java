package sm.services;

import org.springframework.stereotype.Service;

@Service
public class HealthCheckImpl implements HealthCheck {

    public boolean checkHealthStatus() {
        return true;
    }
}
