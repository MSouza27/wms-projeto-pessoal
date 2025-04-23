package br.com.logistica.wms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "recebimento")
@Getter
@Setter
public class Recebimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Atributos padrões do produto
    private String nomeProduto;
    private String loteProduto;
    private BigDecimal codigoProduto;
    private BigDecimal quantidadeProdutoUnitarioSistema;
    private BigDecimal quantidadeProdutoUnitarioConferido;
    private BigDecimal quantidadeCaixaSistema;
    private BigDecimal quantidadeCaixaConferido;

    //Atributos para resposta
    private String mensagemConferenciaUnitario;
    private BigDecimal retornoConferenciaUnitario;
    private String mensagemConferenciaCaixa;
    private BigDecimal retornoConferenciaCaixa;
    private String mensagemLiberacaoRecebimento;

    //Datas importantes do Produto
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataValidade;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private LocalDateTime entradaRecebimento;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column(updatable = true)
    private LocalDateTime dataUltimaMovimentacao;


    //Anotações para incluir os métodos criados
    @PrePersist
    @PreUpdate
    public void entradaProduto() {
        this.entradaRecebimento = LocalDateTime.now();
        this.dataValidade = LocalDateTime.now().plusYears(3);
        this.dataUltimaMovimentacao = LocalDateTime.now();
        mensagemConferencia();
        calcularDivergenciaUnitario();
        mensagemConferenciaCaixa();
        calcularDivergenciaCaixa();
        statusProdutoRecebimento();
    }

    // Métodos da logica do sistema de Recebimento
    public void mensagemConferencia() {
        if (!Objects.equals(quantidadeProdutoUnitarioSistema, quantidadeProdutoUnitarioConferido)) {
            this.mensagemConferenciaUnitario = "Produto está com divergência";
        } else {
            this.mensagemConferenciaUnitario = "Produto está sem divergência";
        }
    }

    public void mensagemConferenciaCaixa() {
        if (!Objects.equals(quantidadeCaixaSistema, quantidadeCaixaConferido)) {
            this.mensagemConferenciaCaixa = "Volume recebido com divergência";
        } else {
            this.mensagemConferenciaCaixa = "Volume está sem divergência";
        }
    }

    public void calcularDivergenciaUnitario() {
        if (quantidadeProdutoUnitarioSistema.compareTo(quantidadeProdutoUnitarioConferido) != 0) {
            this.retornoConferenciaUnitario = quantidadeProdutoUnitarioSistema.subtract(quantidadeProdutoUnitarioConferido);
        } else {
            this.retornoConferenciaUnitario = BigDecimal.ZERO;
        }
    }

    public void calcularDivergenciaCaixa(){
        if (quantidadeCaixaSistema.compareTo(quantidadeCaixaConferido) != 0){
            this.retornoConferenciaCaixa = quantidadeCaixaSistema.subtract(quantidadeCaixaConferido);
        }else {
            this.retornoConferenciaCaixa = BigDecimal.ZERO;
        }
    }

    public void statusProdutoRecebimento(){
        if (quantidadeProdutoUnitarioSistema.compareTo(quantidadeProdutoUnitarioConferido) == 0){
            this.mensagemLiberacaoRecebimento = "Produto Liberado para Estoque";
        }else {
            this.mensagemLiberacaoRecebimento = "Produto com divergência, realizar auditoria";
        }
    }

}