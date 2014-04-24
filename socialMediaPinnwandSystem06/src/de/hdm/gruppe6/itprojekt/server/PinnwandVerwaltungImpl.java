package de.hdm.gruppe6.itprojekt.server;

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
			String nachname, String nickname, String email) throws Exception {

		User user= new User();
		user.setVorname(vorname);
		user.setNachname(nachname);
		user.setNickname(nickname);
		user.setEmail(email);
		return userMapper.anlegen(user);
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
	 
	 public User findeUserAnhandNachname(String nachname) throws Exception {

		    return this.userMapper.findeAnhandNachname(nachname);
		  }
	
	 public Vector <User> findeAlleUser()
		      throws Exception {

		    return this.userMapper.findeAlle();
		  }
	
	
	 // Methoden Pinnwand
	 
	 public Pinnwand pinnwandAnlegen() throws Exception {

			Pinnwand pinnwand= new Pinnwand();
			return pinnwandMapper.anlegen(pinnwand);
		}

		public Pinnwand pinnwandEditieren(Pinnwand pinnwand) throws Exception { 

			return pinnwandMapper.editieren(pinnwand);
	}
		public void pinnwandLoeschen(Pinnwand pinnwand) throws Exception {

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
		public Abonnement aboAnlegen(int userID,
					int pinnwandID) throws Exception {

				Abonnement abonnement= new Abonnement();
				abonnement.setUserID(userID);
				abonnement.setPinnwandID(pinnwandID);
				return abonnementMapper.anlegen(abonnement);
			}

	
		
		public void aboLoeschen(Abonnement abonnement) throws Exception {

				return;
			}
			
		 public Abonnement findeAboAnhandID (int abonnementID) throws Exception {
				    return this.abonnementMapper.findeAnhandID(abonnementID);
				  }
		 
		 public Abonnement findeAbosAnhandUser(User user)
				      throws Exception {

				    return this.abonnementMapper.findeAbosAnhandUser(user);
				  }
		
		 
		// Methoden Kommentar
			public Kommentar kommentarAnlegen(String text) throws Exception {

					Kommentar kommentar= new Kommentar();
					kommentar.setText(text);
					return kommentarMapper.anlegen(kommentar);
				}

			public Kommentar kommentarEditieren(Kommentar kommentar) throws Exception { 

					return kommentarMapper.editieren(kommentar);
			}
			
			public void kommentarLoeschen(Kommentar kommentar) throws Exception {

					return;
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

			public Textbeitrag textbeitragEditieren(Textbeitrag textbeitrag) throws Exception { 

						return textbeitragMapper.editieren(textbeitrag);
				}
			
			public void textbeitragLoeschen(Textbeitrag textbeitrag) throws Exception {

						return;
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
