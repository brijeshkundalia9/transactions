package com.pismo.utils;

import com.pismo.model.Transaction;
import java.util.List;

public class TransactionUtil {

  public static List<Transaction> computeBalance(List<Transaction> transactions,
      Transaction creditTransaction) {

    float balance = creditTransaction.getAmount();
    for (Transaction transaction : transactions) {
      if (balance > (transaction.getBalance() * -1)) {
        balance -= (transaction.getBalance() * -1);
        transaction.setBalance(0F);
        creditTransaction.setBalance(balance);
      } else {
        float remainingBalance = transaction.getBalance() + balance;
        transaction.setBalance(remainingBalance);
        creditTransaction.setBalance(0F);
        break;
      }
    }

    return transactions;

  }

}
