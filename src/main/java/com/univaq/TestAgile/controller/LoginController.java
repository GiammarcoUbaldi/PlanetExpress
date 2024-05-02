package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.service.UtenteService;

public class LoginController {

    public LoginController() {
        UtenteService utenteService = new UtenteService();
    }

    public String login(String username, String password) {
        UtenteService.Utente utente = UtenteService.authenticate(username, password);
        if (utente != null) {
            return utente.getRuolo();
        } else {
            return null;
        }
    }
    public void setErrorMessage(String errorMessage) {
    }
}
