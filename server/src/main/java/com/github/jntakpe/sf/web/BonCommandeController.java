package com.github.jntakpe.sf.web;

import com.github.jntakpe.sf.service.BonCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur gérant l'entité {@link com.github.jntakpe.sf.domain.BonCommande}
 *
 * @author jntakpe
 */
@RestController
@RequestMapping(UrisConstants.BON_COMMANDE)
public class BonCommandeController {

    @Autowired
    private BonCommandeService bonCommandeService;

}
