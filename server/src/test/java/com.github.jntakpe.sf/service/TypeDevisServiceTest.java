package com.github.jntakpe.sf.service;

import com.github.jntakpe.sf.domain.TypeDevis;
import com.github.jntakpe.sf.repository.TypeDevisRepository;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests des services associés à l'entité {@link com.github.jntakpe.sf.domain.TypeDevis}
 *
 * @author jntakpe
 */
public class TypeDevisServiceTest extends AbstractServiceTestContext {

    public static final String TYPE_DEVIS_TABLE = "typedevis";

    private static final String PRESTATION = "Prestation";

    @Autowired
    private TypeDevisRepository typeDevisRepository;

    @Autowired
    private TypeDevisService typeDevisService;

    public static Operation typeDevisOperations() {
        return sequenceOf(
                Operations.deleteAllFrom(TYPE_DEVIS_TABLE),
                Operations.insertInto(TYPE_DEVIS_TABLE)
                        .columns("nom")
                        .values(PRESTATION)
                        .values("Forfait EVO")
                        .build()
        );
    }

    @Test
    public void testSave_shouldCreateNewTypeDevis() {
        TypeDevis forfait = new TypeDevis();
        String forfaitName = "forfait";
        forfait.setNom(forfaitName);
        typeDevisService.save(forfait);
        assertThat(countRowsInTable(TYPE_DEVIS_TABLE)).isEqualTo(initCount + 1);
        assertThat(typeDevisRepository.findByNom(forfaitName)).isPresent();
    }

    @Test
    public void testSave_shouldEditTypeDevis() {
        Optional<TypeDevis> optPresta = typeDevisRepository.findByNom(PRESTATION);
        assertThat(optPresta).isPresent();
        TypeDevis presta = optPresta.get();
        String prestaNewName = "Presta";
        presta.setNom(prestaNewName);
        assertThat(typeDevisRepository.findByNom(prestaNewName)).isPresent();
    }

    @Override
    protected String getTable() {
        return TYPE_DEVIS_TABLE;
    }

    @Override
    protected Operation operations() {
        return typeDevisOperations();
    }
}