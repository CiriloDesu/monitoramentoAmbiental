package br.com.fiap.monitoramento.ambiental.repository;

import br.com.fiap.monitoramento.ambiental.model.HistoricoDeAlertas;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoricoDeAlertasRepository extends MongoRepository<HistoricoDeAlertas, String> {
}
