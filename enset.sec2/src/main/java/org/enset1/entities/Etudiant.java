package org.enset1.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;




@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Etudiant implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	private Long idEtudiant;
	
	@NotNull
	@Size(min=3,max=15)
	private String nom;
	@NotNull
	@Size(min=3,max=12)
	private String prenom;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull
	private Date dateNaissance;
	
	
	public Etudiant(String nom, String prenom, Date dateNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}
	
	
	
	
	

}
