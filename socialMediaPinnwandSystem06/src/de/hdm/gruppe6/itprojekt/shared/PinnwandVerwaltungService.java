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
 * @author Ezgi Demirbilek, �zlem G�l, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
 * In Anlehnung an Hr. Prof. Dr. Thies
 * Synchrone Schnittstelle f�r eine RPC-f�hige Klasse zur Verwaltung von Pinnw�nden.
 */

@RemoteServiceRelativePath("pinnwandVerwaltung")
public interface PinnwandVerwaltungService extends RemoteService {
	
	/**
	 * Anlegen eines Users in der Datenbank.
	 */

	public User userAnlegen(String vorname, String nachname, String nickname, String email, String passwort) throws Exception;
	
	/**
	 * Anmeldung des Users mit Name und Passwort.
	 */
	
	public User userAnmelden(String name, String passwort) throws Exception;
	/**
	 * Bearbeitung des Users.
	 */
	public User userEditieren(int id, String vorname,
			String nachname, String nickname, String email, String passwort)throws Exception;
	/**
	 * Finden aller User.
	 */

	public Vector<User> findeAlleUser() throws Exception;
	/**
	 * Finden des Users anhand seiner ID.
	 */

	public User findeUserAnhandID(int userID) throws Exception;

	/**
	  * Finden des Users anhand seiner Nachname.
	  */
	public Vector<User> findeUserAnhandNachname(String nachname) throws Exception;
	
	
	public Vector<User> findeUserAnhandNickname(String nickname) throws Exception;
	
	/**
	 * L�schen des Users aus der Datenbank.
	 */

	public void userLoeschen(int id, String vorname,
			String nachname, String nickname, String email, String passwort) throws Exception;
	 /**
	  * Z�hlen der Textbeitr�ge von einem User.
	  */
	public int zaehleTextbeitraegeVonUser(User user) throws Exception;
	 /**
	  * Finden von Textbeitrag anhand von User.
	  */
	
	public Vector<Textbeitrag> findeTextbeitragAnhandVonUser(User user) throws Exception;
	 /**
	  * Z�hlen der Abonnements des Users.
	  */
	public int zaehleAbosVonUser(User user) throws Exception;
	 /**
	  * Z�hlen der Kommentare von einem User.
	  */
	
	public int zaehleKommentareVonUser(User user) throws Exception;
	
	public ArrayList<User> findeAbosAnhandUser(int uid) throws Exception;

	/**
	 * Anlegen einer Pinnwand in der Datenbank.
	 */

	public Pinnwand pinnwandAnlegen(Pinnwand pinnwand, User eigentuemer) throws Exception;
	 /**
	  * Bearbeitung des Pinnwands. 
	  */
	public Pinnwand pinnwandEditieren(Pinnwand pinnwand, User eigentuemer)throws Exception;
	/**
	 * Finden von allen Pinnw�nden.
	 */
	public Vector<Pinnwand> findeAllePinnwaende() throws Exception;
	/**
	 * Finden einer Pinnwand anhand seiner ID.
	 */
	public Pinnwand findePinnwandAnhandID(int pinnwandID) throws Exception;
	/**
	 * L�schung des Pinnwands.
	 */
	public void pinnwandLoeschen(Pinnwand pinnwand, User eigentuemer) throws Exception;

	public Abonnement aboAnlegen(String uid, int pid) throws Exception; 
	/**
	 * L�schen des Abonnements.
	 */

	public void aboLoeschen(int id) throws Exception;
	/**
	 * Finden eines Abonnements anhand seiner ID.
	 */
	public Abonnement findeAboAnhandID (int abonnementID) throws Exception;
	
	public int findeAboIDAnhandPinnwandUserID(int uid, int pid) throws Exception;
	
	public int findePinnwandIDAnhandNickname(String nickname) throws Exception;
 
 

	/**
	 * Anlegen eines Kommentars in der Datenbank.
	 */
	public Kommentar kommentarAnlegen(String text, String uid, int tid) throws Exception;
	/**
	 * Bearbeitung des Kommentars.
	 */
	public Kommentar kommentarEditieren(String text, int id) throws Exception;
	/**
	 * L�schen des Kommentars.
	 */
	public void kommentarLoeschen(String text, int id) throws Exception;
	/**
	 * Finden eines Kommentars anhand seiner ID.
	 */
	public Kommentar findeKommentarAnhandID (int kommentarID) throws Exception;
	/**
	 * Finden von allen Kommentaren.
	 */
		

	public Vector <Kommentar> findeAlleKommentare() throws Exception;
	/**
	 * Anlegen einer Textbeitrag in der Datenbank.
	 */

	public Textbeitrag textbeitragAnlegen(String text, String id) throws Exception ;
	/**
	 * Bearbeitung eines Textbeitrags.
	 */
	public Textbeitrag textbeitragEditieren(String text, int id) throws Exception;
	/**
	 * L�schen eines Textbeitrags.
	 */
	public void textbeitragLoeschen(String text, int id) throws Exception ;
	/**
	 * Finden eines Textbeitrags anhand seiner ID.
	 */
		
	public Textbeitrag findeTextbeitragAnhandID (int textbeitragID) throws Exception;
	 /**
	  * Finden von Kommentaren zu Textbeitr�gen.
	  */
	public Vector <Kommentar> findeKommentareZuTextbeitrag(int textID) throws Exception;
	/**
	  * Z�hlen von Likes zu einem Textbeitrag.
	  */
	public int zaehleLikesZuTextbeitrag(int tid) throws Exception;
	/**
	 * Finden von allen Textbeitr�gen.
	 */
	public Vector <Textbeitrag> findeAlleTextbeitraege() throws Exception;
		
	   /**
     * Finden von User zu einem Textbeitrag.
     */
	public User findeUserZuTextbeitrag(Textbeitrag textbeitrag) throws Exception;
	  /**
     * Z�hlen von Kommentaren zu einem Textbeitrag.
     */
	public int zaehleKommentareVonTextbeitrag(Textbeitrag textbeitrag) throws Exception;
	 
					
    
/**
* 	Anlegen einer Like in der Datenbank.
*/

	public Like likeAnlegen(String uid, int tid) throws Exception;
	/**
     * L�schen einer Like.
     */

	public void likeLoeschen(Like like) throws Exception;
	/**
 	 * Finden einer Like anhand seiner ID.
 	 */
		 	
	public Like findeLikeAnhandID (int likeID) throws Exception; 	 
	
	public ArrayList<Textbeitrag> findeAlleUserBeitraege(int userID) throws Exception;
		 	
		 	
}
