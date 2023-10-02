package com.pismo.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pismo.convertor.DTOToModelConvertor;
import com.pismo.convertor.ModelToDTOConvertor;
import com.pismo.dto.TransactionDTO;
import com.pismo.exception.AccountNotFoundException;
import com.pismo.model.Account;
import com.pismo.model.Transaction;
import com.pismo.repository.AccountRepository;
import com.pismo.repository.TransactionRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionServiceImplTest {

  @InjectMocks
  private TransactionServiceImpl transactionService;

  @Mock
  private AccountRepository accountRepository;

  @Mock
  private TransactionRepository transactionRepository;

  @Mock
  private DTOToModelConvertor dtoToModelConvertor;

  @Mock
  private ModelToDTOConvertor modelToDTOConvertor;

  @Test
  void addTransaction_success() {
    TransactionDTO transactionDto = new TransactionDTO();
    Transaction transaction = new Transaction();
    Account account = new Account();
    Long accountId = 1L;
    account.setId(accountId);
    account.setDocumentNumber("abc");
    transactionDto.setAccountId(accountId);

    when(dtoToModelConvertor.convertTransactionDTOToTransaction(transactionDto)).thenReturn(
        transaction);
    when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
    when(modelToDTOConvertor.convertTransactionToTransactionDTO(transaction)).thenReturn(
        transactionDto);

    transactionService.addTransaction(transactionDto);

    verify(accountRepository, times(1)).findById(any(Long.class));
    verify(transactionRepository, times(1)).save(any(Transaction.class));
  }

  @Test
  void addTransactionTest_accountNotFound() {
    Long accountId = 1L;
    when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

    assertThrows(AccountNotFoundException.class,
        () -> transactionService.addTransaction(new TransactionDTO()));
    verify(accountRepository, times(1)).findById(any(Long.class));
  }

}
