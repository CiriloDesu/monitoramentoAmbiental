package br.com.fiap.monitoramento.ambiental.dto;

import br.com.fiap.monitoramento.ambiental.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDTO(
        String usuarioId,

        @NotBlank(message = "nome do usuário obrigatório!")
        String nome,

        @NotBlank(message = "email obrigatório!")
        @Email
        String email,

        @NotBlank(message = "senha obrigatória")
        @Size(min = 6, max = 20, message = "senha deve conter entre 6 e 20 caracteres")
        String senha,
        UsuarioRole role
) {
}
