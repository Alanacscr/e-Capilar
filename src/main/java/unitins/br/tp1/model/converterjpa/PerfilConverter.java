package unitins.br.tp1.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import unitins.br.tp1.model.Usuario.Perfil;

@Converter(autoApply = true)
public class PerfilConverter implements AttributeConverter<Perfil, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Perfil perfil) {
        return perfil == null ? null : perfil.getID();

    }

    @Override
    public Perfil convertToEntityAttribute(Integer id) {
        return Perfil.valueOf(id);
    }

}
