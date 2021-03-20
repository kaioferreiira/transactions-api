package br.com.transaction.service;

import br.com.transaction.domain.transaction.Transaction;
import br.com.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class TransactionService {

  private final TransactionRepository transactionRepository;

  @Transactional
  public Transaction save(Transaction transaction) {
    return transactionRepository.save(transaction);
  }


}
