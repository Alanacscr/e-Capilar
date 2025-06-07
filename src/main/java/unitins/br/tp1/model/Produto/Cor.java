package unitins.br.tp1.model.Produto;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Cor {
    VERMELHO(1, "Vermelho"),
    AZUL(2, "Azul"),
    LARANJA(3, "Laranja"),
    LILAS(4, "Lilas"),
    PRETO(5, "Preto"),
    ROSA(6, "Rosa");

    private final int ID;
    private final String NOME;
    
    
    Cor(int id, String nome) {
        ID = id;
        NOME = nome;
    }


    public int getID() {
        return ID;
    }


    public String getNOME() {
        return NOME;
    }

    public static Cor valueOf(Integer id) {
        if (id == null)
            return null;
        for (Cor cor : Cor.values()) {
            if (cor.getID() == id)
                return cor;
        }
        return null;
     }

    
}