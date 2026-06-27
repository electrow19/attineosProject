package fr.attineos.biblio.controllers;

import java.util.List;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import fr.attineos.biblio.service.LivreService;
import fr.attineos.biblio.models.Livre;

@Controller
@RequestMapping("/livres")
class LivreController {
    LivreService service;
    
    public LivreController(LivreService service) {
        this.service = service;
    }
    @GetMapping("/")
    public String getAllLivres(Model model) { 
        List<Livre> livres = service.getAllLivres();
        model.addAttribute("livres", livres);
        return "livres";
    }

    @GetMapping("/update/{id}")
    public String editLivre(@PathVariable Long id, Model model) {
        Livre livre = service.getLivreById(id);
        model.addAttribute("livre", livre);
        return "update-livre";
    }
    
    @PostMapping("/add")
    public String addLivre(@RequestParam String titre, @RequestParam String auteur, @RequestParam LocalDate datePublication, @RequestParam String synopsis, @RequestParam String isbn) {
        service.createLivre(titre, auteur, datePublication, synopsis, isbn);
        return "redirect:/livres/";
    }

    @PostMapping("/delete/{id}")
    public String deleteLivre(@PathVariable Long id) {
        service.deleteLivre(id);
        return "redirect:/livres/";
    }

    @PostMapping("/update-book/{id}")
    public String updateLivre(@PathVariable Long id, @RequestParam String titre, @RequestParam String auteur, @RequestParam LocalDate dateParution, @RequestParam String synopsis, @RequestParam String isbn) {
        service.updateLivre(id, titre, auteur, dateParution, synopsis, isbn);
        return "redirect:/livres/";
    }
}