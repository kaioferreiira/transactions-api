package br.com.transaction.api;


import br.com.transaction.domain.transaction.Transaction;
import br.com.transaction.service.TransactionService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
@Log4j2
@RequiredArgsConstructor
public class TransactionController {

  private  final TransactionService transactionService;

  @PostMapping
  public ResponseEntity<Transaction> save(@RequestBody @Valid Transaction transaction) {
    return new ResponseEntity<>(transactionService.save(transaction), HttpStatus.CREATED);
  }


}
