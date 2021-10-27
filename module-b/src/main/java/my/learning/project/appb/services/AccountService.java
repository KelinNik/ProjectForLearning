package my.learning.project.appb.services;

import lombok.extern.slf4j.Slf4j;
import my.learning.project.appb.database.model.AccountModel;
import my.learning.project.appb.dto.Account;
import my.learning.project.appb.mapper.AccountMapper;
import my.learning.project.appb.repo.AccountRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public Account getAccountById(String id) {
        log.debug("Получение аккаунта по id = {}", id);
        return accountMapper.mapToAccount(accountRepository.findById(id).orElseThrow());
    }

    @Transactional
    public AccountModel save(Account account) {
        log.debug("Сохранение аккаунта {}, {}", account.getAccountId(), account.getFirstName());
        var accountModel = accountMapper.mapToModel(account);
        accountRepository.saveAndFlush(accountModel);
        return accountModel;
    }

    public void deleteAccountById(String id) {
        log.debug("Удаление аккаунта по id {}", id);
        try {
            accountRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignored) {
            log.warn("Удаление аккаунта по {} не выполнено", id);
        }
    }
}