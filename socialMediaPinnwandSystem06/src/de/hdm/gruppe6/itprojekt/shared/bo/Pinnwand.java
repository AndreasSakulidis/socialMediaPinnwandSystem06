package de.hdm.gruppe6.itprojekt.shared.bo;

import java.util.Vector;


public class Pinnwand extends BusinessObject {

	/**
	 * @author �zlem G�l, Michael Schelkle, Bharti Kumar
	 */
	private static final long serialVersionUID = 1L;
	
	//  TODO Pinnwand mit Eigentuemer + getter u setter private User eigentuemer = new User();
	
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
	this.abo = abo;
}

/* TODO public User getEigentuemer() {
	return eigentuemer;
}

public void setEigentuemer(User eigentuemer) {
	this.eigentuemer = eigentuemer;
}
*/
}
