package br.com.fiap.monitoramento.ambiental.dto;

import br.com.fiap.monitoramento.ambiental.model.QualidadeDoAr;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record QualidadeDoArDTO(Long id,

                               @NotBlank(message = "Insira a localização, pode ser um bairro, municipio ou cep.")
                               String location,

                               Double pm25,
                               Double pm10,


                               LocalDateTime timestamp) {


    public QualidadeDoArDTO(QualidadeDoAr qualidadeDoAr){
        this(
                qualidadeDoAr.getId(),
                qualidadeDoAr.getLocation(),
                qualidadeDoAr.getPm25(),
                qualidadeDoAr.getPm10(),
                qualidadeDoAr.getTimestamp()
        );
    }
}
