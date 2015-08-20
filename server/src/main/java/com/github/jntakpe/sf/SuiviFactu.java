package com.github.jntakpe.sf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe mère de l'application
 *
 * @author jntakpe
 */
@SpringBootApplication
public class SuiviFactu {

    /**
     * Méthode appellée pour le démarrage de l'application
     *
     * @param args paramètres de l'application
     */
    public static void main(String[] args) {
        SpringApplication.run(SuiviFactu.class, args);
    }

}
