package com.github.jntakpe.sf.repository;


import com.github.jntakpe.sf.domain.Utilisateur;

import java.util.Optional;

/**
 * Publication des méthodes de gestion de l'entité {@link Utilisateur}
 *
 * @author jntakpe
 */
public interface UtilisateurRepository extends GenericRepository<Utilisateur> {

    Optional<Utilisateur> findByEmailIgnoreCase(String email);
}
