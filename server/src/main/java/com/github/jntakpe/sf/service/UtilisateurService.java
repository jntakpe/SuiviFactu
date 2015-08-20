package com.github.jntakpe.sf.service;

import com.github.jntakpe.sf.domain.Utilisateur;
import com.github.jntakpe.sf.repository.UtilisateurRepository;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Services associés à l'entité {@link Utilisateur}
 *
 * @author jntakpe
 */
@Service
public class UtilisateurService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurService.class);

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Transactional(readOnly = true)
    public Utilisateur findByEmailWithAuthorities(String email) {
        Utilisateur utilisateur = findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur correspondant au mail " + email));
        LOGGER.debug("Récupération des rôles liés à l'utilisateur : {}", email);
        Hibernate.initialize(utilisateur.getRoles());
        return utilisateur;
    }

    @Transactional(readOnly = true)
    public Optional<Utilisateur> findByEmail(String email) {
        LOGGER.debug("Recherche d'un utilisateur avec son adresse mail : {}", email);
        return utilisateurRepository.findByEmailIgnoreCase(email);
    }
}
