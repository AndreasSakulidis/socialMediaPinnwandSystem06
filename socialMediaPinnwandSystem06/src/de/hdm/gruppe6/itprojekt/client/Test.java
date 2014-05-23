package de.hdm.gruppe6.itprojekt.client;


import java.sql.Date;
import java.util.Vector;

import de.hdm.gruppe6.itprojekt.server.db.AbonnementMapper;
import de.hdm.gruppe6.itprojekt.server.db.KommentarMapper;
import de.hdm.gruppe6.itprojekt.server.db.LikeMapper;
import de.hdm.gruppe6.itprojekt.server.db.PinnwandMapper;
import de.hdm.gruppe6.itprojekt.server.db.TextbeitragMapper;
import de.hdm.gruppe6.itprojekt.server.db.UserMapper;
import de.hdm.gruppe6.itprojekt.shared.bo.Pinnwand;
import de.hdm.gruppe6.itprojekt.shared.bo.User;

public class Test {
	
	private static UserMapper userMapper = null;

	private static PinnwandMapper pinnwandMapper = null;

	private static AbonnementMapper abonnementMapper = null;

	private static KommentarMapper kommentarMapper = null;

	private static LikeMapper likeMapper = null;

	private static TextbeitragMapper textbeitragMapper = null;
	
	public static void init() throws IllegalArgumentException {
		
		// Kommunikation mit der Datenbank

		userMapper = UserMapper.userMapper();
		pinnwandMapper = PinnwandMapper.pinnwandMapper();
		abonnementMapper = AbonnementMapper.abonnementMapper();
		kommentarMapper = KommentarMapper.kommentarMapper();
		likeMapper = LikeMapper.likeMapper();
		textbeitragMapper = TextbeitragMapper.textbeitragMapper();

	}
	public static User userAnlegen(String vorname,
			String nachname, String nickname, String email) throws Exception {

		User user= new User();
		user.setVorname(vorname);
		user.setNachname(nachname);
		user.setNickname(nickname);
		user.setEmail(email);
		Pinnwand pinnwand = new Pinnwand();
		pinnwand.setEigentuemer(user.getNickname());
//		user.setErstellungsZeitpunkt(EZ);
		return userMapper.anlegen(user, pinnwand);
	}
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		init();
		userAnlegen("Alisia","Koch","Lisia","koch@");
		

	}

}