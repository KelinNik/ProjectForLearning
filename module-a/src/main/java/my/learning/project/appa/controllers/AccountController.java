package my.learning.project.appa.controllers;

import lombok.extern.slf4j.Slf4j;
import my.learning.project.appa.model.Account;
import my.learning.project.appa.services.AccountService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Account getAccount(@PathVariable Long id) {
        log.info("GET account by id = {}", id);
        return accountService.getAccountById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void createAccount(@RequestBody Account account) {
        log.info("Creating account...");
        accountService.save(account);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateAccount(@RequestBody Account account) {
        log.info("Updating account...");
        accountService.save(account);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAccount(@PathVariable Long id) {
        log.info("Delete account by id = {}", id);
        accountService.deleteAccountById(id);
    }
}
