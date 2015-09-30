package com.github.jntakpe.sf.service;

import com.github.jntakpe.sf.config.security.SecurityUtils;
import com.github.jntakpe.sf.domain.Role;
import com.github.jntakpe.sf.domain.Utilisateur;
import com.github.jntakpe.sf.exception.FunctionalCode;
import com.github.jntakpe.sf.exception.SfException;
import com.github.jntakpe.sf.exception.TechnicalCode;
import com.github.jntakpe.sf.repository.RoleRepository;
import com.github.jntakpe.sf.repository.UtilisateurRepository;
import com.github.jntakpe.sf.utils.ValidationUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
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

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public Utilisateur current() {
        LOGGER.debug("Récupération de l'utilisateur courant et des rôles associés");
        Utilisateur utilisateur = utilisateurRepository.findOne(SecurityUtils.getCurrentUserId());
        Hibernate.initialize(utilisateur.getRoles());
        return utilisateur;
    }

    @Transactional(readOnly = true)
    public Utilisateur findByEmailWithAuthorities(String email) {
        return findRoles(findByEmail(email)
                .orElseThrow(() -> new SfException("Aucun utilisateur correspondant au mail " + email, FunctionalCode.USERNAME_NOT_FOUND)));
    }

    @Transactional
    public Utilisateur register(Utilisateur utilisateur) {
        checkPasswordMatch(utilisateur);
        encodePassword(utilisateur);
        addRoleUser(utilisateur);
        utilisateur.setActivationKey(RandomStringUtils.randomAlphanumeric(20));
        LOGGER.info("Enregistrement d'un nouvel utilisateur {}", utilisateur);
        return utilisateurRepository.save(utilisateur);
    }

    @Transactional
    public Utilisateur activate(String key) {
        Utilisateur utilisateur = utilisateurRepository.findByActivationKey(key)
                .orElseThrow(() -> new SfException("La clé d'activation " + key + " n'existe pas", TechnicalCode.NO_RESULT));
        utilisateur.setActivated(true);
        utilisateur.setActivationKey(null);
        return utilisateur;
    }

    @Transactional(readOnly = true)
    public boolean isUsernameAvailable(String username, Long id) {
        return ValidationUtils.isAvailable(findByNom(username), id);
    }

    @Transactional(readOnly = true)
    public boolean isEmailAvailable(String mail, Long id) {
        return ValidationUtils.isAvailable(findByEmail(mail), id);
    }

    @Transactional
    public Utilisateur resetPassword(String email) {
        return findByEmail(email).orElseThrow(() -> new SfException("Adresse mail " + email + " introuvable", TechnicalCode.NO_RESULT));
    }

    private void checkPasswordMatch(Utilisateur utilisateur) {
        if (!utilisateur.getPassword().equals(utilisateur.getConfirm())) {
            throw new ValidationException("Les mots de passe ne correspondent pas");
        }
    }

    private void encodePassword(Utilisateur utilisateur) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
    }

    @Transactional(readOnly = true)
    private void addRoleUser(Utilisateur utilisateur) {
        utilisateur.addRole(roleRepository.findByNom(Role.ROLE_USER)
                .orElseThrow(() -> new SfException("Le rôle " + Role.ROLE_USER + " est introuvable", FunctionalCode.FORBIDDEN)));
    }

    @Transactional(readOnly = true)
    private Utilisateur findRoles(Utilisateur utilisateur) {
        LOGGER.debug("Récupération des rôles liés à l'utilisateur : {}", utilisateur);
        Hibernate.initialize(utilisateur.getRoles());
        return utilisateur;
    }

    @Transactional(readOnly = true)
    private Optional<Utilisateur> findByEmail(String email) {
        LOGGER.debug("Recherche d'un utilisateur avec l'adresse mail {}", email);
        return utilisateurRepository.findByEmailIgnoreCase(email);
    }

    @Transactional(readOnly = true)
    private Optional<Utilisateur> findByNom(String nom) {
        LOGGER.debug("Recherche d'une utilisateur avec le login {}", nom);
        return utilisateurRepository.findByNomIgnoreCase(nom);
    }
}
