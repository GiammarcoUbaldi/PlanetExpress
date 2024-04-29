package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.Cliente;

import com.univaq.TestAgile.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("/")
    public String index() {
        return "/home/index";
    }

    @GetMapping("/salvaCliente")
    public String inserisciCliente() {
        return "/salvaCliente";
    }

    @GetMapping("/db")
    public String db(Model model) {
        List<Cliente> datiEsempioDb = clienteService.getTuttiClienti();
        model.addAttribute("testDatiEsempio", datiEsempioDb);
        return "db";
    }


}
