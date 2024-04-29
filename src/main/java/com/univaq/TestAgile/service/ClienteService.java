package com.univaq.TestAgile.service;

import com.univaq.TestAgile.model.Cliente;
import com.univaq.TestAgile.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public void inserisciCliente(String nome, String cognome) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCognome(cognome);
        clienteRepository.save(cliente);
    }

    public List<Cliente> getTuttiClienti() {
        return (List<Cliente>) clienteRepository.findAll();
    }

}
