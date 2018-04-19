package com.univ.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.univ.model.Livre;
import com.univ.model.Etudiant;
import com.univ.model.Emprunt;

@RestController
public class CarRentalController {
		
	EntityManager entityManager;
	
	public CarRentalController(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
		entityManager = emf.createEntityManager();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/livres", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Livre> listOfLivres(){
		return entityManager.createQuery("select l from Livre l").getResultList();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/livres/{isbn}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Livre aLivre(@PathVariable("isbn") String isbn) throws Exception{
		return (Livre) entityManager.createQuery("select l from Livre l where l.isbn like '" + isbn + "'").getSingleResult();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/livres/{isbn}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Livre emprunt(@PathVariable(name="isbn") String isbn, @RequestParam(name="emprunter", required=true) boolean emprunt) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		try {
			Livre livre = (Livre) entityManager.createQuery("select l from Livre l where l.isbn like :isbn").setParameter("isbn", isbn ).getSingleResult();
			List<Emprunt> emprunts = livre.getEmprunts();
			if(emprunts.size() == 0){
				Emprunt emprunt3 = new Emprunt();
				emprunt3.setDate(Calendar.getInstance().getTime());
				emprunts.add(emprunt3);
				emprunt3.setLivre(livre);
				livre.setDisponibilité(true);
			} else {
				emprunts.remove(0);
				livre.setDisponibilité(false);
			}
			entityManager.persist(livre);
			tx.commit();
			
			return livre;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return null;
	}

}
