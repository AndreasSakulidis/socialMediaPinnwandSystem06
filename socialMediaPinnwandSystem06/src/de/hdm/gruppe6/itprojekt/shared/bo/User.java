package de.hdm.gruppe6.itprojekt.shared.bo;

import java.util.Vector;

import de.hdm.gruppe6.itprojekt.shared.bo.Abonnement;


/**
 * @autor Özlem Gül, Michael Schelkle, Bharti Kumar
 */

public class User extends BusinessObject {
//Instanzvariable
	private static final long serialVersionUID = 1L;
	
	
	private String vorname;
	private String nachname;
	private String email;
	private String nickname;
	private Vector<Abonnement> abo = new Vector<Abonnement>();
	
	//Konstruktor
	public User(String vorname, String nachname, String email, String nickname, Vector<Abonnement> abo){
		this.vorname=vorname;
		this.nachname=nachname;
		this.email=email;
		this.nickname=nickname;
		this.abo=abo;
	}

	
	public User() {
	// TODO Auto-generated constructor stub
}

	//Alle Set und Get Methoden dieser Klasse
	public String getVorname() {
		return vorname;
	}
	
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public String getNachname() {
		return nachname;
	}
	
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Vector<Abonnement> getAbo() {
		return abo;
	}

	public void setAbonnement(Vector<Abonnement> abo) {
		this.abo = abo;
	}
	
	public String toString() {
	    return super.toString() + " " + this.vorname + " " + this.nachname +" " + this.email + " "+" " + this.nickname + " ";
	  }
		
}
