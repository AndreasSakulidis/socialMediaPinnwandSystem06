package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import de.hdm.gruppe6.itprojekt.shared.bo.Like;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;
/**
 * @author Bharti Kumar, Özlem Gül, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 *  In Anlehnung an Hr. Prof. Dr. Thies
 * Die Klasse LikeMapper bildet die Like-Objekte auf eine relationale Datenbank ab.
 *  
 */
public class LikeMapper {
	/**
	   * Die Klasse KommentarMapper wird nur einmal instantiiert. 
	   * 
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see likeMapper()
	   */

	private static LikeMapper likeMapper = null;
	 /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected LikeMapper() {
	}
	/**
	   * Diese statische Methode kann aufgrufen werden durch
	   * <code>LikeMapper.likeMapper()</code>. Sie stellt die
	   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
	   * Instanz von <code>LikeMapper</code> existiert.
	   * <p>
	   * 
	   * <b>Fazit:</b> LikeMapper sollte nicht mittels <code>new</code>
	   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	   * 
	   * @return DAS <code>LikeMapper</code>-Objekt.
	   * @see likeMapper
	   */


	public static LikeMapper likeMapper() {
		if (likeMapper == null) {
			likeMapper = new LikeMapper();
		}
		return likeMapper;
	}

	/**
	 * Methode um ein Like in die Datenbank anzulegen.
	 * @param like
	 * @return
	 * @throws Exception
	 */
	public Like anlegen(Like like) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(LikeID) AS maxid "
					+ "FROM liken");

			if (rs.next()) {
				like.setId(rs.getInt("maxid") + 1);

				int uid = 0;
				int tid = 0;
				Timestamp tmstamp = new Timestamp(System.currentTimeMillis());
				stmt.executeUpdate("INSERT INTO liken (LikeID, UserID, ErstellungsZeitpunkt, TextbeitragID)"
						+ "VALUES ('"
						+ like.getId()
						+ "','"
						+ uid
						+ "','"
						+ tmstamp + "','" + tid + "') ");
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return like;
	}
	/**
	 * Methode um einen Datensatz aus der Datenbank zu löschen
	 * 
	 * @param like
	 * @return
	 * @throws Exception
	 */
	public void loeschen(Like like) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM liken " + "WHERE LikeID="
					+ like.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return;
	}
	/** 
	  * Methode mit der man einen Like über ihre ID finden kann.
	   * @param likeID
	   * @return Like-Objekt
	   * @throws Exception
	   */
	public Like findeAnhandID(int likeID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT LikeID, ErstellungsZeitpunkt"
					+ "FROM liken" + "WHERE LikeID=" + likeID
					+ " ORDER BY TextbeitragID");

			if (rs.next()) {
				Like like = new Like();
				like.setId(rs.getInt("LikeID"));
				like.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));

				return like;
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