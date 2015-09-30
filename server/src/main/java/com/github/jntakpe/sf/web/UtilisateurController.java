package com.github.jntakpe.sf.web;

import com.github.jntakpe.sf.domain.Utilisateur;
import com.github.jntakpe.sf.service.MailService;
import com.github.jntakpe.sf.service.UtilisateurService;
import com.github.jntakpe.sf.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    private MailService mailService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, MailService mailService) {
        this.utilisateurService = utilisateurService;
        this.mailService = mailService;
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Utilisateur current() {
        return utilisateurService.current();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Utilisateur register(@Valid @RequestBody Utilisateur utilisateur, HttpServletRequest request) {
        Utilisateur registered = utilisateurService.register(utilisateur);
        mailService.sendActivationEmail(registered, UrlUtils.getBaseUrl(request));
        return registered;
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
    public Utilisateur resetPassword(@RequestBody Utilisateur utilisateur, HttpServletRequest request) {
        Utilisateur updatedUser = utilisateurService.resetPassword(utilisateur.getEmail());
        mailService.sendActivationEmail(updatedUser, UrlUtils.getBaseUrl(request));
        return updatedUser;
    }

    @RequestMapping(value = "/activate/{key}", method = RequestMethod.POST)
    public Utilisateur activate(@PathVariable String key) {
        return utilisateurService.activate(key);
    }

}
