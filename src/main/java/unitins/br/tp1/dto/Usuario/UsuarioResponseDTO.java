package unitins.br.tp1.dto.Usuario;

import unitins.br.tp1.dto.Endereco.EnderecoResponseDTO;
import unitins.br.tp1.model.Usuario.Perfil;
import unitins.br.tp1.model.Usuario.Usuario;

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
