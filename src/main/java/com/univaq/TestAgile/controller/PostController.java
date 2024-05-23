package com.univaq.TestAgile.controller;


import com.univaq.TestAgile.model.OrtoReferente;
import com.univaq.TestAgile.model.Post;
import com.univaq.TestAgile.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/creaPost")
    public String createPost(@ModelAttribute Post post) {
        postRepository.save(post);
        return "redirect:/";
    }

}
