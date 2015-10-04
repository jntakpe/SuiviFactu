package com.github.jntakpe.sf.service;

import com.github.jntakpe.sf.domain.CentreService;
import com.github.jntakpe.sf.repository.CDSRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Services associés à l'entité {@link com.github.jntakpe.sf.domain.CentreService}
 *
 * @author jntakpe
 */
@Service
public class CDSService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CentreService.class);

    private CDSRepository cdsRepository;

    @Autowired
    public CDSService(CDSRepository cdsRepository) {
        this.cdsRepository = cdsRepository;
    }

    @Transactional
    public CentreService save(CentreService centreService) {
        LOGGER.info("Enregistrement du centre de service {}", centreService);
        return cdsRepository.save(centreService);
    }


}
