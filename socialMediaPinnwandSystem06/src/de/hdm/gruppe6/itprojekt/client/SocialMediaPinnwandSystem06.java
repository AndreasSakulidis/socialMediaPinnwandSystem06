package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, Özlem Gül, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse SocialMediaPinnwandSystem06 ist die EntryPointKlasse die die Methode onModuleLoad enthält.
 */

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Cookies;



public class SocialMediaPinnwandSystem06 implements EntryPoint {
 
	int zahl = 0;
	SocialMediaFrontend an = new SocialMediaFrontend();
	Anmelden a = new Anmelden();


	public void onModuleLoad() {

		try{
		String cookie=Cookies.getCookie("SocialMedia6");
		System.out.println("On Module Load cookie: "+cookie);
		if(!cookie.equals(null)){
//			TODO Id von User rausziehen und es dem Pinnwand zuweisen und aufrufen. 
		an.angemeldet();
		}

		}
		catch(Exception e){
			a.anmelden();
			
			

		}

		
	}
		

	
}