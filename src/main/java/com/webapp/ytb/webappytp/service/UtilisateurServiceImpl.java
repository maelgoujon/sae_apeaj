package com.webapp.ytb.webappytp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webapp.ytb.webappytp.modele.Utilisateur;
import com.webapp.ytb.webappytp.repository.UtilisateurRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService{

    private final UtilisateurRepository utilisateurRepository; 

    @Override
    public Utilisateur creer(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }
    
    @Override
    public List<Utilisateur> lire() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur modifier(Long id, Utilisateur utilisateur) {
        return utilisateurRepository.findById(id)
        .map(p-> {
            p.setPrenom(utilisateur.getPrenom());
            p.setNom(utilisateur.getNom());
            p.setLogin(utilisateur.getLogin());
            p.setMdp(utilisateur.getMdp());
            p.setPhotoBase64(utilisateur.getPhotoBase64());
            p.setRole(utilisateur.getRole());
            return utilisateurRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        utilisateurRepository.deleteById(id);
        return "Utilisateur supprimé";
    }

    @Override
    public Utilisateur findById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    @Override
    public void modifierMotDePasse(Long id, String nouveauMotDePasse) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + id));

        // Mettez à jour le mot de passe de l'utilisateur
        utilisateur.setMdp(nouveauMotDePasse);

        // Enregistrez les modifications dans la base de données
        utilisateurRepository.save(utilisateur);
    }

}
