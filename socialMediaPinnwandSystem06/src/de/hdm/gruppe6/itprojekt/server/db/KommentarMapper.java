package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;

import de.hdm.gruppe6.itprojekt.shared.bo.*;
/**
 * @author Bharti Kumar, �zlem G�l, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 *  In Anlehnung an Hr. Prof. Dr. Thies
 * Die Klasse KommentarMapper bildet die Kommentar-Objekte auf eine relationale Datenbank ab.
 *  
 */

public class KommentarMapper {
	/**
	   * Die Klasse KommentarMapper wird nur einmal instantiiert. 
	   * 
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
	   * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see kommentarMapper()
	   */
	private static KommentarMapper kommentarMapper = null;
	 /**
	   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */

	protected KommentarMapper() {
	}
	/**
	   * Diese statische Methode kann aufgrufen werden durch
	   * <code>KommentarMapper.kommentarMapper()</code>. Sie stellt die
	   * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine einzige
	   * Instanz von <code>KommentarMapper</code> existiert.
	   * <p>
	   * 
	   * <b>Fazit:</b> KommentarMapper sollte nicht mittels <code>new</code>
	   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	   * 
	   * @return DAS <code>KommentarMapper</code>-Objekt.
	   * @see kommentarMapper
	   */
	public static KommentarMapper kommentarMapper() {
		if (kommentarMapper == null) {
			kommentarMapper = new KommentarMapper();
		}

		return kommentarMapper;
	}
	/**
	 * Methode um ein Kommentar in die Datenbank anzulegen.
	 * @param kommentar
	 * @return
	 * @throws Exception
	 */

	public Kommentar anlegen(Kommentar kommentar, int uid, int tid) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		try {
			Statement stm = con.createStatement();

			/*
			 * Zun�chst schauen wir nach, welches der momentan h�chste
			 * Prim�rschl�sselwert ist.
			 */
			ResultSet rs = stm.executeQuery("SELECT MAX(KommentarID) AS maxid "
					+ "FROM kommentar ");

			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * a erh�lt den bisher maximalen, nun um 1 inkrementierten
				 * Prim�rschl�ssel.
				 */
				kommentar.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();

				Timestamp tmstamp = new Timestamp(System.currentTimeMillis());
				stmt.executeUpdate("INSERT INTO kommentar (KommentarID, Text, UserID, ErstellungsZeitpunkt, TextbeitragID)"
						+ "VALUES ('"
						+ kommentar.getId()
						+ "','"
						+ kommentar.getText()
						+ "','"
						+ uid
						+ "','"
						+ tmstamp
						+ "','" + tid + "') ");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		return kommentar;
	}
	/**
	 * Methode um einen Datensatz in der Datenbank zu editieren
	 * 
	 * @param kommentar
	 * @return 
	 * @throws Exception
	 */
	public Kommentar editieren(Kommentar kommentar) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

			Timestamp tmstamp = new Timestamp(System.currentTimeMillis());
			String sql = "UPDATE kommentar SET ErstellungsZeitpunkt ='"+ tmstamp +"', Text ='" + kommentar.getText() + "' WHERE KommentarID="+ kommentar.getId();

			 try {
			 stmt = con.createStatement();
			 stmt.executeUpdate(sql);
		}

		catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} 
//		finally {
//			DBVerbindung.closeAll(null, stmt, con);
//		}

		return kommentar;
	}
	/**
	 * Methode um einen Datensatz aus der Datenbank zu l�schen
	 * 
	 * @param kommentar
	 * @return
	 * @throws Exception
	 */
	public void loeschen(Kommentar kommentar) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM kommentar " + "WHERE KommentarID="
					+ kommentar.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} 

//		finally {
//			DBVerbindung.closeAll(null, stmt, con);
//		}
		return;
	}
	
	public void kommentarLoeschenAnhandKommentarID(Kommentar kommentar) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM kommentar " + "WHERE KommentarID="
					+ kommentar.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} 

//		finally {
//			DBVerbindung.closeAll(null, stmt, con);
//		}
	
	}
	
	 /** 
	  * Methode mit der man ein Kommentar �ber ihre ID finden kann.
	   * @param kommentarID
	   * @return Kommentar-Objekt
	   * @throws Exception
	   */
	public Kommentar findeAnhandID(int kommentarID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT KommentarID, ErstellungsZeitpunkt"
					+ "FROM kommentar" + "WHERE KommentarID=" + kommentarID
					+ "ORDER BY KommentarID");

			if (rs.next()) {
				Kommentar kommentar = new Kommentar();
				kommentar.setId(rs.getInt("KommentarID"));
				kommentar.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));

				return kommentar;
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}

		finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}

		return null;
		
	}
	
	public Kommentar findeUserIDAnhandKommentarID(int kommentarID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			

			rs = stmt.executeQuery("SELECT UserID "
					+ " FROM kommentar" + " WHERE KommentarID=" + kommentarID
					+ " ORDER BY KommentarID");

			if (rs.next()) {
				Kommentar kommentar = new Kommentar();
				kommentar.setUserID(rs.getInt("UserID"));

				return kommentar;
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}

//		finally {
//			DBVerbindung.closeAll(rs, stmt, con);
//		}

		return null;
	}
	
	public User findeUserAnhandKommentarID(int kommentarID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		 UserMapper userMapper = null;
		userMapper = UserMapper.userMapper();
		try {
			stmt = con.createStatement();
			
			Kommentar kommentar = new Kommentar();
			kommentar= findeUserIDAnhandKommentarID(kommentarID);
			
			System.out.println("USer anhand ID gefunden "+userMapper.findeAnhandID(kommentar.getUserID()).getNickname());
			 return userMapper.findeAnhandID(kommentar.getUserID());

//			rs = stmt.executeQuery("SELECT Nickname "
//					+ " FROM kommentar" + " WHERE KommentarID=" + kommentarID
//					+ " ORDER BY KommentarID");

//			if (rs.next()) {
////				Kommentar kommentar = new Kommentar();
////				kommentar.(rs.getString("Nickname"));
//
//				return kommentar;
//			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}

//		finally {
//			DBVerbindung.closeAll(rs, stmt, con);
//		}

	}

	 /** 
	  * Alle Datens�tze aus der Tabelle Kommentar werden herausgelesen.
	   * @return 
	   * @throws Exception
	   */
	public Vector<Kommentar> findeAlle() throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		ResultSet rs = null;

		Vector<Kommentar> result = new Vector<Kommentar>();

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT * FROM kommentar "
					+ "ORDER BY KommentarID");

			while (rs.next()) {
				Kommentar kommentar = new Kommentar();
				kommentar.setId(rs.getInt("KommentarID"));
				kommentar.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));

				result.addElement(kommentar);
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}

		finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}

		return result;
	}

}