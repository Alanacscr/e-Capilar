package unitins.br.tp1.model.Endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import unitins.br.tp1.model.DefaultEntity;

@Entity
public class Municipio extends DefaultEntity {

    @Column(length = 100, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
