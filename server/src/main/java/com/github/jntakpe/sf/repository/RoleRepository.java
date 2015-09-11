package com.github.jntakpe.sf.repository;

import com.github.jntakpe.sf.domain.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Publication des méthodes de gestion de l'entité {@link com.github.jntakpe.sf.domain.Role}
 *
 * @author jntakpe
 */
public interface RoleRepository extends CrudRepository<Role, String> {

    Optional<Role> findByNom(String nom);
}
