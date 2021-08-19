package sm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sm.services.HealthCheck;

@Controller
public class HealthCheckController {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

    @Autowired
    HealthCheck healthCheck;

    @GetMapping("/hello")
    public String getGreeting(@RequestParam(name = "name", defaultValue = "Dude") String name, Model model) {
        model.addAttribute("name", name);
        logger.info("name is " + name);
        return "hello";
    }

    @GetMapping("/healthcheck")
    public String getHealthCheck(Model model) {
        boolean healthcheck = healthCheck.checkHealthStatus();
        model.addAttribute("Healhtcheck", healthcheck);
        logger.info("HealthCheeck status is " + healthcheck);
        return "healthcheck";
    }
}
