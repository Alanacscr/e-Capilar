package unitins.br.tp1.model.Pagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Pix extends Pagamento {

    @Column()
    private String chavePix; 

    public Pix() {
        setTipoPagamento("Pix"); 
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }
}

