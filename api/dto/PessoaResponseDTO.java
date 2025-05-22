package med.voll.api.dto;

import med.voll.api.pessoa.Pessoa;

public record PessoaResponseDTO(
        Long id,
        String nome,
        String email,
        String tipo // ou PessoaTipo se você estiver usando enum
) {
    public PessoaResponseDTO(Pessoa pessoa) {
        this(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(), pessoa.getTipo());
    }
}

