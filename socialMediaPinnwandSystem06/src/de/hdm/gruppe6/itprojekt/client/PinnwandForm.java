package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, �zlem G�l, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 *
 * Die Klasse PinnwandForm hat die Funktion einen Textbeitr�ge zu erstellen.
 */

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.Kommentar;
import de.hdm.gruppe6.itprojekt.shared.bo.Like;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;

public class PinnwandForm extends Composite {
	/**
	 * Hier wird der Panel und die Widgets festgelegt.
	 */

	private VerticalPanel addPanel = new VerticalPanel();
	private TextArea ta = new TextArea();
	private Button textbeitragPosten = new Button("Add Post");


	private PinnwandVerwaltungServiceAsync socialmedia = GWT
			.create(PinnwandVerwaltungService.class);

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable postFlexTable = new FlexTable();
	private FlexTable userFlexTable = new FlexTable();
	private Label user = new Label(Cookies.getCookie("SocialMedia6"));
	private Button liken = new Button("Like");

	private Label lbId = new Label();
	private Button bePinnwand = new Button("Bearbeiten");
	private Button koPinnwand = new Button("Kommentieren");
	private Button loePinnwand = new Button("X");
	private Label lastUpdatedLabel = new Label();
	
	
	private VerticalPanel kommentarPanel = new VerticalPanel();
	private FlexTable kommentarTable = new FlexTable();
	private Label commentL = new Label();
	private Button kbearbeiten = new Button("Bearbeiten");
	private Button kloeschen = new Button("X");
	private Label commentUpdatedLabel = new Label();
	private Label likeLabel = new Label();
	
	private ScrollPanel scrollPanel= new ScrollPanel();

	

	public PinnwandForm() {

	}

	public Widget zeigePost() {

		ta.setCharacterWidth(60);
		ta.setVisibleLines(5);

		/**
		 * Die Widgets werden dem addPanel hinzugef�gt.
		 */
		String uid = Cookies.getCookie("SocialMedia6ID");
		int userID = Integer.parseInt(uid);
		if(userID!=0){
			addPanel.add(ta);
			addPanel.add(textbeitragPosten);
			addPanel.addStyleName("addPanel");
			addPanel.addStyleName("PinnwandAnzeigen");
		}
	

		// RootPanel.get("Details").clear();

		RootPanel.get("Details").add(addPanel);
		ta.setFocus(true);
		/**
		 * Mit einem Klick auf den Button Add Post wird ein Textbeitrag erzeugt.
		 */
		textbeitragPosten.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String a = ta.getText();

				Beitrag b = new Beitrag();
				b.beitragErstellen(a);
				ta.setText("");
			}

		});
		return addPanel;
	}
	

	// METHODE

	public Widget anzeigen() {
		String uid = Cookies.getCookie("SocialMedia6ID");
		int userID = Integer.parseInt(uid);
		socialmedia.findeAlleUserBeitraege(userID,
				new AsyncCallback<ArrayList<Textbeitrag>>() {
					@Override
					public void onSuccess(ArrayList<Textbeitrag> result) {
						System.out
								.println("in der Methode findeAlleUserBeitraege vor der Schleife");
						System.out.println("Werte von result: " + result.size());

						for (Textbeitrag tb : result) {
							
							PinnwandForm pinnwandAnzeigen = new PinnwandForm();
						
							pinnwandAnzeigen.beitragAnzeigen(tb);
							
							pinnwandAnzeigen.kommentarAnzeigen(tb);
							pinnwandAnzeigen.likesAnzeigen(tb);
							pinnwandAnzeigen.nicknameAnzeigen(tb); 

							
							addPanel.add(pinnwandAnzeigen);
							
							
							
						}
						
						

					}

					@Override
					public void onFailure(Throwable caught) {
						System.out
								.println("hat nicht geklappt mit den Post ausgaben: "
										+ caught.getMessage());
					}
				});

		return addPanel;
	}
	
	public void nicknameAnzeigen(Textbeitrag a){
		lbId.setText(String.valueOf(a.getId()));
		int tid = Integer.parseInt(lbId.getText()); 
		//TODO statt das untere eine Methode findeUserAnhandTextbeitragID -> Methode in Mapper auch schreiben
//		gebe ein String zurück
		socialmedia.findeUserZuTextbeitragID(a.getId(), new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				try{
					user.setText(result);

				}
				catch(Exception e){
					
				}
			}
		});

	}
	
	public void likesAnzeigen(Textbeitrag a){
		lbId.setText(String.valueOf(a.getId()));
		int tid = Integer.parseInt(lbId.getText()); 
		
		socialmedia.zaehleLikesZuTextbeitrag(tid,
				new AsyncCallback<Integer>() {
					public void onFailure(Throwable caught) {
						
					}

					public void onSuccess(Integer result) {
						
						String likes = Integer.toString(result);
						likeLabel.setText(likes + " Like(s)");
																	
					}
				});
	}

	// METHODE

	public void beitragAnzeigen(final Textbeitrag a) {

		initWidget(this.vPanel);
		
		String uid = Cookies.getCookie("SocialMedia6ID");
		final int userID = Integer.parseInt(uid);
		
		socialmedia.findeUserIDAnhandTextbeitragID(a.getId(), new AsyncCallback<Textbeitrag>() {

			@Override
			public void onFailure(Throwable caught) {
				
				
			}

			@Override
			public void onSuccess(Textbeitrag result) {
				if(result.getUserID()== userID){
					loePinnwand.setEnabled(true);
					bePinnwand.setEnabled(true);
		
					
					
					
				}else{
					
					loePinnwand.setEnabled(false);
					bePinnwand.setEnabled(false);
				}
				
			}
		});
		

		postFlexTable.setWidget(0, 0, user);
		user.addStyleName("Userspalte");
		/**
		 * Die Textbeitrï¿½ge werden in einer Flextable gepostet.
		 */
		
	


		postFlexTable.getFlexCellFormatter().setColSpan(1, 0, 190);  
		postFlexTable.getFlexCellFormatter().setColSpan(3, 0, 50);
		postFlexTable.getFlexCellFormatter().setColSpan(0, 1, 20);
		
		postFlexTable.setText(1, 0, a.getText());
		postFlexTable.setWidget(0, 1, loePinnwand);
		postFlexTable.setWidget(0, 2, bePinnwand);
		postFlexTable.setWidget(0, 3, koPinnwand);
		postFlexTable.setWidget(0, 4, liken);
		postFlexTable.setWidget(0, 5, likeLabel);

		postFlexTable.setStyleName("postFlexTable");
		

		lbId.setText(String.valueOf(a.getId()));
		postFlexTable.setWidget(1, 5, lbId);
		lbId.setVisible(false);

		lastUpdatedLabel.setText(String.valueOf(a.getErstellungsZeitpunkt()));

		postFlexTable.setWidget(3,0, lastUpdatedLabel);

		/**
		 * Die Flextable wird dem vPanel zugeordnet.
		 */
		vPanel.add(userFlexTable);
		vPanel.add(postFlexTable);
		vPanel.addStyleName("Textbeitrag");

		/**
		 * Mit einem Klick auf den Kommentieren Button wird die Klasse
		 * KommentarErstellen aufgerufen und ein Kommentar wird angelegt.
		 */

		bePinnwand.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				final MeineDialogBox comment = new MeineDialogBox("Bearbeiten");
				comment.center();
				comment.setText("Bearbeiten");
				comment.setContent(a.getText());

				comment.abbrechen.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						comment.hide();

					}

				});

				comment.ok.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						comment.hide();
						a.setText(comment.getContent());

						int id = a.getId();
						final String text = a.getText();
						socialmedia.textbeitragEditieren(text, id,
								new AsyncCallback<Textbeitrag>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Fehler beim Editieren!");
									}

									@Override
									public void onSuccess(Textbeitrag result) {
										Window.alert("Textbeitrag wurde editiert!");
										Window.Location.reload();
									}
								});

					}
				});

				comment.show();
			}
		});

		liken.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String uid = Cookies.getCookie("SocialMedia6ID");
				int tid = Integer.parseInt(lbId.getText());
				
				socialmedia.likeAnlegen(uid, tid,
						new AsyncCallback<Like>() {
							public void onFailure(Throwable caught) {
								Window.alert("Fehler beim Liken!");
							}

							public void onSuccess(Like like) {
								if (like == null) {
									final Warnung warnung = new Warnung("");
									warnung.center();
									warnung.setText("Dieser Beitrag wurde schon geliked! Möchtest du ihn disliken?");
									warnung.abbrechen.addClickHandler(new ClickHandler(){
										public void onClick(ClickEvent event){
											warnung.hide();
										}
									});
									warnung.ok.addClickHandler(new ClickHandler(){
										public void onClick(ClickEvent event){
											String uid = Cookies.getCookie("SocialMedia6ID");
											int tid = Integer.parseInt(lbId.getText());
											
											socialmedia.likeLoeschen(uid, tid, new AsyncCallback<Void>(){
													public void onFailure(Throwable caught) {
												Window.alert("Fehler beim Löschen!");
											}

											@Override
											public void onSuccess(Void result) {
												Window.alert("Like wurde gelöscht!");
												RootPanel.get("Details").clear();
												PinnwandForm pinnForm = new PinnwandForm();
												pinnForm.zeigePost();
												pinnForm.anzeigen();
												warnung.hide();
												
											}
										});
										}
									});
										
									
									
								} else {
									int tid = Integer.parseInt(lbId.getText()); 
									socialmedia.zaehleLikesZuTextbeitrag(tid,
											new AsyncCallback<Integer>() {
												public void onFailure(Throwable caught) {
													Window.alert("Fehler beim Liken!");
												}

												public void onSuccess(Integer result) {
													Window.alert("Erfolgreich geliked!");
													String likes = Integer.toString(result);
													likeLabel.setText(likes + " Like(s)");
																								
												}
											});
									
								}
								
								
								

								
							}
						});
			}
		});

		koPinnwand.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				final MeineDialogBox comment = new MeineDialogBox("Kommentieren");
				final Label commentL = new Label();
				final Label lbId = new Label();
				
				comment.center();
				comment.setText("Kommentieren");

				/**
				 * Die Aktion wird abgebrochen und es wird kein Kommentar gepostet.
				 */
				comment.abbrechen.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						comment.hide();


					}

				});

				/**
				 * Mit einem Klick auf ok wird ein Kommentar gepostet und in einer
				 * FlexTable angelegt.
				 */

				comment.ok.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						comment.hide(true);
						
						commentL.setText(comment.getContent());

						String text = commentL.getText();
						String uid = Cookies.getCookie("SocialMedia6ID");

						socialmedia.kommentarAnlegen(text, uid, a.getId(),
								new AsyncCallback<Kommentar>() {

									@Override
									public void onFailure(Throwable caught) {
										System.out.println(caught.getMessage());
										Window.alert("Fehler beim Anlegen!");
									}

									@Override
									public void onSuccess(Kommentar kommentar) {
										lbId.setText(String.valueOf(kommentar.getId()));
										comment.hide(false);
										Window.alert("Kommentar wurde angelegt!");
										RootPanel.get("Details").clear();
										PinnwandForm pinnForm = new PinnwandForm();
										pinnForm.zeigePost();
										pinnForm.anzeigen();
										
									//	Window.Location.reload();

										
										
									}
								});

					}
				});
				

//				KommentarErstellen kommentarErstellen = new KommentarErstellen();
//				int tid = a.getId();
//				kommentarErstellen.setComment(a.getText(), tid);
//			//	vPanel.add(kommentarErstellen.setComment(a.getText(), tid));
				

			}

		});
		/**
		 * Mit einem Klick auf den Loeschen Button wird der Textbeitrag
		 * gelï¿½scht.
		 * 
		 */

		loePinnwand.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				int id = a.getId();
				String text = a.getText();
				socialmedia.textbeitragLoeschen(text, id,
						new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Fehler beim loeschen!");
							}

							@Override
							public void onSuccess(Void result) {
								int id = a.getId();
								String text = a.getText();

								socialmedia.kZuTextbeitragLoeschen(text, id,
										new AsyncCallback<Void>() {
											@Override
											public void onFailure(Throwable caught) {
												Window.alert("Fehler beim kLöschen!");
											}

											@Override
											public void onSuccess(Void result) {
																								
											}
										});
								
								socialmedia.lZuTextbeitragLoeschen(text, id, new AsyncCallback<Void>(){
									
									public void onFailure(Throwable caught){
										Window.alert("Fehler beim llöschen");
									}
									
									public void onSuccess(Void result){
										
									}
								});
								Window.alert("Textbeitrag wurde geloescht!");
								kommentarTable.removeFromParent();
								postFlexTable.removeFromParent();
								user.removeFromParent();
								RootPanel.get("Details").clear();
								PinnwandForm pinnForm = new PinnwandForm();
								pinnForm.zeigePost();
								pinnForm.anzeigen();
							}
						});
			}

		});
	}


		/**
		 * Mit einem Klick auf den Bearbeiten Button wird ein Dialogbox
		 * ge�ffnet, indem der User den Textbeitrag bearbeiten kann.
		 * 
		 */
	
public void kommentarAnzeigen(final Textbeitrag a) {
		
		socialmedia.findeKommentareZuTextbeitrag(a.getId(),
				new AsyncCallback<ArrayList<Kommentar>>() {

					public void onSuccess(ArrayList<Kommentar> result) {
					
						kommentarPanel.addStyleName("Kommentare");
						kommentarTable.addStyleName("KomTable");
						
						int zeile=0;
						String uid = Cookies.getCookie("SocialMedia6ID");
						final int userID = Integer.parseInt(uid);
						
						for (final Kommentar k : result) {
							Label commentUpdatedLabel = new Label();



							final Button kbearbeiten = new Button("Bearbeiten");
							final Button kloeschen = new Button("X");
							final Label nichnameAnzeigen = new Label();
							
							nichnameAnzeigen.addStyleName("Nicknameanzeigen");


							
							socialmedia.findeUserIDAnhandKommentarID(k.getId(), new AsyncCallback<Kommentar>() {

								@Override
								public void onFailure(Throwable caught) {
									System.out.println("Fehler in findeUserIDAnhandkommentarID");
									
								}

								@Override
								public void onSuccess(Kommentar result) {
									 if(result.getUserID() == userID ){
	
										kloeschen.setEnabled(true);
										kbearbeiten.setEnabled(true);
										
										}
										else{
											kloeschen.setEnabled(false);
											kbearbeiten.setEnabled(false);

										}
									 
									
								}
							});
							
							
					
							final Label text = new Label();
							text.setText(k.getText());
							
							final Label lID = new Label();
							lID.setText(String.valueOf(k.getId()));
							lID.setVisible(false);
							commentUpdatedLabel.setText(String.valueOf(k.getErstellungsZeitpunkt()));
						
							socialmedia.findeUserAnhandKommentarID(k.getId(),
									new AsyncCallback<User>() {

										@Override
										public void onFailure(Throwable caught) {
											System.out.println("Fehler bei der Async Klasse "+caught.getMessage());
										}

										@Override
										public void onSuccess(User result) {
							
												nichnameAnzeigen.setText(result.getNickname()+" sagt :  ");
										
										}
									});
							kommentarTable.getFlexCellFormatter().setColSpan(zeile, 3, 290);

						
						
							kommentarTable.setWidget(zeile, 1, nichnameAnzeigen);
							kommentarTable.setWidget(zeile, 3, text);
							kommentarTable.setWidget(zeile, 7, kloeschen);
							kommentarTable.setWidget(zeile, 8, kbearbeiten);
							kommentarTable.setWidget(zeile, 9, commentUpdatedLabel);
							kommentarTable.setWidget(zeile, 11, lID);

							
							zeile++;
							
//							kommentarTable.setStyleName("kommentarTable");
							
							kloeschen.addClickHandler(new ClickHandler(){

								@Override
								public void onClick(ClickEvent event) {
									socialmedia.kommentarLoeschenAnhandKommentarID(k, new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
											Window.alert("Kommentar wurder  nicht gelöscht " + caught.getMessage());

											
										}

										@Override
										public void onSuccess(Void result) {
											Window.alert("Kommentar wurder erfolgreich gelöscht");
											RootPanel.get("Details").clear();
											PinnwandForm pinnForm = new PinnwandForm();
											pinnForm.zeigePost();
											pinnForm.anzeigen();
										}
									});
									
								}
								
							});
							
							
						kbearbeiten.addClickHandler(new ClickHandler(){

							@Override
							public void onClick(ClickEvent event) {
									
								//TODO
								final MeineDialogBox comment = new MeineDialogBox("Bearbeiten");
								comment.center();
								comment.setText("Bearbeiten");
								comment.setContent(k.getText());

								comment.abbrechen.addClickHandler(new ClickHandler() {
									@Override
									public void onClick(ClickEvent event) {
										comment.hide();

									}

								});
								
								comment.ok.addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {
										comment.hide();
										k.setText(comment.getContent());

//										int id = a.getId();
//										final String text = a.getText();
										System.out.println("Parameter "+ k.getText() + " " + k.getId() );
										socialmedia.kommentarEditieren(k.getText(),k.getId(),
												new AsyncCallback<Kommentar>() {

													@Override
													public void onFailure(Throwable caught) {
														Window.alert("Fehler beim Editieren!");
													}

													@Override
													public void onSuccess(Kommentar result) {
														Window.alert("Textbeitrag wurde editiert!" + result.getText()+" "+result.getId());
														
														RootPanel.get("Details").clear();
														PinnwandForm pinnForm = new PinnwandForm();
														pinnForm.zeigePost();
														pinnForm.anzeigen();
														comment.hide();
													}
												});

									}
								});
								
								comment.show();

							}
							
						});
						
						}
						kommentarPanel.add(kommentarTable);
						vPanel.add(kommentarPanel);
						
					
						
					}
				
					

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Hat leider nicht funktioniert");

					}
				});
		
		
	}
}