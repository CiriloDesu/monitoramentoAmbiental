package br.com.fiap.monitoramento.ambiental.service;

import br.com.fiap.monitoramento.ambiental.dto.ControleIrrigacaoDTO;
import br.com.fiap.monitoramento.ambiental.exception.NaoEncontradoException;
import br.com.fiap.monitoramento.ambiental.model.ControleIrrigacao;
import br.com.fiap.monitoramento.ambiental.repository.ControleIrrigacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

        public Page<ControleIrrigacaoDTO> listarTodos(Pageable pageable){

            return controleIrrigacaoRepository
                    .findAll(pageable)
                    .map(ControleIrrigacaoDTO::new);
        }

        public ControleIrrigacaoDTO criarIrrigacao(ControleIrrigacaoDTO controleIrrigacaoDTO){
            ControleIrrigacao controleIrrigacao = new ControleIrrigacao();
            BeanUtils.copyProperties(controleIrrigacaoDTO, controleIrrigacao);
            return new ControleIrrigacaoDTO(controleIrrigacaoRepository.save(controleIrrigacao));
        }

    public ControleIrrigacaoDTO buscarPorId(Long id){
        Optional<ControleIrrigacao> controleIrrigacaoOptional = controleIrrigacaoRepository.findById(id);
        if (controleIrrigacaoOptional.isPresent()){
            return new ControleIrrigacaoDTO(controleIrrigacaoOptional.get());
        } else {
            throw new NaoEncontradoException("Irrigação não existe!");        }
    }

        public void excluir(Long id){
            controleIrrigacaoRepository.delete(controleIrrigacaoRepository.findById(id).get());
        }

        public ControleIrrigacao atualizar(ControleIrrigacao controleIrrigacao){
            return controleIrrigacaoRepository.save(controleIrrigacao);
        }

    public List<ControleIrrigacaoDTO> listarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal){
        return controleIrrigacaoRepository
                .listarPorPeriodo(dataInicial.atStartOfDay(), dataFinal.atTime(LocalTime.MAX))
                .stream()
                .map(ControleIrrigacaoDTO::new)
                .toList();
    }
    }

