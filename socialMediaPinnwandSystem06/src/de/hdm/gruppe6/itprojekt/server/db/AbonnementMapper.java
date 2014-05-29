package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import de.hdm.gruppe6.itprojekt.shared.bo.Abonnement;
import de.hdm.gruppe6.itprojekt.shared.bo.Pinnwand;
import de.hdm.gruppe6.itprojekt.shared.bo.User;


/**
 * @author Bharti Kumar, Özlem Gül, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 *  In Anlehnung an Hr. Prof. Dr. Thies
 * Die Klasse AbonnementMapper bildet die Abonnement-Objekte auf eine relationale Datenbank ab.
 *  
 */

public class AbonnementMapper {
	/**
	   * Die Klasse AbonnementMapper wird nur einmal instantiiert. 
	   * 
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see abonnementMapper()
	   */
	
	private static AbonnementMapper abonnementMapper = null;
	  /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected AbonnementMapper() {
	}
	 /**
	   * Diese statische Methode kann aufgrufen werden durch
	   * <code>AbonnementMapper.abonnementMapper()</code>. Sie stellt die
	   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
	   * Instanz von <code>AbonnementMapper</code> existiert.
	   * <p>
	   * 
	   * <b>Fazit:</b> AbonnementMapper sollte nicht mittels <code>new</code>
	   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	   * 
	   * @return DAS <code>AbonnementMapper</code>-Objekt.
	   * @see abonnementMapper
	   */
	public static AbonnementMapper abonnementMapper() {
		if (abonnementMapper == null) {
			abonnementMapper = new AbonnementMapper();
		}
		return abonnementMapper;
	}
	
	/**
	 * Methode um ein Abonnement in die Datenbank anzulegen.
	 * @param abo
	 * @return
	 * @throws Exception
	 */

	public Abonnement anlegen(Abonnement abo) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		try {
			Statement stm = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stm.executeQuery("SELECT MAX(AboID) AS maxid "
					+ "FROM abonnement ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * abo erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				abo.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				int pid = 0;
				int uid = 0;

				Timestamp tmstamp = new Timestamp(System.currentTimeMillis());
				stmt.executeUpdate("INSERT INTO abonnement (AboID, UserID, PinnwandID, ErstellungsZeitpunkt)"
						// + "VALUES ("
						// + abo.getId()
						// + ","
						// + abo.getUser() // TODO OBJEKT? eig-> String oder int
						// (Methodenparameter auch ändern
						// + "','"
						// + abo.getPinnwand() //TODO OBJEKT? eig-> String oder
						// int
						// + "',"
						// + abo.getErstellungsZeitpunkt() + ")");

						+ "VALUES ('"
						+ abo.getId()
						+ "','"
						+ uid
						+ "','"
						+ pid
						+ "','" 
						+ tmstamp + "') ");
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return abo;
	}
	
	/**
	 * Methode um einen Datensatz aus der Datenbank zu löschen
	 * 
	 * @param abonnement
	 * @return
	 * @throws Exception
	 */

	public void loeschen(Abonnement abo) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM abonnement " + "WHERE AboID="
					+ abo.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbankfehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return;
	}
	
	 /** 
	  * Methode mit der man ein Abonnement über ihre ID finden kann.
	   * @param aboID
	   * @return Abonnement-Objekt
	   * @throws Exception
	   */

	public Abonnement findeAnhandID(int aboID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT AboID, User, Pinnwand, ErstellungsZeitpunkt" // TODO
																						// OBJEKT
																						// wird
																						// übergeben
																						// kein
																						// WERT
							+ "WHERE AboID=" + aboID + " ORDER BY AboID");
			// Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt
			// erstellt.
			if (rs.next()) {
				Abonnement abonnement = new Abonnement();
				abonnement.setId(rs.getInt("AboID"));
				abonnement.setUser(((Abonnement) rs).getUser());
				abonnement.setPinnwand(((Abonnement) rs).getPinnwand());
				abonnement.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));

				return abonnement;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}

		return null;
	}

}
