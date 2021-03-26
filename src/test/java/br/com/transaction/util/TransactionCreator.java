package br.com.transaction.util;

import br.com.transaction.domain.transaction.Transaction;
import java.math.BigDecimal;

public class TransactionCreator {


  public static Transaction createTransactionToBeSaved(){
    return Transaction.builder()
        .valor(new BigDecimal(50)).build();
  }

}
