package com.github.jntakpe.sf.config.security;

import com.github.jntakpe.sf.config.ConfigConstants;
import com.github.jntakpe.sf.exception.FunctionalCode;
import com.github.jntakpe.sf.exception.SfException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Optional;

/**
 * Classe utilitaire de Spring Security
 *
 * @author jntakpe
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static Optional<SpringSecurityUser> getCurrentUser() {
        Authentication authentication = getAuthentification();
        SpringSecurityUser springSecurityUser;
        if (authentication != null && authentication.getPrincipal() instanceof SpringSecurityUser) {
            springSecurityUser = (SpringSecurityUser) authentication.getPrincipal();
            return Optional.of(springSecurityUser);
        }
        return Optional.empty();
    }

    public static Long getCurrentUserId() {
        return getCurrentUser().orElseThrow(() -> new SfException(FunctionalCode.NOT_AUTHENTICATED)).getId();
    }

    public static boolean isAuthenticated() {
        Collection<? extends GrantedAuthority> authorities = getAuthentification().getAuthorities();
        if (authorities != null) {
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(ConfigConstants.ANONYMOUS)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Authentication getAuthentification() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
