package br.com.transaction.service;

import br.com.transaction.domain.dto.RequisicaoTransacaoDTO;
import br.com.transaction.domain.dto.UpdateTransactionDTO;
import br.com.transaction.domain.transaction.SituacaoEnum;
import br.com.transaction.domain.transaction.Transaction;
import br.com.transaction.exception.BadRequestException;
import br.com.transaction.mapper.TransactionMapper;
import br.com.transaction.repository.TransactionRepository;
import java.time.LocalDateTime;
import java.util.List;
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
  public Transaction save(RequisicaoTransacaoDTO requisicaoTransacaoDTO) {

    Transaction transaction = TransactionMapper.INSTANCE.toTransaction(requisicaoTransacaoDTO);
    transaction.setSituacao(SituacaoEnum.NAO_ANALISADA);
    transaction.setData(LocalDateTime.now());

    return transactionRepository.save(transaction);
  }


  @Transactional
  public Transaction save(Transaction transaction) {
    return transactionRepository.save(transaction);
  }

  public Transaction findByIdOrThrowBadRequestException(String id) {
    return transactionRepository.findById(id)
        .orElseThrow(() -> new BadRequestException("Transaction not Found"));
  }

  public List<Transaction> listAll() {
    return transactionRepository.findAll();
  }

  public void replace(UpdateTransactionDTO updateTransactionDTO) {

    Transaction savedTransaction = findByIdOrThrowBadRequestException(updateTransactionDTO.getId());
    Transaction transaction = TransactionMapper.INSTANCE.toTransaction(updateTransactionDTO);
    transaction.setId(savedTransaction.getId());
    transactionRepository.save(transaction);
  }

  public void delete(String id) {
    transactionRepository.delete(findByIdOrThrowBadRequestException(id));
  }

}
