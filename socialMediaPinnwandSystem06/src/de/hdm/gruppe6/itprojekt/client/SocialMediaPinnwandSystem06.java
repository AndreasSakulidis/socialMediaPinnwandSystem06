package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, Özlem Gül, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse SocialMediaPinnwandSystem06 ist die EntryPointKlasse die die Methode onModuleLoad enthält.
 */

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;



public class SocialMediaPinnwandSystem06 implements EntryPoint {
 
	int zahl = 0;
	SocialMediaFrontend an = new SocialMediaFrontend();
	Anmelden a = new Anmelden();
	public VerticalPanel mainPanel = new VerticalPanel();
	private PinnwandVerwaltungServiceAsync socialmedia = GWT.create(PinnwandVerwaltungService.class);



	public void onModuleLoad() {

		try{
		String cookie=Cookies.getCookie("SocialMedia6");
		System.out.println("On Module Load cookie: "+cookie);
		if(!cookie.equals(null)){
//			TODO Id von User rausziehen und es dem Pinnwand zuweisen und aufrufen. 
		an.angemeldet();
		mainPanel.clear();
		PinnwandForm pAF = new PinnwandForm();
		//Beitrag b = new Beitrag("");
		
//		mainPanel.add(pAF.zeigePost()); //TODO Hier wird ein Fehler in der Konsole ausgegeben
	//	mainPanel.add(b);
		RootPanel.get("Details").add(mainPanel);
		}

		}
		catch(Exception e){
			a.anmelden();
			

		}
		
		

		
	}
		

	
}