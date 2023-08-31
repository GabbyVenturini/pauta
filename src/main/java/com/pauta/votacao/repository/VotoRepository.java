package com.pauta.votacao.repository;

import com.pauta.votacao.model.Usuario;
import com.pauta.votacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
    @Repository
    public interface VotoRepository extends JpaRepository<Voto, Long> {
    }

