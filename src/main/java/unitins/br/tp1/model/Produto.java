package unitins.br.tp1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Produto extends DefaultEntity {

    @Column(length = 60, nullable = false)
    private String nome;
    @Column(length = 100, nullable = false)
    private String descricao;
    @Column(nullable = false)
    private double preco;
    @Column(columnDefinition = "INT CHECK (quantidadeEstoque >= 0)")
    private Integer quantidadeEstoque;

    private Cor cor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

}
