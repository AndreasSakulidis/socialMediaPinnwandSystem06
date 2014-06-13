package de.hdm.gruppe6.itprojekt.shared;

import java.util.ArrayList;
import java.util.List;
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

	void aboAnlegen(String uid, int pid, AsyncCallback<Abonnement> callback);
	

	void aboLoeschen(int id, AsyncCallback<Void> callback);

	void findeAboAnhandID(int abonnementID, AsyncCallback<Abonnement> callback);

	void findeAbosAnhandUser(int uid, AsyncCallback<ArrayList<User>> callback);

	void findeAlleKommentare(AsyncCallback<Vector<Kommentar>> callback);

	void findeAllePinnwaende(AsyncCallback<Vector<Pinnwand>> callback);

	void findeAlleTextbeitraege(AsyncCallback<Vector<Textbeitrag>> callback);

	void findeAlleUser(AsyncCallback<Vector<User>> callback);

	void findeKommentarAnhandID(int kommentarID,
			AsyncCallback<Kommentar> callback);

	void findeKommentareZuTextbeitrag(int textID,
			AsyncCallback<ArrayList<Kommentar>> callback);

	void findeLikeAnhandID(int likeID, AsyncCallback<Like> callback);

	void findePinnwandAnhandID(int pinnwandID, AsyncCallback<Pinnwand> callback);

	void findeTextbeitragAnhandID(int textbeitragID,
			AsyncCallback<Textbeitrag> callback);

	void findeUserAnhandID(int userID, AsyncCallback<User> callback);

	void kommentarAnlegen(String text, String uid, int tid, AsyncCallback<Kommentar> callback);

	void kommentarEditieren(String text, int id, AsyncCallback<Kommentar> callback);

	void kommentarLoeschen(String text, int id, AsyncCallback<Void> callback);

	void likeAnlegen(String uid, int tid, AsyncCallback<Like> callback);

	void likeLoeschen(String uid, int tid, AsyncCallback<Void> callback);

	void pinnwandAnlegen(Pinnwand pinnwand, User eigentuemer, AsyncCallback<Pinnwand> callback);

	void pinnwandEditieren(Pinnwand pinnwand, User eigentuemer, AsyncCallback<Pinnwand> callback);

	void pinnwandLoeschen(Pinnwand pinnwand, User eigentuemer, AsyncCallback<Void> callback);

	void zaehleAbosVonPinnwandAnhandEigentuemer(String eigentuemer,
			AsyncCallback<Integer> callback);

	void zaehleEigeneBeitraegeVonPinnwandAnhandPinnwandID(int pinnwandID,
			AsyncCallback<Integer> callback);

	void zaehleLikesVonPinnwandAnhandPinnwandID(int pinnwandID,
			AsyncCallback<Integer> callback);

	void findePinnwandAnhandEigentuemer(String eigentuemer,
			AsyncCallback<Pinnwand> callback);

	void findeBeliebtestePinnwandJeZeitraum(String anfangsZeitpunkt,
			String endZeitpunkt, AsyncCallback<Pinnwand> callback);

	void textbeitragAnlegen(String text, String id, AsyncCallback<Textbeitrag> callback);

	void textbeitragEditieren(String text, int id,
			AsyncCallback<Textbeitrag> callback);

	void textbeitragLoeschen(String text, int id,
			AsyncCallback<Void> callback);

	void userAnlegen(String vorname, String nachname, String nickname,
			String email, String passwort, AsyncCallback<User> callback);
	
	void userAnmelden(String name, String passwort, AsyncCallback<User> callback);

	void userEditieren(int id, String vorname,
			String nachname, String nickname, String email, String passwort, AsyncCallback<User> callback);

	void userLoeschen(int id, String vorname,
			String nachname, String nickname, String email, String passwort, AsyncCallback<Void> callback);

	void zaehleLikesZuTextbeitrag(int tid,
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


	void findeUserAnhandNickname(String nickname,
			AsyncCallback<Vector<User>> callback);


	void findeAlleUserBeitraege(int userID,
			AsyncCallback<List<Textbeitrag>> callback);
	
	void findeAlleJeZeitraumSortiertNachAnzahlLikes(
			AsyncCallback<Vector<Textbeitrag>> callback);
	
	void findeAlleJeZeitraumSortiertNachAnzahlKommentaren(
			AsyncCallback<Vector<Textbeitrag>> callback);

	void findeAboIDAnhandPinnwandUserID(int uid, int pid,
			AsyncCallback<Integer> callback);


	void findePinnwandIDAnhandNickname(String nickname,
			AsyncCallback<Integer> callback);


	void findePinnwandIDAnhandUserID(int userID,
			AsyncCallback<ArrayList<Integer>> callback);


	void findeTextbeitragIDsAnhandPinnwandID(int pinnwandID,
			AsyncCallback<ArrayList<Integer>> callback);
	
	void findeTextbeitragMitMeistenLikes(String anfangsZeitpunkt,
			String endZeitpunkt, AsyncCallback<Textbeitrag> callback);

	void findeTextbeitragMitMeistenKommentarenJeZeitraum(
			String anfangsZeitpunkt, String endZeitpunkt,
			AsyncCallback<Textbeitrag> callback);

	void zaehleKommentareZuTextbeitragJeZeitraum(Textbeitrag textbeitrag,
			String anfangsZeitpunkt, String endZeitpunkt,
			AsyncCallback<Integer> callback);

	void zaehleLikesZuTextbeitragJeZeitraum(Textbeitrag textbeitrag, String anfangsZeitpunkt,
			String endZeitpunkt, AsyncCallback<Integer> callback);

	void findeUserZuTextbeitragID(int textbeitragID,
			AsyncCallback<String> callback);


	void tZuUserLoeschen(int id, String vorname, String nachname,
			String nickname, String email, String passwort,
			AsyncCallback<Void> callback);


	void kZuUserLoeschen(int id, String vorname, String nachname,
			String nickname, String email, String passwort,
			AsyncCallback<Void> callback);


	void lZuUserLoeschen(int id, String vorname, String nachname,
			String nickname, String email, String passwort,
			AsyncCallback<Void> callback);


	void aZuUserLoeschen(int id, String vorname, String nachname,
			String nickname, String email, String passwort,
			AsyncCallback<Void> callback);


	void kZuTextbeitragLoeschen(String text, int id,
			AsyncCallback<Void> callback);


	void lZuTextbeitragLoeschen(String text, int id,
			AsyncCallback<Void> callback);


	void findeUserIDAnhandTextbeitragID(int textbeitragID,
			AsyncCallback<Textbeitrag> callback);


	void findeUserIDAnhandKommentarID(int kommentarID,
			AsyncCallback<Kommentar> callback);


	void findeUserAnhandKommentarID(int kommentarID,
			AsyncCallback<User> callback);


	void kommentarLoeschenAnhandKommentarID(Kommentar kommentar,
			AsyncCallback<Void> callback);


	void findeAllePinnwaendeJeZeitraum(String anfangsZeitpunkt,
			String endZeitpunkt, AsyncCallback<Vector<Pinnwand>> callback);
	
	

}
