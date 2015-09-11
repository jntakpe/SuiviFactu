package com.github.jntakpe.sf.web;

import com.github.jntakpe.sf.domain.Utilisateur;
import com.github.jntakpe.sf.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Contrôlleur gérant l'entité {@link com.github.jntakpe.sf.domain.Utilisateur}
 *
 * @author jntakpe
 */
@RestController
@RequestMapping(UrisConstants.UTILISATEURS)
public class UtilisateurController {

    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Utilisateur register(@Valid @RequestBody Utilisateur utilisateur) {
        return utilisateurService.register(utilisateur);
    }
}
