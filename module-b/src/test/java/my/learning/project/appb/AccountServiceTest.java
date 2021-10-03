package my.learning.project.appb;

import my.learning.project.appb.dto.Account;
import my.learning.project.appb.repo.AccountRepository;
import my.learning.project.appb.services.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Тестирование CRUD Сервиса")
class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private Account account;

    @Test
    @Transactional
    @DisplayName("Успешное сохранение аккаунта")
    void checkCreateAccountPositive() {

        // Arrange
        account.setAccountId("111");
        account.setFirstName("FirstName");
        account.setSecondName("FamilyName");

        // Act
        var accountModel = accountService.createAccount(account);

        // Assert
        assertEquals("111", accountRepository.getById(accountModel.getAccountId()).getAccountId());
        assertEquals("FirstName", accountRepository.getById(accountModel.getAccountId()).getFirstName());
        assertEquals("FamilyName", accountRepository.getById(accountModel.getAccountId()).getSecondName());
    }

    @Test
    @Transactional
    @DisplayName("Успешное получение аккаунта")
    void checkGetAccountPositive() {

        // Arrange
        account.setAccountId("FF");
        account.setFirstName("FirstName");
        account.setSecondName("FamilyName");
        accountService.createAccount(account);

        // Act
        var ff = accountRepository.getById("FF");
        // Assert
        assertEquals("FF", accountRepository.getById(ff.getAccountId()).getAccountId());
        assertEquals("FirstName", accountRepository.getById(ff.getAccountId()).getFirstName());
        assertEquals("FamilyName", accountRepository.getById(ff.getAccountId()).getSecondName());
    }

    @Test
    @Transactional
    @DisplayName("Успешное обновление аккаунта")
    void checkUpdateAccountPositive() {

        // Arrange
        account.setAccountId("FF1");
        account.setFirstName("FirstName");
        account.setSecondName("FamilyName");
        var accountModel = accountService.createAccount(account);

        // Act
        accountRepository.getById("FF1").setFirstName("UpdatedName");
        accountRepository.getById("FF1").setSecondName("UpdatedFName");

        // Assert
        assertEquals("FF1", accountRepository.getById(accountModel.getAccountId()).getAccountId());
        assertEquals("UpdatedName", accountRepository.getById(accountModel.getAccountId()).getFirstName());
        assertEquals("UpdatedFName", accountRepository.getById(accountModel.getAccountId()).getSecondName());
    }

    @Test
    @Transactional
    @DisplayName("Успешное удаление аккаунта")
    void checkDeleteAccountPositive() {

        // Arrange
        account.setAccountId("FF2");
        account.setFirstName("FirstName");
        account.setSecondName("FamilyName");
        var accountModel = accountService.createAccount(account);
        var accountId = accountModel.getAccountId();

        // Assert
        assertNotNull(accountId);
        assertEquals("FF2", accountRepository.getById(accountId).getAccountId());
        assertEquals("FirstName", accountRepository.getById(accountId).getFirstName());
        assertEquals("FamilyName", accountRepository.getById(accountId).getSecondName());

        // Act
        accountRepository.deleteById(accountId);

        // Assert
        assertThrows(JpaObjectRetrievalFailureException.class, () -> accountRepository.getById(accountId));
    }

    @Test
    @Transactional
    @DisplayName("Ошибка при сохранении аккаунта")
    void checkCreateAccountNegative() {

        // Arrange
        account.setAccountId(null);
        account.setFirstName("FirstName");
        account.setSecondName("FamilyName");

        // Act
        // Assert
        assertThrows(JpaSystemException.class, () -> accountService.createAccount(this.account));

        // Arrange
        account.setAccountId("");
        account.setFirstName("FirstName");
        account.setSecondName("FamilyName");

        // Act
        // Assert
        assertThrows(ConstraintViolationException.class, () -> accountService.createAccount(this.account));

        // Arrange
        account.setAccountId(" ");
        account.setFirstName("FirstName");
        account.setSecondName("FamilyName");

        // Act
        // Assert
        assertThrows(ConstraintViolationException.class, () -> accountService.createAccount(this.account));
    }
}
