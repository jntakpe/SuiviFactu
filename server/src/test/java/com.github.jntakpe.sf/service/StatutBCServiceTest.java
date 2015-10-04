package com.github.jntakpe.sf.service;

import com.github.jntakpe.sf.domain.StatutBC;
import com.github.jntakpe.sf.repository.StatutBCRepository;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests associés aux services de l'entité {@link com.github.jntakpe.sf.domain.StatutBC}
 *
 * @author jntakpe
 */
public class StatutBCServiceTest extends AbstractServiceTestContext {

    public static final String STATUT_BC_TABLE = "statutbc";

    private static final String REFUSE = "Refusé";

    @Autowired
    private StatutBCService statutBCService;

    @Autowired
    private StatutBCRepository statutBCRepository;

    public static Operation statutBCOperations() {
        return sequenceOf(
                Operations.deleteAllFrom(STATUT_BC_TABLE),
                Operations.insertInto(STATUT_BC_TABLE)
                        .columns("nom")
                        .values(REFUSE)
                        .values("A envoyer")
                        .build()
        );
    }

    @Test
    public void testFindAll_shouldFind() {
        assertThat(statutBCService.findAll()).isNotEmpty().hasSize(2).contains(getDetachedStatutBC());
    }

    @Test
    public void testSave_shouldCreateNewStatutBC() {
        StatutBC statutBC = new StatutBC();
        String newName = "Supprimé";
        statutBC.setNom(newName);
        statutBCService.save(statutBC);
        assertThat(countRowsInTable(STATUT_BC_TABLE)).isEqualTo(initCount + 1);
        assertThat(statutBCRepository.findByNom(newName)).isPresent();
    }

    @Test
    public void testSave_shouldEditStatutBC() {
        StatutBC detachedStatutBC = getDetachedStatutBC();
        String newName = "A facturer";
        detachedStatutBC.setNom(newName);
        statutBCService.save(detachedStatutBC);
        assertThat(statutBCRepository.findByNom(newName)).isPresent();
    }

    @Override
    protected String getTable() {
        return STATUT_BC_TABLE;
    }

    @Override
    protected Operation operations() {
        return statutBCOperations();
    }

    private StatutBC getDetachedStatutBC() {
        Optional<StatutBC> statutBC = statutBCRepository.findByNom(REFUSE);
        assertThat(statutBC).isPresent();
        StatutBC detachedStatutBC = new StatutBC();
        detachedStatutBC.setId(statutBC.get().getId());
        detachedStatutBC.setNom(statutBC.get().getNom());
        return detachedStatutBC;
    }
}