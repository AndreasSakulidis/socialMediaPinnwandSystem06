package de.hdm.gruppe6.itprojekt.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.User;

public class UserBearbeiten {

	private VerticalPanel vPanel = new VerticalPanel();
	private Label lbRname = new Label("Name");
	private TextBox tbRname = new TextBox();
	private Label lbRs = new Label("Nachname");
	private TextBox tbNachname = new TextBox();
	private Label lbNick = new Label("Nickname");
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
				vPanel.add(lbRname);
				vPanel.add(tbRname);
				tbRname.setText(result.getVorname());
				vPanel.add(lbRs);
				vPanel.add(tbNachname);
				tbNachname.setText(result.getNachname());
				vPanel.add(lbNick);
				vPanel.add(tbNick);
				vPanel.add(hinweisnick);
				tbNick.setText(result.getNickname());
				vPanel.add(lbEmail);
				vPanel.add(tbEmail);
				tbEmail.setText(result.getEmail());
				vPanel.add(lbRPasswort);
				vPanel.add(tbRPasswort);
				tbRPasswort.setText(result.getPasswort());
				vPanel.add(bbutton);
				deleteUserPanel.add(info);
				deleteUserPanel.add(lbutton);
				deleteUserPanel.addStyleName("deleteUserPanel");
				
				mainPanel.add(vPanel);
				mainPanel.add(deleteUserPanel);
				mainPanel.addStyleName("einstellungenPanel");
				RootPanel.get("Details").add(mainPanel);
				
				hinweisnick.addStyleName("hinweisnick");
				tbNick.addStyleName("nick");
		
			}
		});
		
		lbutton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				int uid = Integer.parseInt(id);
				String vorname = tbRname.getText();
				String nachname = tbNachname.getText();
				String nickname = tbNick.getText();
				String email = tbEmail.getText();
				String passwort = tbRPasswort.getText();
				pinnwandVerwaltung.userLoeschen(uid, vorname, nachname,
						nickname, email, passwort, new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Fehler beim Löschen!");
							}

							@Override
							public void onSuccess(Void result) {
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
			}
			
		});

		bbutton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				int uid = Integer.parseInt(id);
				String vorname = tbRname.getText();
				String nachname = tbNachname.getText();
				String nickname = tbNick.getText();
				String email = tbEmail.getText();
				String passwort = tbRPasswort.getText();
				pinnwandVerwaltung.userEditieren(uid, vorname, nachname, nickname,
						email, passwort, new AsyncCallback<User>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Fehler beim Editieren!");
							}

							@Override
							public void onSuccess(User result) {
								Window.alert("Deine Profildaten wurden erfolgreich editiert!");
								mainPanel.clear();
								RootPanel.get("Navigator").clear();
								RootPanel.get("Header").clear();
								RootPanel.get().clear();
							}
						});
			}

		});

		return mainPanel;
	}
}
