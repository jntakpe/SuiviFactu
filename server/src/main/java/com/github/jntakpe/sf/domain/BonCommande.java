package com.github.jntakpe.sf.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entité définissant un bon de commande
 *
 * @author jntakpe
 */
@Entity
public class BonCommande extends GenericDomain {

    @NotNull
    @Column(unique = true, nullable = false)
    private String numero;

    @NotNull
    @Column(unique = true, nullable = false)
    private String libelle;

    private String gedep;

    @NotNull
    @Column(nullable = false)
    private BigDecimal montant;

    @NotNull
    @Column(nullable = false)
    private BigDecimal joursHomme;

    @Column(nullable = false)
    private BigDecimal joursHorsSite = new BigDecimal(0);

    @Enumerated(EnumType.STRING)
    private SaisieGPS saisieGPS;

    @ManyToOne(optional = false)
    private CentreService centreService;

    @ManyToOne(optional = false)
    private Client client;

    @ManyToOne(optional = false)
    private Devis devis;

    @ManyToOne(optional = false)
    private StatutBC statutBC;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getGedep() {
        return gedep;
    }

    public void setGedep(String gedep) {
        this.gedep = gedep;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getJoursHomme() {
        return joursHomme;
    }

    public void setJoursHomme(BigDecimal joursHomme) {
        this.joursHomme = joursHomme;
    }

    public BigDecimal getJoursHorsSite() {
        return joursHorsSite;
    }

    public void setJoursHorsSite(BigDecimal joursHorsSite) {
        this.joursHorsSite = joursHorsSite;
    }

    public SaisieGPS getSaisieGPS() {
        return saisieGPS;
    }

    public void setSaisieGPS(SaisieGPS saisieGPS) {
        this.saisieGPS = saisieGPS;
    }

    public CentreService getCentreService() {
        return centreService;
    }

    public void setCentreService(CentreService centreService) {
        this.centreService = centreService;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public StatutBC getStatutBC() {
        return statutBC;
    }

    public void setStatutBC(StatutBC statutBC) {
        this.statutBC = statutBC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BonCommande)) return false;
        BonCommande that = (BonCommande) o;
        return Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("numero", numero)
                .append("libelle", libelle)
                .append("montant", montant)
                .toString();
    }
}
