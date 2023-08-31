package com.pauta.votacao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

//Todo: adicionar as anotacoes @Id e @GenerateValue para dizer que nosso atributo vai ser um identificador unico e gerado automaticamente na hora da criacao.
//Todo: adicionar anotacao @Entity para dizer que a classe/entidade é uma tabela no banco de dados