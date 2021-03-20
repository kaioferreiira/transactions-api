package br.com.transaction.mapper;


import br.com.transaction.domain.dto.RequisicaoTransacaoDTO;
import br.com.transaction.domain.transaction.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class TransactionMapper {
    public static final TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    public abstract Transaction toTransaction(RequisicaoTransacaoDTO requisicaoTransacaoDTO);

}
