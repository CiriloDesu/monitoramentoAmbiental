package br.com.fiap.monitoramento.ambiental.service;

import br.com.fiap.monitoramento.ambiental.dto.UsuarioCadastroDTO;
import br.com.fiap.monitoramento.ambiental.dto.UsuarioExibicaoDTO;
import br.com.fiap.monitoramento.ambiental.exception.NaoEncontradoException;
import br.com.fiap.monitoramento.ambiental.model.Usuario;
import br.com.fiap.monitoramento.ambiental.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDTO salvarUsuario(UsuarioCadastroDTO usuarioDTO) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDTO(usuarioSalvo);
    }

    public UsuarioExibicaoDTO buscarPorId(String id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDTO(usuarioOptional.get());
        } else {
            throw new NaoEncontradoException("Usuário não existe no banco de dados!");
        }
    }

    public List<UsuarioExibicaoDTO> listarTodos() {
        return usuarioRepository
                .findAll()
                .stream()
                .map(UsuarioExibicaoDTO::new)
                .toList();
    }

    public void excluir(String id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new NaoEncontradoException("Usuário não encontrado!");
        }
    }

    public UsuarioExibicaoDTO atualizar(UsuarioCadastroDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioDTO.usuarioId());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            BeanUtils.copyProperties(usuarioDTO, usuario, "senha");
            if (usuarioDTO.senha() != null) {
                usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioDTO.senha()));
            }
            return new UsuarioExibicaoDTO(usuarioRepository.save(usuario));
        } else {
            throw new NaoEncontradoException("Usuário não encontrado!");
        }
    }
}
