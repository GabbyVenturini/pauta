package com.pauta.votacao.service;

import com.pauta.votacao.model.Voto;
import com.pauta.votacao.repository.VotoRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class VotoService {

    private VotoRepository votoRepository;

    public VotoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public Optional<Voto> buscarVoto(Long id) {
        return votoRepository.findById(id);
    }
}
