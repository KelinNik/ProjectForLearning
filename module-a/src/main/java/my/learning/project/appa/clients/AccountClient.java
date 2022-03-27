package my.learning.project.appa.clients;

import my.learning.project.appa.model.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AccountClient {

    @Value("${app.b.base.url}")
    private String baseUrl;

    RestTemplate template = new RestTemplate();

    public Account getBuId(String id) {
        return template.getForObject(baseUrl +"account/"+ id, Account.class);
    }

    public void save(Account account){
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var request = new HttpEntity<>(account, headers);
        template.postForObject(baseUrl + "account", request, Account.class);
    }
}
