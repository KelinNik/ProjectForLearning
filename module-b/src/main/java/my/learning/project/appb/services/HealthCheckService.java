package my.learning.project.appb.services;

import lombok.extern.slf4j.Slf4j;
import my.learning.project.appb.mapper.AccountMapper;
import my.learning.project.appb.repo.AccountRepository;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class HealthCheckService {

    private final AccountRepository accountRepository;
    private final AccountMapper mapper;

    public HealthCheckService(AccountRepository accountRepository, AccountMapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    public boolean getAccountsFromDB() {
        try {
            var account =  mapper.mapToAccount(accountRepository.getAccounts());
            return account.getAccountId() != null;
        } catch (Exception e) {
            log.error("В БД не удалось ничего найти", e);
        }
        return false;
    }
}
