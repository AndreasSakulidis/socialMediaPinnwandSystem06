package de.hdm.gruppe6.itprojekt.server;

import java.util.ArrayList;
import java.util.Vector;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.gruppe6.itprojekt.server.db.AbonnementMapper;
import de.hdm.gruppe6.itprojekt.server.db.KommentarMapper;
import de.hdm.gruppe6.itprojekt.server.db.LikeMapper;
import de.hdm.gruppe6.itprojekt.server.db.PinnwandMapper;
import de.hdm.gruppe6.itprojekt.server.db.TextbeitragMapper;
import de.hdm.gruppe6.itprojekt.server.db.UserMapper;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.bo.Abonnement;
import de.hdm.gruppe6.itprojekt.shared.bo.Kommentar;
import de.hdm.gruppe6.itprojekt.shared.bo.Like;
import de.hdm.gruppe6.itprojekt.shared.bo.Pinnwand;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;

/**
 * @author Ezgi Demirbilek, �zlem G�l, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
 * In Anlehnung an Hr. Prof. Dr. Thies
 */


public class PinnwandVerwaltungImpl extends RemoteServiceServlet implements PinnwandVerwaltungService {

	private static final long serialVersionUID = 1L;
	
	

	private UserMapper userMapper = null;

	private PinnwandMapper pinnwandMapper = null;

	private AbonnementMapper abonnementMapper = null;

	private KommentarMapper kommentarMapper = null;

	private LikeMapper likeMapper = null;

	private TextbeitragMapper textbeitragMapper = null;
	
	public PinnwandVerwaltungImpl() throws IllegalArgumentException {
	}
	public void init() throws IllegalArgumentException {
		
		// Kommunikation mit der Datenbank

		userMapper = UserMapper.userMapper();
		pinnwandMapper = PinnwandMapper.pinnwandMapper();
		abonnementMapper = AbonnementMapper.abonnementMapper();
		kommentarMapper = KommentarMapper.kommentarMapper();
		likeMapper = LikeMapper.likeMapper();
		textbeitragMapper = TextbeitragMapper.textbeitragMapper();
	}
	
	// Methoden User
	
	@Override
	public User userAnlegen(String vorname,
			String nachname, String nickname, String email, String pw) throws Exception {
		User user = new User(); 
		Pinnwand pinnwand = new Pinnwand(); 
		boolean check = this.userMapper.nicknamePr�fen(nickname);
		System.out.println("Impl userAnlegen check: "+check+" nickname: "+nickname);
		if(!this.userMapper.nicknamePr�fen(nickname)){
//			Window.alert("Nickname schon vergeben");
			System.out.println("Nickname schon vergeben");
		return null;
		}
		else{
		System.out.println("User Anlegen in Impl: Passwort: "+pw);
		user.setVorname(vorname); 
		user.setNachname(nachname); 
		user.setNickname(nickname); 
		user.setEmail(email); 
		user.setPasswort(pw); 
		
		pinnwand.setEigentuemer(user.getNickname()); 
		
		pinnwandMapper.anlegen(pinnwand, user); 
		 return userMapper.anlegen(user, pinnwand); 
		}
		
//		return null;
	}
	
	@Override
	public User userAnmelden(String name, String passwort) throws Exception {
		
		
		return this.userMapper.anmelden(name, passwort);
		
		
		
		
	}

	public User userEditieren(User user) throws Exception { 

		return userMapper.editieren(user);
}
	public void userLoeschen(User user) throws Exception {

		return;
	}
	
	 public User findeUserAnhandID(int userID) throws Exception {
		    return this.userMapper.findeAnhandID(userID);
		  }
	 
	 public Vector<User> findeUserAnhandNachname(String nachname) throws Exception {

		    return this.userMapper.findeAnhandNachname(nachname);
		  }

	
	 public Vector <User> findeAlleUser()
		      throws Exception {

		    return this.userMapper.findeAlle();
		  }
	 
	 public int zaehleTextbeitraegeVonUser(User user) 
			 throws Exception{
		 
		 return this.userMapper.zaehleTextbeitraegeVonUser(user);
	 }
	 
	 public Vector<Textbeitrag> findeTextbeitragAnhandVonUser(User user)
			 throws Exception {
		 
		 return this.userMapper.findeTextbeitragAnhandVonUser(user);
	 }
	
	 public int zaehleAbosVonUser(User user) 
			 throws Exception {
		 
		 return this.userMapper.zaehleAbosVonUser(user);
	 }
	 
	 public int zaehleKommentareVonUser(User user) 
			 throws Exception {
		 
		 return this.userMapper.zaehleKommentareVonUser(user);
	 }
	
	 public ArrayList<User> findeAbosAnhandUser(User user) 
			 throws Exception {
		 
		 return this.userMapper.findeAbosAnhandUser(user);
	 }
	 
	 // Methoden Pinnwand
	 
	 public Pinnwand pinnwandAnlegen(Pinnwand pinnwand, User eigentuemer) throws Exception {

			return pinnwandMapper.anlegen(pinnwand, eigentuemer);
		}

		public Pinnwand pinnwandEditieren(Pinnwand pinnwand, User eigentuemer) throws Exception { 

			return pinnwandMapper.editieren(pinnwand, eigentuemer);
	}
		public void pinnwandLoeschen(Pinnwand pinnwand, User eigentuemer) throws Exception {

			return;
		}
		
		 public Pinnwand findePinnwandAnhandID (int pinnwandID) throws Exception {
			    return this.pinnwandMapper.findeAnhandID(pinnwandID);
			  }
		 
		
		 public Vector<Pinnwand> findeAllePinnwaende()
			      throws Exception {

			    return this.pinnwandMapper.findeAlle();
			  }
		 
		 
	// Methoden Abonnement
		public Abonnement aboAnlegen(User user, String id) throws Exception {

				Abonnement abonnement = new Abonnement();
				int userID = Integer.parseInt(id); 
				int pid = pinnwandMapper.findeAnhandUserID(userID);
			
				return abonnementMapper.anlegen(abonnement, userID, pid);
			}

	
		
		public void aboLoeschen(Abonnement abonnement) throws Exception {

				return;
			}
			
		 public Abonnement findeAboAnhandID (int abonnementID) throws Exception {
				    return this.abonnementMapper.findeAnhandID(abonnementID);
				  }
		 
		
		 
		// Methoden Kommentar
		 
			public Kommentar kommentarAnlegen(String text, String uid, int tid) throws Exception {

					Kommentar kommentar= new Kommentar();
					kommentar.setText(text);
					int userID = Integer.parseInt(uid);
					
					return kommentarMapper.anlegen(kommentar, userID, tid);
				}

			public Kommentar kommentarEditieren(String text, int id) throws Exception {

				Kommentar kommentar = new Kommentar();
				kommentar.setId(id);
				kommentar.setText(text);

				return kommentarMapper.editieren(kommentar);

			}
			
			public void kommentarLoeschen(String text, int id) throws Exception {

				Kommentar kommentar = new Kommentar();
				kommentar.setId(id);
				kommentar.setText(text);

				kommentarMapper.loeschen(kommentar);
			}
				
			public Kommentar findeKommentarAnhandID (int kommentarID) throws Exception {
					    return this.kommentarMapper.findeAnhandID(kommentarID);
					  }
				 
				
			public Vector <Kommentar> findeAlleKommentare()
					      throws Exception {

					    return this.kommentarMapper.findeAlle();
					  }
				 
			
		// Methoden Textbeitrag
			//@Override
			public Textbeitrag textbeitragAnlegen(String text, String id) throws Exception {

						Textbeitrag textbeitrag= new Textbeitrag();
						textbeitrag.setText(text);
						
						int userID = Integer.parseInt(id); 
						int pid = pinnwandMapper.findeAnhandUserID(userID);
						System.out.println("Das ist die PinnwandID " + pinnwandMapper.findeAnhandUserID(userID) + " Das ist die UserID" + userID);

						
						return textbeitragMapper.anlegen(textbeitrag, userID, pid);
					}

			public Textbeitrag textbeitragEditieren(String text, int id) throws Exception {

				Textbeitrag textbeitrag = new Textbeitrag();
				textbeitrag.setId(id);
				textbeitrag.setText(text);

				return textbeitragMapper.editieren(textbeitrag);
			}
			
			public void textbeitragLoeschen(String text, int id) throws Exception {

				Textbeitrag textbeitrag = new Textbeitrag();
				textbeitrag.setId(id);
				textbeitrag.setText(text);
				textbeitragMapper.loeschen(textbeitrag);
			}
					
			
			 public Textbeitrag findeTextbeitragAnhandID (int textbeitragID) throws Exception {
						    return this.textbeitragMapper.findeAnhandID(textbeitragID);
						  }
			
			 
			 public Vector <Kommentar> findeKommentareZuTextbeitrag( Textbeitrag textbeitrag)
				      throws Exception {

				    return this.textbeitragMapper.findeKommentareZuTextbeitrag(textbeitrag);
				  }
			 
			 
			 public int zaehleLikesZuTextbeitrag(Textbeitrag textbeitrag)
		 		      throws Exception {

		 		    return this.textbeitragMapper.zaehleLikesZuTextbeitrag(textbeitrag);
		 		  }
			
			 
		     public Vector <Textbeitrag> findeAlleTextbeitraege()
						      throws Exception {

						    return this.textbeitragMapper.findeAlle();
						  }
		    
		     
		     public User findeUserZuTextbeitrag(Textbeitrag textbeitrag)
		     				throws Exception {
		    	 
		    	 return this.textbeitragMapper.findeUserZuTextbeitrag(textbeitrag);
		     }
		     
		     public int zaehleKommentareVonTextbeitrag(Textbeitrag textbeitrag)
		     				throws Exception {
		    	 			
		    	 		return this.textbeitragMapper.zaehleKommentareVonTextbeitrag(textbeitrag);
		     }
					
		     
		 // Methoden Like
		     
		     public Like likeAnlegen(String uid, int tid) throws Exception {

		 		Like like= new Like();
		 		int userID = Integer.parseInt(uid);
		 		return likeMapper.anlegen(like, userID, tid);
		 	}

		     
		 	public void likeLoeschen(Like like) throws Exception {

		 		return;
		 	}
		 	
		 	public Like findeLikeAnhandID (int likeID) throws Exception {
		 		    return this.likeMapper.findeAnhandID(likeID);
		 		  }
		 	
		 
}
