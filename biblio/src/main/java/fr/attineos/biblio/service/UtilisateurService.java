package fr.attineos.biblio.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import fr.attineos.biblio.models.Utilisateur;
import fr.attineos.biblio.repository.UtilisateurRepository;
import fr.attineos.biblio.Security.SecurityConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Set;


@Service
public class UtilisateurService {
    UtilisateurRepository utilisateurRepository;

    PasswordEncoder encoder;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder encoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.encoder = encoder;
    }

    public long register(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword) {
        // Vérifier si l'utilisateur existe déjà
        if (utilisateurRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Le nom d'utilisateur est déjà utilisé.");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(username);
        utilisateur.setEmail(email);
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Les mots de passe ne correspondent pas.");
        }
        utilisateur.setPassword(encoder.encode(password));
        utilisateur.setRoles(Set.of("USER"));

        utilisateurRepository.save(utilisateur);
        return utilisateur.getId();
    }
}