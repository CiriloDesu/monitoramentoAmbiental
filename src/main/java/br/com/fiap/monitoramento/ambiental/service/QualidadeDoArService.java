package br.com.fiap.monitoramento.ambiental.service;

import br.com.fiap.monitoramento.ambiental.dto.ControleIrrigacaoDTO;
import br.com.fiap.monitoramento.ambiental.dto.QualidadeDoArDTO;
import br.com.fiap.monitoramento.ambiental.exception.NaoEncontradoException;
import br.com.fiap.monitoramento.ambiental.model.ControleIrrigacao;
import br.com.fiap.monitoramento.ambiental.model.QualidadeDoAr;
import br.com.fiap.monitoramento.ambiental.repository.QualidadeDoArRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class QualidadeDoArService {

    @Autowired
    private QualidadeDoArRepository qualidadeDoArRepository;

    public Page<QualidadeDoArDTO> listarTodos(Pageable paginacao) {
        return qualidadeDoArRepository.findAll(paginacao)
                .map(QualidadeDoArDTO::new);
    }

    public QualidadeDoArDTO criarNovo(QualidadeDoArDTO qualidadeDoArDTO){
        QualidadeDoAr qualidadeDoAr = new QualidadeDoAr();
        BeanUtils.copyProperties(qualidadeDoArDTO, qualidadeDoAr);
        return new QualidadeDoArDTO(qualidadeDoArRepository.save(qualidadeDoAr));
    }


    public QualidadeDoArDTO buscarPorId(Long id) {
        Optional<QualidadeDoAr> qualidadeDoArOptional = qualidadeDoArRepository.findById(id);
        if (qualidadeDoArOptional.isPresent()) {
            return new QualidadeDoArDTO(qualidadeDoArOptional.get());
        } else {
            throw new NaoEncontradoException("Irrigação não existe!");
        }
    }

    public void excluir(Long id) {
        qualidadeDoArRepository.delete(qualidadeDoArRepository.findById(id).get());
    }

    public QualidadeDoAr atualizar(QualidadeDoAr qualidadeDoAr) {
        return qualidadeDoArRepository.save(qualidadeDoAr);
    }

    public List<QualidadeDoArDTO> listarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        return qualidadeDoArRepository
                .listarPorPeriodo(dataInicial.atStartOfDay(), dataFinal.atTime(LocalTime.MAX))
                .stream()
                .map(QualidadeDoArDTO::new)
                .toList();
    }
}
