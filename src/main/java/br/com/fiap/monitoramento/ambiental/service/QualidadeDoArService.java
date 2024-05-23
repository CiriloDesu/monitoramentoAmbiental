package br.com.fiap.monitoramento.ambiental.service;

import br.com.fiap.monitoramento.ambiental.dto.QualidadeDoArDTO;
import br.com.fiap.monitoramento.ambiental.model.QualidadeDoAr;
import br.com.fiap.monitoramento.ambiental.repository.QualidadeDoArRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualidadeDoArService {

    @Autowired
    private QualidadeDoArRepository qualidadeDoArRepository;

    public Page<QualidadeDoArDTO> listarTodos(Pageable paginacao){
        return qualidadeDoArRepository.findAll(paginacao)
                .map(QualidadeDoArDTO::new);
    }

    public QualidadeDoAr criarNovo(QualidadeDoAr qualidadeDoAr){
        return qualidadeDoArRepository.save(qualidadeDoAr);
    }
}
