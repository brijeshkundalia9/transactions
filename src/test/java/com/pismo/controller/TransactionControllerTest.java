package com.pismo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.dto.TransactionDTO;
import com.pismo.model.Account;
import com.pismo.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private AccountRepository accountRepository;

  @Test
  void createTransaction_success() throws Exception {
    createAccount();

    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setAccountId(1L);
    transactionDTO.setOperationTypeId(1);
    transactionDTO.setAmount(-100.00F);
    String requestBody = new ObjectMapper().writeValueAsString(transactionDTO);

    mvc.perform(post("/transactions").content(requestBody)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  void createTransaction_badRequest_accountNotFound() throws Exception {
    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setAccountId(100L);
    transactionDTO.setOperationTypeId(1);
    transactionDTO.setAmount(-100.00F);
    String requestBody = new ObjectMapper().writeValueAsString(transactionDTO);

    mvc.perform(post("/transactions").content(requestBody)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Account not found"));
  }

  @Test
  void createTransaction_failure_ruleBasedOnOpType() throws Exception {
    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setAccountId(1L);
    transactionDTO.setOperationTypeId(1);
    transactionDTO.setAmount(100.00F);
    String requestBody = new ObjectMapper().writeValueAsString(transactionDTO);

    mvc.perform(post("/transactions").content(requestBody)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().json(
            "[\"Transactions of type purchase and withdrawal are registered with negative amounts, while transactions of credit voucher are registered with positive value.\"]"));
  }

  private void createAccount() {
    Account account = new Account();
    account.setDocumentNumber("abc");

    accountRepository.save(account);
  }

}
