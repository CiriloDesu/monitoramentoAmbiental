package br.com.fiap.monitoramento.ambiental.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "historico_alertas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HistoricoDeAlertas {
    @Id
    private String alertaId;
    private String tipo;
    private String localizacao;
    private LocalDateTime timestamp;
    private String alteracao;
}
