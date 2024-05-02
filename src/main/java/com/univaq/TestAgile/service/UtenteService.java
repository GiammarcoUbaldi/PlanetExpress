package com.univaq.TestAgile.service;

import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class UtenteService {
    private Map<String, Utente> utenti;

    public UtenteService() {
        utenti = new HashMap<>();
        utenti.put("Cliente", new Utente("utente", "utepassword", "ute"));
        utenti.put("Amministratore", new Utente("admin", "adminpassword", "admin"));
        utenti.put("Referente", new Utente("referent", "referentpassword", "referent"));
    }

    public static Utente authenticate(String username, String password) {
        Utente utente = utente.get(username);
        if (utente != null && utente.getPassword().equals(password)) {
            return utente;
        }
        return null;
    }

    public List<Utente> getUtenti() {
        return null;
    }

    public static class Utente {
        private String username;
        private String password;
        private String ruolo;
        public Utente(String username, String password, String ruolo) {
            this.username = username;
            this.password = password;
            this.ruolo = ruolo;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getRuolo() {
            return ruolo;
        }
    } }