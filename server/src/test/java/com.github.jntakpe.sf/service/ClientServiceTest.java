package com.github.jntakpe.sf.service;

import com.github.jntakpe.sf.domain.Client;
import com.github.jntakpe.sf.repository.ClientRepository;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests des services de l'entité {@link com.github.jntakpe.sf.domain.Client}
 *
 * @author jntakpe
 */
public class ClientServiceTest extends AbstractServiceTestContext {

    public static final String CLIENT_TABLE = "client";

    public static final String SPAI = "SPAI";

    public static final String CENTER = "CENTER";

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    public static Operation bcOperations() {
        return sequenceOf(
                Operations.deleteAllFrom(CLIENT_TABLE),
                Operations.insertInto(CLIENT_TABLE)
                        .columns("nom")
                        .values(CENTER)
                        .values(SPAI)
                        .build()
        );
    }

    @Test
    public void testFindAll_shouldFind() {
        assertThat(clientService.findAll()).isNotEmpty().hasSize(2).contains(getDetachedClient());
    }

    @Test
    public void testSave_shouldCreateNewClient() {
        Client dipro = new Client();
        String clientName = "DIPRO";
        dipro.setNom(clientName);
        clientService.save(dipro);
        assertThat(countRowsInTable(CLIENT_TABLE)).isEqualTo(initCount + 1);
        assertThat(clientRepository.findByNom(clientName)).isPresent();
    }

    @Test
    public void testSave_shouldEditClient() {
        Client detachedClient = getDetachedClient();
        String newName = "TEST";
        detachedClient.setNom(newName);
        clientService.save(detachedClient);
        assertThat(clientRepository.findByNom(newName)).isPresent();
    }

    @Override
    protected String getTable() {
        return CLIENT_TABLE;
    }

    @Override
    protected Operation operations() {
        return bcOperations();
    }

    private Client getDetachedClient() {
        Optional<Client> center = clientRepository.findByNom(CENTER);
        assertThat(center).isPresent();
        Client detachedClient = new Client();
        detachedClient.setId(center.get().getId());
        detachedClient.setNom(center.get().getNom());
        return detachedClient;
    }
}