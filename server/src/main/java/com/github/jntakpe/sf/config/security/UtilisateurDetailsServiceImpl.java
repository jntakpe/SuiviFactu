package com.github.jntakpe.sf.config.security;

import com.github.jntakpe.sf.domain.Utilisateur;
import com.github.jntakpe.sf.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Récupération de l'utilisateur tentant de s'authentifier
 *
 * @author jntakpe
 */
@Component
public class UtilisateurDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurDetailsServiceImpl.class);

    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurDetailsServiceImpl(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    private static SpringSecurityUser mapUserDetails(Utilisateur user) {
        return new SpringSecurityUser(user.getId(), user.getEmail(), user.getPassword(), user.isActivated(), mapAuthorities(user));
    }

    private static List<GrantedAuthority> mapAuthorities(Utilisateur utilisateur) {
        return utilisateur.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNom()))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpringSecurityUser loadUserByUsername(String email) {
        LOGGER.debug("Authentification de l'utilisateur avec l'addresse mail {}", email);
        return mapUserDetails(utilisateurService.findByEmailWithAuthorities(email));
    }
}
