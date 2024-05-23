package br.com.fiap.monitoramento.ambiental.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_qualidade_ar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class QualidadeDoAr {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_QUALIDADE_AR"
    )
    @SequenceGenerator(
            name = "SEQ_QUALIDADE_AR",
            sequenceName = "SEQ_QUALIDADE_AR",
            allocationSize = 1
    )
    @Column(name = "qualidade_ar_id")
    private Long id;

    private String location;

    private Double pm25;

    private Double pm10;

    private LocalDateTime timestamp;
}
