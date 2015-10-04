package com.github.jntakpe.sf.service;

import com.github.jntakpe.sf.domain.Client;
import com.github.jntakpe.sf.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service associé à l'entité {@link Client}
 *
 * @author jntakpe
 */
@Service
public class ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public List<Client> findAll() {
        LOGGER.debug("Recherche la liste des clients");
        return clientRepository.findAll();
    }

    @Transactional
    public Client save(Client client) {
        LOGGER.info("Enregistrement du client {}", client);
        return clientRepository.save(client);
    }
}
