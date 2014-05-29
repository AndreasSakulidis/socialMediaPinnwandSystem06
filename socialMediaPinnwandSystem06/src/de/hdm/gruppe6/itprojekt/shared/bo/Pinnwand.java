package de.hdm.gruppe6.itprojekt.shared.bo;

import java.util.ArrayList;
import java.util.Vector;
/**
 * @author Ezgi Demirbilek, Özlem Gül, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
 * In Anlehnung an Hr. Prof. Dr. Thies

 * Realisierung einer exemplarischen Pinnwandbeschreibung. 
 
 */

public class Pinnwand extends BusinessObject {

	
	private static final long serialVersionUID = 1L;

	private User user = new User(); 
	
	private String eigentuemer = user.getNickname();

	private ArrayList<Abonnement> abo = new ArrayList<Abonnement>();
	
	private ArrayList<Textbeitrag> textbeitrag= new ArrayList<Textbeitrag>();

	/** Konstruktoren
	 * 
	 * @param abo
	 * @param textbeitrag
	 * @param user
	 */
	public Pinnwand(ArrayList<Abonnement> abo, ArrayList<Textbeitrag> textbeitrag, User user) {
		this.user= user;
		this.textbeitrag=textbeitrag;
		this.abo=abo;
	}
	
	public Pinnwand(){
		
	}



/**
 * Auslesen des Abonnements
 * @return
 */
public ArrayList<Abonnement> getAbo() {
	return abo;
}
/**
 * Setzen des Abonnements
 * @param abo
 */
public void setAbonnement(ArrayList<Abonnement> abo) {
	this.abo = abo
			;
}
/** 
 * Auslesen des Eigentümers
 * @return
 */

public String getEigentuemer() {
	return eigentuemer;
}

/**
 * Setzen des Eigentümers
 * @param eigentuemer
 */
public void setEigentuemer(String eigentuemer) {
	this.eigentuemer = eigentuemer;
}
/**
 * Auslesen des Textbeitrags
 * @return
 */
	
	public ArrayList<Textbeitrag> getTextbeitrag() {
		return textbeitrag;
	}

}
