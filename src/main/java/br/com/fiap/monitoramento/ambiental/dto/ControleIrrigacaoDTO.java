package br.com.fiap.monitoramento.ambiental.dto;

import br.com.fiap.monitoramento.ambiental.model.ControleIrrigacao;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ControleIrrigacaoDTO(Long id,

                                   @NotBlank(message = "Insira o tamanho da área")
                                   String area,

                                   @NotBlank(message = "Insira se está ativo/inativo, em espera ou concluído")
                                   String status,

                                   @NotNull(message = "Inserir data e hora com minutos e segundos!")
                                   LocalDateTime programadoPara,

                                   @NotNull(message = "Duração da irrigação.")
                                   Integer duracaoEmMinutos,
                                   Double umidade,
                                   Double Temperatura
) {

    public ControleIrrigacaoDTO(ControleIrrigacao controleIrrigacao){
        this(
                controleIrrigacao.getId(),
                controleIrrigacao.getArea(),
                controleIrrigacao.getStatus(),
                controleIrrigacao.getProgramadoPara(),
                controleIrrigacao.getDuracaoEmMinutos(),
                controleIrrigacao.getUmidade(),
                controleIrrigacao.getTemperatura()
        );
    }


}
