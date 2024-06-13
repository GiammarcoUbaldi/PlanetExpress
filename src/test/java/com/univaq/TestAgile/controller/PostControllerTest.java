package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.controller.api.PostController;
import com.univaq.TestAgile.controller.api.UtenteController;
import com.univaq.TestAgile.model.Commento;
import com.univaq.TestAgile.model.Post;
import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.CommentoRepository;
import com.univaq.TestAgile.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePost() {
        Post post = new Post();
        String viewName = postController.createPost(post);
        assertEquals("redirect:/no-user/forum", viewName);
        verify(postRepository, times(1)).save(post);
    }

    @Test
    void testAggiungiCommento() {
        Post post = new Post();
        Utente utente = new Utente();
        utente.setNome("John");
        utente.setCognome("Doe");

        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(utenteController.getUtenteLoggato()).thenReturn(utente);

        String viewName = postController.aggiungiCommento(1L, "Test Comment", model);

        assertEquals("redirect:/no-user/forum/1", viewName);
        verify(commentoRepository, times(1)).save(any(Commento.class));
        verify(model, times(1)).addAttribute("post", post);
        verify(model, times(1)).addAttribute("commenti", post.getCommenti());
    }

    @Test
    void testEliminaPost() {
        doNothing().when(postRepository).deleteById(anyLong());
        String viewName = postController.eliminaPost(1L);
        assertEquals("redirect:/no-user/forum", viewName);
        verify(postRepository, times(1)).deleteById(1L);
    }

    @Test
    void testEliminaCommento() {
        doNothing().when(commentoRepository).deleteById(anyLong());
        String viewName = postController.eliminaCommento(1L);
        assertEquals("redirect:/no-user/forum", viewName);
        verify(commentoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetTuttiPost() {
        when(postRepository.findAll()).thenReturn(Arrays.asList(new Post(), new Post()));
        assertEquals(2, postController.getTuttiPost().size());
        verify(postRepository, times(1)).findAll();
    }

    @Test
    void testGetPostByTipo() {
        when(postRepository.findByTipo(anyString())).thenReturn(Arrays.asList(new Post(), new Post()));
        assertEquals(2, postController.getPostByTipo("Test").size());
        verify(postRepository, times(1)).findByTipo("Test");
    }

    @Test
    void testSearchPostsWithQuery() {
        when(postRepository.findByQuery(anyString())).thenReturn(Arrays.asList(new Post(), new Post()));
        assertEquals(2, postController.searchPosts("query").size());
        verify(postRepository, times(1)).findByQuery("query");
    }

    @Test
    void testSearchPostsWithoutQuery() {
        when(postRepository.findAll()).thenReturn(Arrays.asList(new Post(), new Post()));
        assertEquals(2, postController.searchPosts("").size());
        verify(postRepository, times(1)).findAll();
    }

    @Test
    void testModificaPost() {
        Post post = new Post();
        post.setId(1L);
        post.setTitolo("Old Title");
        post.setDescrizione("Old Description");

        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));

        String viewName = postController.modificaPost(1L, "New Title", "New Description", model);

        assertEquals("redirect:/no-user/forum", viewName);
        verify(postRepository, times(1)).save(post);
        assertEquals("New Title", post.getTitolo());
        assertEquals("New Description", post.getDescrizione());
    }

    @Test
    void testModificaCommento() {
        Commento commento = new Commento();
        commento.setId(1L);
        commento.setDescrizione("Old Description");

        when(commentoRepository.findById(anyLong())).thenReturn(Optional.of(commento));

        String viewName = postController.modificaCommento(1L, "New Description", model);

        assertEquals("redirect:/no-user/forum", viewName);
        verify(commentoRepository, times(1)).save(commento);
        assertEquals("New Description", commento.getDescrizione());
    }
}
