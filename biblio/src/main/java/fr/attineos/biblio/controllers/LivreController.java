package fr.attineos.biblio.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
}