package com.teste.sebrae.conta.model.request;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@Data
public class ContaRequest extends RepresentationModel {

    private Long id;
    private String nome;
    private String descricao;
}
