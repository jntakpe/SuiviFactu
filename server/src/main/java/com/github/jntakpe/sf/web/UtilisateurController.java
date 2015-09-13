package com.github.jntakpe.sf.web;

import com.github.jntakpe.sf.domain.Utilisateur;
import com.github.jntakpe.sf.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Utilisateur current() {
        return utilisateurService.current();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Utilisateur register(@Valid @RequestBody Utilisateur utilisateur) {
        return utilisateurService.register(utilisateur);
    }

    @RequestMapping(value = "/nameAvailable", method = RequestMethod.GET)
    public ResponseEntity isNameAvailable(@RequestParam String name, @RequestParam(required = false) Long id) {
        return new ResponseEntity(utilisateurService.isUsernameAvailable(name, id) ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/emailAvailable", method = RequestMethod.GET)
    public ResponseEntity isEmailAvailable(@RequestParam String email, @RequestParam(required = false) Long id) {
        return new ResponseEntity(utilisateurService.isEmailAvailable(email, id) ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ResponseEntity resetPassword(@RequestBody Utilisateur utilisateur) {
        return new ResponseEntity(HttpStatus.OK);
    }

}
