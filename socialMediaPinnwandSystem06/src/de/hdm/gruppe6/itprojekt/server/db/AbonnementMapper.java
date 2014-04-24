package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.gruppe6.itprojekt.shared.bo.Abonnement;


public class AbonnementMapper {
	
	private static AbonnementMapper abonnementMapper=null;
	
	protected AbonnementMapper(){
	}
	public static AbonnementMapper abonnementMapper(){
		if(abonnementMapper==null){
			abonnementMapper=new AbonnementMapper();
		}
		return abonnementMapper;
	}
	
	public Abonnement anlegen(Abonnement abo) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		
		try{
			stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO Abonnement (AbonnementID, User, Pinnwand, ErstellungsZeitpunkt)"
					+ "VALUES ("
					+ "NULL,'"
					+ abo.getUser()
					+ "','"
					+ abo.getPinnwand() 
					+ "','"
					+ abo.getErstellungsZeitpunkt() +"')");
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		}
		finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return abo;
	}


	public void loeschen(Abonnement abo) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM Abonnement " + "WHERE AbonnementID="
					+ abo.getId());
			
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbankfehler!" + e2.toString());
		} finally {
			DBVerbindung.closeAll(null, stmt, con);
		}
		return;
	}

	
	public Abonnement findeAnhandID(int abonnementID) throws Exception {
		Connection con = DBVerbindung.connection();
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT AbonnementID, User, Pinnwand ErstellungsZeitpunkt" 
					+ "WHERE AbonnementID=" + abonnementID + " ORDER BY AbonnementID");
			
			if(rs.next()){
				Abonnement abonnement = new Abonnement();
				abonnement.setId(rs.getInt("AbonnementID"));
				abonnement.setUser(((Abonnement) rs).getUser());
				abonnement.setPinnwand(((Abonnement) rs).getPinnwand ());
				abonnement.setErstellungsZeitpunkt(rs.getDate("ErstellungsZeitpunkt"));
				
				return abonnement;
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
	