package br.com.fiap.monitoramento.ambiental.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

import java.time.LocalDateTime;

@Document(collection = "controle_de_irrigacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ControleIrrigacao {
    @Id
    private String id; // MongoDB geralmente usa String para o ID, mas você pode usar ObjectId se preferir.

    private String area;
    private String status;
    private LocalDateTime programadoPara;
    private Integer duracaoEmMinutos;
    private Double temperatura;
    private Double umidade;
}
