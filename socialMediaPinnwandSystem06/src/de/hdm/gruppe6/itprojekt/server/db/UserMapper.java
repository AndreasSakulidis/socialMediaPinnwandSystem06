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

public class UserMapper {

	private static UserMapper userMapper = null;

	protected UserMapper() {
	}

	public static UserMapper userMapper() {
		if (userMapper == null) {
			userMapper = new UserMapper();
		}
		return userMapper;
	}

	public User anlegen(User user, Pinnwand pinnwand) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(UserID) AS maxid "
					+ "FROM user ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * a erhölt den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
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
//		Nur benützen, wenn man mit Google SQL Cloud verbidet!!!
//		finally {
//			DBVerbindung.closeAll(rs, stmt, con);
//		}

		return null;
	}

	public User editieren(User user) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("UPDATE user " + "SET Nachname =\""
					+ user.getNachname() + "\", Vorname =\""
					+ user.getVorname() + "\", Nickname =\""
					+ user.getNickname() + "\", Email =\"" + user.getEmail()
					+ "\" , ErstellungsZeitpunkt =\""
					+ user.getErstellungsZeitpunkt() + "\" WHERE UserID="
					+ user.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return user;
	}

	public void loeschen(User user) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM user " + "WHERE UserID="
					+ user.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
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

	public User findeAnhandID(int userID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT UserID, Vorname, Nachname, Nickname, Email, ErstellungsZeitpunkt"
							+ "FROM user"
							+ "WHERE UserID="
							+ userID
							+ " ORDER BY Nachname");

			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("UserID"));
				u.setVorname(rs.getString("Vorname"));
				u.setNachname(rs.getString("Nachname"));
				u.setNickname(rs.getString("Nickname"));
				u.setEmail(rs.getString("Email"));
				u.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));

				return u;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}

		return null;
	}

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

	public ArrayList<User> findeAbosAnhandUser(User user) throws Exception {

		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		ArrayList<User> result = new ArrayList<User>();

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT user.UserID, pinnwand.Eigentuemer "
							+ "FROM user INNER JOIN (pinnwand INNER JOIN abonnement "
							+ " ON pinnwand.PinnwandID = abonnement.PinnwandID) ON user.UserID = abonnement.UserID"
							+ "WHERE UserID=" + user.getId());

			while (rs.next()) {
				User u = new User();
				u = userMapper.findeAnhandID(u.getId());

				result.add(u);
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}

		return result;
	}

	public int getUidFromNickname(String nickname) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("Select UserID From user " +
			"where nickname='" + nickname + "'");
			rs.next();
				int uid = rs.getInt("UserID");
				return uid;
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}
	}

}
