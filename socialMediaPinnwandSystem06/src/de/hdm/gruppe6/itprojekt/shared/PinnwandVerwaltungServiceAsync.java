package de.hdm.gruppe6.itprojekt.shared;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.gruppe6.itprojekt.shared.bo.Abonnement;
import de.hdm.gruppe6.itprojekt.shared.bo.Kommentar;
import de.hdm.gruppe6.itprojekt.shared.bo.Like;
import de.hdm.gruppe6.itprojekt.shared.bo.Pinnwand;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;
/**
 * @author Ezgi Demirbilek, �zlem G�l, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
 * In Anlehnung an Hr. Prof. Dr. Thies
 * Diese Klasse ist das asynchrone Gegenst�ck des Interface {@link PinnwandVerwaltungService}. Sie wird automatisch
 * durch das Google Plugin erstellt. F�r weitere Details siehe das synchrone Interface.
 */
public interface PinnwandVerwaltungServiceAsync {

	void aboAnlegen(User user, String id, AsyncCallback<Abonnement> callback);
	

	void aboLoeschen(Abonnement abonnement, AsyncCallback<Void> callback);

	void findeAboAnhandID(int abonnementID, AsyncCallback<Abonnement> callback);

	void findeAbosAnhandUser(User user, AsyncCallback<ArrayList<User>> callback);

	void findeAlleKommentare(AsyncCallback<Vector<Kommentar>> callback);

	void findeAllePinnwaende(AsyncCallback<Vector<Pinnwand>> callback);

	void findeAlleTextbeitraege(AsyncCallback<Vector<Textbeitrag>> callback);

	void findeAlleUser(AsyncCallback<Vector<User>> callback);

	void findeKommentarAnhandID(int kommentarID,
			AsyncCallback<Kommentar> callback);

	void findeKommentareZuTextbeitrag(Textbeitrag textbeitrag,
			AsyncCallback<Vector<Kommentar>> callback);

	void findeLikeAnhandID(int likeID, AsyncCallback<Like> callback);

	void findePinnwandAnhandID(int pinnwandID, AsyncCallback<Pinnwand> callback);

	void findeTextbeitragAnhandID(int textbeitragID,
			AsyncCallback<Textbeitrag> callback);

	void findeUserAnhandID(int userID, AsyncCallback<User> callback);

	

	void zaehleTextbeitraegeVonUser(User user, AsyncCallback<Integer> callback);
	
	void kommentarAnlegen(String text, String uid, int tid, AsyncCallback<Kommentar> callback);

	void kommentarEditieren(String text, int id, AsyncCallback<Kommentar> callback);

	void kommentarLoeschen(String text, int id, AsyncCallback<Void> callback);

	void likeAnlegen(String uid, int tid, AsyncCallback<Like> callback);

	void likeLoeschen(Like like, AsyncCallback<Void> callback);

	void pinnwandAnlegen(Pinnwand pinnwand, User eigentuemer, AsyncCallback<Pinnwand> callback);

	void pinnwandEditieren(Pinnwand pinnwand, User eigentuemer, AsyncCallback<Pinnwand> callback);

	void pinnwandLoeschen(Pinnwand pinnwand, User eigentuemer, AsyncCallback<Void> callback);

	void textbeitragAnlegen(String text, String id, AsyncCallback<Textbeitrag> callback);

	void textbeitragEditieren(String text, int id,
			AsyncCallback<Textbeitrag> callback);

	void textbeitragLoeschen(String text, int id,
			AsyncCallback<Void> callback);

	void userAnlegen(String vorname, String nachname, String nickname,
			String email, String passwort, AsyncCallback<User> callback);
	
	void userAnmelden(String name, String passwort, AsyncCallback<User> callback);

	void userEditieren(User user, AsyncCallback<User> callback);

	void userLoeschen(User user, AsyncCallback<Void> callback);

	void zaehleLikesZuTextbeitrag(Textbeitrag textbeitrag,
			AsyncCallback<Integer> callback);

	void findeTextbeitragAnhandVonUser(User user,
			AsyncCallback<Vector<Textbeitrag>> callback);

	void zaehleAbosVonUser(User user, AsyncCallback<Integer> callback);

	void zaehleKommentareVonUser(User user, AsyncCallback<Integer> callback);

	void findeUserZuTextbeitrag(Textbeitrag textbeitrag,
			AsyncCallback<User> callback);

	void zaehleKommentareVonTextbeitrag(Textbeitrag textbeitrag,
			AsyncCallback<Integer> callback);

	void findeUserAnhandNachname(String nachname,
			AsyncCallback<Vector<User>> callback);
	
	

}
