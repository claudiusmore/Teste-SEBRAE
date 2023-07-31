package com.teste.sebrae.conta.service;


import com.teste.sebrae.conta.model.Conta;
import com.teste.sebrae.conta.model.request.ContaRequest;
import com.teste.sebrae.conta.model.response.ContaResponse;
import com.teste.sebrae.conta.repository.ContaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ContaService {


    @Autowired
    ContaRepository contaRepository;

    public List<ContaResponse> findAll(){
        return contaRepository.findAll().stream()
                .map(this::getResponse)
                .collect(Collectors.toList());
    }

    public ContaResponse getConta(Long id) {
        var contaOpt = contaRepository.findById(id);

        if(contaOpt.isPresent()){
            return getResponse(contaOpt.get());
        }
        return null;
    }

    public void saveConta(ContaRequest contaRequest) {
        Conta conta = getConta(contaRequest);
        contaRepository.save(conta);
    }

    public void deleteConta(Long idConta) {
        contaRepository.deleteById(idConta);
    }

    private Conta getConta(ContaRequest source){
        var target = new Conta();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    private ContaResponse getResponse(Conta source){
        var target = new ContaResponse();
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
