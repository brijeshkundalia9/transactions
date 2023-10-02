package com.pismo.controller;

import com.pismo.dto.TransactionDTO;
import com.pismo.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @PostMapping
  public ResponseEntity<TransactionDTO> addTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {
    TransactionDTO transaction = transactionService.addTransaction(transactionDTO);
    return new ResponseEntity<>(transaction, HttpStatus.CREATED);
  }

}
