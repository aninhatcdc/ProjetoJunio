package med.voll.api.repository;

import med.voll.api.lancamento.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    List<Lancamento> findByAtivoTrue();

}


