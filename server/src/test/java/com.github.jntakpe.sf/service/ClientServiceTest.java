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

    @Override
    protected String getTable() {
        return CLIENT_TABLE;
    }

    @Override
    protected Operation operations() {
        return bcOperations();
    }

    @Test
    public void testSave_shouldCreateNewClient() {
        Client dipro = new Client();
        dipro.setNom("DIPRO");
        clientService.save(dipro);
        assertThat(countRowsInTable(CLIENT_TABLE)).isEqualTo(initCount + 1);
    }

    @Test
    public void testSave_shouldEditClient() {
        Optional<Client> center = clientRepository.findByNom(CENTER);
        assertThat(center).isPresent();
        Client test = center.get();
        String newName = "TEST";
        test.setNom(newName);
        assertThat(clientRepository.findByNom(newName)).isPresent();
    }
}