package com.univaq.TestAgile.service;

import com.univaq.TestAgile.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewDispatcher {

    private Stage primaryStage;

    public ViewDispatcher(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void redirectToWelcomePage(String role) {
        String viewPath = getViewPathForRole(role);
        if (viewPath != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Errore: Vista non disponibile per il ruolo " + role);
        }
    }

    private String getViewPathForRole(String role) {
        // Restituisci il percorso della vista corretta in base al ruolo
        switch (role) {
            case "utente":
                return "benvenutoUtente.fxml";
            case "referente":
                return "benvenutoReferente.fxml";
            case "admin":
                return "benvenutoAdmin.fxml";
            default:
                return null;
        }
    }

    public void redirectToLoginPageWithError(String errorMessage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            LoginController controller = loader.getController();
            controller.setErrorMessage(errorMessage); // Passa il messaggio di errore al controller di login
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
