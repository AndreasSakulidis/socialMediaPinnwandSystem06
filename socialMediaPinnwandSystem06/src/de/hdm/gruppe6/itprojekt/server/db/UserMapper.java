package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;


public class UserMapper {
	
	private static UserMapper userMapper=null;
	
	
	protected UserMapper(){
	}
	public static UserMapper userMapper(){
		if(userMapper==null){
			userMapper=new UserMapper();
		}
		return userMapper;
	}
	
	public User anlegen(User user) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		
		try{
			stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO user (UserID, Vorname, Nachname, Email, Nickname, ErstellungsZeitpunkt)"
					+ "VALUES ("
					+ "NULL,'"
					+ user.getVorname()
					+ "','"
					+ user.getNachname() 
					+ "','"
					+ user.getEmail()
					+ "','"
					+ user.getNickname()
					+ "','"
					+ user.getErstellungsZeitpunkt() +"')");
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return user;
	}
		
	public User editieren (User user) throws Exception{
	Connection con = DBVerbindung.connection();
	Statement stmt = null;
	try {
		stmt = con.createStatement(); 
		stmt.executeUpdate ("UPDATE user " + "SET Nachname =\""
				+ user.getNachname() + "\", Vorname =\""
				+ user.getVorname() + "\", Nickname =\""
				+ user.getNickname() + "\", Email =\""
				+ user.getEmail() + "\" , ErstellungsZeitpunkt =\"" 
				+ user.getErstellungsZeitpunkt () + "\" WHERE UserID=" 
				+ user.getId());
		
	} catch (SQLException e2) {
		e2.printStackTrace();
		throw new Exception ("Datenbank fehler!" + e2.toString());
	}finally {
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

	public User findeAnhandNachname(String nachname) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT UserID, Vorname, Nachname, Nickname, Email, ErstellungsZeitpunkt"
					+ "WHERE Nachname=" + nachname + "ORDER BY Nachname");
			
			if(rs.next()){
				User u = new User();
				u.setId(rs.getInt("UserID"));
				u.setVorname(rs.getString("Vorname"));
				u.setNachname(rs.getString("Nachname"));
				u.setNickname(rs.getString("Nickname"));
				u.setEmail(rs.getString("Email"));
				u.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));
				
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
	
	
	public User findeAnhandID(int userID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT UserID, Vorname, Nachname, Nickname, Email, ErstellungsZeitpunkt" 
					+ "WHERE UserID=" + userID + " ORDER BY Nachname");
			
			if(rs.next()){
				User u = new User();
				u.setId(rs.getInt("UserID"));
				u.setVorname(rs.getString("Vorname"));
				u.setNachname(rs.getString("Nachname"));
				u.setNickname(rs.getString("Nickname"));
				u.setEmail(rs.getString("Email"));
				u.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));
				
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
			
			rs = stmt.executeQuery("SELECT * FROM user "
					+ "ORDER BY UserID");
			
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("UserID"));
				user.setVorname(rs.getString("Vorname"));
				user.setNachname(rs.getString("Nachname"));
				user.setNickname(rs.getString("Nickname"));
				user.setEmail(rs.getString("Email"));
				user.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));
				
				result.addElement(user);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}

		return result;
		}
	
	public int zaehleLikesVonUser(User user) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		
		  try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT COUNT(LikeID) AS AnzahlLikes FROM Like WHERE UserID = " + user.getId());
				
			return rs.getInt("AnzahlLikes");
			
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
			
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}
		
	}
	
	public int zaehleTextbeitraegeVonUser(User user) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		
		  try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT COUNT(TextbeitragID) AS AnzahlTextbeitraege FROM Textbeitrag WHERE UserID = " + user.getId());
				
			return rs.getInt("AnzahlTextbeiraege");
			
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
			
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}
		
	}
	
	public int zaehleAbosVonUser(User user) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		
		  try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT COUNT(UserID) AS AnzahlAbos FROM Abonnement WHERE UserID = " + user.getId());
				
			return rs.getInt("AnzahlAbos");
			
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
			
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}
		
	}
	
	public int zaehleKommentareVonUser(User user) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		
		  try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT COUNT(UserID) AS AnzahlKommentare FROM Kommentar WHERE UserID = " + user.getId());
				
			return rs.getInt("AnzahlKommentare");
			
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
			
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}
		
	}
	
	/** GEZIM & ANDI BITTE ÜBERARBEITEN! 
	 * public User findeAbosAnhandUser(User user) throws Exception {
	 
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT userID, ErstellungsZeitpunkt, abonnement.UserID, abonnement.PinnwandID, abonnement.ErstellungsZeitpunkt FROM user INNER JOIN abonnement" 
					+ "WHERE UserID=" + user.getId() + " ORDER BY abonnement.ErstellungsZeitpunkt");
			
			if(rs.next()){
				User u = new User();
				beitrag.setId(rs.getInt("TextbeitragID"));
				beitrag.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));
				beitrag.setText("text");
				
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
	 **/ 
	
	
	}
	
	


