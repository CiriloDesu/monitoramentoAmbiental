package br.com.fiap.monitoramento.ambiental.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "alerta")  // Especifica a coleção no MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Alerta {

    @Id  // Usar o identificador padrão do MongoDB
    private String id;  // Pode ser String ou ObjectId (tipo específico do MongoDB)

    private String tipo; // inundações, incêndios florestais e terremotos
    private String localizacao;
    private LocalDateTime timestamp;
}
