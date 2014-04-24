package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.appengine.api.rdbms.AppEngineDriver;

/**
 * *Enthält alle Elemente und nötigen Methoden für das Durchführungs Formular
 * 
 * @author Yasemin Karakoc, Jan Plank, Selim Karazehir, Julia Hammerer, Denis Fuerst, Daniel Krakow
 *In Anlehnung an Hr. Prof. Dr. Thies
 */
public class DBVerbindung {

	
	private static Connection con = null;
	

	
//	cloud sql
	private static String url = "jdbc:google:rdbms://itprojectgruppe3:group3three/stundenplanverwaltung?user=root&";
	
	
	
	
	/**
	 * Baut die Verbindung zur Datenbank auf die Google Cloud SQL
	 * @return Die Verbindung
	 * @throws Exception 
	 */
	public static Connection connection() throws Exception  {
		//Verbindung erstllen wenn es noch keine gibt
//		if (con.isClosed() || con == null) {
			try {
				//Google connection
				DriverManager.registerDriver(new AppEngineDriver());
				
				con = DriverManager.getConnection(url);
			} 
			catch (SQLException e1) {
				con = null;
				e1.printStackTrace();
				throw new Exception("DBVerbindung Fehler!" + e1.toString());
			} 
			
			
//		}
		
		//Die Verbindung
		return con;
	}
	
	/**
	 * Schließt das ResultSet, das Statement und die Connection
	 * @param rs
	 * @param stmt
	 * @param con
	 * @throws Exception
	 */
	public static void closeAll(ResultSet rs, Statement stmt, Connection con) throws Exception {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			} 
			if (con != null) {
				con.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Connection close Fehler!" + e.toString());
		}
	}
	

}
