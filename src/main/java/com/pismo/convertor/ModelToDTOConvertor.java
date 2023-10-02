package com.pismo.convertor;

import com.pismo.dto.AccountDTO;
import com.pismo.dto.TransactionDTO;
import com.pismo.model.Account;
import com.pismo.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class ModelToDTOConvertor {

  public AccountDTO convertAccountToAccountDTO(Account account) {
    AccountDTO accountDTO = new AccountDTO();
    accountDTO.setId(account.getId());
    accountDTO.setDocumentNumber(account.getDocumentNumber());
    return accountDTO;
  }

  public TransactionDTO convertTransactionToTransactionDTO(Transaction transaction) {
    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setAccountId(transaction.getAccount().getId());
    transactionDTO.setOperationTypeId(transaction.getOperationTypeId().getId());
    transactionDTO.setAmount(transaction.getAmount());
    return transactionDTO;
  }

}
