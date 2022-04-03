package my.learning.project.appa.services;

import my.learning.project.appa.clients.AccountClient;
import my.learning.project.appa.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountService {

    private final AccountClient client;

    public AccountService(AccountClient client) {
        this.client = client;
    }

    public Account getAccountById(Long id) {
        var account = client.getBuId(id);
        return account;
    }

    public void save(Account account) {
        client.save(account);
    }

    public void deleteAccountById(Long id) {
        client.delete(id);
    }
}
