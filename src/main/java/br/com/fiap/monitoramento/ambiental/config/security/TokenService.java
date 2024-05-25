package br.com.fiap.monitoramento.ambiental.config.security;

import br.com.fiap.monitoramento.ambiental.model.Usuario;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${chave.secreta}")
    private String chaveSecreta;

    public String gerarToken(Usuario usuario){                      // Método que gera um token JWT para um usuário
        try {
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta); // Cria o algoritmo de criptografia HMAC256 com a chave secreta
            String token = JWT
                    .create()                                       // Inicializa a criação do token JWT
                    .withIssuer("ambiente")                              // Define o emissor do token como "ambiente"
                    .withSubject(usuario.getEmail())                    // Define o assunto do token como o e-mail do usuário
                    .withExpiresAt(gerarDataDeExpiracao())                   // Define a data de expiração do token
                    .sign(algorithm);                                   // Assina o token com o algoritmo especificado
            return token;                                               // Retorna o token gerado
        } catch (JWTCreationException erro) {                           // Captura exceções específicas de criação de JWT
            throw new RuntimeException("não foi possível gerar o token!"); // Lança uma exceção runtime com uma mensagem de erro
        }
    }


    public String validarToken(String token){                     // Método que valida um token JWT e retorna o assunto (subject) do token
        try {
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta); // Cria o algoritmo de criptografia HMAC256 com a chave secreta
            return JWT.require(algorithm)                           // Inicializa o processo de verificação do token com o algoritmo especificado
                    .withIssuer("ambiente")                         // Especifica que o emissor esperado do token é "ambiente"
                    .build()                                         // Constrói o verificador de token JWT
                    .verify(token)                                      // Verifica o token passado como argumento
                    .getSubject();                                      // Retorna o assunto (subject) do token se a verificação for bem-sucedida
        } catch (JWTVerificationException erro) {                   // Captura exceções específicas de verificação de JWT
            return "";                                              // Retorna uma string vazia se a verificação do token falhar
        }
    }



    public Instant gerarDataDeExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
