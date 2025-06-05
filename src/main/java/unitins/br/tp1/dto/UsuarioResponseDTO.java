package unitins.br.tp1.dto;

import unitins.br.tp1.model.Perfil;
import unitins.br.tp1.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String senha,
        Perfil perfil,
        TelefoneResponseDTO telefone,
        EnderecoResponseDTO endereco) {

    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        if (usuario == null)
            return null;
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getPerfil(),
                TelefoneResponseDTO.valueOf(usuario.getTelefone()),
                EnderecoResponseDTO.valueOf(usuario.getEndereco()));
    }
}
