package de.hdm.gruppe6.itprojekt.shared.bo;

import java.util.Vector;


public class Pinnwand extends BusinessObject {

	/**
	 * @author Özlem Gül, Michael Schelkle, Bharti Kumar
	 */
	private static final long serialVersionUID = 1L;
	
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
}
