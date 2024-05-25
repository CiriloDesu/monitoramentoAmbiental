package br.com.fiap.monitoramento.ambiental.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;



    @Bean
    public SecurityFilterChain filtrarCadeiaDeSeguranca(
            HttpSecurity httpSecurity) throws Exception {


        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/alerta/*").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/alertas").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/alerta").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/alerta").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/alerta/*").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/qualidade").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/qualidade").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/qualidade/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/qualidades").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/qualidade/*").hasRole("USER")

                        .requestMatchers(HttpMethod.POST, "/api/irrigacao").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/irrigacao").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/irrigacao/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/irrigacoes").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/irrigacao/*").hasRole("USER")
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(
                        verificarToken,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

}
