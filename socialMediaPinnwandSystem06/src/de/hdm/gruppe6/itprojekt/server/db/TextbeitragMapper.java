package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.gruppe6.itprojekt.shared.bo.Kommentar;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;

/**
 * @author Bharti Kumar, �zlem G�l, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 *  In Anlehnung an Hr. Prof. Dr. Thies
 * Die Klasse TextbeitragMapper bildet die Textbeitrag-Objekte auf eine relationale Datenbank ab.
 *  
 */

public class TextbeitragMapper {
	/**
	   * Die Klasse TextbeitragMapper wird nur einmal instantiiert. 
	   * 
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
	   * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see textbeitragMapper()
	   */

	private static TextbeitragMapper textbeitragMapper = null;
	 /**
	   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */


	protected TextbeitragMapper() {
	}
	/**
	   * Diese statische Methode kann aufgrufen werden durch
	   * <code>TextbeitragMapper.textbeitragMapper()</code>. Sie stellt die
	   * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine einzige
	   * Instanz von <code>TextbeitragMapper</code> existiert.
	   * <p>
	   * 
	   * <b>Fazit:</b> TextbeitragMapper sollte nicht mittels <code>new</code>
	   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	   * 
	   * @return DAS <code>TextbeitragMapper</code>-Objekt.
	   * @see textbeitragMapper
	   */

	public static TextbeitragMapper textbeitragMapper() {
		if (textbeitragMapper == null) {
			textbeitragMapper = new TextbeitragMapper();
		}
		return textbeitragMapper;
	}
	
	/**
	 * Methode um einen Textbeitrag in die Datenbank anzulegen.
	 * @param textbeitrag
	 * @return
	 * @throws Exception
	 */

	public Textbeitrag anlegen(Textbeitrag textbeitrag, int uid, int pid) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			/*
			 * Zun�chst schauen wir nach, welches der momentan h�chste
			 * Prim�rschl�sselwert ist.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT MAX(TextbeitragID) AS maxid "
							+ "FROM textbeitrag ");

			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * a erh�lt den bisher maximalen, nun um 1 inkrementierten
				 * Prim�rschl�ssel.
				 */
				textbeitrag.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				Timestamp tmstamp = new Timestamp(System.currentTimeMillis());
				stmt.executeUpdate("INSERT INTO textbeitrag (TextbeitragID, Text, UserID, ErstellungsZeitpunkt, PinnwandID)"
						+ "VALUES ('"
						+ textbeitrag.getId()
						+ "','"
						+ textbeitrag.getText()
						+ "','"
						+ uid
						+ "','"
						+ tmstamp
						+ "','" + pid + "') ");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		// finally {
		// DBVerbindung.closeAll(null, stmt, con);
		// }
		return textbeitrag;
	}
	/**
	 * Methode um einen Datensatz in der Datenbank zu editieren
	 * 
	 * @param textbeitrag
	 * @return 
	 * @throws Exception
	 */
	 public Textbeitrag editieren(Textbeitrag textbeitrag) throws Exception {
	 Connection con = DBVerbindung.connection();
	 Statement stmt = null;
	 Timestamp tmstamp = new Timestamp(System.currentTimeMillis());
	 String sql = "UPDATE textbeitrag SET ErstellungsZeitpunkt ='"+ tmstamp+"', Text ='" + textbeitrag.getText() + "' WHERE TextbeitragID="+ textbeitrag.getId();
	 try {
	 stmt = con.createStatement();
	 stmt.executeUpdate(sql);

	 } catch (SQLException e2) {
	 e2.printStackTrace();
	 throw new Exception("Datenbank fehler!" + e2.toString());
	 }

	 // finally {
	 // DBVerbindung.closeAll(null, stmt, con);
	 // }
	 return textbeitrag;
	 }

		/**
		 * Methode um einen Datensatz aus der Datenbank zu l�schen
		 * 
		 * @param textbeitrag
		 * @return
		 * @throws Exception
		 */
	public void loeschen(Textbeitrag textbeitrag) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM textbeitrag "
					+ "WHERE TextbeitragID=" + textbeitrag.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}

		// finally {
		// DBVerbindung.closeAll(null, stmt, con);
		// }
		return;
	}
	 /** 
	  * Methode mit der man ein Textbeitrag �ber ihre ID finden kann.
	   * @param textbeitragID
	   * @return Textbeitrag-Objekt
	   * @throws Exception
	   */
	public Textbeitrag findeAnhandID(int textbeitragID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT TextbeitragID, ErstellungsZeitpunkt"
					+ "FROM textbeitrag" + "WHERE TextbeitragID="
					+ textbeitragID + " ORDER BY TextbeitragID");

			if (rs.next()) {
				Textbeitrag textbeitrag = new Textbeitrag();
				textbeitrag.setId(rs.getInt("TextbeitragID"));
				textbeitrag.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));

				return textbeitrag;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}

		return null;
	}

	public ArrayList<Integer> findeTextbeitragIDsAnhandPinnwandID(int pinnwandID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		ArrayList<Integer> result = new ArrayList<Integer>();
		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT TextbeitragID "
					+ "FROM textbeitrag" + "WHERE PinnwandID="
					+ pinnwandID 
					+ " ORDER BY ErstellungsZeitpunkt DESC");

			while (rs.next()) {
				Integer textbeitragID = new Integer(rs.getInt("TextbeitragID"));

				result.add(textbeitragID);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} 
//		finally {
//			DBVerbindung.closeAll(rs, stmt, con);
//		}

		return result;
	}
	 /** 
	  * Alle Datens�tze aus der Tabelle Textbeitrag werden herausgelesen.
	   * @return 
	   * @throws Exception
	   */
	public Vector<Textbeitrag> findeAlle() throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		ResultSet rs = null;

		Vector<Textbeitrag> result = new Vector<Textbeitrag>();

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT * FROM textbeitrag "
					+ "ORDER BY TextbeitragID");

			while (rs.next()) {
				Textbeitrag textbeitrag = new Textbeitrag();
				textbeitrag.setId(rs.getInt("TextbeitragID"));
				textbeitrag.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));

				result.addElement(textbeitrag);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}

		return result;
	}
	 /** 
	  * Alle Kommentare zu einem Textbeitrag werden gefunden.
	  * @param textbeitrag
	   * @return 
	   * @throws Exception
	   */
	public ArrayList<Kommentar> findeKommentareZuTextbeitrag(
			Textbeitrag textbeitrag) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		ArrayList<Kommentar> result = new ArrayList<Kommentar>();

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT kommentar.KommentarID, kommentar.Text, kommentar.ErstellungsZeitpunkt "
							+ "FROM textbeitrag INNER JOIN kommentar ON textbeitrag.TextbeitragID = kommentar.TextbeitragID "
							+ "WHERE textbeitrag.TextbeitragID ="+ textbeitrag.getId());

			while (rs.next()) {
				Kommentar kommentar = new Kommentar();
				kommentar.setId(rs.getInt("kommentar.KommentarID"));
				kommentar.setErstellungsZeitpunkt(rs
						.getTimestamp("kommentar.ErstellungsZeitpunkt"));
				kommentar.setText(rs.getString("kommentar.Text"));
//				kommentar.setId(rs.getInt("TextbeitragID"));
				System.out.println("Kommentarobjekt : "+kommentar.getText() + "Kommentar ID: " + kommentar.getId());
				result.add(kommentar); 
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		// finally {
		// DBVerbindung.closeAll(rs, stmt, con);
		// }
		return result;
	}
	 /** 
	  * Alle Likes zu einem Textbeitrag werden gez�hlt.
	  * @param textbeitrag
	   * @return 
	   * @throws Exception
	   */
	public int zaehleLikesZuTextbeitrag(int tid)
			throws Exception {
		Connection con = DBVerbindung.connection();
//		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			int AnzahlLikes = 0;

			ResultSet rs = stmt
					.executeQuery("SELECT COUNT(TextbeitragID) AS AnzahlLikes FROM liken WHERE TextbeitragID = "
							+ tid);
			
			if(rs.next()){
				   AnzahlLikes = rs.getInt("AnzahlLikes");
				   
				}
			
			return AnzahlLikes;

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} 
//		finally {
//			DBVerbindung.closeAll(rs, stmt, con);
//		}
		

	}
	/** 
	  * Alle Kommentare zu einem Textbeitrag werden gez�hlt.
	  * @param textbeitrag
	   * @return 
	   * @throws Exception
	   */
	public int zaehleKommentareVonTextbeitrag(Textbeitrag textbeitrag)
			throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT COUNT(TextbeitragID) AS AnzahlKommentare FROM kommentar WHERE TextbeitragID = "
							+ textbeitrag.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());

		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}
		return rs.getInt("AnzahlKommentare");
	}
	/** 
	  * Alle User zu einem Textbeitrag werden gefunden.
	  * @param textbeitrag
	   * @return 
	   * @throws Exception
	   */

	public User findeUserZuTextbeitrag(Textbeitrag textbeitrag)
			throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT user.Vorname, user.Nachname, textbeitrag.Text FROM user INNER JOIN textbeitrag ON user.UserID = textbeitrag.UserID WHERE textbeitrag.TextbeitragID = "
							+ textbeitrag.getId());

//			Textbeitrag t = new Textbeitrag();
//			t.setNameUser(rs.getString("User.Vorname")+" "+rs.getString("Nachname"));
			User user = new User();
			user.setId(rs.getInt("TextbeitragID"));
			user.setVorname(rs.getString("User.Vorname"));
			user.setNachname(rs.getString("User.Nachname"));
			
			

			return user;

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());

		} 
//			finally {
//			DBVerbindung.closeAll(rs, stmt, con);
//		}
	}
	
	//SELECT UserID FROM textbeitrag WHERE TextbeitragID
	
	public int findeUserZuTextbeitragID(int textbeitragID )
			throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		int result = 0;
		
		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT UserID FROM textbeitrag WHERE TextbeitragID = "
							+ textbeitragID);
			
			if(rs.next()){
				result = rs.getInt("UserID");
				   
				}

			return result;

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());

		} 
//			finally {
//			DBVerbindung.closeAll(rs, stmt, con);
//		}
	}
	
	
	
	
	
	public ArrayList<Textbeitrag> findeAlleUserBeitraege(int userID)
			throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<Textbeitrag> result = new ArrayList<Textbeitrag>();

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT * FROM textbeitrag "
					+ "WHERE UserID =" + userID
					+ " ORDER BY ErstellungsZeitpunkt DESC");

			while (rs.next()) {
				Textbeitrag textbeitrag = new Textbeitrag();
				textbeitrag.setId(rs.getInt("TextbeitragID"));
				textbeitrag.setText(rs.getString("Text"));
				textbeitrag.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));

				result.add(textbeitrag);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		// finally {
		// DBVerbindung.closeAll(rs, stmt, con);
		// }

		return result;
	}
	
	public ArrayList<Textbeitrag> findeAlleUserBeitraegeAnhandPinnwandID(int pinnwandID)
			throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<Textbeitrag> result = new ArrayList<Textbeitrag>();
		
		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT * FROM textbeitrag "
					+ "WHERE PinnwandID =" + pinnwandID
					+ " ORDER BY ErstellungsZeitpunkt DESC");
//			rs = stmt.executeQuery("SELECT * FROM textbeitrag WHERE PinnwandID = 1 OR PinnwandID = 3 ORDER BY ErstellungsZeitpunkt DESC");

			while (rs.next()) {
				Textbeitrag textbeitrag = new Textbeitrag();
				textbeitrag.setId(rs.getInt("TextbeitragID"));
				textbeitrag.setText(rs.getString("Text"));
				textbeitrag.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));

				result.add(textbeitrag);
				System.out.println("TextbeitragMapper: "+textbeitrag.getText()+" Textbeitrag ID "+textbeitrag.getId());
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		// finally {
		// DBVerbindung.closeAll(rs, stmt, con);
		// }

		return result;
	}
	

	
	
}
