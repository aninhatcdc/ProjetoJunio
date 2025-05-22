package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.PessoaRequestDTO;
import med.voll.api.dto.PessoaResponseDTO;
import med.voll.api.pessoa.Pessoa;
import med.voll.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    @PostMapping
    public ResponseEntity<PessoaResponseDTO> cadastrar(@RequestBody @Valid PessoaRequestDTO dto) {
        Pessoa pessoa = new Pessoa(dto);
        repository.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PessoaResponseDTO(pessoa));
    }

    @GetMapping
    public List<PessoaResponseDTO> listar() {
        return repository.findByAtivoTrue().stream()
                .map(PessoaResponseDTO::new)
                .toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaRequestDTO dto) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        pessoa.setNome(dto.nome());
        pessoa.setEmail(dto.email());
        pessoa.setTipo(dto.tipo());
        repository.save(pessoa);
        return ResponseEntity.ok(new PessoaResponseDTO(pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        pessoa.setAtivo(false);
        repository.save(pessoa);
        return ResponseEntity.noContent().build();
    }
}
