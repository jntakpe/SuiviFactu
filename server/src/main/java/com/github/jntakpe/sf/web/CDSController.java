package com.github.jntakpe.sf.web;

import com.github.jntakpe.sf.domain.CentreService;
import com.github.jntakpe.sf.service.CDSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Contrôleur gérant l'entité {@link com.github.jntakpe.sf.domain.CentreService}
 *
 * @author jntakpe
 */
@RestController
@RequestMapping(UrisConstants.CDS)
public class CDSController {

    private CDSService cdsService;

    @Autowired
    public CDSController(CDSService cdsService) {
        this.cdsService = cdsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CentreService> list() {
        return cdsService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public CentreService create(@RequestBody CentreService centreService) {
        return cdsService.save(centreService);
    }
}
