package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import de.hdm.gruppe6.itprojekt.shared.bo.Like;


public class LikeMapper {
	
	private static LikeMapper likeMapper=null;
		
	protected LikeMapper(){
	}
	public static LikeMapper likeMapper(){
		if(likeMapper==null){
			likeMapper=new LikeMapper();
		}
		return likeMapper;
	}
	
	public Like anlegen(Like like) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		
		try{
			stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO liken (TextbeitragID,ErstellungsZeitpunkt)"
					+ "VALUES ("
					+ "NULL,'"
					+ "','"
					+ like.getErstellungsZeitpunkt() +"')");
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return like;
	}
		
	
	public void loeschen(Like like) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM liken " + "WHERE LikeID="
					+ like.getId());
			
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return;
	}

	
	public Like findeAnhandID(int likeID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT LikeID, ErstellungsZeitpunkt" 
					+ "WHERE LikeID=" + likeID + " ORDER BY TextbeitragID");
			
			if(rs.next()){
				Like like = new Like();
				like.setId(rs.getInt("LikeID"));
				like.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));
				
				return like;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(rs, stmt, con);
		}
		
		return null;
	}
	
	
	}
	