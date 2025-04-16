package unitins.br.tp1.dto;

import unitins.br.tp1.model.Telefone;

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
