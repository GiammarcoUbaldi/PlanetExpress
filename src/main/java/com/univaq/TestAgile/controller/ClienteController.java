package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.Cliente;
import com.univaq.TestAgile.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getAllClienti() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> getClienteById(@PathVariable Long id) {
        return clienteRepository.findById(id);
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PostMapping("/nuovo")
    public String creaCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/db";
    }

//        @PutMapping("/{id}")
//        public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
//            cliente.setId(id);
//            return clienteRepository.save(cliente);
//        }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }
}
