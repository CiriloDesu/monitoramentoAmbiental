package br.com.fiap.monitoramento.ambiental.service;

import br.com.fiap.monitoramento.ambiental.dto.ControleIrrigacaoDTO;
import br.com.fiap.monitoramento.ambiental.model.ControleIrrigacao;
import br.com.fiap.monitoramento.ambiental.repository.ControleIrrigacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControleIrrigacaoService {

    @Autowired
    private ControleIrrigacaoRepository controleIrrigacaoRepository;

    public Page<ControleIrrigacaoDTO> listarTodos(Pageable paginacao){
        return controleIrrigacaoRepository.findAll(paginacao)
                .map(ControleIrrigacaoDTO::new);
    }

    public ControleIrrigacao criarNovo(ControleIrrigacao controleIrrigacao){
        return controleIrrigacaoRepository.save(controleIrrigacao);
    }
}
