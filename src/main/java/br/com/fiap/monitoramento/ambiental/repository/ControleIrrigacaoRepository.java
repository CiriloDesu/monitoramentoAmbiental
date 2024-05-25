package br.com.fiap.monitoramento.ambiental.repository;

import br.com.fiap.monitoramento.ambiental.dto.ControleIrrigacaoDTO;
import br.com.fiap.monitoramento.ambiental.model.Alerta;
import br.com.fiap.monitoramento.ambiental.model.ControleIrrigacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ControleIrrigacaoRepository extends JpaRepository<ControleIrrigacao, Long> {

    @Query("SELECT c FROM ControleIrrigacao c WHERE c.programadoPara BETWEEN :dataInicial AND :dataFinal")
    List<ControleIrrigacao> listarPorPeriodo(
            @Param("dataInicial") LocalDateTime dataInicial,
            @Param("dataFinal") LocalDateTime dataFinal
    );
}
