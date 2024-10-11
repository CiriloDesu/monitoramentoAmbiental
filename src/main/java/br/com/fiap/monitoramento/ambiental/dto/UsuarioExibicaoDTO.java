package br.com.fiap.monitoramento.ambiental.dto;

import br.com.fiap.monitoramento.ambiental.model.Usuario;
import br.com.fiap.monitoramento.ambiental.model.UsuarioRole;

public record UsuarioExibicaoDTO(
        String usuarioId,
        String nome,
        String email,
        UsuarioRole role
) {
    public UsuarioExibicaoDTO (Usuario usuario){
        this(
                usuario.getUsuarioId(),
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getRole());
    }
}