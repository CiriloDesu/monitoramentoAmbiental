package br.com.fiap.monitoramento.ambiental.service;

import br.com.fiap.monitoramento.ambiental.dto.QualidadeDoArDTO;
import br.com.fiap.monitoramento.ambiental.exception.NaoEncontradoException;
import br.com.fiap.monitoramento.ambiental.model.QualidadeDoAr;
import br.com.fiap.monitoramento.ambiental.repository.QualidadeDoArRepository;
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
public class QualidadeDoArService {

    @Autowired
    private QualidadeDoArRepository qualidadeDoArRepository;

    public List<QualidadeDoArDTO> listarTodos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return qualidadeDoArRepository
                .findAll(pageable)
                .map(QualidadeDoArDTO::new)
                .toList();
    }

    public QualidadeDoArDTO criarNovo(QualidadeDoArDTO qualidadeDoArDTO) {
        QualidadeDoAr qualidadeDoAr = new QualidadeDoAr();
        BeanUtils.copyProperties(qualidadeDoArDTO, qualidadeDoAr);
        return new QualidadeDoArDTO(qualidadeDoArRepository.save(qualidadeDoAr));
    }

    public QualidadeDoArDTO buscarPorId(String id) {
        Optional<QualidadeDoAr> qualidadeDoArOptional = qualidadeDoArRepository.findById(id);
        if (qualidadeDoArOptional.isPresent()) {
            return new QualidadeDoArDTO(qualidadeDoArOptional.get());
        } else {
            throw new NaoEncontradoException("Qualidade do ar não existe!");
        }
    }

    public void excluir(String id) {
        qualidadeDoArRepository.deleteById(id);
    }

    public QualidadeDoArDTO atualizar(QualidadeDoArDTO qualidadeDoArDTO) {
        QualidadeDoAr qualidadeDoAr = new QualidadeDoAr();
        BeanUtils.copyProperties(qualidadeDoArDTO, qualidadeDoAr);
        return new QualidadeDoArDTO(qualidadeDoArRepository.save(qualidadeDoAr));
    }

    public List<QualidadeDoArDTO> listarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        LocalDateTime inicio = dataInicial.atStartOfDay();
        LocalDateTime fim = dataFinal.atTime(LocalTime.MAX);

        return qualidadeDoArRepository
                .findByTimestampBetween(inicio, fim)
                .stream()
                .map(QualidadeDoArDTO::new)
                .toList();
    }
}
