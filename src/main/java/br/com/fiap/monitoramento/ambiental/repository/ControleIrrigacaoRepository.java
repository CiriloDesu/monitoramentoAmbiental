package br.com.fiap.monitoramento.ambiental.repository;

import br.com.fiap.monitoramento.ambiental.model.ControleIrrigacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ControleIrrigacaoRepository extends MongoRepository<ControleIrrigacao, String> {

    @Query("{ 'programadoPara' : { $gte: ?0, $lte: ?1 } }")
    List<ControleIrrigacao> findByProgramadoParaBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

}
