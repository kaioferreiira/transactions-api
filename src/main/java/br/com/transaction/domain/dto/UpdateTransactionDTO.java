package br.com.transaction.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateTransactionDTO extends TransactionDTO {

  private String id;

}
