package br.com.fiap.monitoramento.ambiental.repository;

import br.com.fiap.monitoramento.ambiental.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    UserDetails findByEmail(String email);
}
