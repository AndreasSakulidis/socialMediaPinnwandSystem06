package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gruppe6.itprojekt.shared.bo.Pinnwand;
import de.hdm.gruppe6.itprojekt.shared.bo.User;


public class PinnwandMapper {
	
	private static PinnwandMapper pinnwandMapper=null;
	
	
	protected PinnwandMapper(){
	}
	public static PinnwandMapper pinnwandMapper(){
		if(pinnwandMapper==null){
			pinnwandMapper=new PinnwandMapper();
		}
		return pinnwandMapper;
	}
	
	public Pinnwand anlegen(Pinnwand pinnwand, User eigentuemer) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		try{
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(PinnwandID) AS maxid "
					+ "FROM pinnwand");
			
			if (rs.next()){
			pinnwand.setId(rs.getInt("maxid") +1);
			
			stmt = con.createStatement();
				
			stmt.executeUpdate("INSERT INTO pinnwand (PinnwandID, ErstellungsZeitpunkt, Eigentuemer)"
					+ "VALUES ("
					+ pinnwand.getId()
					+ ","
					+ pinnwand.getErstellungsZeitpunkt()
					+ ",'"
					+ eigentuemer.getNickname()
					+ "')");
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return pinnwand;
	}
		
	public Pinnwand editieren (Pinnwand pinnwand, User eigentuemer) throws Exception{
	Connection con = DBVerbindung.connection();
	Statement stmt = null;
	try {
		stmt = con.createStatement(); 
		stmt.executeUpdate ("UPDATE pinnwand " + "SET ErstellungsZeitpunkt =\""
				+ pinnwand.getErstellungsZeitpunkt () 
				+ "\","
				+ "Eigentuemer=\""
				+ eigentuemer.getNickname()
				+ "\" WHERE PinnwandID=" 
				+ pinnwand.getId());
		
	} catch (SQLException e2) {
		e2.printStackTrace();
		throw new Exception ("Datenbank fehler!" + e2.toString());
	}finally {
		DBVerbindung.closeAll(null, stmt, con); 
	}
	return pinnwand; 
	}
	
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

	
	
	public Pinnwand findeAnhandID(int pinnwandID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT PinnwandID, ErstellungsZeitpunkt, Eigentuemer" 
					+ "FROM pinnwand"
					+ "WHERE PinnwandID=" + pinnwandID + " ORDER BY ErstellungsZeitpunkt");
			
			if(rs.next()){
				Pinnwand pinnwand = new Pinnwand();
				pinnwand.setId(rs.getInt("PinnwandID"));
				pinnwand.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));
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
				pinnwand.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));
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
	
	}
	