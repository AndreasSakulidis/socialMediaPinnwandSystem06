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
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Verwaltung von Pinnwänden.
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
	public User userEditieren(User user)throws Exception;
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
	/**
	 * Löschen des Users aus der Datenbank.
	 */

	public void userLoeschen(User user) throws Exception;
	 /**
	  * Zählen der Textbeiträge von einem User.
	  */
	public int zaehleTextbeitraegeVonUser(User user) throws Exception;
	 /**
	  * Finden von Textbeitrag anhand von User.
	  */
	
	public Vector<Textbeitrag> findeTextbeitragAnhandVonUser(User user) throws Exception;
	 /**
	  * Zählen der Abonnements des Users.
	  */
	public int zaehleAbosVonUser(User user) throws Exception;
	 /**
	  * Zählen der Kommentare von einem User.
	  */
	
	public int zaehleKommentareVonUser(User user) throws Exception;
	/**
	  * Finden von Abonnenten des Users.
	  */
	public ArrayList<User> findeAbosAnhandUser(User user) throws Exception;

	/**
	 * Anlegen einer Pinnwand in der Datenbank.
	 */

	public Pinnwand pinnwandAnlegen(Pinnwand pinnwand, User eigentuemer) throws Exception;
	 /**
	  * Bearbeitung des Pinnwands. 
	  */
	public Pinnwand pinnwandEditieren(Pinnwand pinnwand, User eigentuemer)throws Exception;
	/**
	 * Finden von allen Pinnwänden.
	 */
	public Vector<Pinnwand> findeAllePinnwaende() throws Exception;
	/**
	 * Finden einer Pinnwand anhand seiner ID.
	 */
	public Pinnwand findePinnwandAnhandID(int pinnwandID) throws Exception;
	/**
	 * Löschung des Pinnwands.
	 */
	public void pinnwandLoeschen(Pinnwand pinnwand, User eigentuemer) throws Exception;

	/**
	 * Anlegen eines Abonnements.
	 */
	public Abonnement aboAnlegen(User user, Pinnwand pinnwand) throws Exception; 
	/**
	 * Löschen des Abonnements.
	 */

	public void aboLoeschen(Abonnement abonnement) throws Exception;
	/**
	 * Finden eines Abonnements anhand seiner ID.
	 */
	public Abonnement findeAboAnhandID (int abonnementID) throws Exception;
 
 

	/**
	 * Anlegen eines Kommentars in der Datenbank.
	 */
	public Kommentar kommentarAnlegen(String text) throws Exception;
	/**
	 * Bearbeitung des Kommentars.
	 */
	public Kommentar kommentarEditieren(String text, int id) throws Exception;
	/**
	 * Löschen des Kommentars.
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

	public Textbeitrag textbeitragAnlegen(String text) throws Exception ;
	/**
	 * Bearbeitung eines Textbeitrags.
	 */
	public Textbeitrag textbeitragEditieren(String text, int id) throws Exception;
	/**
	 * Löschen eines Textbeitrags.
	 */
	public void textbeitragLoeschen(String text, int id) throws Exception ;
	/**
	 * Finden eines Textbeitrags anhand seiner ID.
	 */
		
	public Textbeitrag findeTextbeitragAnhandID (int textbeitragID) throws Exception;
	 /**
	  * Finden von Kommentaren zu Textbeiträgen.
	  */
	public Vector <Kommentar> findeKommentareZuTextbeitrag( Textbeitrag textbeitrag) throws Exception;
	/**
	  * Zählen von Likes zu einem Textbeitrag.
	  */
	public int zaehleLikesZuTextbeitrag(Textbeitrag textbeitrag) throws Exception;
	/**
	 * Finden von allen Textbeiträgen.
	 */
	public Vector <Textbeitrag> findeAlleTextbeitraege() throws Exception;
		
	   /**
     * Finden von User zu einem Textbeitrag.
     */
	public User findeUserZuTextbeitrag(Textbeitrag textbeitrag) throws Exception;
	  /**
     * Zählen von Kommentaren zu einem Textbeitrag.
     */
	public int zaehleKommentareVonTextbeitrag(Textbeitrag textbeitrag) throws Exception;
	 
					
    
/**
* 	Anlegen einer Like in der Datenbank.
*/

	public Like likeAnlegen() throws Exception;
	/**
     * Löschen einer Like.
     */

	public void likeLoeschen(Like like) throws Exception;
	/**
 	 * Finden einer Like anhand seiner ID.
 	 */
		 	
	public Like findeLikeAnhandID (int likeID) throws Exception; 	 
		 	
		 	
}
