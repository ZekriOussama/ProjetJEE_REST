package com.univ.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Emprunt {
	
	Date date;
	long idEmprunt;
	Etudiant etudiant;
	Livre livre;
	
	
	
	public Emprunt(Date date, Etudiant etudiant) {
		super();
		this.date = date;
		this.etudiant = etudiant;
	}

	public Emprunt() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getIdEmprunt() {
		return idEmprunt;
	}
	
	public void setIdEmprunt(long idemprunt) {
		this.idEmprunt = idemprunt;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	@ManyToOne
	@JsonIgnore
	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	
	
}
