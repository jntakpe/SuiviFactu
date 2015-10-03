package com.github.jntakpe.sf.repository;

import com.github.jntakpe.sf.domain.Client;

import java.util.Optional;

/**
 * Repository gérant l'entité {@link com.github.jntakpe.sf.domain.Client}
 *
 * @author jntakpe
 */
public interface ClientRepository extends GenericRepository<Client> {

    Optional<Client> findByNom(String nom);

}
