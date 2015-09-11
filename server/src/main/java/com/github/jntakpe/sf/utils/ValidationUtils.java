package com.github.jntakpe.sf.utils;

import com.github.jntakpe.sf.domain.GenericDomain;

import java.util.Optional;

/**
 * Classe utilitaire pour les validations
 *
 * @author jntakpe
 */
public final class ValidationUtils {

    private ValidationUtils() {
    }

    /**
     * Indique si un objet peut être enregistré sans violer de constrainte d'unicité
     *
     * @param search résultat de la recherche sur la propriété ayant la contrainte d'unicité
     * @param id     identifiant de l'objet à enregistrer
     * @return true si l'objet peut être enregistré
     */
    public static <T extends GenericDomain> boolean isAvailable(Optional<T> search, Long id) {
        return !search.isPresent() || search.get().getId().equals(id);
    }
}
