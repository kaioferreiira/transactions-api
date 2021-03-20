package br.com.transaction.domain.dto;

import br.com.transaction.domain.transaction.Beneficiario;
import br.com.transaction.domain.transaction.Conta;
import br.com.transaction.domain.transaction.TipoTransacao;
import java.math.BigDecimal;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {

    @NotNull(message = "Informar o valor da transação")
    private BigDecimal valor;

    @NotNull(message = "Informar a conta de origem da transação")
    private Conta contaOrigem;

    @NotNull(message = "Informar o beneficiário da transação")
    @Valid
    private Beneficiario beneficiario;

    @NotNull(message = "Informar o tipo da transação")
    private TipoTransacao tipoTransacao;

    private UUID uui;

}
