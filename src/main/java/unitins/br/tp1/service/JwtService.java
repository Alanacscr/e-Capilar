package unitins.br.tp1.service;

public interface JwtService {

    String generateJwt(String email, String perfil);
    
}
