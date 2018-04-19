package com.univ.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
		EntityManager entityManager = emf.createEntityManager();
		
		EntityTransaction tx = entityManager.getTransaction();
		
    	try{
    		
			tx.begin();
			
			Livre livre1 = new Livre();
			livre1.setIsbn(1234);
			livre1.setTitre("titre1");
			livre1.setAuteur("auteur1");
			livre1.setDisponibilité(true);
			
			

			Livre livre2 = new Livre();
			livre2.setTitre("livre2");
			livre2.setAuteur("auteur2");
			livre2.setIsbn(345);
			livre2.setDisponibilité(false);
			
			
			Etudiant etudiant1 = new Etudiant();
			
			Emprunt emprunt1 = new Emprunt();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date beginDate = dateFormat.parse("23/09/2017");
			emprunt1.setDate(beginDate);
			Date endDate = dateFormat.parse("23/10/2017");
			emprunt1.setDate(endDate);
			
			emprunt1.setEtudiant(etudiant1);
			
			livre1.getEmprunts().add(emprunt1);
			emprunt1.setLivre(livre1);
			
			Emprunt emprunt2 = new Emprunt();
			beginDate = dateFormat.parse("23/09/2017");
			emprunt2.setDate(beginDate);
			endDate = dateFormat.parse("23/10/2018");
			emprunt2.setDate(endDate);
				
			emprunt2.setEtudiant(etudiant1);
			
			livre1.getEmprunts().add(emprunt2);
			emprunt2.setLivre(livre2);
			
			etudiant1.getEmprunts().add(emprunt1);
			etudiant1.getEmprunts().add(emprunt2);
			
			
			entityManager.persist(livre1);
			
			

			
			entityManager.persist(livre2);		
			
			tx.commit();			
		
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}
		
	}
}
