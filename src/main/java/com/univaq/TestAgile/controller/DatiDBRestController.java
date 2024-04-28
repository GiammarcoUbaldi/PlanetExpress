package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.Cliente;
import com.univaq.TestAgile.repository.ClienteRepository;
import com.univaq.TestAgile.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class DatiDBRestController {
   // @Autowired
   // private ClienteRepository clienteRepository;


    @GetMapping("/DatiDb")
    public void riempiDataBase() {
        ClienteService clienteRestController = new ClienteService();
        clienteRestController.inserisciCliente("Stefano","Rossi");
        clienteRestController.inserisciCliente("Giovanni","Bianchi");
        clienteRestController.inserisciCliente("Giammarco","Ubaldi");
    }
}
