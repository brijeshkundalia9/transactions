package com.pismo.controller;

import com.pismo.dto.AccountDTO;
import com.pismo.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts")
// TODO : Add documentation
// TODO : Add docker
// TODO : Improve Readme
// TODO : Add test cases
public class AccountController {

  @Autowired
  private AccountService accountService;

  @PostMapping
  public ResponseEntity<AccountDTO> addAccount(@RequestBody @Valid AccountDTO accountDTO) {
    AccountDTO account = accountService.addAccount(accountDTO);
    return new ResponseEntity<>(account, HttpStatus.CREATED);
  }

  @GetMapping(path = "{accountId}")
  public ResponseEntity<AccountDTO> getAccount(@PathVariable(value = "accountId") Long accountId) {
    AccountDTO account = accountService.getAccount(accountId);
    return new ResponseEntity<>(account, HttpStatus.OK);
  }

}
