package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.EsempioDati;
//import com.univaq.TestAgile.repository.MySqlRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

//    @GetMapping("/db")
//    public String db(Model model) {
//        List<Pianta> datiEsempioDb = mySqlRepository.findAll();
//        model.addAttribute("testDatiEsempio", datiEsempioDb);
//        return "db";
//    }
}
