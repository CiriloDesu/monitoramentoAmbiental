package br.com.fiap.monitoramento.ambiental.repository;

import br.com.fiap.monitoramento.ambiental.model.ControleIrrigacao;
import br.com.fiap.monitoramento.ambiental.model.QualidadeDoAr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface QualidadeDoArRepository extends JpaRepository<QualidadeDoAr, Long> {

    @Query("SELECT c FROM QualidadeDoAr c WHERE c.timestamp BETWEEN :dataInicial AND :dataFinal")
    List<QualidadeDoAr> listarPorPeriodo(
            @Param("dataInicial") LocalDateTime dataInicial,
            @Param("dataFinal") LocalDateTime dataFinal
    );
}
