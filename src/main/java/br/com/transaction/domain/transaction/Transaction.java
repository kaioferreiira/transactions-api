package br.com.transaction.domain.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 3275599364607868470L;

    @Id
    @Indexed(unique = true)
    private String id;

    @NotNull(message = "Informar o valor da transação")
    @Schema(description = "This is the Anime's name", example = "Tensei Shittara Slime Datta Ken", required = true)
    private BigDecimal valor;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime data;

    @NotNull(message = "Informar a conta de origem da transação")
    private Conta contaOrigem;

    @NotNull(message = "Informar o beneficiário da transação")
    @Valid
    private Beneficiario beneficiario;

    @NotNull(message = "Informar o tipo da transação")
    private TipoTransacao tipoTransacao;

    private UUID uui;

    private SituacaoEnum situacao;



}
