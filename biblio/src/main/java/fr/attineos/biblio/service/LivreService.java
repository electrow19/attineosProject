package fr.attineos.biblio.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.attineos.biblio.models.Livre;
import fr.attineos.biblio.repository.LivreRepository;

@Service
public class LivreService {
	private LivreRepository repository;
	
	public LivreService(LivreRepository repository) {
		this.repository = repository;
	}
	
	public long createLivre(String titre, String auteur, LocalDate dateParution, String synopsis, String isbn) {
		if (!verifyIsbn(isbn, "create")) {
			return -1;
		}
		try {
			Livre saved = repository.save(new Livre(titre, auteur, dateParution, synopsis, isbn));
			return saved.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
	}

	public long deleteLivre(long id) {
		try {
			repository.deleteById(id);
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public long updateLivre(long id, String titre, String auteur, LocalDate dateParution, String synopsis, String isbn) {
		if (!verifyIsbn(isbn, "update")) {
			return -1;
		}
		try {
			Livre livre = repository.findById(id).orElse(null);
			if (livre == null) {
				return -2;
			}
			livre.setTitre(titre);
			livre.setAuteur(auteur);
			livre.setDateParution(dateParution);
			livre.setSynopsis(synopsis);
			livre.setIsbn(isbn);
			repository.save(livre);
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		}
	}

	public Livre getLivreById(long id) {
		try {
			return repository.findById(id).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Livre> getAllLivres() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Livre> searchLivres(String query) {
		try {
			return repository.findByTitreContainingIgnoreCaseOrAuteurContainingIgnoreCase(query, query);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	private boolean verifyIsbn(String isbn, String operation) {
		if (isbn == null || isbn.length() != 13) {
			return false;
		}
		if (!operation.equals("update") && repository.findByIsbn(isbn) != null) {
			return false;
		}
		for (int i = 0; i < isbn.length(); i++) {
			if (!Character.isDigit(isbn.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
