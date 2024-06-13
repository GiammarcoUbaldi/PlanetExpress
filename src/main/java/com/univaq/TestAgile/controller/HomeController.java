package com.univaq.TestAgile.controller;


import com.univaq.TestAgile.controller.api.EventoController;
import com.univaq.TestAgile.controller.api.PostController;
import com.univaq.TestAgile.controller.api.RiempiDbCotroller;
import com.univaq.TestAgile.controller.api.UtenteController;
import com.univaq.TestAgile.model.*;


import com.univaq.TestAgile.repository.CommentoRepository;
import com.univaq.TestAgile.repository.PostRepository;

import com.univaq.TestAgile.repository.UtenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
@Controller
public class HomeController {


    @Autowired
    EventoController eventoController;

    @Autowired
    UtenteController utenteController;

    @Autowired
    RiempiDbCotroller riempiDbCotroller;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    private CommentoRepository commentoRepository;

    @Autowired
    PostController postController;



    //Path della home
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("listaPost", postRepository.findAll());
        List<Evento> eventiRefe = eventoController.getEventiAccettati();
        List<Post> listPost = postController.getTuttiPost();
        model.addAttribute("ListaPost",listPost);
        model.addAttribute("EventiRefe", eventiRefe);
        List<Evento> eventiNormali = eventoController.getEventi33();
        model.addAttribute("EventiNormali", eventiNormali);
        return "/home/homePage";
    }

    //Autenticazione e Registrazione
    @GetMapping("/login")
    public String login() {
        return "/autenticazione/login";
    }

    @GetMapping("/registrazione")
    public String registrazioneForm(Utente utente) {
        return "/autenticazione/registrazione";
    }

    //Usato per lo sviluppo
    @GetMapping("/no-user/riempiDb")
    public String riempiDb() {
        riempiDbCotroller.inserisciDati();
        return "redirect:/";
    }

    //per la dashboard
    @GetMapping("/dashboard")
    public String mostraDashboard() {
        Utente utente = utenteController.getUtenteLoggato();
        if (utente != null) {
            switch (utente.getTipoUtente()) {
                case "USER":
                    return "redirect:/user/dashboard";
                case "ADMIN":
                    return "redirect:/admin/dashboard";
                case "REFERENTE":
                    return "redirect:/referente/dashboard";
            }
        }
        return "redirect:/";
    }


    //Evento Utente non Registrato



    @GetMapping("/no-user/mostraDettagliEvento/{id}")
    public String mostraDettagliEvento(Model model, @PathVariable long id) {
        Evento evento = eventoController.getEventoById(id);
        model.addAttribute("datiDettagli", evento);
        return "/home/dettagliEventiNoUser";
    }

    @GetMapping("/no-user/form-richiesta-orto")
    public String mostraFormRichiestaOrtoReferente(Model model) {
        return "/referente/formRichiestaOrtoReferete";
    }


    @GetMapping("/post/creaPost")
    public String creaPost() {
        return "/Post/ScriviPost";
    }
    @GetMapping("/no-user/forum")
    public String forum(Model model) {
        List<Post> listPost = postController.getTuttiPost();
        model.addAttribute("ListaPost",listPost);
        return "/Post/filtraggioPost";
    }
    @GetMapping("/no-user/forum/{postId}")
    public String visualizzaPost(@PathVariable Long postId, Model model) {
        // Trova il post per ID
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            // Ottieni tutti i commenti relativi al post
            List<Commento> commenti = commentoRepository.findByPost(post);
            // Aggiungi il post e i relativi commenti al modello
            model.addAttribute("post", post);
            model.addAttribute("commenti", commenti);
            // Ritorna il nome della vista per visualizzare il post e i commenti
            return "/Post/SingoloPost";
        } else {
            // Se il post non esiste, reindirizza alla homepage o a una pagina di errore
            return "redirect:/";
        }
    }


    // Aggiungi un nuovo endpoint per ottenere i post filtrati
    @GetMapping("/no-user/forum/filter")
    @ResponseBody
    public ResponseEntity<List<Post>> filterPosts(@RequestParam String categoria) {
        if(categoria.equals("tutti")) return ResponseEntity.ok(postController.getTuttiPost());
        List<Post> filteredPosts = postController.getPostByTipo(categoria);
        return new ResponseEntity<List<Post>>(filteredPosts, HttpStatus.OK);
    }

    @GetMapping("/no-user/forum/search")
    @ResponseBody
    public ResponseEntity<List<Post>> searchPosts(@RequestParam String query) {
        List<Post> searchedPosts = postController.searchPosts(query);
        return ResponseEntity.ok(searchedPosts);
    }

}

