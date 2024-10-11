package br.com.fiap.monitoramento.ambiental.repository;

import br.com.fiap.monitoramento.ambiental.model.Alerta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AlertaRepository extends MongoRepository<Alerta, String> {

    @Query("{ 'timestamp' : { $gte: ?0, $lte: ?1 } }")
    List<Alerta> findByTimestampBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

}
