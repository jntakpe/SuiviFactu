package com.github.jntakpe.sf.service;

import com.github.jntakpe.sf.domain.StatutBC;
import com.github.jntakpe.sf.repository.StatutBCRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service associé à l'entité {@link com.github.jntakpe.sf.domain.StatutBC}
 *
 * @author jntakpe
 */
@Service
public class StatutBCService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatutBCService.class);

    private StatutBCRepository statutBCRepository;

    @Autowired
    public StatutBCService(StatutBCRepository statutBCRepository) {
        this.statutBCRepository = statutBCRepository;
    }

    @Transactional
    public StatutBC save(StatutBC statutBC) {
        LOGGER.info("Enregistrement du statut du bon commande {}", statutBC);
        return statutBCRepository.save(statutBC);
    }
}
