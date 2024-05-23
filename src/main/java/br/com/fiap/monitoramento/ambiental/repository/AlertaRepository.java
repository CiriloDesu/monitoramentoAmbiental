package br.com.fiap.monitoramento.ambiental.repository;

import br.com.fiap.monitoramento.ambiental.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    
}
