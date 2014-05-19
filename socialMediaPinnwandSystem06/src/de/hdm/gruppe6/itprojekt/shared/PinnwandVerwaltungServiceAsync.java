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

public interface PinnwandVerwaltungServiceAsync {

	void aboAnlegen(User user, Pinnwand pinnwand,
			AsyncCallback<Abonnement> callback);

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
	
	void kommentarAnlegen(String text, AsyncCallback<Kommentar> callback);

	void kommentarEditieren(Kommentar kommentar,
			AsyncCallback<Kommentar> callback);

	void kommentarLoeschen(Kommentar kommentar, AsyncCallback<Void> callback);

	void likeAnlegen(AsyncCallback<Like> callback);

	void likeLoeschen(Like like, AsyncCallback<Void> callback);

	void pinnwandAnlegen(Pinnwand pinnwand, User eigentuemer, AsyncCallback<Pinnwand> callback);

	void pinnwandEditieren(Pinnwand pinnwand, User eigentuemer, AsyncCallback<Pinnwand> callback);

	void pinnwandLoeschen(Pinnwand pinnwand, User eigentuemer, AsyncCallback<Void> callback);

	void textbeitragAnlegen(String text, AsyncCallback<Textbeitrag> callback);

	void textbeitragEditieren(Textbeitrag textbeitrag,
			AsyncCallback<Textbeitrag> callback);

	void textbeitragLoeschen(Textbeitrag textbeitrag,
			AsyncCallback<Void> callback);

	void userAnlegen(String vorname, String nachname, String nickname,
			String email, AsyncCallback<User> callback);

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
