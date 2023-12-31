package com.webapp.ytb.webappytp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Récupérer des détails sur l'erreur à partir de la requête
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error403"; // Page d'erreur personnalisée pour le statut 403
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error404"; // Page d'erreur personnalisée pour le statut 404
            }
        }

        return "error"; // Page d'erreur générale
    }

    public String getErrorPath() {
        return "/error";
    }
}