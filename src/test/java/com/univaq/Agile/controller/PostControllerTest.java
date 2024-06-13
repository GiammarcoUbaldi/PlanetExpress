package com.univaq.Agile.controller;

import com.univaq.Agile.controller.api.PostController;
import com.univaq.Agile.controller.api.UtenteController;
import com.univaq.Agile.model.Commento;
import com.univaq.Agile.model.Post;
import com.univaq.Agile.model.Utente;
import com.univaq.Agile.repository.CommentoRepository;
import com.univaq.Agile.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PostControllerTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentoRepository commentoRepository;

    @Mock
    private UtenteController utenteController;

    @Mock
    private Model model;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePost() {
        Post post = new Post();
        String result = postController.createPost(post);
        verify(postRepository, times(1)).save(post);
        assertEquals("redirect:/no-user/forum", result);
    }

    @Test
    public void testAggiungiCommento() {
        Post post = new Post();
        post.setId(1L);
        Utente utente = new Utente();
        utente.setNome("Test");
        utente.setCognome("User");

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(utenteController.getUtenteLoggato()).thenReturn(utente);

        String result = postController.aggiungiCommento(1L, "Test Comment", model);

        verify(commentoRepository, times(1)).save(any(Commento.class));
        verify(model, times(1)).addAttribute("post", post);
        verify(model, times(1)).addAttribute("commenti", post.getCommenti());

        assertEquals("redirect:/no-user/forum/1", result);
    }

    @Test
    public void testEliminaPost() {
        doNothing().when(postRepository).deleteById(1L);
        Boolean result = postController.eliminaPost(1L);
        assertTrue(result);
    }

    @Test
    public void testEliminaPostFailure() {
        doThrow(new RuntimeException()).when(postRepository).deleteById(1L);
        Boolean result = postController.eliminaPost(1L);
        assertFalse(result);
    }

    @Test
    public void testEliminaCommento() {
        doNothing().when(commentoRepository).deleteById(1L);
        Boolean result = postController.eliminaCommento(1L);
        assertTrue(result);
    }

    @Test
    public void testEliminaCommentoFailure() {
        doThrow(new RuntimeException()).when(commentoRepository).deleteById(1L);
        Boolean result = postController.eliminaCommento(1L);
        assertFalse(result);
    }

    @Test
    public void testGetTuttiPost() {
        List<Post> posts = new ArrayList<>();
        when(postRepository.findAll()).thenReturn(posts);
        List<Post> result = postController.getTuttiPost();
        assertEquals(posts, result);
    }

    @Test
    public void testGetPostByTipo() {
        List<Post> posts = new ArrayList<>();
        when(postRepository.findByTipo("tipo1")).thenReturn(posts);
        List<Post> result = postController.getPostByTipo("tipo1");
        assertEquals(posts, result);
    }

    @Test
    public void testSearchPosts() {
        List<Post> posts = new ArrayList<>();
        when(postRepository.findByQuery("query")).thenReturn(posts);
        List<Post> result = postController.searchPosts("query");
        assertEquals(posts, result);

        when(postRepository.findAll()).thenReturn(posts);
        result = postController.searchPosts(null);
        assertEquals(posts, result);
    }
}

