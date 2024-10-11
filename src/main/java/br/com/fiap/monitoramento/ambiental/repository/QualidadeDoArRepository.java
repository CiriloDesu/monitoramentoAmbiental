package br.com.fiap.monitoramento.ambiental.repository;

import br.com.fiap.monitoramento.ambiental.model.QualidadeDoAr;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface QualidadeDoArRepository extends MongoRepository<QualidadeDoAr, String> {

    @Query("{ 'timestamp' : { $gte: ?0, $lte: ?1 } }")
    List<QualidadeDoAr> findByTimestampBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

}
