package unitins.br.tp1.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import unitins.br.tp1.model.Produto.Cor;

@Converter(autoApply = true)
public class CorConverter implements AttributeConverter<Cor, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Cor cor) {
        return cor == null ? null : cor.getID();

    }

    @Override
    public Cor convertToEntityAttribute(Integer id) {
        return Cor.valueOf(id);
    }

}
