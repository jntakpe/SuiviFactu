package com.github.jntakpe.sf.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Entité représentant un devis
 *
 * @author jntakpe
 */
@Entity
public class Devis extends GenericDomain {

    @NotNull
    @Column(unique = true, nullable = false)
    private Integer numero;

    @NotNull
    @Column(unique = true, nullable = false)
    private String reference;

    @ManyToOne
    private TypeDevis typeDevis;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public TypeDevis getTypeDevis() {
        return typeDevis;
    }

    public void setTypeDevis(TypeDevis typeDevis) {
        this.typeDevis = typeDevis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Devis)) return false;
        Devis devis = (Devis) o;
        return Objects.equals(numero, devis.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("numero", numero)
                .append("reference", reference)
                .toString();
    }
}
