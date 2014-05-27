package de.hdm.gruppe6.itprojekt.server;

import java.util.ArrayList;
import java.util.Vector;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
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
 * @author Ezgi Demirbilek, Özlem Gül, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
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
	
	public User userAnlegen(String vorname,
			String nachname, String nickname, String email, String passwort) throws Exception {

		User user = new User();
		user.setVorname(vorname);
		user.setNachname(nachname);
		user.setNickname(nickname);
		user.setEmail(email);
		user.setPasswort(passwort);
		Pinnwand pinnwand = new Pinnwand();
		pinnwand.setEigentuemer(user.getNickname());
		pinnwandMapper.anlegen(pinnwand, user);
		return userMapper.anlegen(user, pinnwand);
	}
	
	public User userAnmelden(String name, String passwort) throws Exception{
		System.out.println("userAnmelden im Impl... Name: "+name+" und PW "+passwort);
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
		public Abonnement aboAnlegen(User user, Pinnwand pinnwand) throws Exception {

				Abonnement abonnement = new Abonnement();
			
				return abonnementMapper.anlegen(abonnement);
			}

	
		
		public void aboLoeschen(Abonnement abonnement) throws Exception {

				return;
			}
			
		 public Abonnement findeAboAnhandID (int abonnementID) throws Exception {
				    return this.abonnementMapper.findeAnhandID(abonnementID);
				  }
		 
		
		 
		// Methoden Kommentar
		 
			public Kommentar kommentarAnlegen(String text) throws Exception {

					Kommentar kommentar= new Kommentar();
					kommentar.setText(text);
					return kommentarMapper.anlegen(kommentar);
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
			public Textbeitrag textbeitragAnlegen(String text) throws Exception {

						Textbeitrag textbeitrag= new Textbeitrag();
						textbeitrag.setText(text);
						

						
						return textbeitragMapper.anlegen(textbeitrag);
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
		     
		     public Like likeAnlegen() throws Exception {

		 		Like like= new Like();
		 		return likeMapper.anlegen(like);
		 	}

		     
		 	public void likeLoeschen(Like like) throws Exception {

		 		return;
		 	}
		 	
		 	public Like findeLikeAnhandID (int likeID) throws Exception {
		 		    return this.likeMapper.findeAnhandID(likeID);
		 		  }
		 	
		 
}
