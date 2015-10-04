package com.github.jntakpe.sf.repository;

import com.github.jntakpe.sf.domain.GenericDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Publication des méthodes de bases permettant de manipuler une entité générique de type {@link GenericDomain}
 *
 * @author jntakpe
 */
@NoRepositoryBean
public interface GenericRepository<T extends GenericDomain> extends JpaRepository<T, Long> {

}
