package com.github.jntakpe.sf.service;

import com.github.jntakpe.sf.domain.CentreService;
import com.github.jntakpe.sf.repository.CDSRepository;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests des services associés à l'entité {@link com.github.jntakpe.sf.domain.CentreService}
 *
 * @author jntakpe
 */
public class CDSServiceTest extends AbstractServiceTestContext {

    public static final String CDS_TABLE = "centreservice";

    private static final String NTIC_3 = "NTIC3";

    @Autowired
    private CDSService cdsService;

    @Autowired
    private CDSRepository cdsRepository;

    public static Operation cdsOperations() {
        return sequenceOf(
                Operations.deleteAllFrom(CDS_TABLE),
                Operations.insertInto(CDS_TABLE)
                        .columns("nom")
                        .values(NTIC_3)
                        .build()
        );
    }

    @Test
    public void testSave_shouldCreateNewCDS() {
        CentreService lille = new CentreService();
        lille.setNom("Lille");
        cdsService.save(lille);
        assertThat(countRowsInTable(CDS_TABLE)).isEqualTo(initCount + 1);
    }

    @Test
    public void testSave_shouldEditCDS() {
        Optional<CentreService> optNtic3 = cdsRepository.findByNom(NTIC_3);
        assertThat(optNtic3).isPresent();
        CentreService ntic3 = optNtic3.get();
        String newName = "ntic4";
        ntic3.setNom(newName);
        assertThat(cdsRepository.findByNom(newName)).isPresent();
    }

    @Override
    protected String getTable() {
        return CDS_TABLE;
    }

    @Override
    protected Operation operations() {
        return cdsOperations();
    }
}