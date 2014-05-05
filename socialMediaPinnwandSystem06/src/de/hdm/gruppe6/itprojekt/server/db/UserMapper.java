package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.gruppe6.itprojekt.shared.bo.Abonnement;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonUserReport;


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
			
			/*
		       * Zunächst schauen wir nach, welches der momentan höchste
		       * Primärschlüsselwert ist.
		       */
		      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
		          + "FROM User ");
		      
		      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
		      if (rs.next()) {
		        /*
		         * a erhält den bisher maximalen, nun um 1 inkrementierten
		         * Primärschlüssel.
		         */
		        user.setId(rs.getInt("maxid") + 1);
		        
		        stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO User (UserID, Vorname, Nachname, Email, Nickname, ErstellungsZeitpunkt)"
					+ "VALUES ("
			        + user.getId()
			        + "','"
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
		stmt.executeUpdate ("UPDATE User " + "SET Nachname =\""
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
			
			stmt.executeUpdate("DELETE FROM User " + "WHERE UserID="
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
			
			rs = stmt.executeQuery("SELECT * FROM User "
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
	

	
	public int zaehleTextbeitraegeVonUser(User user) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		
		InfosVonUserReport report = new InfosVonUserReport();
		
		  try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT COUNT(TextbeitragID) AS AnzahlTextbeitraege FROM Textbeitrag WHERE UserID = " + user.getId() + "AND ErstellungsZeitpunkt >= " + report.getAnfangszeitpunkt() + "AND ErstellungsZeitpunkt <= " + report.getEndzeitpunkt() );
				
		
			
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
			
		} 
		  
		  
		  finally {
			DBVerbindung.closeAll(rs, stmt, con);
			
		}
		  return rs.getInt("AnzahlTextbeitraege");
		  
	}
	
	public Vector<Textbeitrag> findeTextbeitragAnhandVonUser(User user) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		ResultSet rs = null;
		
		Vector<Textbeitrag> result = new Vector<Textbeitrag>();
		
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT TextbeitragID, Text, ErstellungsZeitpunkt FROM Textbeitrag INNER JOIN User ON textbeitrag.UserID = " + user.getId());
			
			while (rs.next()) {
				Textbeitrag textbeitrag = new Textbeitrag();
				textbeitrag.setId(rs.getInt("TextbeitragID"));
				textbeitrag.setText(rs.getString("Text"));
				textbeitrag.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));
				
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
			
			rs = stmt.executeQuery("SELECT COUNT(UserID) AS AnzahlAbos FROM Abonnement WHERE UserID = " + user.getId());
				
			
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
			
			rs = stmt.executeQuery("SELECT COUNT(UserID) AS AnzahlKommentare FROM Kommentar WHERE UserID = " + user.getId());
				
			
			
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
			
			rs = stmt.executeQuery("SELECT User.UserID, Pinnwand.Eigentuemer "
					+ "FROM User INNER JOIN (Pinnwand INNER JOIN Abonnement "
					+ " ON Pinnwand.PinnwandID = Abonnement.PinnwandID) ON User.UserID = Abonnement.UserID"
					+ "WHERE UserID=" + user.getId());
			
			while (rs.next()){
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

	
	
	}
	
	


