package br.com.transaction.api;


import br.com.transaction.domain.dto.RequisicaoTransacaoDTO;
import br.com.transaction.domain.transaction.Transaction;
import br.com.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
@Log4j2
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;

  @PostMapping
  @Operation(summary = "Cria uma nova transação", description = "Cria uma transação e envia para validação das informações. ")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful Operation"),
  })
  public ResponseEntity<Transaction> createTransaction(
      @RequestBody @Valid RequisicaoTransacaoDTO requisicaoTransacaoDTO) {
    return new ResponseEntity<>(transactionService.save(requisicaoTransacaoDTO),
        HttpStatus.CREATED);
  }

  @GetMapping(path = "/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful Operation"),
      @ApiResponse(responseCode = "400", description = "When Transaction Does Not Exist in The Database")
  })
  public ResponseEntity<Transaction> findById(@PathVariable String id) {
    return ResponseEntity.ok(transactionService.findByIdOrThrowBadRequestException(id));
  }

  @GetMapping(path = "/all")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful Operation"),
  })
  public ResponseEntity<List<Transaction>> listAll() {
    return ResponseEntity.ok(transactionService.listAll());
  }

  @PutMapping
  public ResponseEntity<Void> replace(@RequestBody Transaction transaction) {
    transactionService.replace(transaction);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping(path = "/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Successful Operation"),
      @ApiResponse(responseCode = "400", description = "When Transaction Does Not Exist in The Database")
  })
  public ResponseEntity<Void> delete(@PathVariable String id) {
    transactionService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


}
