package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import de.hdm.gruppe6.itprojekt.shared.bo.Like;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;

public class LikeMapper {

	private static LikeMapper likeMapper = null;

	protected LikeMapper() {
	}

	public static LikeMapper likeMapper() {
		if (likeMapper == null) {
			likeMapper = new LikeMapper();
		}
		return likeMapper;
	}

	public Like anlegen(Like like) throws Exception {
		Connection con = DBVerbindung.connection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(LikeID) AS maxid "
					+ "FROM liken");

			if (rs.next()) {
				like.setId(rs.getInt("maxid") + 1);

				int uid = 0;
				int tid = 0;
				Timestamp tmstamp = new Timestamp(System.currentTimeMillis());
				stmt.executeUpdate("INSERT INTO liken (LikeID, UserID, ErstellungsZeitpunkt, TextbeitragID)"
						+ "VALUES ('"
						+ like.getId()
						+ "','"
						+ uid
						+ "','"
						+ tmstamp + "','" + tid + "') ");
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
			throw new Exception("Datenbank fehler!" + e2.toString());
		} finally {
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
					+ "FROM liken" + "WHERE LikeID=" + likeID
					+ " ORDER BY TextbeitragID");

			if (rs.next()) {
				Like like = new Like();
				like.setId(rs.getInt("LikeID"));
				like.setErstellungsZeitpunkt(rs
						.getTimestamp("ErstellungsZeitpunkt"));

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