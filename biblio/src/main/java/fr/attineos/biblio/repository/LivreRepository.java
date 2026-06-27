package fr.attineos.biblio.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.attineos.biblio.models.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long>{
	List<Livre> findAll();

	Livre findByIsbn(String isbn);

	List<Livre> findByTitreContainingIgnoreCaseOrAuteurContainingIgnoreCase(String titre, String auteur);
}
