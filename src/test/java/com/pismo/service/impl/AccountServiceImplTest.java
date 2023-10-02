package com.pismo.service.impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pismo.convertor.DTOToModelConvertor;
import com.pismo.convertor.ModelToDTOConvertor;
import com.pismo.dto.AccountDTO;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.pismo.exception.AccountNotFoundException;
import com.pismo.model.Account;
import com.pismo.repository.AccountRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountServiceImplTest {

  @InjectMocks
  private AccountServiceImpl accountService;

  @Mock
  private AccountRepository accountRepository;

  @Mock
  private DTOToModelConvertor dtoToModelConvertor;

  @Mock
  private ModelToDTOConvertor modelToDTOConvertor;

  @Test
  void createAccountTest_success() {
    AccountDTO accountDto = new AccountDTO();
    Account account = new Account();
    when(dtoToModelConvertor.convertAccountDTOToAccount(accountDto)).thenReturn(account);
    when(accountRepository.save(account)).thenReturn(account);
    when(modelToDTOConvertor.convertAccountToAccountDTO(account)).thenReturn(accountDto);

    accountService.addAccount(accountDto);
    verify(accountRepository, times(1)).save(any(Account.class));
  }

  @Test
  void getAccountTest_success() {
    AccountDTO accountDto = new AccountDTO();
    Account account = new Account();
    Long accountId = 1L;
    account.setId(accountId);
    account.setDocumentNumber("abc");
    when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
    when(modelToDTOConvertor.convertAccountToAccountDTO(account)).thenReturn(accountDto);

    accountService.getAccount(accountId);
    verify(accountRepository, times(1)).findById(any(Long.class));
  }

  @Test
  void getAccountTest_accountNotFound() {
    Long accountId = 1L;
    when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

    assertThrows(AccountNotFoundException.class, () -> accountService.getAccount(accountId));
    verify(accountRepository, times(1)).findById(any(Long.class));
  }

}
