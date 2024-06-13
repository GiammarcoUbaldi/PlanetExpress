package com.univaq.Agile.controller.api;


import com.univaq.Agile.model.Commento;
import com.univaq.Agile.model.Post;
import com.univaq.Agile.model.Utente;
import com.univaq.Agile.repository.CommentoRepository;
import com.univaq.Agile.repository.PostRepository;
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

    @Autowired
    UtenteController utenteController;

    @PostMapping("/creaPost")
    public String createPost(@ModelAttribute Post post) {
        postRepository.save(post);
        return "redirect:/no-user/forum";
    }


    @PostMapping("/aggiungiCommento")
    public String aggiungiCommento(@RequestParam("postId") Long postId,
                                   @RequestParam("commentoPost") String commentoPost,
                                   Model model) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        Utente u = utenteController.getUtenteLoggato();
        if (optionalPost.isPresent() && u != null) {
            Post post = optionalPost.get();
            Commento commento = new Commento();
            commento.setPost(post);
            commento.setDescrizione(commentoPost);

            commento.setUsername(u.getNome() + " " + u.getCognome());
            commento.setUtente(u);

            commentoRepository.save(commento);

            model.addAttribute("post", post);
            model.addAttribute("commenti", post.getCommenti());

            return "redirect:/no-user/forum/" + postId; // Reindirizza alla pagina del post con i commenti aggiornati
        }
        return "redirect:/"; // Reindirizza alla homepage o a una pagina di errore se il post non esiste
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


    // Esempio di metodo nel controller dei post per cercare i post
    public List<Post> searchPosts(String query) {
        if (query == null || query.isEmpty()) {
            return getTuttiPost(); // Restituisce tutti i post se la query è vuota
        }
        // Cerca i post in base alla query
        return postRepository.findByQuery(query);
    }



  /*  public List<Post> getPostsByCategory(@RequestParam(required = false) String category) {
        if (category == null || category.isEmpty()) {
            return PostRepository.findAll();
        } else {
            return postRepository.findByTipo(tipo);
        }
    } */

}
