package de.hdm.gruppe6.itprojekt.shared.bo;

import java.util.ArrayList;
import java.util.Vector;


public class Pinnwand extends BusinessObject {

	/**
	 * @author Özlem Gül, Michael Schelkle, Bharti Kumar
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
	
	public User getAbo() {
		return user;
	}
	
	public ArrayList<Textbeitrag> getTextbeitrag() {
		return textbeitrag;
	}
}
