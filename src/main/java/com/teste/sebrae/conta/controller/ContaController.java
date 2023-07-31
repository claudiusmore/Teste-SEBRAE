package com.teste.sebrae.conta.controller;

import com.teste.sebrae.conta.model.Endereco;
import com.teste.sebrae.conta.model.request.ContaRequest;
import com.teste.sebrae.conta.model.response.ContaResponse;
import com.teste.sebrae.conta.service.ContaService;
import com.teste.sebrae.conta.service.EnderecoEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("conta")
public class ContaController {

    @Autowired
    EnderecoEndpoint endpoint;

    @Autowired
    ContaService contaService;

    @GetMapping("endereco")
    public Endereco getConta(){
        return endpoint.getEndereco();
    }

    // GET
    @GetMapping("/contas")
    public ResponseEntity<List<ContaResponse>> list() {
        var contas = contaService.findAll();

        contas.forEach(cnt -> cnt.add(linkTo(methodOn(ContaController.class).get(cnt.getId())).withSelfRel()));
        try {
            return new ResponseEntity<>(contas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //	 GET (id)
    @GetMapping("/{id}")
    public ResponseEntity<ContaResponse> get(@PathVariable Long id) {
        try {
            var conta = contaService.getConta(id);

            conta.add(linkTo(methodOn(ContaController.class).list()).withSelfRel());
            return new ResponseEntity<>(conta, HttpStatus.OK); // 200
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
    }

    // POST
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody ContaRequest conta) {
        contaService.saveConta(conta);
        return new ResponseEntity<>(HttpStatus.CREATED); // 201
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ContaRequest contaRequest) {
        try {
            var atual = contaService.getConta(id);
            contaRequest.setId(atual.getId());
            contaService.saveConta(contaRequest);
            return new ResponseEntity<>(HttpStatus.OK); // 200
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
    }

    //	 DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            ContaResponse contaResponse = contaService.getConta(id);
            contaService.deleteConta(contaResponse.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
    }
}
