package br.com.fiap.monitoramento.ambiental.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_alertas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Alerta {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ALERTA"
    )
    @SequenceGenerator(
            name = "SEQ_ALERTA",
            sequenceName = "SEQ_ALERTA",
            allocationSize = 1
    )
    @Column(name = "alerta_id")
    private Long id;
    private String tipo; // inundações, incêndios florestais e terremotos
    private String localizacao;
    private LocalDateTime timestamp;
}
