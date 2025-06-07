package unitins.br.tp1.model.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import unitins.br.tp1.model.DefaultEntity;

@Entity
public class Telefone extends DefaultEntity {

    @Column(length = 3, nullable = false)
    private String codigoArea;
    @Column(length = 10, nullable = false)
    private String numero;

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
