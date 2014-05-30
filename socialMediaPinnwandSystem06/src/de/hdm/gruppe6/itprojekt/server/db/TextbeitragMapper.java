package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;

import de.hdm.gruppe6.itprojekt.shared.bo.Kommentar;
import de.hdm.gruppe6.itprojekt.shared.bo.Pinnwand;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;

public class TextbeitragMapper {

	private static TextbeitragMapper textbeitragMapper = null;

	protected TextbeitragMapper() {
	}

	public static TextbeitragMapper textbeitragMapper() {
		if (textbeitragMapper == null) {
			textbeitragMapper = new TextbeitragMapper();
		}
		return textbeitragMapper;
	}

	public Textbeitrag anlegen(Textbeitrag textbeitrag, int uid, int pid) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT MAX(TextbeitragID) AS maxid "
							+ "FROM textbeitrag ");

			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * a erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				textbeitrag.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				Timestamp tmstamp = new Timestamp(System.currentTimeMillis());
				stmt.executeUpdate("INSERT INTO textbeitrag (TextbeitragID, ErstellungsZeitpunkt, Text, UserID, PinnwandID) "
						+ "VALUES ('"
						+ textbeitrag.getId()
						+ "','"
						+ tmstamp
						+ "','"
						+ textbeitrag.getText()
						+ "','"
						+ uid
						+ "','" 
						+ pid + "') ");
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

	public Vector<Kommentar> findeKommentareZuTextbeitrag(
			Textbeitrag textbeitrag) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		Vector<Kommentar> result = new Vector<Kommentar>();

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT TextbeitragID, ErstellungsZeitpunkt, kommentar.Text, kommentar.ErstellungsZeitpunkt FROM textbeitrag INNER JOIN kommentar"
							+ "ON TextbeitragID="
							+ textbeitrag.getId()
							+ "ON textbeitrag.KommentarID = kommentar.KommentarID ORDER BY kommentar.ErstellungsZeitpunkt");

			while (rs.next()) {
				Kommentar kommentar = new Kommentar();
				kommentar.setId(rs.getInt("KommentarID"));
				kommentar.setErstellungsZeitpunkt(rs
						.getTimestamp("kommentar.ErstellungsZeitpunkt"));
				kommentar.setText(rs.getString("kommentar.Text"));
				kommentar.setId(rs.getInt("TextbeitragID"));

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}
		return result;
	}

	public int zaehleLikesZuTextbeitrag(Textbeitrag textbeitrag)
			throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt
					.executeQuery("SELECT COUNT(TextbeitragID) AS AnzahlLikes FROM like WHERE TextbeitragID = "
							+ textbeitrag.getId());

			return rs.getInt("AnzahlLikes");

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}

	}

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

			User user = new User();
			user.setId(rs.getInt("TextbeitragID"));
			user.setVorname(rs.getString("User.Vorname"));
			user.setNachname(rs.getString("User.Nachname"));

			return user;

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());

		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}
	}

	public Vector<Textbeitrag> findeAlleUserBeitraege(int userID) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		ResultSet rs = null;

		Vector<Textbeitrag> result = new Vector<Textbeitrag>();

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT * FROM textbeitrag "
					+ "WHERE UserID ="
					+ userID);

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
		} 
//		finally {
//			DBVerbindung.closeAll(rs, stmt, con);
//		}

		return result;
	}
	
}