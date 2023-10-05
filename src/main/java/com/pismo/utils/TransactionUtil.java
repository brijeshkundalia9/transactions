package com.pismo.utils;

import com.pismo.model.Transaction;
import java.util.List;

public class TransactionUtil {

  public static List<Transaction> computeBalance(List<Transaction> transactions,
      Transaction creditTransaction) {

    float balance = creditTransaction.getAmount();
    for (Transaction transaction : transactions) {
      if (balance > transaction.getAmount()) {
        transaction.setBalance(0F);
        balance -= transaction.getAmount();
        if (balance == 0f) {
          creditTransaction.setBalance(0F);
        }
      } else {
        float remainingBalance = transaction.getAmount() - balance;
        transaction.setBalance(remainingBalance);
        creditTransaction.setBalance(0F);
        break;
      }
    }

    return transactions;

  }

}
