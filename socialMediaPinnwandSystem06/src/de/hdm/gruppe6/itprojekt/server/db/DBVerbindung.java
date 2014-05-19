package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * *Enthält alle Elemente und nötigen Methoden für das Durchführungs Formular
 * 
 * @author �zlem G�l, Michael Schelkle, Bharti Kumar
 *In Anlehnung an Hr. Prof. Dr. Thies
 */
public class DBVerbindung {

	
	private static Connection con = null;
	

	
//	cloud sql
	private static String url = "jdbc:mysql://localhost:3306/social-media?user=root&password=root";
		
	
	/**
	 * Baut die Verbindung zur Datenbank auf die Google Cloud SQL
	 * @return Die Verbindung
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
	
	public static Connection connection()  {
		//Verbindung erstllen wenn es noch keine gibt
		if ( con == null ) {
		//	try {

				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					con = DriverManager.getConnection(url);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} 
//			catch (SQLException e1) {
//				con = connectToGoogle();
//			} 
		return con;	
	}
		
		//Die Verbindung
//		return con;
//}

	
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
