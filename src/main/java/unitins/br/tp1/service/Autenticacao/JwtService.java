package unitins.br.tp1.service.Autenticacao;

public interface JwtService {

    String generateJwt(String email, String perfil);
    
}
