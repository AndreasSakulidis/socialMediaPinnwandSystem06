package de.hdm.gruppe6.itprojekt.server;

import java.util.ArrayList;
import java.util.Vector;


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
	
	
/**
 * Referenz auf den UserMapper in der Datenbank.
 */
	private UserMapper userMapper = null;

/**
 * Referenz auf den PinnwandMapper in der Datenbank.
 */

	private PinnwandMapper pinnwandMapper = null;

/**
 * Referenz auf den AbonnementMapper in der Datenbank.
 */
	private AbonnementMapper abonnementMapper = null;

	/**
	 * Referenz auf den KommentarMapper in der Datenbank.
	 */
	
	private KommentarMapper kommentarMapper = null;
	
	/**
	 * Referenz auf den LikeMapper in der Datenbank.
	 */
	
	private LikeMapper likeMapper = null;
	
	/**
	 * Referenz auf den TextbeitragMapper in der Datenbank.
	 */
	private TextbeitragMapper textbeitragMapper = null;
	
	public PinnwandVerwaltungImpl() throws IllegalArgumentException {
	}
	public void init() throws IllegalArgumentException {
		
	/**
	 * Die Kommunikation mit Datenbank wird aufgebaut.
	 */

		userMapper = UserMapper.userMapper();
		pinnwandMapper = PinnwandMapper.pinnwandMapper();
		abonnementMapper = AbonnementMapper.abonnementMapper();
		kommentarMapper = KommentarMapper.kommentarMapper();
		likeMapper = LikeMapper.likeMapper();
		textbeitragMapper = TextbeitragMapper.textbeitragMapper();
	}
	
	/**
	 * Anlegen eines Users in der Datenbank.
	 */
	
	@Override
	public User userAnlegen(String vorname,
			String nachname, String nickname, String email, String pw) throws Exception {
		User user = new User(); 
		Pinnwand pinnwand = new Pinnwand(); 
		boolean check = this.userMapper.nicknamePrüfen(nickname);
		System.out.println("Impl userAnlegen check: "+check+" nickname: "+nickname);
		if(!this.userMapper.nicknamePrüfen(nickname)){
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
	
	/**
	 * Anmeldung des Users mit Name und Passwort.
	 */
	
	@Override
	public User userAnmelden(String name, String passwort) throws Exception {
		
		
		return this.userMapper.anmelden(name, passwort);
							
	}
	
/**
 * Bearbeitung des Users.
 */
	public User userEditieren(User user) throws Exception { 

		return userMapper.editieren(user);
}

/**
 * Löschen des Users aus der Datenbank.
 */
	public void userLoeschen(User user) throws Exception {

		return;
	}
	
	/**
	 * Finden des Users anhand seiner ID.
	 */
	 public User findeUserAnhandID(int userID) throws Exception {
		    return this.userMapper.findeAnhandID(userID);
		  }
	 /**
	  * Finden des Users anhand seiner Nachname.
	  */
	 
	 public Vector<User> findeUserAnhandNachname(String nachname) throws Exception {

		    return this.userMapper.findeAnhandNachname(nachname);
		  }

	/**
	 * Finden aller User.
	 */
	 public Vector <User> findeAlleUser()
		      throws Exception {

		    return this.userMapper.findeAlle();
		  }
	 
	 /**
	  * Zählen der Textbeiträge von einem User.
	  */
	 public int zaehleTextbeitraegeVonUser(User user) 
			 throws Exception{
		 
		 return this.userMapper.zaehleTextbeitraegeVonUser(user);
	 }
	 
	 /**
	  * Finden von Textbeitrag anhand von User.
	  */
	 public Vector<Textbeitrag> findeTextbeitragAnhandVonUser(User user)
			 throws Exception {
		 
		 return this.userMapper.findeTextbeitragAnhandVonUser(user);
	 }
	
	 /**
	  * Zählen der Abonnenten des Users.
	  */
	 public int zaehleAbosVonUser(User user) 
			 throws Exception {
		 
		 return this.userMapper.zaehleAbosVonUser(user);
	 }
	 
	 /**
	  * Zählen der Kommentare von einem User.
	  */
	 public int zaehleKommentareVonUser(User user) 
			 throws Exception {
		 
		 return this.userMapper.zaehleKommentareVonUser(user);
	 }
	
	 /**
	  * Finden von Abonnenten des Users.
	  */
	 public ArrayList<User> findeAbosAnhandUser(User user) 
			 throws Exception {
		 
		 return this.userMapper.findeAbosAnhandUser(user);
	 }
	 
	/**
	 * Anlegen einer Pinnwand in der Datenbank.
	 */
	 
	 public Pinnwand pinnwandAnlegen(Pinnwand pinnwand, User eigentuemer) throws Exception {

			return pinnwandMapper.anlegen(pinnwand, eigentuemer);
		}

	 /**
	  * Bearbeitung des Pinnwands. 
	  */
		public Pinnwand pinnwandEditieren(Pinnwand pinnwand, User eigentuemer) throws Exception { 

			return pinnwandMapper.editieren(pinnwand, eigentuemer);
	}
		/**
		 * Löschung des Pinnwands.
		 */
		public void pinnwandLoeschen(Pinnwand pinnwand, User eigentuemer) throws Exception {

			return;
		}
		
		/**
		 * Finden einer Pinnwand anhand seiner ID.
		 */
		 public Pinnwand findePinnwandAnhandID (int pinnwandID) throws Exception {
			    return this.pinnwandMapper.findeAnhandID(pinnwandID);
			  }
		 
		/**
		 * Finden von allen Pinnwänden.
		 */
		 public Vector<Pinnwand> findeAllePinnwaende()
			      throws Exception {

			    return this.pinnwandMapper.findeAlle();
			  }
		 
		 
	/**
	 * Anlegen eines Abonnements.
	 */
		public Abonnement aboAnlegen(User user, Pinnwand pinnwand) throws Exception {

				Abonnement abonnement = new Abonnement();
			
				return abonnementMapper.anlegen(abonnement);
			}

	
		/**
		 * Löschen des Abonnements.
		 */
		public void aboLoeschen(Abonnement abonnement) throws Exception {

				return;
			}
			
		/**
		 * Finden eines Abonnements anhand seiner ID.
		 */
		 public Abonnement findeAboAnhandID (int abonnementID) throws Exception {
				    return this.abonnementMapper.findeAnhandID(abonnementID);
				  }
		 
		
		 
		/**
		 * Anlegen eines Kommentars in der Datenbank.
		 */
		 
			public Kommentar kommentarAnlegen(String text) throws Exception {

					Kommentar kommentar= new Kommentar();
					kommentar.setText(text);
					return kommentarMapper.anlegen(kommentar);
				}

			/**
			 * Bearbeitung des Kommentars.
			 */
			public Kommentar kommentarEditieren(String text, int id) throws Exception {

				Kommentar kommentar = new Kommentar();
				kommentar.setId(id);
				kommentar.setText(text);

				return kommentarMapper.editieren(kommentar);

			}
			
			/**
			 * Löschen des Kommentars.
			 */
			public void kommentarLoeschen(String text, int id) throws Exception {

				Kommentar kommentar = new Kommentar();
				kommentar.setId(id);
				kommentar.setText(text);

				kommentarMapper.loeschen(kommentar);
			}
				
			/**
			 * Finden eines Kommentars anhand seiner ID.
			 */
			public Kommentar findeKommentarAnhandID (int kommentarID) throws Exception {
					    return this.kommentarMapper.findeAnhandID(kommentarID);
					  }
			/**
			 * Finden von allen Kommentaren.
			 */
				
			public Vector <Kommentar> findeAlleKommentare()
					      throws Exception {

					    return this.kommentarMapper.findeAlle();
					  }
				 
		/**
		 * Anlegen einer Textbeitrag in der Datenbank.
		 */
			public Textbeitrag textbeitragAnlegen(String text) throws Exception {

						Textbeitrag textbeitrag= new Textbeitrag();
						textbeitrag.setText(text);
						

						
						return textbeitragMapper.anlegen(textbeitrag);
					}

			/**
			 * Bearbeitung eines Textbeitrags.
			 */
			public Textbeitrag textbeitragEditieren(String text, int id) throws Exception {

				Textbeitrag textbeitrag = new Textbeitrag();
				textbeitrag.setId(id);
				textbeitrag.setText(text);

				return textbeitragMapper.editieren(textbeitrag);
			}
			
			/**
			 * Löschen eines Textbeitrags.
			 */
			public void textbeitragLoeschen(String text, int id) throws Exception {

				Textbeitrag textbeitrag = new Textbeitrag();
				textbeitrag.setId(id);
				textbeitrag.setText(text);
				textbeitragMapper.loeschen(textbeitrag);
			}
			
			/**
			 * Finden eines Textbeitrags anhand seiner ID.
			 */
					
			
			 public Textbeitrag findeTextbeitragAnhandID (int textbeitragID) throws Exception {
						    return this.textbeitragMapper.findeAnhandID(textbeitragID);
						  }
			
			 /**
			  * Finden von Kommentaren zu Textbeiträgen.
			  */
			 public Vector <Kommentar> findeKommentareZuTextbeitrag( Textbeitrag textbeitrag)
				      throws Exception {

				    return this.textbeitragMapper.findeKommentareZuTextbeitrag(textbeitrag);
				  }
			 
			 /**
			  * Zählen von Likes zu einem Textbeitrag.
			  */
			 public int zaehleLikesZuTextbeitrag(Textbeitrag textbeitrag)
		 		      throws Exception {

		 		    return this.textbeitragMapper.zaehleLikesZuTextbeitrag(textbeitrag);
		 		  }
			 
			/**
			 * Finden von allen Textbeiträgen.
			 */
			 
		     public Vector <Textbeitrag> findeAlleTextbeitraege()
						      throws Exception {

						    return this.textbeitragMapper.findeAlle();
						  }
		    
		     /**
		      * Finden von User zu einem Textbeitrag.
		      */
		     
		     public User findeUserZuTextbeitrag(Textbeitrag textbeitrag)
		     				throws Exception {
		    	 
		    	 return this.textbeitragMapper.findeUserZuTextbeitrag(textbeitrag);
		     }
		     /**
		      * Zählen von Kommentaren zu einem Textbeitrag.
		      */
		     public int zaehleKommentareVonTextbeitrag(Textbeitrag textbeitrag)
		     				throws Exception {
		    	 			
		    	 		return this.textbeitragMapper.zaehleKommentareVonTextbeitrag(textbeitrag);
		     }
					
		     
	/**
	 * 	Anlegen einer Like in der Datenbank.
	 */
		     
		     public Like likeAnlegen() throws Exception {

		 		Like like= new Like();
		 		return likeMapper.anlegen(like);
		 	}

		     /**
		      * Löschen einer Like.
		      */
		     
		 	public void likeLoeschen(Like like) throws Exception {

		 		return;
		 	}
		 	
		 	/**
		 	 * Finden einer Like anhand seiner ID.
		 	 */
		 	public Like findeLikeAnhandID (int likeID) throws Exception {
		 		    return this.likeMapper.findeAnhandID(likeID);
		 		  }
		 	
		 
}
