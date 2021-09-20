package my.learning.project.appb.services;

import lombok.extern.slf4j.Slf4j;
import my.learning.project.appb.dto.AccountDto;
import my.learning.project.appb.exceptions.EmptyAccountListException;
import my.learning.project.appb.repo.AccountRepository;
import org.springframework.stereotype.Service;

import static my.learning.project.appb.mapper.AccountMapper.MAPPER;

@Slf4j
@Service
public class HealthCheckService {

    private final AccountRepository accountRepository;

    public HealthCheckService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean getAccountsFromDB() {
        try {
            AccountDto accountDto = MAPPER.mapToDto(accountRepository.getAccounts());
            return accountDto.getAccountId() != null;
        } catch (Exception e) {
            log.error("В БД не удалось ничего найти", e);
        }
        throw new EmptyAccountListException();
    }
}
