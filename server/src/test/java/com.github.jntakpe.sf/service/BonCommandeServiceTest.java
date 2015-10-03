package com.github.jntakpe.sf.service;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;

/**
 * Tests des services de l'entité {@link BonCommandeServiceTest}
 *
 * @author jntakpe
 */
public class BonCommandeServiceTest extends AbstractServiceTestContext {

    public static final String BC_TABLE = "boncommande";

    @Autowired
    private BonCommandeService bonCommandeService;

    public static Operation bcOperations() {
        return sequenceOf(
                Operations.deleteAllFrom(BC_TABLE)
        );
    }

    @Override
    protected String getTable() {
        return BC_TABLE;
    }

    @Override
    protected Operation operations() {
        return bcOperations();
    }
}