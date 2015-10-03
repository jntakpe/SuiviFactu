package com.github.jntakpe.sf.service;

import com.github.jntakpe.sf.repository.BonCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service associé aux bons de commandes {@link com.github.jntakpe.sf.domain.BonCommande}
 *
 * @author jntakpe
 */
@Service
public class BonCommandeService {

    private BonCommandeRepository bonCommandeRepository;

    @Autowired
    public BonCommandeService(BonCommandeRepository bonCommandeRepository) {
        this.bonCommandeRepository = bonCommandeRepository;
    }
}
