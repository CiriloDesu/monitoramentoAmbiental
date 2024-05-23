package br.com.fiap.monitoramento.ambiental.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_controle_irrigacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ControleIrrigacao {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_IRRIGACAO"
    )
    @SequenceGenerator(
            name = "SEQ_IRRIGACAO",
            sequenceName = "SEQ_IRRIGACAO",
            allocationSize = 1
    )
    @Column(name = "irrigacao_id")
    private Long id;
    private String area;
    private String status;
    private LocalDateTime programadoPara;
    private Integer duracaoEmMinutos;
    private Double temperatura;
    private Double umidade;
}
