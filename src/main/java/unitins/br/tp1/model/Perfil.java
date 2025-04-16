package unitins.br.tp1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Perfil {
    ADMINISTRADOR(1, "Administrador"),
    CLIENTE(2, "Cliente");

    private final int ID;
    private final String NOME;

    Perfil(int id, String nome) {
        ID = id;
        NOME = nome;
    }

    public int getID() {
        return ID;
    }

    public String getNOME() {
        return NOME;
    }

    public static Perfil valueOf(Integer id) {
        if (id == null)
            return null;
        for (Perfil perfil : Perfil.values()) {
            if (perfil.getID() == id)
                return perfil;
        }
        return null;
    }

}
