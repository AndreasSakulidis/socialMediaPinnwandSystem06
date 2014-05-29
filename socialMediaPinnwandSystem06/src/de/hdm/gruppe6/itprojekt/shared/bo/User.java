package de.hdm.gruppe6.itprojekt.shared.bo;

import java.util.Vector;

import de.hdm.gruppe6.itprojekt.shared.bo.Abonnement;


/**
 * @author Ezgi Demirbilek, Özlem Gül, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
 * In Anlehnung an Hr. Prof. Dr. Thies

 * Realisierung einer exemplarischen Userbeschreibung. 
 
 */

public class User extends BusinessObject {
//Instanzvariable
	private static final long serialVersionUID = 1L;
	
	
	private String vorname;
	private String nachname;
	private String email;
	private String passwort;
	private String nickname;
	private Vector<Abonnement> abo = new Vector<Abonnement>();
	
	/** 
	 * Konstruktor
	 * @param vorname
	 * @param nachname
	 * @param email
	 * @param passwort
	 * @param nickname
	 * @param abo
	 */
	public User(String vorname, String nachname, String email, String passwort, String nickname, Vector<Abonnement> abo){
		this.vorname=vorname;
		this.nachname=nachname;
		this.email=email;
		this.passwort=passwort;
		this.nickname=nickname;
		this.abo=abo;
	}

	/**
	 * Konstruktor
	 */
	public User() {
	super();
}

	/**
	 * Auslesen der Vorname
	 * @return
	 */
	public String getVorname() {
		return vorname;
	}
	/**
	 * Setzen der Vorname
	 * @param vorname
	 */
	
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	/** Auslesen der Nachname
	 * @return
	 */
	
	public String getNachname() {
		return nachname;
	}
	/**
	 * Setzen der Nachname
	 * @param nachname
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	/**
	 * Auslesen der Email
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Setzen der Email
	 */
	
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Auslesen der Nickname
	 * @return
	 */
	
	public String getNickname() {
		return nickname;
	}
	/**
	 * Setzen der Nickname
	 * @param nickname
	 */
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

/**
 * Auslesen des Abonnements
 * @return
 */
	public Vector<Abonnement> getAbo() {
		return abo;
	}
	/**
	 * Setzen des Abonnements
	 * @param abo
	 */

	public void setAbonnement(Vector<Abonnement> abo) {
		this.abo = abo;
	}
	
	/**
	 * textuelle Darstellung erzeugen
	 */
	public String toString() {
	    return super.toString() + " " + this.vorname + " " + this.nachname +" " + this.email + " "+" " + this.passwort + " " + this.nickname + " ";
	  }


	public String getPasswort() {
		return passwort;
	}


	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
	// User Attribute auf Null setzten beim ausloggen
	public void abmelden(){
		vorname = null;
		nachname = null;
		email = null;
		passwort = null;
		nickname = null;
		abo = null;
	}
		
}
