package de.hdm.gruppe6.itprojekt.client;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;

public class UserBearbeiten {

	private VerticalPanel vPanel = new VerticalPanel();
	private Label lbRname = new Label("Name");
	private TextBox tbRname = new TextBox();
	private Label lbRs = new Label("Nachname");
	private TextBox tbNachname = new TextBox();
	private TextBox tbNick = new TextBox();
	private Label lbEmail = new Label("Email-Adresse");
	private TextBox tbEmail = new TextBox();
	private Label lbRPasswort = new Label("Passwort");
	private PasswordTextBox tbRPasswort = new PasswordTextBox();
	private Label bearbeiten = new Label("User Einstellungen bearbeiten: ");
	private Button bbutton = new Button("Bearbeiten");
	private Label info = new Label("Hier klicken um Profil zu löschen: ");
	private Button lbutton = new Button("Profil löschen");
	private VerticalPanel deleteUserPanel = new VerticalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();
	private Label hinweisnick = new Label();

	PinnwandVerwaltungServiceAsync pinnwandVerwaltung = GWT
			.create(PinnwandVerwaltungService.class);

	public UserBearbeiten() {

	}

	public Widget bearbeiteProfil() {

		final String id = Cookies.getCookie("SocialMedia6ID");
		int uid = Integer.parseInt(id);
		pinnwandVerwaltung.findeUserAnhandID(uid, new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim loeschen!");
			}

			@Override
			public void onSuccess(User result) {
				vPanel.add(bearbeiten);
				bearbeiten.addStyleName("bearbeitenLabel");
				vPanel.add(lbRname);
				vPanel.add(tbRname);
				tbRname.setText(result.getVorname());
				vPanel.add(lbRs);
				vPanel.add(tbNachname);
				tbNachname.setText(result.getNachname());
				vPanel.add(tbNick);
				vPanel.add(hinweisnick);
				tbNick.setText(result.getNickname());
				tbNick.setVisible(false);

				vPanel.add(lbEmail);
				vPanel.add(tbEmail);
				tbEmail.setText(result.getEmail());
				vPanel.add(lbRPasswort);
				vPanel.add(tbRPasswort);
				tbRPasswort.setText(result.getPasswort());
				vPanel.add(bbutton);
				bbutton.setStyleName("editButton");
				deleteUserPanel.add(info);
				deleteUserPanel.add(lbutton);
				lbutton.setStyleName("deleteProfileButton");
				deleteUserPanel.addStyleName("deleteUserPanel");

				mainPanel.add(vPanel);
				mainPanel.add(deleteUserPanel);
				mainPanel.addStyleName("einstellungenPanel");
				RootPanel.get("Details").add(mainPanel);

				hinweisnick.addStyleName("hinweisnick");
				tbNick.addStyleName("nick");

			}
		});

		lbutton.addClickHandler(new ClickHandler() {
						
			public void onClick(ClickEvent event) {
				final Warnung warnung = new Warnung("");
				warnung.center();
				warnung.setText("Profil wirklich löschen?");

				warnung.abbrechen.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						warnung.hide();

					}

				});

				warnung.ok.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						

						final String id = Cookies.getCookie("SocialMedia6ID");
						int uid = Integer.parseInt(id);
						String vorname = tbRname.getText();
						String nachname = tbNachname.getText();
						String nickname = tbNick.getText();
						String email = tbEmail.getText();
						String passwort = tbRPasswort.getText();
					
						pinnwandVerwaltung.userLoeschen(uid, vorname, nachname,
								nickname, email, passwort,
								new AsyncCallback<Void>() {
									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Fehler beim Löschen!");
									}

									@Override
									public void onSuccess(Void result) {
										
										final String id = Cookies.getCookie("SocialMedia6ID");
										final int uid = Integer.parseInt(id);
										int pinnwandID = uid;
									
										pinnwandVerwaltung.findeTextbeitraegeAnhandPinnwandID(pinnwandID, new AsyncCallback<ArrayList<Textbeitrag>>(){
											public void onFailure(Throwable caught){
												Window.alert("Fehler");
											}
											public void onSuccess(ArrayList<Textbeitrag> result){
												
												
//												for (Textbeitrag tb : result){
//												
//													
//													
//													pinnwandVerwaltung.lZuUserLoeschen(uid, null, null,
//															null, null, null,
//															new AsyncCallback<Void>() {
//																@Override
//																public void onFailure(Throwable caught) {
//																	Window.alert("Fehler beim kLöschen!");
//																}
//
//																@Override
//																public void onSuccess(Void result) {
//																
//																}
//															});
//													
//													
//
//													
//												}
												
												for (Textbeitrag tb : result){
													int t = tb.getId();
													String text = tb.getText();
												
												

													pinnwandVerwaltung.lZuTextbeitragLoeschen(text, t, new AsyncCallback<Void>(){
														
														public void onFailure(Throwable caught){
															Window.alert("Fehler beim llöschen");
														}
														
														public void onSuccess(Void result){
															
														}
													});	
													
													

												
												
												pinnwandVerwaltung.kZuTextbeitragLoeschen(text, t,
														new AsyncCallback<Void>() {
															@Override
															public void onFailure(Throwable caught) {
																Window.alert("Fehler beim kLöschen!");
															}

															@Override
															public void onSuccess(Void result) {
																										
															}
														});
												}
												
											}
											
										});
										
										
										

										String vorname = tbRname.getText();
										String nachname = tbNachname.getText();
										String nickname = tbNick.getText();
										String email = tbEmail.getText();
										String passwort = tbRPasswort.getText();
										pinnwandVerwaltung.tZuUserLoeschen(uid, vorname, nachname,
												nickname, email, passwort,
												new AsyncCallback<Void>() {
													@Override
													public void onFailure(Throwable caught) {
														Window.alert("Fehler beim tLöschen!");
													}

													@Override
													public void onSuccess(Void result) {
														
														
														

																										
													}
												});
										
										
										pinnwandVerwaltung.kZuUserLoeschen(uid, vorname, nachname,
												nickname, email, passwort,
												new AsyncCallback<Void>() {
													@Override
													public void onFailure(Throwable caught) {
														Window.alert("Fehler beim kLöschen!");
													}

													@Override
													public void onSuccess(Void result) {
														
													}
												});
										
										pinnwandVerwaltung.lZuUserLoeschen(uid, vorname, nachname,
												nickname, email, passwort,
												new AsyncCallback<Void>() {
													@Override
													public void onFailure(Throwable caught) {
														Window.alert("Fehler beim lLöschen!");
													}

													@Override
													public void onSuccess(Void result) {
																	
														
													}
												});
										
										pinnwandVerwaltung.aZuUserLoeschenAnhandPID(uid, vorname, 
												nachname, nickname, email, passwort, new AsyncCallback<Void>() {

											@Override
											public void onFailure(
													Throwable caught) {
												
												
											}

											@Override
											public void onSuccess(Void result) {
												
												
											}
										});
										
										pinnwandVerwaltung.aZuUserLoeschen(uid, vorname, nachname,
												nickname, email, passwort,
												new AsyncCallback<Void>() {
													@Override
													public void onFailure(Throwable caught) {
														Window.alert("Fehler beim aLöschen!");
													}

													@Override
													public void onSuccess(Void result) {
																										
													}
												});
										
										mainPanel.clear();
										RootPanel.get("Navigator").clear();
										RootPanel.get("Header").clear();
										RootPanel.get().clear();
										Window.alert("Deine Prodildaten wurden erfolgreich gelöscht!");
										Cookies.removeCookie("SocialMedia6");
										Anmelden startseite = new Anmelden();
										startseite.anmelden();
									}
								});
						warnung.hide();

					}
				});

				warnung.show();
			}

		});
		Anmelden startseite = new Anmelden();
		

		bbutton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				int uid = Integer.parseInt(id);
				String vorname = tbRname.getText();
				String nachname = tbNachname.getText();
				String nickname = tbNick.getText();
				String email = tbEmail.getText();
				String passwort = tbRPasswort.getText();
				pinnwandVerwaltung.userEditieren(uid, vorname, nachname,
						nickname, email, passwort, new AsyncCallback<User>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Fehler beim Editieren!");
							}

							@Override
							public void onSuccess(User result) {
								Window.alert("Deine Profildaten wurden erfolgreich editiert!");

							}
						});
			}

		});

		return mainPanel;
	}
}
