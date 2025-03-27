package unitins.br.tp1.model;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;
    
    @Entity
    public class Categoria extends DefaultEntity {
    
        @Column(length = 100)
        private String nome;
    
        @ManyToOne
        @JoinColumn(name = "id_produto")
        private Produto produto;
    
        public String getNome() {
            return nome;
        }
    
        public void setNome(String nome) {
            this.nome = nome;
        }
    
        public Produto getProduto() {
            return produto;
        }
    
        public void setProduto(Produto produto) {
            this.produto = produto;
        }
    
    }

