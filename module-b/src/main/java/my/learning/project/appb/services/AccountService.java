package my.learning.project.appb.services;

import lombok.extern.slf4j.Slf4j;
import my.learning.project.appb.dto.AccountDto;
import my.learning.project.appb.exceptions.EmptyAccountListException;
import my.learning.project.appb.exceptions.ThereIsNoSuchUserException;
import my.learning.project.appb.model.Account;
import my.learning.project.appb.repo.AccountRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static my.learning.project.appb.mapper.AccountMapper.MAPPER;

@Service
@Slf4j
public class AccountService {
    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDto getAccountById(String id){
        AccountDto accountDto = null;
        if(id == null){
            log.error("id = null");
            throw new ThereIsNoSuchUserException();
        }
        if (!id.isBlank()) {
            log.debug("Получение аккаунта по id = " + id);
            try {
                accountDto = MAPPER.mapToDto(accountRepository.getById(id));
            } catch (EntityNotFoundException e) {
                log.error("Не найден аккаунт по id = " + id, e);
                throw new ThereIsNoSuchUserException();
            }
            return accountDto;
        }
        log.error("id is blank or null");
        return accountDto;
    }

    @Transactional
    public Boolean updateAccountById(AccountDto accountDto) {
        AccountDto accountById = getAccountById(accountDto.getAccountId());
        if (accountById.getAccountId() != null) {
            Account account = MAPPER.mapToAccount(accountDto);
            String accountId = accountDto.getAccountId();
            account.setAccountId(accountId);
            log.debug("Обновление аккаунта " + accountId);
            accountRepository.save(account);
            accountRepository.flush();
            return true;
        }
        log.error("Полученный аккаунт null");
        throw new ThereIsNoSuchUserException();
    }

    public Boolean createAccount(AccountDto accountDto) {
        if (accountDto.getAccountId() != null && accountDto.getFirstName() != null) {
            Account account = MAPPER.mapToAccount(accountDto);
            String accountId = accountDto.getAccountId();
            account.setAccountId(accountId);
            log.info("Сохранение нового аккаунта " + accountId +
                    " " + accountDto.getFirstName() +
                    " " + accountDto.getSecondName());
            accountRepository.save(account);
            accountRepository.flush();
            return true;
        }
        return false;
    }

    public List<AccountDto> getAccounts() {
        log.debug("Получение всех доступных записей");
        List<Account> accountList = accountRepository.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();
        for (Account account : accountList) {
            accountDtos.add(MAPPER.mapToDto(account));
        }
        if(accountDtos.isEmpty()){
            log.error("Записей не найдено");
            throw new EmptyAccountListException();
        }
        return accountDtos;
    }

    @Transactional
    public Boolean deleteAccountById(String id) {
        try {
            log.debug("Удаление аккаунта по id " + id);
            accountRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Аккаунт с " + id +" не найден", e);
            throw new ThereIsNoSuchUserException();
        }
        return true;
    }
}