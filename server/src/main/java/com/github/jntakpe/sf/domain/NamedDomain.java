package com.github.jntakpe.sf.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Entité mère de toutes les entités possédant une seule colonne 'nom'
 *
 * @author jntakpe
 */
@MappedSuperclass
public abstract class NamedDomain extends GenericDomain {

    @NotNull
    @Column(unique = true, nullable = false)
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamedDomain)) return false;
        NamedDomain that = (NamedDomain) o;
        return Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("nom", nom)
                .toString();
    }
}
