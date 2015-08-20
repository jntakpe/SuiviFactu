package com.github.jntakpe.sf.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entité définissant un utilisateur
 *
 * @author jntakpe
 */
@Entity
public class Utilisateur extends GenericDomain {

    @Email
    @Size(max = 100)
    @Column(length = 100, unique = true)
    private String email;

    @Size(max = 50)
    @Column(length = 50)
    private String prenom;

    @Size(max = 50)
    @Column(length = 50)
    private String nom;

    @Column(length = 10)
    private String telephone;

    @ManyToMany
    @JoinTable(name = "utilisateur_role",
            joinColumns = {@JoinColumn(name = "utilisateur_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_nom", referencedColumnName = "nom")})
    private Set<Role> roles = new HashSet<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("telephone", telephone)
                .append("nom", nom)
                .append("prenom", prenom)
                .append("email", email)
                .toString();
    }
}
