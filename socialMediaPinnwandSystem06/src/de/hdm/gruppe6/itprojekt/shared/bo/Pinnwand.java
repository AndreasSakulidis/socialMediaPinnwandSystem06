package de.hdm.gruppe6.itprojekt.shared.bo;

import java.util.ArrayList;
import java.util.Vector;


public class Pinnwand extends BusinessObject {

	/**
	 * @author �zlem G�l, Michael Schelkle, Bharti Kumar
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	private ArrayList<Abonnement> abo = new ArrayList<Abonnement>();
	private ArrayList<Textbeitrag> textbeitrag= new ArrayList<Textbeitrag>();

	// Konstruktoren
	public Pinnwand(ArrayList<Abonnement> abo, ArrayList<Textbeitrag> textbeitrag, User user) {
		this.user= user;
		this.textbeitrag=textbeitrag;
		this.abo=abo;
	}

	public Pinnwand() {
	}

	// Getter
	public ArrayList<Abonnement> getUser() {
		return abo;
	}
	
<<<<<<< HEAD
	private User user = new User(); 
	
	private String eigentuemer = user.getNickname();

	private Vector<Abonnement> abo = new Vector<Abonnement>();

//Konstruktor
public Pinnwand(Vector<Abonnement> abo){
	this.abo=abo;
}

public Pinnwand(){
}

//Getter und Setter
public Vector<Abonnement> getAbo() {
	return abo;
}

public void setAbonnement(Vector<Abonnement> abo) {
	this.abo = abo
			;
}

public String getEigentuemer() {
	return eigentuemer;
}

public void setEigentuemer(String eigentuemer) {
	this.eigentuemer = eigentuemer;
}

=======
	public User getAbo() {
		return user;
	}
	
	public ArrayList<Textbeitrag> getTextbeitrag() {
		return textbeitrag;
	}
>>>>>>> refs/remotes/origin/Gezim
}
