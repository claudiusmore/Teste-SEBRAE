package com.teste.sebrae.conta.model.response;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@Data
public class ContaResponse extends RepresentationModel {

    private Long id;
    private String nome;
    private String descricao;
}
