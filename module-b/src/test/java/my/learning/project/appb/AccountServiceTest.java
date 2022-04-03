package my.learning.project.appb;

import my.learning.project.appb.dto.Account;
import my.learning.project.appb.repo.AccountRepository;
import my.learning.project.appb.services.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DisplayName("Тестирование CRUD Сервиса")
class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Transactional
    @DisplayName("Успешное сохранение аккаунта")
    void checkCreateAccountPositive() {
        // Arrange
        var account = new Account();
        account.setAccountId(new Random().longs(1, 999999999).findFirst().getAsLong());
        account.setFirstName("FirstName");
        account.setSecondName("FamilyName");

        // Act
        var accountModel = accountService.save(account);

        // Assert
        var founded = accountRepository.getById(accountModel.getAccountId());
        assertEquals(account.getAccountId(), founded.getAccountId());
        assertEquals(account.getFirstName(), founded.getFirstName());
        assertEquals(account.getSecondName(), founded.getSecondName());
    }

    @Test
    @Transactional
    @DisplayName("Успешное получение аккаунта")
    void checkGetAccountPositive() {
        // Arrange
        var account = new Account();
        var id =   new Random().longs(1, 999999999).findFirst().getAsLong();
        account.setAccountId(id);
        account.setFirstName("FirstName");
        account.setSecondName("FamilyName");
        accountService.save(account);

        // Act
        var ff = accountRepository.getById(id);
        // Assert
        assertEquals(account.getAccountId(), accountRepository.getById(ff.getAccountId()).getAccountId());
        assertEquals(account.getFirstName(), accountRepository.getById(ff.getAccountId()).getFirstName());
        assertEquals(account.getSecondName(), accountRepository.getById(ff.getAccountId()).getSecondName());
    }

    @Test
    @Transactional
    @DisplayName("Успешное обновление аккаунта")
    void checkUpdateAccountPositive() {
        // Arrange
        var account = new Account();
        account.setAccountId(new Random().longs(1, 999999999).findFirst().getAsLong());
        account.setFirstName("FirstName");
        account.setSecondName("FamilyName");

        // Act
        accountService.save(account);
        account.setSecondName("upd");
        var accountModel = accountService.save(account);

        // Assert
        assertEquals(account.getAccountId(), accountModel.getAccountId());
        assertEquals(account.getFirstName(), accountModel.getFirstName());
        assertEquals(account.getSecondName(), accountModel.getSecondName());
    }

    @Test
    @Transactional
    @DisplayName("Успешное удаление аккаунта")
    void checkDeleteAccountPositive() {
        // Arrange
        var account = new Account();
        account.setAccountId(new Random().longs(1, 999999999).findFirst().getAsLong());
        account.setFirstName("fn");
        var accountModel = accountService.save(account);
        var accountId = accountModel.getAccountId();

        // Act
        accountRepository.deleteById(accountId);

        // Assert
        assertThrows(JpaObjectRetrievalFailureException.class, () -> accountRepository.getById(accountId));
    }

    @Test
    @Transactional
    @DisplayName("НЕ успешное удаление аккаунта")
    void checkDeleteAccountNegative() {
        // Act -> Assert
       assertThrows(Exception.class, () -> accountRepository.deleteById(123L));
    }
}
