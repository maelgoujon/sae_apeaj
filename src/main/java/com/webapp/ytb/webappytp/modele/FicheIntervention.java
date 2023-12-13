package com.webapp.ytb.webappytp.modele;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.webapp.ytb.webappytp.modele.ElementsFiche.Demande;
import com.webapp.ytb.webappytp.modele.ElementsFiche.Intervention;
import com.webapp.ytb.webappytp.modele.ElementsFiche.Maintenance;
import com.webapp.ytb.webappytp.modele.ElementsFiche.NatureIntervention;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "FicheIntervention")
@Entity
@Getter
@Setter
@NoArgsConstructor
@Valid
public class FicheIntervention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private Demande demande;

    @Embedded
    private Intervention intervention;

    @Column
    //@NotNull(message = "Le champ 'dateCreation' ne peut pas être vide")
    private LocalDate dateCreation;

    @Embedded
    private Maintenance maintenance;

    @Column(length = 50)
    @NotNull(message = "Le champ 'etatFiche' ne peut pas être vide")
    private boolean etatFicheFinie;

    @Column(length = 250)
    private String travauxRealises;;

    @Column(length = 250)
    private String travauxNonRealises;

    @Column
    private boolean nouvelleIntervention;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @Column(name = "materiaux")
    private List<String> materiauxOptions;

    @Lob
    @Column(nullable = true)
    private byte[] evaluation;

    @Embedded
    private NatureIntervention natureIntervention;


    //getters
    public String getNomDemandeur() {
        return demande.getNomDemandeur();
    }

    public LocalDate getDateDemande() {
        return demande.getDateDemande();
    }

    public String getLocalisation() {
        return demande.getLocalisation();
    }

    public String getDescription() {
        return demande.getDescription();
    }

    public int getDegreUrgence() {
        return demande.getDegreUrgence();
    }

    public LocalDate getDateIntervention() {
        return intervention.getDateIntervention();
    }

    public int getDureeIntervention() {
        return intervention.getDureeIntervention();
    }





    public FicheIntervention(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        this.materiauxOptions = new ArrayList<>();
        this.demande = new Demande();
        this.intervention = new Intervention();
        this.maintenance = new Maintenance();
    }
    

}