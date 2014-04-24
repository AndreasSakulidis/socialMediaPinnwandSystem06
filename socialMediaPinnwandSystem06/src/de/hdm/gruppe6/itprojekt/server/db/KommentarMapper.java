package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gruppe6.itprojekt.shared.bo.*;


public class KommentarMapper {
	
	private static KommentarMapper kommentarMapper = null;
	
	protected KommentarMapper() {
			}
	
	public static KommentarMapper kommentarMapper() {
		if (kommentarMapper == null) {
			kommentarMapper = new KommentarMapper();
		}
		
		return kommentarMapper;
	}
	
	public Kommentar anlegen(Kommentar kommentar) throws Exception{
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		
		try{
			stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO kommentar (KommentarID, ErstellungsZeitpunkt)"
					+ "VALUES ("
					+ "NULL,'"
					+ kommentar.getErstellungsZeitpunkt() +"')");
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		return kommentar;
	}
	
	public Kommentar editieren (Kommentar kommentar) throws Exception{
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("UPDATE kommentar " + "SET ErstellungsZeitpunkt =\""
					+ kommentar.getErstellungsZeitpunkt() + "\" WHERE KommentarID="
					+ kommentar.getId());
		}
		
		catch(SQLException e2){
			e2.printStackTrace();throw new Exception("Datenbank fehler!" + e2.toString());
		}
		finally{
			DBVerbindung.closeAll(null, stmt, con);
		}
		
		return kommentar;
	}
	
	public void loeschen(Kommentar kommentar) throws Exception{
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		
		try{
			stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM kommentar " + "WHERE KommentarID="
					+ kommentar.getId());
		}
		catch(SQLException e2){
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		finally{
			DBVerbindung.closeAll(null, stmt, con);
		}
		return;
	}
	
	public Kommentar findeAnhandID(int kommentarID) throws Exception{
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		
		try{
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT KommentarID, ErstellungsZeitpunkt"
					+ "WHERE KommentarID=" + kommentarID + "ORDER BY KommentarID");
			
			if(rs.next()){
				Kommentar kommentar = new Kommentar();
				kommentar.setId(rs.getInt("KommentarID"));
				kommentar.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));
				
				return kommentar;
			}
		}
		
		catch(SQLException e2){
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		
		finally{
			DBVerbindung.closeAll(rs, stmt, con);
		}
		
		return null;
	}
	
	public Vector<Kommentar> findeAlle() throws Exception{
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		ResultSet rs = null;
		
		Vector<Kommentar> result = new Vector<Kommentar>();
		
		try{
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT * FROM kommentar "
					+ "ORDER BY KommentarID");
			
			while(rs.next()){
				Kommentar kommentar = new Kommentar();
				kommentar.setId(rs.getInt("KommentarID"));
				kommentar.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));
				
				result.addElement(kommentar);
			}
		}
		
		catch(SQLException e2){
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		
		finally{
			DBVerbindung.closeAll(rs, stmt, con);
		}
		
		return result;
	}

}