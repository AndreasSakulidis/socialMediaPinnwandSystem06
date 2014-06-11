package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.gruppe6.itprojekt.shared.bo.Pinnwand;
import de.hdm.gruppe6.itprojekt.shared.bo.User;
/**
 * @author Bharti Kumar, �zlem G�l, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 *  In Anlehnung an Hr. Prof. Dr. Thies
 * Die Klasse PinnwandMapper bildet die Pinnwand-Objekte auf eine relationale Datenbank ab.
 *  
 */

public class PinnwandMapper {
	/**
	   * Die Klasse KommentarMapper wird nur einmal instantiiert. 
	   * 
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
	   * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see pinnwandMapper()
	   */

	private static PinnwandMapper pinnwandMapper = null;
	 /**
	   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected PinnwandMapper() {
	}
	/**
	   * Diese statische Methode kann aufgrufen werden durch
	   * <code>PinnwandMapper.pinnwandMapper()</code>. Sie stellt die
	   * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine einzige
	   * Instanz von <code>PinnwandMapper</code> existiert.
	   * <p>
	   * 
	   * <b>Fazit:</b> PinnwandMapper sollte nicht mittels <code>new</code>
	   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	   * 
	   * @return DAS <code>PinnwandMapper</code>-Objekt.
	   * @see pinnwandMapper
	   */
	public static PinnwandMapper pinnwandMapper() {
		if (pinnwandMapper == null) {
			pinnwandMapper = new PinnwandMapper();
		}
		return pinnwandMapper;
	}
	/**
	 * Methode um eine Pinnwand in die Datenbank anzulegen.
	 * @param pinnwand
	 * @return
	 * @throws Exception
	 */


	public Pinnwand anlegen(Pinnwand pinnwand, User eigentuemer)
			throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(PinnwandID) AS maxid "
					+ "FROM pinnwand");

			if (rs.next()) {
				pinnwand.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				Timestamp tmstamp = new Timestamp(System.currentTimeMillis());
				stmt.executeUpdate("INSERT INTO pinnwand (PinnwandID, ErstellungsZeitpunkt, Eigentuemer)"
						+ "VALUES ('"
						+ pinnwand.getId()
						+ "','"
						+ tmstamp
						+ "','" 
						+ eigentuemer.getNickname() + "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} 
			finally {
//			DBVerbindung.closeAll(null, stmt, con);
		}
		return pinnwand;
	}
	/**
	 * Methode um einen Datensatz in der Datenbank zu editieren
	 * 
	 * @param pinnwand
	 * @return 
	 * @throws Exception
	 */

	public Pinnwand editieren(Pinnwand pinnwand, User eigentuemer)
			throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("UPDATE pinnwand "
					+ "SET ErstellungsZeitpunkt =\""
					+ pinnwand.getErstellungsZeitpunkt() + "\","
					+ "Eigentuemer=\"" + eigentuemer.getNickname()
					+ "\" WHERE PinnwandID=" + pinnwand.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return pinnwand;
	}
	/**
	 * Methode um einen Datensatz aus der Datenbank zu l�schen
	 * 
	 * @param pinnwand
	 * @return
	 * @throws Exception
	 */

	public void loeschen(Pinnwand pinnwand) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM pinnwand " + "WHERE PinnwandID="
					+ pinnwand.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return;
	}
	 /** 
	  * Methode mit der man eine Pinnwand �ber ihre ID finden kann.
	   * @param pinnwandID
	   * @return Pinnwand-Objekt
	   * @throws Exception
	   */

	public Pinnwand findeAnhandID(int pinnwandID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT PinnwandID, ErstellungsZeitpunkt, Eigentuemer"
							+ "FROM pinnwand"
							+ "WHERE PinnwandID="
							+ pinnwandID + " ORDER BY ErstellungsZeitpunkt");

			if (rs.next()) {
				Pinnwand pinnwand = new Pinnwand();
				pinnwand.setId(rs.getInt("PinnwandID"));
				pinnwand.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));
				pinnwand.setEigentuemer(rs.getString("Eigentuemer"));

				return pinnwand;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}

		return null;
	}
	 /** 
	  * Alle Datens�tze aus der Tabelle Pinnwand werden herausgelesen.
	   * @return 
	   * @throws Exception
	   */

	public Vector<Pinnwand> findeAlle() throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		ResultSet rs = null;

		Vector<Pinnwand> result = new Vector<Pinnwand>();

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT * FROM pinnwand "
					+ "ORDER BY PinnwandID");

			while (rs.next()) {
				Pinnwand pinnwand = new Pinnwand();
				pinnwand.setId(rs.getInt("PinnwandID"));
				pinnwand.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));
				pinnwand.setEigentuemer(rs.getString("Eigentuemer"));

				result.addElement(pinnwand);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}

		return result;
	}

	public int findeAnhandUserID(int userID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT PinnwandID "
							+ "FROM user "
							+ "WHERE UserID="
							+ userID);

			if (rs.next()) {
//				Pinnwand pinnwand = new Pinnwand();
//				pinnwand.setId(rs.getInt("PinnwandID"));
//				pinnwand.setErstellungsZeitpunkt(rs
//						.getTimestamp("ErstellungsZeitpunkt"));
//				pinnwand.setEigentuemer(rs.getString("Eigentuemer"));
				int pinnwand = rs.getInt("PinnwandID");
				return pinnwand;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} 
//		finally {
//			DBVerbindung.closeAll(rs, stmt, con);
//		}

		return 0;
	}

	public int findePinnwandIDAnhandNickname(String nickname) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT PinnwandID "
							+ "FROM pinnwand "
							+ "WHERE Eigentuemer='"
							+ nickname + "'");

			if (rs.next()) {
//				Pinnwand pinnwand = new Pinnwand();
//				pinnwand.setId(rs.getInt("PinnwandID"));
//				pinnwand.setErstellungsZeitpunkt(rs
//						.getTimestamp("ErstellungsZeitpunkt"));
//				pinnwand.setEigentuemer(rs.getString("Eigentuemer"));
				int pinnwand = rs.getInt("PinnwandID");
				return pinnwand;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} 
//		finally {
//			DBVerbindung.closeAll(rs, stmt, con);
//		}

		return 0;
	}
	
	public ArrayList<Integer> findePinnwandIDAnhandUserID(int userID) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<Integer> result = new ArrayList<Integer>();

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT PinnwandID "
							+ "FROM abonnement "
							+ "WHERE UserID="
							+ userID);

			while (rs.next()) {
				Integer pinnwandID = new Integer(rs.getInt("PinnwandID"));

				result.add(pinnwandID);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} //finally {
			//DBVerbindung.closeAll(rs, stmt, con);
		//}

		return result;
	}
}
