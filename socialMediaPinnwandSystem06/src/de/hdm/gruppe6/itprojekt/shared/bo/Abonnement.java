package de.hdm.gruppe6.itprojekt.shared.bo;

/**
 * @author Özlem Gül, Michael Schelkle, Bharti Kumar
 */

import java.util.*;

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
	
	public Abonnement(){
		}

	//Getter und Setter
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Pinnwand getPinnwand() {
		return pinnwand;
	}

	public void setPinnwand(Pinnwand pinnwand) {
		this.pinnwand = pinnwand;
	}

	public void setUserID(int userID) {
		// TODO Auto-generated method stub
		
	}

	public void setPinnwandID(int pinnwandID) {
		// TODO Auto-generated method stub
		
	}	
}
