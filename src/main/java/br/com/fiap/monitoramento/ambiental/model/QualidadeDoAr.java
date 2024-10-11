package br.com.fiap.monitoramento.ambiental.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "qualidade_do_ar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class QualidadeDoAr {

    @Id
    private String id;  // Use String for the ID in MongoDB
    private String location;
    private Double pm25;
    private Double pm10;
    private LocalDateTime timestamp;
}
