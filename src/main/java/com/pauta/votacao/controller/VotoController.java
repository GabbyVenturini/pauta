package com.pauta.votacao.controller;


import com.pauta.votacao.model.Usuario;
import com.pauta.votacao.model.Voto;
import com.pauta.votacao.service.VotoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/voto")
public class VotoController {

    private VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @GetMapping("/{id}")
    public Optional<Voto> buscarVoto(@PathVariable Long voto){
        return votoService.buscarVoto(voto);

    }
}
