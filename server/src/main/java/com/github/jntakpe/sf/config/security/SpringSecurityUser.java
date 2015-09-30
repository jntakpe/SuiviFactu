package com.github.jntakpe.sf.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Utilisateur Spring security avec un identifiant pour faciliter les recherches dans le cache
 *
 * @author jntakpe
 */
public class SpringSecurityUser extends User {

    private final Long id;

    public SpringSecurityUser(Long id, String username, String password, boolean activated, Collection<? extends GrantedAuthority> auths) {
        super(username, password, activated, true, true, true, auths);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
