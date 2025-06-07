package unitins.br.tp1.service.Autenticacao;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(String email, String perfil) {

        // data
        Instant expiryDate = Instant.now().plus(EXPIRATION_TIME);

        // papeis (perfil)
        Set<String> roles = new HashSet<String>();
        roles.add(perfil);

        // gerando o token
        return Jwt.issuer("unitins-jwt")
                .subject(email)
                .groups(roles)
                .expiresAt(expiryDate)
                .sign();

    }
    
}
