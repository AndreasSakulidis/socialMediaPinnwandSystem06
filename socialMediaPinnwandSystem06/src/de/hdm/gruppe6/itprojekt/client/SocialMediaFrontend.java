package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, Özlem Gül, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse SocialMediaFrontend enthält die Menüleiste und ruft die Methode zur Überprüfung der Anmeldung auf. //SO STEHEN LASSEN?
 */

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.gruppe6.itprojekt.shared.bo.User;

public class SocialMediaFrontend extends Composite {

	private VerticalPanel mainPanel = new VerticalPanel();
	private Label userSuchen = new Label("User suchen:");
	private VerticalPanel suchePanel = new VerticalPanel();
	final VerticalPanel aboPanel = new VerticalPanel();
	private Button abo = new Button ("Abos anzeigen");
	private Label beispiel = new Label("Bsp.: Schmidt");
	private Label trennlinie = new Label("______________");
	
	// Registrierung
	private TextBox tbName = new TextBox();
	private PasswordTextBox tbPasswort = new PasswordTextBox();
	private Button loginButton = new Button("Anmelden");
	
	static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	//public SocialMediaFrontend() {
	
	public Widget angemeldet() {
	
		trennlinie.addStyleName("linie");
		// Menübar erstellen
		Command cmd1 = new Command() {
			public void execute() {
				mainPanel.clear();
				FormInfosVonUserReport eins = new FormInfosVonUserReport("");
				mainPanel.add(eins);
			}
		};

		Command cmd2 = new Command() {
			public void execute() {
				mainPanel.clear();
				FormInfosVonBeitragReport zwei = new FormInfosVonBeitragReport("");
				mainPanel.add(zwei);
			}
		};

		Command cmd3 = new Command() {
			public void execute() {
				mainPanel.clear();
				FormInfosVonAllenUsernReport drei = new FormInfosVonAllenUsernReport("");
				mainPanel.add(drei);
			}
		};

		Command cmd4 = new Command() {
			public void execute() {
				mainPanel.clear();
				FormInfosVonAllenBeitraegenReport vier = new FormInfosVonAllenBeitraegenReport(
						"");
				mainPanel.add(vier);
			}
		};
		Command cmd5 = new Command() {
			public void execute() {
				mainPanel.clear();
				PinnwandForm pF = new PinnwandForm();
				mainPanel.add(pF.zeigePost());
			}
		};

		Command cmd6 = new Command() {
			public void execute() {
				mainPanel.clear();
				PinnwandAnzeigenForm pAF = new PinnwandAnzeigenForm();
				mainPanel.add(pAF.zeigePinnwand()); //TODO Hier wird ein Fehler in der Konsole ausgegeben
			}
		};
		
		Command logout = new Command() {
			public void execute() {
				Cookies.removeCookie("SocialMedia6");
				mainPanel.clear();
				suchePanel.clear();
				RootPanel.get("Header").clear();
				RootPanel.get().clear();
				User u = new User();
				u.abmelden();
				tbName.setVisible(true);
				tbPasswort.setVisible(true);
				loginButton.setVisible(true);
				tbName.setText("");
				tbPasswort.setText("");
				mainPanel.add(tbName);
				mainPanel.add(tbPasswort);
				mainPanel.add(loginButton);
				
			}
		};
		


		MenuBar fooMenu = new MenuBar(true);

		MenuBar barMenu = new MenuBar(true);

		MenuBar reportMenu = new MenuBar(true);
		reportMenu.addItem("Infoausgabe von User", cmd1);
		reportMenu.addItem("Infoausgabe von Beitrag", cmd2);
		reportMenu.addItem("Infosausgabe von allen Usern", cmd3);
		reportMenu.addItem("Infosausgabe von allen Beitreagen", cmd4);
		reportMenu.addStyleName("reportmenu");
		fooMenu.addItem("Textbeitrag posten", cmd5);
		fooMenu.addItem("Pinnwand anzeigen", cmd6);
		//
		// Make a new menu bar, adding a few cascading menus to it.
		MenuBar menu = new MenuBar();
		menu.addItem("Pinnwand", fooMenu);
		menu.addItem("Reports", reportMenu);
		menu.addItem("Einstellungen", barMenu);
		menu.addItem("LogOut", logout);

		// Add it to the root panel.
		RootPanel.get("Header").add(menu);

		// public void suchen(){
		final Button sendSucheButton = new Button("Suchen");
		final TextBox nameField = new TextBox();


		// We can add style names to widgets
		sendSucheButton.addStyleName("sendSucheButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		

		suchePanel.add(userSuchen);
		suchePanel.add(nameField);
		suchePanel.add(beispiel);
		suchePanel.add(sendSucheButton);
		suchePanel.add(aboPanel);
		aboPanel.add(trennlinie);
		aboPanel.add(abo);
		aboPanel.add(trennlinie);
		aboPanel.addStyleName("abo");
		
		abo.addClickHandler(new ClickHandler(){
			VerticalPanel aboPanel= new VerticalPanel();

			public void onClick(ClickEvent event){
				
					
					final FlexTable aboTable = new FlexTable();
					initWidget(this.aboPanel);
			        
					// Erstelle Tabelle für Infos von einem bestimmten Beitrag in einem
					// bestimmten Zeitraum
					aboTable.setText(0, 0, "Nickname");
					aboTable.setText(0, 1, "Löschen");
					
					suchePanel.add(aboTable);
					aboTable.addStyleName("flextable");
		   
		}


	});
		
		
		
		
		sendSucheButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				System.out.println("On Click in der User Trefferliste ");
				System.out.println(nameField.getText());

				String text = nameField.getText();

				if (text.isEmpty()) {

					UserTrefferliste ut = new UserTrefferliste();
					mainPanel.clear();
					mainPanel.add(ut.zeigeTabelle());


				} else {
					mainPanel.clear();
					System.out.println("Else Block, Namefeld ist: "
							+ nameField.getText());
					UserTrefferliste ut = new UserTrefferliste();
					mainPanel.add(ut.zeigeUserNameTabelle(text));

				}

			}
		});
		
		
		// Add it to the root Panel
		RootPanel.get("Details").add(mainPanel);

		nameField.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				System.out.println("Keypress ");
				System.out.println(nameField.getText());
				String text = nameField.getText();
				System.out.println(("Event von Key: " + event.getCharCode()));
				if (text.isEmpty() && event.getCharCode() == KeyCodes.KEY_ENTER) {

					UserTrefferliste ut = new UserTrefferliste();
					mainPanel.clear();
					mainPanel.add(ut.zeigeTabelle());

				} else if (event.getCharCode() == KeyCodes.KEY_ENTER) {

//					boolean test = nameField.equals("");
					System.out.println("Else Block, Keypress ist: "
							+ nameField.getText());
					UserTrefferliste ut = new UserTrefferliste();
					mainPanel.clear();
					mainPanel.add(ut.zeigeUserNameTabelle(text));

				}

			}

		});
		
		
		// Add it to the root panel.
		RootPanel.get("Navigator").add(suchePanel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();
		
		return mainPanel;
	}
	
	

}
