package com.schedule.vote.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

//Todo: adicionar as anotacoes @Id e @GenerateValue para dizer que nosso atributo vai ser um identificador unico e gerado automaticamente na hora da criacao.
//Todo: adicionar anotacao @Entity para dizer que a classe/entidade é uma tabela no banco de dados