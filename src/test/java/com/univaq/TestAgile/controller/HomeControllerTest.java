package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.controller.api.EventoController;
import com.univaq.TestAgile.controller.api.PostController;
import com.univaq.TestAgile.controller.api.RiempiDbCotroller;
import com.univaq.TestAgile.controller.api.UtenteController;
import com.univaq.TestAgile.model.Commento;
import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.model.Post;
import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.CommentoRepository;
import com.univaq.TestAgile.repository.PostRepository;
import com.univaq.TestAgile.repository.UtenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    @Mock
    private EventoController eventoController;

    @Mock
    private UtenteController utenteController;

    @Mock
    private RiempiDbCotroller riempiDbCotroller;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UtenteRepository utenteRepository;

    @Mock
    private CommentoRepository commentoRepository;

    @Mock
    private PostController postController;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    private Utente utente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        utente = new Utente();
        utente.setTipoUtente("USER");
    }

    @Test
    void testIndex() {
        List<Evento> eventiRefe = Arrays.asList(new Evento(), new Evento());
        List<Post> listPost = Arrays.asList(new Post(), new Post());

        when(eventoController.getEventiAccettati()).thenReturn(eventiRefe);
        when(postController.getTuttiPost()).thenReturn(listPost);
        when(eventoController.getEventi33()).thenReturn(eventiRefe);
        when(postRepository.findAll()).thenReturn(listPost);

        String viewName = homeController.index(model);

        assertEquals("/home/homePage", viewName);
        verify(model, times(1)).addAttribute(eq("ListaPost"), anyList());
        verify(model, times(1)).addAttribute(eq("EventiRefe"), anyList());
        verify(model, times(1)).addAttribute(eq("EventiNormali"), anyList());
    }

    @Test
    void testLogin() {
        String viewName = homeController.login();
        assertEquals("/autenticazione/login", viewName);
    }

    @Test
    void testRegistrazioneForm() {
        String viewName = homeController.registrazioneForm(new Utente());
        assertEquals("/autenticazione/registrazione", viewName);
    }

    @Test
    void testRiempiDb() {
        String viewName = homeController.riempiDb();
        assertEquals("redirect:/", viewName);
        verify(riempiDbCotroller, times(1)).inserisciDati();
    }

    @Test
    void testMostraDashboard() {
        when(utenteController.getUtenteLoggato()).thenReturn(utente);
        String viewName = homeController.mostraDashboard();
        assertEquals("redirect:/user/dashboard", viewName);
    }

    @Test
    void testMostraDettagliEvento() {
        Evento evento = new Evento();
        when(eventoController.getEventoById(anyLong())).thenReturn(evento);

        String viewName = homeController.mostraDettagliEvento(model, 1L);

        assertEquals("/home/dettagliEventiNoUser", viewName);
        verify(model, times(1)).addAttribute(eq("datiDettagli"), any(Evento.class));
    }

    @Test
    void testMostraFormRichiestaOrtoReferente() {
        String viewName = homeController.mostraFormRichiestaOrtoReferente(model);
        assertEquals("/referente/formRichiestaOrtoReferete", viewName);
    }

    @Test
    void testCreaPost() {
        String viewName = homeController.creaPost();
        assertEquals("/Post/ScriviPost", viewName);
    }

    @Test
    void testForum() {
        List<Post> listPost = Arrays.asList(new Post(), new Post());
        when(postController.getTuttiPost()).thenReturn(listPost);

        String viewName = homeController.forum(model);

        assertEquals("/Post/filtraggioPost", viewName);
        verify(model, times(1)).addAttribute(eq("ListaPost"), anyList());
    }

    @Test
    void testVisualizzaPost() {
        Post post = new Post();
        List<Commento> commenti = Arrays.asList(new Commento(), new Commento());
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(commentoRepository.findByPost(any(Post.class))).thenReturn(commenti);

        String viewName = homeController.visualizzaPost(1L, model);

        assertEquals("/Post/SingoloPost", viewName);
        verify(model, times(1)).addAttribute(eq("post"), any(Post.class));
        verify(model, times(1)).addAttribute(eq("commenti"), anyList());
    }

    @Test
    void testFilterPosts() {
        List<Post> filteredPosts = Arrays.asList(new Post(), new Post());
        when(postController.getPostByTipo(anyString())).thenReturn(filteredPosts);

        ResponseEntity<List<Post>> response = homeController.filterPosts("categoria");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(filteredPosts, response.getBody());
    }

    @Test
    void testSearchPosts() {
        List<Post> searchedPosts = Arrays.asList(new Post(), new Post());
        when(postController.searchPosts(anyString())).thenReturn(searchedPosts);

        ResponseEntity<List<Post>> response = homeController.searchPosts("query");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(searchedPosts, response.getBody());
    }
}

