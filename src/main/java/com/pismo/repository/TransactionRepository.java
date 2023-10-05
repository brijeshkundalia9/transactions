package com.pismo.repository;

import com.pismo.contants.OperationType;
import com.pismo.model.Account;
import com.pismo.model.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//  @Query("SELECT t FROM Transaction t where account = 1 and operationTypeId in (2) and balance <> 0 order by eventDate ASC")
//  List<Transaction> findTransactionsWithBalance(Account account, List<Integer> ops);

  List<Transaction> findByAccountAndOperationTypeIdInAndBalanceNot(Account account, List<OperationType> operationTypes, float balance);

}
