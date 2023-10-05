package com.pismo.convertor;

import com.pismo.contants.OperationType;
import com.pismo.dto.AccountDTO;
import com.pismo.dto.TransactionDTO;
import com.pismo.model.Account;
import com.pismo.model.Transaction;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class DTOToModelConvertor {

  public Account convertAccountDTOToAccount(AccountDTO accountDTO) {
    Account account = new Account();
    account.setDocumentNumber(accountDTO.getDocumentNumber());
    return account;
  }

  public Transaction convertTransactionDTOToTransaction(TransactionDTO transactionDTO) {
    Transaction transaction = new Transaction();
    transaction.setOperationTypeId(OperationType.getById(transactionDTO.getOperationTypeId()));
    transaction.setAmount(transactionDTO.getAmount());
    transaction.setEventDate(LocalDateTime.now());
    transaction.setBalance(transaction.getAmount());
    return transaction;
  }
}
