package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.LancamentoRequestDTO;
import med.voll.api.dto.LancamentoResponseDTO;
import med.voll.api.lancamento.Lancamento;
import med.voll.api.pessoa.Pessoa;
import med.voll.api.repository.LancamentoRepository;
import med.voll.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping
    public ResponseEntity<LancamentoResponseDTO> cadastrar(@RequestBody @Valid LancamentoRequestDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(dto.pessoaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        Lancamento lancamento = new Lancamento(dto, pessoa);
        lancamentoRepository.save(lancamento);
        return ResponseEntity.ok(new LancamentoResponseDTO(lancamento));
    }

    @GetMapping
    public List<LancamentoResponseDTO> listar() {
        return lancamentoRepository.findByAtivoTrue().stream()
                .map(LancamentoResponseDTO::new)
                .toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancamentoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid LancamentoRequestDTO dto) {
        Lancamento lancamento = lancamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lançamento não encontrado"));

        Pessoa pessoa = pessoaRepository.findById(dto.pessoaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        lancamento.setDescricao(dto.descricao());
        lancamento.setValor(dto.valor());
        lancamento.setTipo(dto.tipo());
        lancamento.setData(dto.data());
        lancamento.setPessoa(pessoa);

        lancamentoRepository.save(lancamento);
        return ResponseEntity.ok(new LancamentoResponseDTO(lancamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Lancamento lancamento = lancamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lançamento não encontrado"));

        lancamento.setAtivo(false);
        lancamentoRepository.save(lancamento);
        return ResponseEntity.noContent().build();
    }
}
