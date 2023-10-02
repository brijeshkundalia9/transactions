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
public class AccountController {

  @Autowired
  private AccountService accountService;

  /**
   * It is a restful API to create an Account.
   *
   * It should not have account id populated.
   *
   * 201 Created -- As response for successful creation.
   * @param accountDTO
   * @return accountDTO as response.
   */
  @PostMapping
  public ResponseEntity<AccountDTO> addAccount(@RequestBody @Valid AccountDTO accountDTO) {
    AccountDTO account = accountService.addAccount(accountDTO);
    return new ResponseEntity<>(account, HttpStatus.CREATED);
  }

  /**
   * It is a restful API to get account details.
   *
   * 404 Not Found -- if account doesn't exist.
   * 200 OK -- account with all details if it exists.
   * @param accountId
   * @return accountDTO as response
   */
  @GetMapping(path = "{accountId}")
  public ResponseEntity<AccountDTO> getAccount(@PathVariable(value = "accountId") Long accountId) {
    AccountDTO account = accountService.getAccount(accountId);
    return new ResponseEntity<>(account, HttpStatus.OK);
  }

}
