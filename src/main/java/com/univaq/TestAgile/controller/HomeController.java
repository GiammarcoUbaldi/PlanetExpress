package com.univaq.TestAgile.controller;

import com.mysql.cj.xdevapi.Client;
import com.univaq.TestAgile.model.Cliente;
import com.univaq.TestAgile.model.EsempioDati;
//import com.univaq.TestAgile.repository.MySqlRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.univaq.TestAgile.service.ClienteService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    //  @Autowired
    // MySqlRepository mySqlRepository;

    @GetMapping("/")
    public String index() {
        return "/home/index";
    }

    private static List<EsempioDati> datiEsempio;

    static {
        datiEsempio = new ArrayList<>();
        datiEsempio.add(new EsempioDati("Giovanni", "non lo so"));
        datiEsempio.add(new EsempioDati("Giammarco", "informatica"));
        datiEsempio.add(new EsempioDati("Marco", "mhhh"));
        datiEsempio.add(new EsempioDati("Piero", "agile"));
    }

    @GetMapping("/dynamic")
    public String dynamic(Model model) {
        model.addAttribute("testDatiEsempio", datiEsempio);
        return "dynamic";
    }

    @Autowired
    ClienteService clienteService;

    @GetMapping("/salvaCliente")
    public String inserisciCliente() {
        new Cliente("Giorgio","rossi");
    //    clienteService.inserisciCliente("Giovanni","Altieri");
        return "/home/index";
    }

    @GetMapping("/db")
    public String db(Model model) {
        //List<Cliente> datiEsempioDb = clienteService.getTuttiClienti();
        List<Cliente> datiEsempioDb = new ArrayList<>();
        System.out.print("Dati tutti i clienti");
        System.out.print(clienteService.getTuttiClienti());
        model.addAttribute("testDatiEsempio", datiEsempioDb);
        return "db";
    }

//
//    @Autowired
//    private EntityManager entityManager;
//
//    @PostConstruct
//    public void inizializzaDati() {
//        // Crea oggetti Cliente predefiniti
//        Cliente cliente1 = new Cliente("Giovanni", "Rossi");
//        Cliente cliente2 = new Cliente("Maria", "Bianchi");
//
//        // Salva oggetti Cliente nel database
//        entityManager.persist(cliente1);
//        entityManager.persist(cliente2);
//    }


}
