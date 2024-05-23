package br.com.fiap.monitoramento.ambiental.dto;

import br.com.fiap.monitoramento.ambiental.model.Alerta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

public record AlertaDTO( Long id,

                        @NotBlank(message = "Insira o tipo de desastre: inundação, incêndio florestal ou terremoto.")
                         String tipo,

                        @NotBlank(message = "Localização não pode ser vazio!")
                         String localizacao,

                         LocalDateTime timestamp) {


    public AlertaDTO(Alerta alertaDTO){
        this(
                alertaDTO.getId(),
                alertaDTO.getTipo(),
                alertaDTO.getLocalizacao(),
                alertaDTO.getTimestamp()
        );
    }

}
