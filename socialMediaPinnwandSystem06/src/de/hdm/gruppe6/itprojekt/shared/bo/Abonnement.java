package de.hdm.gruppe6.itprojekt.shared.bo;


import java.util.*;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
/**
 * @author Ezgi Demirbilek, Özlem Gül, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
 * In Anlehnung an Hr. Prof. Dr. Thies

 * Realisierung einer exemplarischen Abonnementbeschreibung. 
 
 */
 
public class Abonnement extends BusinessObject{
	
	//Instanzvariablen
	private static final long serialVersionUID = 1L;

	private User user;
	private Pinnwand pinnwand;
	
	//Konstruktor
	public Abonnement(User user, Pinnwand pinnwand){
		this.setUser(user);
		this.setPinnwand(pinnwand);	
	}
	
	/**
	 * Konstruktor
	 */
	public Abonnement(){
		super();
		}

	/**
	 * User auslesen
	 * @return
	 */
	public User getUser() {
		return user;
	}
	/**
	 * User setzten
	 * @param user
	 */

	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * Pinnwand auslesen
	 * @return
	 */

	public Pinnwand getPinnwand() {
		return pinnwand;
	}
/**
 * Pinnwand setzen
 * @param pinnwand
 */
	public void setPinnwand(Pinnwand pinnwand) {
		this.pinnwand = pinnwand;
	}


}
