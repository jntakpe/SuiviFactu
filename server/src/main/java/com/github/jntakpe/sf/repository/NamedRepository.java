package com.github.jntakpe.sf.repository;

import com.github.jntakpe.sf.domain.NamedDomain;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * Repository gérant les entités filles de {@link com.github.jntakpe.sf.domain.NamedDomain}
 *
 * @author jntakpe
 */
@NoRepositoryBean
public interface NamedRepository<T extends NamedDomain> extends GenericRepository<T> {

    Optional<T> findByNom(String nom);

}
