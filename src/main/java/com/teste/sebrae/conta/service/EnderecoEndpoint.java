package com.teste.sebrae.conta.service;

import com.teste.sebrae.conta.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "endereco", url = "http://viacep.com.br/ws/01001000/json/")
public interface EnderecoEndpoint {

    @RequestMapping(method = RequestMethod.GET, value = "")
    Endereco getEndereco();

}
