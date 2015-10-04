package com.github.jntakpe.sf.service;

import com.github.jntakpe.sf.domain.TypeDevis;
import com.github.jntakpe.sf.repository.TypeDevisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Services associés à l'entité {@link com.github.jntakpe.sf.domain.TypeDevis}
 *
 * @author jntakpe
 */
@Service
public class TypeDevisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeDevisService.class);

    private TypeDevisRepository typeDevisRepository;

    @Autowired
    public TypeDevisService(TypeDevisRepository typeDevisRepository) {
        this.typeDevisRepository = typeDevisRepository;
    }

    @Transactional
    public TypeDevis save(TypeDevis typeDevis) {
        LOGGER.info("Enregistrement du type de devis {}", typeDevis);
        return typeDevisRepository.save(typeDevis);
    }
}
