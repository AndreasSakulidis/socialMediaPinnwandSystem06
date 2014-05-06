package de.hdm.gruppe6.itprojekt.shared.bo;

import java.util.ArrayList;
import java.util.Vector;


public class Pinnwand extends BusinessObject {

	/**
	 * @author �zlem G�l, Michael Schelkle, Bharti Kumar
	 */
	private static final long serialVersionUID = 1L;

	private User user = new User(); 
	
	private String eigentuemer = user.getNickname();

	private ArrayList<Abonnement> abo = new ArrayList<Abonnement>();
	
	private ArrayList<Textbeitrag> textbeitrag= new ArrayList<Textbeitrag>();

	// Konstruktoren
	public Pinnwand(ArrayList<Abonnement> abo, ArrayList<Textbeitrag> textbeitrag, User user) {
		this.user= user;
		this.textbeitrag=textbeitrag;
		this.abo=abo;
	}
	
	public Pinnwand(){
		
	}



//Getter und Setter
public ArrayList<Abonnement> getAbo() {
	return abo;
}

public void setAbonnement(ArrayList<Abonnement> abo) {
	this.abo = abo
			;
}

public String getEigentuemer() {
	return eigentuemer;
}

public void setEigentuemer(String eigentuemer) {
	this.eigentuemer = eigentuemer;
}

	
	public ArrayList<Textbeitrag> getTextbeitrag() {
		return textbeitrag;
	}

}
