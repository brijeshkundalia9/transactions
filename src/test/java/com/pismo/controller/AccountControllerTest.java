package com.pismo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.dto.AccountDTO;
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
class AccountControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private AccountRepository accountRepository;

  @Test
  void getAccount_success() throws Exception {
    createAccount();

    mvc.perform(get("/accounts/1"))
        .andExpect(status().isOk())
        .andExpect(content().json("{\"account_id\":1,\"document_number\":\"abc\"}"));
  }

  @Test
  void createAccount_success() throws Exception {
    AccountDTO accountDTO = new AccountDTO();
    accountDTO.setDocumentNumber("abc");
    String requestBody = new ObjectMapper().writeValueAsString(accountDTO);

    mvc.perform(post("/accounts").content(requestBody)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  void createAccount_failure_accountIdShouldBeBlank() throws Exception {
    AccountDTO accountDTO = new AccountDTO();
    accountDTO.setId(10L);
    accountDTO.setDocumentNumber("abc");
    String requestBody = new ObjectMapper().writeValueAsString(accountDTO);

    mvc.perform(post("/accounts").content(requestBody)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  private void createAccount() {
    Account account = new Account();
    account.setDocumentNumber("abc");

    accountRepository.save(account);
  }

}
