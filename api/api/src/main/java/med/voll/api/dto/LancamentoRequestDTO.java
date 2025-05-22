package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoRequestDTO(

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        @NotNull(message = "O valor é obrigatório")
        @Positive(message = "O valor deve ser positivo")
        BigDecimal valor,

        @NotBlank(message = "O tipo é obrigatório (DEBITO ou CREDITO)")
        String tipo, // ou TipoLancamento se estiver usando enum

        @NotNull(message = "A data é obrigatória")
        LocalDate data,

        @NotNull(message = "O ID da pessoa é obrigatório")
        Long pessoaId
) {}

