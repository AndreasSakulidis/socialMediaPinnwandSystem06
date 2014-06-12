package de.hdm.gruppe6.itprojekt.client;
/**
 * @author Bharti Kumar, �zlem G�l, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse UserTrefferliste erstellt die Flextable von der UserSuche.
 */

import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.Abonnement;
import de.hdm.gruppe6.itprojekt.shared.bo.User;


public class UserTrefferliste{

	VerticalPanel mainPanelUser = new VerticalPanel();

	PinnwandVerwaltungServiceAsync pinnwandVerwaltung = GWT
			.create(PinnwandVerwaltungService.class);
	
	public UserTrefferliste(){
		
	}
	
	public Widget zeigeTabelle() {
		// Erstellen einer flexiblen Tabelle
		final FlexTable flexTableUser = new FlexTable();
		
		//HTML class hinzufügen, damit die Tabelle das Design annimmt.
//		DOM.setElementAttribute(flexTableUser.getElement(), "class", "table table-striped table table-bordered");
		
		//Spaltenbezeichnungen hinzuf�gen
		flexTableUser.setText(0, 0, "ID");
		flexTableUser.setText(0, 1, "Name");
		flexTableUser.setText(0, 2, "Vorname");
		flexTableUser.setText(0, 3, "Nickname");
		flexTableUser.setText(0, 4, "E-Mail");
		flexTableUser.setText(0, 5, "Abonnieren");

		flexTableUser.getRowFormatter().addStyleName(0, "userliste");
		// Hier sollen die Usereintr�ge aus der Datenbank ausgelesen und in die Tabelle eingetragen werden,
		// Bei Fehlern soll eine Fehlermeldung erscheinen

		pinnwandVerwaltung.findeAlleUser(new AsyncCallback<Vector<User>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Ein Fehler ist aufgetreten! - " + caught.getMessage());

					}

					@Override
					public void onSuccess(Vector<User> result) {
						int rowCounter = 1;

                        // Hier werden die Objekte durchlaufen. 
						//User ist final, da es in Inner Class ClickHandler verwendet  werden soll			
						
						for (final User u : result) {

							Button a = new Button("+");

							String nachname, nickname, email, vorname;
							
							nachname = u.getNachname();
							vorname = u.getVorname();
							nickname = u.getNickname();
							email = u.getEmail();
							
							// Das Label soll mit Inhalt gef�llt werden
							Label labNach = new Label(nachname);
							Label labVor = new Label(vorname);
							Label labNick = new Label(nickname);
							Label labEm = new Label(email);
							
							// Hier werden die Labels in der Tabelle positioniert
							flexTableUser.setText(rowCounter, 0, String.valueOf(u.getId()));
							flexTableUser.setWidget(rowCounter, 1, labNach);
							flexTableUser.setWidget(rowCounter, 2, labVor);
							flexTableUser.setWidget(rowCounter, 3, labNick);
							flexTableUser.setWidget(rowCounter, 4, labEm);
							flexTableUser.setWidget(rowCounter, 5, a);
							a.setStyleName("aboButton");

							a.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									String id = Cookies.getCookie("SocialMedia6ID");
									int pid = u.getId();
									int uid = Integer.parseInt(id);
									if(uid == pid){
										Window.alert("Such dir Freunde!");
									}
									else {
										
									
									pinnwandVerwaltung.aboAnlegen(id, pid, new AsyncCallback<Abonnement>() {
										@Override
										public void onSuccess(Abonnement result) {
											if (result == null) {
												final Warnung warnung = new Warnung("");
												warnung.center();
												warnung.setText("Dieses Abonnement existiert bereits!");
												warnung.abbrechen.addClickHandler(new ClickHandler(){
													public void onClick(ClickEvent event){
														warnung.hide();
													}
												});
												warnung.ok.addClickHandler(new ClickHandler(){
													public void onClick(ClickEvent event){
														warnung.hide();
														}
													});
											} else {
											Window.alert("Abo wurde angelegt");
											//RootPanel.get("Navigator").clear();
											Window.Location.reload();
											}
										}
										@Override
										public void onFailure(Throwable caught) {
											System.out
													.println("hat nicht geklappt mit den Post ausgaben: "
															+ caught.getMessage());
										}
									});
									}
							
							}
						
						});
					

							//Zeile hochzählen
							rowCounter++;
						}

					}

				}

				);
							
		mainPanelUser.add(flexTableUser);
		// das Flextable wird dem mainPanelUser hinzugef�gt.
		return mainPanelUser;
	}
	
	public Widget zeigeUserNameTabelle(String nickname) {
		// Erstellen einer flexiblen Tabelle
		final FlexTable flexTableUser = new FlexTable();
		
		//Spaltenbezeichnungen hinzuf�gen
		flexTableUser.setText(0, 0, "ID");
		flexTableUser.setText(0, 1, "Name");
		flexTableUser.setText(0, 2, "Vorname");
		flexTableUser.setText(0, 3, "Nickname");
		flexTableUser.setText(0, 4, "E-Mail");
		flexTableUser.setText(0, 5, "Abonnieren");

		// Hier sollen die Usereintr�ge aus der Datenbank ausgelesen und in die Tabelle eingetragen werden,
		// Bei Fehlern soll eine Fehlermeldung erscheinen
//		pinnwandVerwaltung.findeUserAnhandNachname(, new AsyncCallbackVector<User>);
		
		pinnwandVerwaltung.findeUserAnhandNickname(nickname, new AsyncCallback<Vector<User>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ein Fehler ist aufgetreten! - " + caught.getMessage());
				
			}

			@Override
			public void onSuccess(Vector<User> result) {
				int rowCounter = 1;
				System.out.println("TESTTESTTESTTEST");
//				System.out.println("result test: "+result.toString());
                // Hier werden die Objekte durchlaufen. 
				//User ist final, da es in Inner Class ClickHandler verwendet  werden soll			
				
				for (final User u : result) {

					Button a = new Button("+");

					String nachname, nickname, email, vorname;
					
					nachname = u.getNachname();
					vorname = u.getVorname();
					nickname = u.getNickname();
					email = u.getEmail();
					
					// Das Label soll mit Inhalt gef�llt werden
					Label labNach = new Label(nachname);
					Label labVor = new Label(vorname);
					Label labNick = new Label(nickname);
					Label labEm = new Label(email);
					
					// Hier werden die Labels in der Tabelle positioniert
					flexTableUser.setText(rowCounter, 0, String.valueOf(u.getId()));
					flexTableUser.setWidget(rowCounter, 1, labNach);
					flexTableUser.setWidget(rowCounter, 2, labVor);
					flexTableUser.setWidget(rowCounter, 3, labNick);
					flexTableUser.setWidget(rowCounter, 4, labEm);
					flexTableUser.setWidget(rowCounter, 5, a);

					a.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							String id = Cookies.getCookie("SocialMedia6ID");
							int pid = u.getId();
							int uid = Integer.parseInt(id);

							if(uid == pid){
								Window.alert("Such dir Freunde!");
							}
							else{
								
							
							pinnwandVerwaltung.aboAnlegen(id, pid, new AsyncCallback<Abonnement>() {
								@Override
								public void onSuccess(Abonnement result) {
									if (result == null) {
										final Warnung warnung = new Warnung("");
										warnung.center();
										warnung.setText("Dieses Abonnement existiert bereits!");
										warnung.abbrechen.addClickHandler(new ClickHandler(){
											public void onClick(ClickEvent event){
												warnung.hide();
											}
										});
										warnung.ok.addClickHandler(new ClickHandler(){
											public void onClick(ClickEvent event){
												warnung.hide();
												}
											});
									} else {
									Window.alert("Abo wurde angelegt");
									Window.Location.reload();
									}
								}
								@Override
								public void onFailure(Throwable caught) {
									System.out
											.println("hat nicht geklappt mit den Post ausgaben: "
													+ caught.getMessage());
								}
							});
							}
					
					}

					});

					//Zeile hochzählen
					rowCounter++;
				}

				
				
				
			}
		});

		mainPanelUser.add(flexTableUser);
		// das Flextable wird dem mainPanelUser hinzugef�gt.
		return mainPanelUser;
	}




}
