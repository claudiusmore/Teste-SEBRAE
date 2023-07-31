package com.teste.sebrae.conta.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@Entity
@Table(name = "conta")
public class Conta extends RepresentationModel<Conta> implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

}