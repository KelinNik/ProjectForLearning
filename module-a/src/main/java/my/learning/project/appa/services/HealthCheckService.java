package my.learning.project.appa.services;

import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {

    public boolean checkHealthStatus() {
        return true;
    }
}
