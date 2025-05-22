package med.voll.api.repository;

import med.voll.api.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findByAtivoTrue();

}


