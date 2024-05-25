package br.com.fiap.monitoramento.ambiental.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @Email(message = "email inválido")
        @NotBlank(message = "email obrigatório")
        String email,
        @NotBlank(message = "senha obrigatória")
        @Size(min = 8, max = 20, message = "senha deve conter no minímo 8 e máximo 20 caracteres!")
        String senha
) {
}