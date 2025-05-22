package med.voll.api.dto;

import med.voll.api.lancamento.Lancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoResponseDTO(
        Long id,
        String descricao,
        BigDecimal valor,
        String tipo, // ou TipoLancamento se estiver usando enum
        LocalDate data,
        String nomePessoa
) {
    public LancamentoResponseDTO(Lancamento lancamento) {
        this(
                lancamento.getId(),
                lancamento.getDescricao(),
                lancamento.getValor(),
                lancamento.getTipo(),
                lancamento.getData(),
                lancamento.getPessoa().getNome()
        );
    }
}


