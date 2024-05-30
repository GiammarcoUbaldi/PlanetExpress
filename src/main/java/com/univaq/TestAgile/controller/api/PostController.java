package com.univaq.TestAgile.controller.api;


import com.univaq.TestAgile.model.Commento;
import com.univaq.TestAgile.model.OrtoReferente;
import com.univaq.TestAgile.model.Post;
import com.univaq.TestAgile.repository.CommentoRepository;
import com.univaq.TestAgile.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentoRepository commentoRepository;
    @PostMapping("/creaPost")
    public String createPost(@ModelAttribute Post post) {
        postRepository.save(post);
        return "redirect:/";
    }


    @PostMapping("/aggiungiCommento")
    public String aggiungiCommento(@RequestParam("postId") Long postId,
                                   @RequestParam("commentoPost") String commentoPost,
                                   Model model) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            Commento commento = new Commento();
            commento.setPost(post);
            commento.setDescrizione(commentoPost);

            //Dopo il merge di secure-root aggiungere lo username di chi scrive
            //commento.setUsername(); //Gli va passato Utente

            commentoRepository.save(commento);

            model.addAttribute("post", post);
            model.addAttribute("commenti", post.getCommenti());

            return "redirect:/" + postId; // Reindirizza alla pagina del post con i commenti aggiornati
        }
        return "redirect:/"; // Reindirizza alla homepage o a una pagina di errore se il post non esiste
    }


    @GetMapping("/view/{postId}")
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


    @Transactional
    @GetMapping("/rimuoviPost")
    public Boolean eliminaPost(@RequestParam Long postId) {
        try {
            postRepository.deleteById(postId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Transactional
    @GetMapping("/rimuoviCommento")
    public Boolean eliminaCommento(@RequestParam Long commentoId) {
        try {
            commentoRepository.deleteById(commentoId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/tutti")
    public List<Post> getTuttiPost() {
        return postRepository.findAll();
    }
    @GetMapping("/byTipo")
    public List<Post> getPostByTipo(@RequestParam("tipo") String tipo) {
        return postRepository.findByTipo(tipo);
    }




  /*  public List<Post> getPostsByCategory(@RequestParam(required = false) String category) {
        if (category == null || category.isEmpty()) {
            return PostRepository.findAll();
        } else {
            return postRepository.findByTipo(tipo);
        }
    } */

}
