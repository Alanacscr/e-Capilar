package unitins.br.tp1.model.Pagamento;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Boleto extends Pagamento {

    @Column()
    private String codigoBarras;

    @Column(nullable = false)
    private LocalDateTime dataVencimento;

    public Boleto() {
        setTipoPagamento("Boleto"); 
        this.dataVencimento = LocalDateTime.now().plusDays(7);
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
