package med.voll.api.lancamento;

import jakarta.persistence.*;
import med.voll.api.dto.LancamentoRequestDTO;
import med.voll.api.pessoa.Pessoa;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "lancamento")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private BigDecimal valor;

    private String tipo; // CREDITO ou DEBITO

    private LocalDate data;

    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Lancamento() {
        this.ativo = true;
    }

    public Lancamento(LancamentoRequestDTO dto, Pessoa pessoa) {
        this.descricao = dto.descricao();
        this.valor = dto.valor();
        this.tipo = dto.tipo();
        this.data = dto.data();
        this.pessoa = pessoa;
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
