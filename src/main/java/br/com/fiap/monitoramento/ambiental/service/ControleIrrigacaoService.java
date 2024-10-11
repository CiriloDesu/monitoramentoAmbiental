package br.com.fiap.monitoramento.ambiental.service;

import br.com.fiap.monitoramento.ambiental.dto.ControleIrrigacaoDTO;
import br.com.fiap.monitoramento.ambiental.exception.NaoEncontradoException;
import br.com.fiap.monitoramento.ambiental.model.ControleIrrigacao;
import br.com.fiap.monitoramento.ambiental.repository.ControleIrrigacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ControleIrrigacaoService {

    @Autowired
    private ControleIrrigacaoRepository controleIrrigacaoRepository;

    public List<ControleIrrigacaoDTO> listarTodos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return controleIrrigacaoRepository
                .findAll(pageable)
                .map(ControleIrrigacaoDTO::new)
                .toList();
    }

    public ControleIrrigacaoDTO criarIrrigacao(ControleIrrigacaoDTO controleIrrigacaoDTO) {
        ControleIrrigacao controleIrrigacao = new ControleIrrigacao();
        BeanUtils.copyProperties(controleIrrigacaoDTO, controleIrrigacao);
        return new ControleIrrigacaoDTO(controleIrrigacaoRepository.save(controleIrrigacao));
    }

    public ControleIrrigacaoDTO buscarPorId(String id) {
        Optional<ControleIrrigacao> controleIrrigacaoOptional = controleIrrigacaoRepository.findById(id);
        if (controleIrrigacaoOptional.isPresent()) {
            return new ControleIrrigacaoDTO(controleIrrigacaoOptional.get());
        } else {
            throw new NaoEncontradoException("Irrigação não existe!");
        }
    }

    public void excluir(String id) {
        controleIrrigacaoRepository.deleteById(id);
    }

    public ControleIrrigacaoDTO atualizar(ControleIrrigacaoDTO controleIrrigacaoDTO) {
        ControleIrrigacao controleIrrigacao = new ControleIrrigacao();
        BeanUtils.copyProperties(controleIrrigacaoDTO, controleIrrigacao);
        return new ControleIrrigacaoDTO(controleIrrigacaoRepository.save(controleIrrigacao));
    }

    public List<ControleIrrigacaoDTO> listarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        LocalDateTime inicio = dataInicial.atStartOfDay();
        LocalDateTime fim = dataFinal.atTime(LocalTime.MAX);

        return controleIrrigacaoRepository
                .findByProgramadoParaBetween(inicio, fim)
                .stream()
                .map(ControleIrrigacaoDTO::new)
                .toList();
    }
}
