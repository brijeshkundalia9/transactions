package com.pismo.service.impl;

import com.pismo.convertor.DTOToModelConvertor;
import com.pismo.convertor.ModelToDTOConvertor;
import com.pismo.dto.TransactionDTO;
import com.pismo.exception.AccountNotFoundException;
import com.pismo.model.Account;
import com.pismo.model.Transaction;
import com.pismo.repository.AccountRepository;
import com.pismo.repository.TransactionRepository;
import com.pismo.service.TransactionService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private DTOToModelConvertor dtoToModelConvertor;

  @Autowired
  private ModelToDTOConvertor modelToDTOConvertor;

  @Override
  public TransactionDTO addTransaction(TransactionDTO transactionDTO) {
    Optional<Account> accountOptional = accountRepository.findById(transactionDTO.getAccountId());
    if (accountOptional.isEmpty()) {
      log.error("Account not found for id : {}", transactionDTO.getAccountId());
      throw new AccountNotFoundException();
    }
    Account account = accountOptional.get();
    log.info("Creating new transaction for account {}", account.getId());
    Transaction transaction = dtoToModelConvertor.convertTransactionDTOToTransaction(
        transactionDTO);
    transaction.setAccount(account);
    transaction = transactionRepository.save(transaction);
    log.info("Transaction created for account {}", account.getId());
    return modelToDTOConvertor.convertTransactionToTransactionDTO(transaction);
  }
}
