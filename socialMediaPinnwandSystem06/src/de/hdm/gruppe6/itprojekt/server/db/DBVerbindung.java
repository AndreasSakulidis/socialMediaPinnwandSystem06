package de.hdm.gruppe6.itprojekt.server.db;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import com.google.appengine.api.rdbms.AppEngineDriver;
//
///**
// * *Enth√§lt alle Elemente und n√∂tigen Methoden f√ºr das Durchf√ºhrungs Formular
// * 
// * @author ÷zlem G¸l, Michael Schelkle, Bharti Kumar
// *In Anlehnung an Hr. Prof. Dr. Thies
// */
//public class DBVerbindung {
//
//	
//	private static Connection con = null;
//	
//
//	
////	cloud sql
//	private static String url = "jdbc:mysql://localhost:3306/Social-Media?user=root&password=root";
//		
//	
//	/**
//	 * Baut die Verbindung zur Datenbank auf die Google Cloud SQL
//	 * @return Die Verbindung
//	 * @throws Exception 
//	 */
//	public static Connection connection() throws Exception  {
//		//Verbindung erstllen wenn es noch keine gibt
////		if (con.isClosed() || con == null) {
//			try {
//				//Google connection
//				DriverManager.registerDriver(new AppEngineDriver());
//				
//				con = DriverManager.getConnection(url);
//			} 
//			catch (SQLException e1) {
//				con = null;
//				e1.printStackTrace();
//				throw new Exception("DBVerbindung Fehler!" + e1.toString());
//			} 
//			
//			
////		}
//		
//		//Die Verbindung
//		return con;
//	}
//	
//	/**
//	 * Schlie√üt das ResultSet, das Statement und die Connection
//	 * @param rs
//	 * @param stmt
//	 * @param con
//	 * @throws Exception
//	 */
//	public static void closeAll(ResultSet rs, Statement stmt, Connection con) throws Exception {
//		try {
//			if (rs != null) {
//				rs.close();
//			}
//			if (stmt != null) {
//				stmt.close();
//			} 
//			if (con != null) {
//				con.close();
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new Exception("Connection close Fehler!" + e.toString());
//		}
//	}
//	
//
//}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.appengine.api.rdbms.AppEngineDriver;

/**
 * *Enth√§lt alle Elemente und n√∂tigen Methoden f√ºr das Durchf√ºhrungs Formular
 * 
 * @author ÷zlem G¸l, Michael Schelkle, Bharti Kumar
 *In Anlehnung an Hr. Prof. Dr. Thies
 */
public class DBVerbindung {

	
	private static Connection con = null;
	

	
//	cloud sql
	private static String url = "jdbc:mysql://localhost:3306/social-media?user=root&password=";
//	private static String url = "jdbc:google:rdbms://localhost:3306/social-media?user=root&password=root";
//	private static String url = "jdbc:google:rdbms://localhost:3306/social-media?user=root";
//	private static String url = "jdbc:google:rdbms://localhost:3306/social-media?user=root&password=";
	
	/**
	 * Baut die Verbindung zur Datenbank auf die Google Cloud SQL
	 * @return Die Verbindung
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws Exception 
	 */
//	public static Connection connection() throws Exception  {
//		//Verbindung erstllen wenn es noch keine gibt
////		if (con.isClosed() || con == null) {
//			try {
//				//Google connection
//				DriverManager.registerDriver(new AppEngineDriver());
//				
//				con = DriverManager.getConnection(url);
//			} 
//			catch (SQLException e1) {
//				con = null;
//				e1.printStackTrace();
//				throw new Exception("DBVerbindung Fehler!" + e1.toString());
//			} 
//			
//			
////		}
//		
//		//Die Verbindung
//		return con;
//	}
	
//	public static Connection connection() {
//		// Wenn es bisher keine Conncetion zur DB gab, ... 
//		if ( con == null ) {
//			try {
//				// Ersteinmal muss der passende DB-Treiber geladen werden
//				DriverManager.registerDriver(new AppEngineDriver());
//
//				/*
//				 * Dann erst kann uns der DriverManager eine Verbindung mit den oben
//				 * in der Variable url angegebenen Verbindungsinformationen aufbauen.
//				 * 
//				 * Diese Verbindung wird dann in der statischen Variable con 
//				 * abgespeichert und fortan verwendet.
//				 */
//				con = DriverManager.getConnection(url);
//			} 
//			catch (SQLException e1) {
//				con = null;
//				e1.printStackTrace();
//			}
//		}
//		
//		// Zur¸ckgegeben der Verbindung
//		return con;
//	}
	
	public static Connection connection() throws SQLException, InstantiationException, IllegalAccessException  {
		//Verbindung erstllen wenn es noch keine gibt
		if ( con == null ) {
		//	try {

				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();

					System.out.println("Treiber wurde erkannt!");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Treiber wurde nicht erkannt!");
				}
				
				try {
					con = DriverManager.getConnection(url);
					System.out.println("DB Verbindung Gelungen ;) "+con.toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("DB Verbindung fehlgeschlagen!");
				}
				
			} 
//			catch (SQLException e1) {
//				con = connectToGoogle();
//			} 
		return con;	
	}


	
	/**
	 * Schlie√üt das ResultSet, das Statement und die Connection
	 * @param rs
	 * @param stmt
	 * @param con
	 * @throws Exception
	 */
	// Google Cloud SQL Schlieﬂen, Sehr wichtig
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


