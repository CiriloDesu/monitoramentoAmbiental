package br.com.fiap.monitoramento.ambiental.repository;

import br.com.fiap.monitoramento.ambiental.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    @Query("SELECT c FROM Alerta c WHERE c.timestamp BETWEEN :dataInicial AND :dataFinal")
    List<Alerta> listarAlertasPorPeriodo(
            @Param("dataInicial")LocalDateTime dataInicial,
            @Param("dataFinal") LocalDateTime dataFinal
            );
}
