package com.univaq.TestAgile.controller;


import com.univaq.TestAgile.model.OrtoReferente;
import com.univaq.TestAgile.model.Post;
import com.univaq.TestAgile.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/creaPost")
    public String createPost(@ModelAttribute Post post) {
        postRepository.save(post);
        System.out.println(post);
        return "redirect:/";
    }
  /*  public List<Post> getPostsByCategory(@RequestParam(required = false) String category) {
        if (category == null || category.isEmpty()) {
            return PostRepository.findAll();
        } else {
            return postRepository.findByTipo(tipo);
        }
    } */
}
