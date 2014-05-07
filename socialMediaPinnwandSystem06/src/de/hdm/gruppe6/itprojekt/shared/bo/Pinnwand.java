package de.hdm.gruppe6.itprojekt.shared.bo;

import java.util.Vector;


public class Pinnwand extends BusinessObject {

	/**
	 * @author �zlem G�l, Michael Schelkle, Bharti Kumar
	 */
	private static final long serialVersionUID = 1L;
	
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

}
