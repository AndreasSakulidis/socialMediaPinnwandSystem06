package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gruppe6.itprojekt.shared.bo.Kommentar;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;


public class TextbeitragMapper {
	
	private static TextbeitragMapper textbeitragMapper=null;
	
	
	protected TextbeitragMapper(){
	}
	public static TextbeitragMapper textbeitragMapper(){
		if(textbeitragMapper==null){
			textbeitragMapper=new TextbeitragMapper();
		}
		return textbeitragMapper;
	}
	
	public Textbeitrag anlegen(Textbeitrag textbeitrag) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		
		try{
			stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO textbeitrag (TextbeitragID, ErstellungsZeitpunkt, Text)"
					+ "VALUES ("
					+ "NULL,'"
					+ textbeitrag.getErstellungsZeitpunkt()
					+"','"
					+ textbeitrag.getText()
					+"')");
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return textbeitrag;
	}
		
	public Textbeitrag editieren (Textbeitrag textbeitrag) throws Exception{
	Connection con = DBVerbindung.connection();
	Statement stmt = null;
	try {
		stmt = con.createStatement(); 
		stmt.executeUpdate ("UPDATE textbeitrag " + "SET ErstellungsZeitpunkt =\""
				+ textbeitrag.getErstellungsZeitpunkt () +"SET Text =\""
				+ textbeitrag.getText() + "\" WHERE TextbeitragID=" 
				+ textbeitrag.getId());
		
	} catch (SQLException e2) {
		e2.printStackTrace();
		throw new Exception ("Datenbank fehler!" + e2.toString());
	}finally {
		DBVerbindung.closeAll(null, stmt, con); 
	}
	return textbeitrag; 
	}
	
	public void loeschen(Textbeitrag textbeitrag) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM textbeitrag " + "WHERE TextbeitragID="
					+ textbeitrag.getId());
			
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return;
	}

	
	public Textbeitrag findeAnhandID(int textbeitragID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT TextbeitragID, ErstellungsZeitpunkt" 
					+ "WHERE TextbeitragID=" + textbeitragID + " ORDER BY TextbeitragID");
			
			if(rs.next()){
				Textbeitrag textbeitrag = new Textbeitrag();
				textbeitrag.setId(rs.getInt("TextbeitragID"));
				textbeitrag.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));
				
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
	
	
	public Vector<Kommentar> findeKommentareZuTextbeitrag(Textbeitrag textbeitrag) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		
		Vector<Kommentar> result = new Vector<Kommentar>();
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT TextbeitragID, ErstellungsZeitpunkt, kommentar.Text, kommentar.ErstellungsZeitpunkt FROM textbeitrag INNER JOIN kommentar" 
					+ "ON TextbeitragID=" + textbeitrag.getId() + "ON textbeitrag.KommentarID = kommentar.KommentarID ORDER BY kommentar.ErstellungsZeitpunkt");
			
			while(rs.next()){
			Kommentar kommentar = new Kommentar();
			kommentar.setId(rs.getInt("KommentarID"));
			kommentar.setErstellungsZeitpunkt(rs.getDate("kommentar.ErstellungsZeitpunkt"));
			kommentar.setText(rs.getString("kommentar.Text"));
			kommentar.setBeitragsId(rs.getInt("TextbeitragID"));
				
			
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}
		return result;
	}
	


	public int zaehleLikesZuTextbeitrag(Textbeitrag textbeitrag) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		
		  try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT COUNT(TextbeitragID) AS AnzahlLikes FROM Like WHERE TextbeitragID = " + textbeitrag.getId());
				
				return rs.getInt("AnzahlLikes");
			
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}
		
	}
	
	
	}

	