package de.hdm.gruppe6.itprojekt.shared;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.gruppe6.itprojekt.shared.bo.Abonnement;
import de.hdm.gruppe6.itprojekt.shared.bo.Kommentar;
import de.hdm.gruppe6.itprojekt.shared.bo.Like;
import de.hdm.gruppe6.itprojekt.shared.bo.Pinnwand;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;
/**
 * @author Ezgi Demirbilek, Özlem Gül, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
 * In Anlehnung an Hr. Prof. Dr. Thies
 */

@RemoteServiceRelativePath("pinnwandVerwaltung")
public interface PinnwandVerwaltungService extends RemoteService {

	public User userAnlegen(String vorname, String nachname, String nickname, String email, String passwort) throws Exception;
	
	public User userAnmelden(String name, String passwort) throws Exception;

	public User userEditieren(User user)throws Exception;

	public Vector<User> findeAlleUser() throws Exception;

	public User findeUserAnhandID(int userID) throws Exception;

	public Vector<User> findeUserAnhandNachname(String nachname) throws Exception;

	public void userLoeschen(User user) throws Exception;
	
	public int zaehleTextbeitraegeVonUser(User user) throws Exception;
	
	public Vector<Textbeitrag> findeTextbeitragAnhandVonUser(User user) throws Exception;
	
	public int zaehleAbosVonUser(User user) throws Exception;
	
	public int zaehleKommentareVonUser(User user) throws Exception;
	
	public ArrayList<User> findeAbosAnhandUser(User user) throws Exception;

	

	public Pinnwand pinnwandAnlegen(Pinnwand pinnwand, User eigentuemer) throws Exception;

	public Pinnwand pinnwandEditieren(Pinnwand pinnwand, User eigentuemer)throws Exception;

	public Vector<Pinnwand> findeAllePinnwaende() throws Exception;

	public Pinnwand findePinnwandAnhandID(int pinnwandID) throws Exception;

	public void pinnwandLoeschen(Pinnwand pinnwand, User eigentuemer) throws Exception;

	
	public Abonnement aboAnlegen(User user, String id) throws Exception; 

	public void aboLoeschen(Abonnement abonnement) throws Exception;
	
	public Abonnement findeAboAnhandID (int abonnementID) throws Exception;
 
 

 
	public Kommentar kommentarAnlegen(String text, String uid, int tid) throws Exception;

	public Kommentar kommentarEditieren(String text, int id) throws Exception;

	public void kommentarLoeschen(String text, int id) throws Exception;

	public Kommentar findeKommentarAnhandID (int kommentarID) throws Exception;

	public Vector <Kommentar> findeAlleKommentare() throws Exception;


	public Textbeitrag textbeitragAnlegen(String text, String id) throws Exception;

	public Textbeitrag textbeitragEditieren(String text, int id) throws Exception;

	public void textbeitragLoeschen(String text, int id) throws Exception ;

	public Textbeitrag findeTextbeitragAnhandID (int textbeitragID) throws Exception;

	public Vector <Kommentar> findeKommentareZuTextbeitrag( Textbeitrag textbeitrag) throws Exception;

	public int zaehleLikesZuTextbeitrag(Textbeitrag textbeitrag) throws Exception;

	public Vector <Textbeitrag> findeAlleTextbeitraege() throws Exception;

	public User findeUserZuTextbeitrag(Textbeitrag textbeitrag) throws Exception;

	public int zaehleKommentareVonTextbeitrag(Textbeitrag textbeitrag) throws Exception;
	 
	public Vector <Textbeitrag> findeAlleUserBeitraege(int userID) throws Exception; 
					
		     
	public Like likeAnlegen(String uid, int tid) throws Exception;

	public void likeLoeschen(Like like) throws Exception;
		 	
	public Like findeLikeAnhandID (int likeID) throws Exception; 	 
		 	
		 	
}
