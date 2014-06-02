package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.gruppe6.itprojekt.shared.bo.Pinnwand;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonUserReport;

/**
 * @author Bharti Kumar, �zlem G�l, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 *  In Anlehnung an Hr. Prof. Dr. Thies
 * Die Klasse UserMapper bildet die Kommentar-Objekte auf eine relationale Datenbank ab.
 *  
 */
public class UserMapper {
	/**
	   * Die Klasse UserMapper wird nur einmal instantiiert. 
	   * 
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
	   * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see userMapper()
	   */
	private static UserMapper userMapper = null;
	 /**
	   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */

	protected UserMapper() {
	}
	/**
	   * Diese statische Methode kann aufgrufen werden durch
	   * <code>UserMapper.userMapper()</code>. Sie stellt die
	   * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine einzige
	   * Instanz von <code>UserMapper</code> existiert.
	   * <p>
	   * 
	   * <b>Fazit:</b> UserMapper sollte nicht mittels <code>new</code>
	   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	   * 
	   * @return DAS <code>UserMapper</code>-Objekt.
	   * @see userMapper
	   */
	public static UserMapper userMapper() {
		if (userMapper == null) {
			userMapper = new UserMapper();
		}
		return userMapper;
	}
	/**
	 * Methode um einen User in die Datenbank anzulegen.
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User anlegen(User user, Pinnwand pinnwand) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			/*
			 * Zun�chst schauen wir nach, welches der momentan h�chste
			 * Prim�rschl�sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(UserID) AS maxid "
					+ "FROM user ");

			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * a erh�lt den bisher maximalen, nun um 1 inkrementierten
				 * Prim�rschl�ssel.
				 */
				user.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				Timestamp tmstamp = new Timestamp(System.currentTimeMillis());

				stmt.executeUpdate("INSERT INTO user (UserID, Vorname, Nachname, Email, Passwort, Nickname, ErstellungsZeitpunkt, PinnwandID)"
						+ "VALUES ('"
						+ user.getId()
						+ "','"
						+ user.getVorname()
						+ "','"
						+ user.getNachname()
						+ "','"
						+ user.getEmail()
						+ "','"
						+ user.getPasswort()
						+ "','"
						+ user.getNickname()
						+ "','"
						+ tmstamp
						+ "',"
						+ pinnwand.getId() + ") ");

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		// finally {
		// DBVerbindung.closeAll(null, stmt, con);
		// }
		return user;
	}
	/**
	 * Methode um den Nickname auf Verf�gbarkeit zu pr�fen.
	 * 
	 * @param name
	 * @return 
	 * @throws Exception
	 */

	public boolean nicknamePruefen(String name) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		String nick = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user WHERE Nickname = '"+ name + "'");
			// .executeQuery("SELECT * FROM user WHERE Nickname");
			// WHERE Nickname = '" + name + "'"
			
			//wasNull()
			if(rs.next()){
//				User u = new User();

				nick=(rs.getString("Nickname"));
				if(name.equals(nick)) {
					return false;
				}
				else{
				return true;
				}
			}
//			while (rs.next()) {
//			if(nick.equals(null))	{
//				u.setId(rs.getInt("UserID"));
//				u.setVorname(rs.getString("Vorname"));
//				u.setNachname(rs.getString("Nachname"));
//				u.setNickname(rs.getString("Nickname"));
//				u.setPasswort(rs.getString("Passwort"));
//				u.setEmail(rs.getString("Email"));
//			}
//				return true;
//			}
//
//			else if (name.equals(u.getNickname())) {
//				return false;
//			}

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		return true;
	}

	// // finally {
	// // DBVerbindung.closeAll(rs, stmt, con);
	// // }
	// //
	/**
	 * Methode um den User anzumelden
	 * 
	 * @param name,passwort
	 * @return 
	 * @throws Exception
	 */
	public User anmelden(String name, String passwort) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		System.out.println("Anfang von anmelden passwort: "+passwort+" und Nickname: "+name);
		try {
			 stmt = con.createStatement();

			 ResultSet rs = stmt.executeQuery("SELECT * FROM `user` WHERE Nickname= '"+ name +"'" );
			 
			 System.out.println("UserMapper im Try");
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("UserID"));
				u.setVorname(rs.getString("Vorname"));
				u.setNachname(rs.getString("Nachname"));
				u.setNickname(rs.getString("Nickname"));
				u.setPasswort(rs.getString("Passwort"));
				u.setEmail(rs.getString("Email"));
//				u.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));
				
				System.out.println("in dem ersten if Block passwort von DB: "+u.getPasswort()+" und passwort von Parameter: "+passwort);
				if(u.getPasswort().equals(passwort)){
					System.out.println("Passwort ist richtig...Passwort von DB " + u.getPasswort()+" Passswort von user: " + passwort );
					return u;
				
				}else { 
					System.out.println("Passwort ist falsch...Passwort von Datenbank: " +u.getPasswort());
					return null;
				}

				// System.out.println("erster If User Mapper Name von User: "+ u.getNickname());
			//	System.out.println("erster If User Mapper Passwort von User: "+u.getPasswort());
				
				//return u;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} 
//		Nur ben�tzen, wenn man mit Google SQL Cloud verbidet!!!
//		finally {
//			DBVerbindung.closeAll(rs, stmt, con);
//		}

		return null;
	}
	/**
	 * Methode um einen Datensatz in der Datenbank zu editieren
	 * 
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public User editieren(User user) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		Timestamp tmstamp = new Timestamp(System.currentTimeMillis());
		 String sql = "UPDATE user SET ErstellungsZeitpunkt ='"+ tmstamp+"', Vorname = '" + user.getVorname() + "', Nachname = '" + user.getNachname() + "', Nickname = '" + user.getNickname() + "', Email = '" + user.getEmail() + "', Passwort ='" + user.getPasswort() + "' WHERE UserID="+ user.getId();
		 try {
		 stmt = con.createStatement();
		 stmt.executeUpdate(sql);
		

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} 
		 
//		 finally {
//			DBVerbindung.closeAll(null, stmt, con);
//		}
		 
		return user;
	}
	
	/**
	 * Methode um einen Datensatz aus der Datenbank zu l�schen
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */

	public void loeschen(User user) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			stmt.executeUpdate("DELETE user, textbeitrag, kommentar, liken, pinnwand FROM user, textbeitrag, kommentar, liken, pinnwand " + "WHERE user.UserID=" + user.getId() + " AND textbeitrag.UserID=" + user.getId() + " AND kommentar.UserID=" + user.getId() + " AND liken.UserID=" + user.getId() + " AND pinnwand.PinnwandID=" + user.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} 
//			finally {
//			DBVerbindung.closeAll(null, stmt, con);
//		}
		return;
	}

	public Vector<User> findeAnhandNachname(String nachname) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		Vector<User> result = new Vector<User>();

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT * FROM `user` WHERE `Nachname` = '"
					+ nachname + "'");

			// if(rs.next()){
			// User u = new User();
			// u.setId(rs.getInt("UserID"));
			// u.setVorname(rs.getString("Vorname"));
			// u.setNachname(rs.getString("Nachname"));
			// u.setNickname(rs.getString("Nickname"));
			// u.setEmail(rs.getString("Email"));
			// u.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("UserID"));
				user.setVorname(rs.getString("Vorname"));
				user.setNachname(rs.getString("Nachname"));
				user.setNickname(rs.getString("Nickname"));
				user.setEmail(rs.getString("Email"));
				user.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));

				result.addElement(user);

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
	
	
	public Vector<User> findeAnhandNickname(String nickname) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		Vector<User> result = new Vector<User>();

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT * FROM `user` WHERE `Nickname` = '"
					+ nickname + "'");

			// if(rs.next()){
			// User u = new User();
			// u.setId(rs.getInt("UserID"));
			// u.setVorname(rs.getString("Vorname"));
			// u.setNachname(rs.getString("Nachname"));
			// u.setNickname(rs.getString("Nickname"));
			// u.setEmail(rs.getString("Email"));
			// u.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("UserID"));
				user.setVorname(rs.getString("Vorname"));
				user.setNachname(rs.getString("Nachname"));
				user.setNickname(rs.getString("Nickname"));
				user.setEmail(rs.getString("Email"));
				user.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));

				result.addElement(user);

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
	  * Methode mit der man einen User �ber ihr ID finden kann.
	   * @param userID
	   * @return User-Objekt
	   * @throws Exception
	   */

	public User findeAnhandID(int userID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT UserID, Vorname, Nachname, Nickname, Email, Passwort, ErstellungsZeitpunkt "
							+ "FROM user "
							+ "WHERE UserID="
							+ userID);

			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("UserID"));
				u.setVorname(rs.getString("Vorname"));
				u.setNachname(rs.getString("Nachname"));
				u.setNickname(rs.getString("Nickname"));
				u.setEmail(rs.getString("Email"));
				u.setPasswort(rs.getString("Passwort"));
				u.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));

				return u;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} 
//		finally {
//			DBVerbindung.closeAll(rs, stmt, con);
//		}

		return null;
	}

	 /** 
	  * Alle Datens�tze aus der Tabelle User werden herausgelesen.
	   * @return 
	   * @throws Exception
	   */
	public Vector<User> findeAlle() throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		ResultSet rs = null;

		Vector<User> result = new Vector<User>();

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT * FROM user " + "ORDER BY UserID");

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("UserID"));
				user.setVorname(rs.getString("Vorname"));
				user.setNachname(rs.getString("Nachname"));
				user.setNickname(rs.getString("Nickname"));
				user.setEmail(rs.getString("Email"));
				user.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));

				result.addElement(user);
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

	public int zaehleTextbeitraegeVonUser(User user) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		InfosVonUserReport report = new InfosVonUserReport();

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT COUNT(TextbeitragID) AS AnzahlTextbeitraege FROM textbeitrag WHERE UserID = "
							+ user.getId()
							+ "AND ErstellungsZeitpunkt >= "
							+ report.getAnfangszeitpunkt()
							+ "AND ErstellungsZeitpunkt <= "
							+ report.getEndzeitpunkt());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());

		}

		finally {
			DBVerbindung.closeAll(rs, stmt, con);

		}
		return rs.getInt("AnzahlTextbeitraege");

	}

	public Vector<Textbeitrag> findeTextbeitragAnhandVonUser(User user)
			throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		ResultSet rs = null;

		Vector<Textbeitrag> result = new Vector<Textbeitrag>();

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT TextbeitragID, Text, ErstellungsZeitpunkt FROM textbeitrag INNER JOIN user ON textbeitrag.UserID = "
							+ user.getId());

			while (rs.next()) {
				Textbeitrag textbeitrag = new Textbeitrag();
				textbeitrag.setId(rs.getInt("TextbeitragID"));
				textbeitrag.setText(rs.getString("Text"));
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
	  * Alle Abonnenten von einen Users werden gez�hlt.
	  * @param user
	   * @return 
	   * @throws Exception
	   */

	public int zaehleAbosVonUser(User user) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT COUNT(UserID) AS AnzahlAbos FROM abonnement WHERE UserID = "
							+ user.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());

		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}
		return rs.getInt("AnzahlAbos");

	}
	 /** 
	  * Alle Kommentare von einem User werden gez�hlt.
	  * @param user
	   * @return 
	   * @throws Exception
	   */

	public int zaehleKommentareVonUser(User user) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT COUNT(UserID) AS AnzahlKommentare FROM kommentar WHERE UserID = "
							+ user.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());

		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}
		return rs.getInt("AnzahlKommentare");
	}
	 /** 
	  * Abonnements anhand User finden.
	  * @param user
	   * @return 
	   * @throws Exception
	   */
	public ArrayList<User> findeAbosAnhandUser(int uid) throws Exception {

		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		ArrayList<User> result = new ArrayList<User>();

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT pinnwand.Eigentuemer "
							+ "FROM user INNER JOIN (pinnwand INNER JOIN abonnement " 
							+ " ON pinnwand.PinnwandID = abonnement.PinnwandID) ON user.UserID = abonnement.UserID " 
							+ "WHERE user.UserID= " + uid);

			while (rs.next()) {
				User u = new User();
				u.setNickname(rs.getString("pinnwand.Eigentuemer"));

				result.add(u);
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

}
