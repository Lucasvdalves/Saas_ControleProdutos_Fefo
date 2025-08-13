package br.com.controleProdutosFefo.Api.dto;

import java.time.LocalDate;

public class ProdutoRequestDTO {

    private String nomeProduto;
    private String lote;
    private Double quantidade;
    private LocalDate dataValidade;
    private LocalDate dataCompra;

    public ProdutoRequestDTO(String nomeProduto, String lote, Double quantidade, LocalDate dataValidade, LocalDate dataCompra) {
        this.nomeProduto = nomeProduto;
        this.lote = lote;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
        this.dataCompra = dataCompra;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }
}

