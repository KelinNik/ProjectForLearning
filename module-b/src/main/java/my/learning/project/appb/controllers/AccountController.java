package my.learning.project.appb.controllers;

import lombok.extern.slf4j.Slf4j;
import my.learning.project.appb.dto.AccountDto;
import my.learning.project.appb.services.AccountService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/getAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    AccountDto getAccount(@RequestParam(name = "id") String id) {
        return accountService.getAccountById(id);
    }

    @GetMapping(value = "/getAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<AccountDto> getAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping(value = "/createAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Boolean createAccount(@RequestBody AccountDto accountDto) {
        return accountService.createAccount(accountDto);
    }

    @PutMapping(value = "/updateAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Boolean updateAccount(@RequestBody AccountDto accountDto) {
      return accountService.updateAccountById(accountDto);
    }

    @DeleteMapping(value = "/deleteAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Boolean deleteAccount(@RequestParam(name = "id") String id) {
        return accountService.deleteAccountById(id);
    }
}
