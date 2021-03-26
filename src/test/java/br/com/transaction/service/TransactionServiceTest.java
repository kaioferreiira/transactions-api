package br.com.transaction.service;


import br.com.transaction.domain.transaction.Transaction;
import br.com.transaction.repository.TransactionRepository;
import br.com.transaction.util.TransactionCreator;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TransactionServiceTest {

  @InjectMocks
  private TransactionService transactionService;

  @Mock
  private TransactionRepository transactionRepositoryMock;

  @BeforeEach
  void setUp(){

  }


  @Test
  @DisplayName("listAll returns list of transactions when successful")
  void listAll_ReturnsListOfTransactions_WhenSuccessful(){

    BDDMockito.when(transactionRepositoryMock.findAll())
        .thenReturn(List.of(TransactionCreator.createTransactionToBeSaved()));

    List<Transaction> transactions = transactionService.listAll();

    Assertions.assertThat(transactions).isNotEmpty()
        .isNotNull().hasSize(1);
  }


}