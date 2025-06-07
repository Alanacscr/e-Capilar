package unitins.br.tp1.dto.Usuario;

import unitins.br.tp1.model.Usuario.Telefone;

public record TelefoneResponseDTO(
        Long id,
        String codigoArea,
        String numero) {

    public static TelefoneResponseDTO valueOf(Telefone telefone) {
        if (telefone == null)
            return null;
        return new TelefoneResponseDTO(telefone.getId(), telefone.getCodigoArea(),
                telefone.getNumero());
    }
}
