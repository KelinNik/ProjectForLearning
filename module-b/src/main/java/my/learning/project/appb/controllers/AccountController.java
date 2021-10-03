package my.learning.project.appb.controllers;

import lombok.extern.slf4j.Slf4j;
import my.learning.project.appb.database.model.AccountModel;
import my.learning.project.appb.dto.Account;
import my.learning.project.appb.services.AccountService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@Slf4j
@Validated
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Account getAccount(@PathVariable @NotBlank String id) {
        return accountService.getAccountById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountModel createAccount(@RequestBody @Valid Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountModel updateAccount(@RequestBody @Valid Account account) {
        return accountService.createAccount(account);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAccount(@PathVariable @NotBlank String id) {
        accountService.deleteAccountById(id);
    }
}
