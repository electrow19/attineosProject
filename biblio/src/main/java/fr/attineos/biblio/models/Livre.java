package fr.attineos.biblio.models;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Livre {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String titre;
	
	private String auteur;
	
	private LocalDate dateParution;
	
	private String synopsis;
	
	@Column(unique = true)
	private String isbn;

	public Livre(String titre, String auteur, LocalDate dateParution, String synopsis, String isbn) {
		this.titre = titre;
		this.auteur = auteur;
		this.dateParution = dateParution;
		this.synopsis = synopsis;
		this.isbn = isbn;
	}
}
