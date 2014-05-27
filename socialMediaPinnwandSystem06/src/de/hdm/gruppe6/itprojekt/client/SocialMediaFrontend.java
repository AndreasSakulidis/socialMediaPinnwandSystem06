package de.hdm.gruppe6.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SocialMediaFrontend extends Composite {

	private static final int REFRESH_INTERVAL = 5000; // ms
	private VerticalPanel mainPanel = new VerticalPanel();
	private Label userSuchen = new Label("User suchen:");
	private VerticalPanel suchePanel = new VerticalPanel();
	final VerticalPanel aboPanel = new VerticalPanel();
	private Button abo = new Button ("Abos anzeigen");
	private Label beispiel = new Label("Bsp.: Schmidt");
	private Label trennlinie = new Label("______________");
	
	

	public SocialMediaFrontend() {
		trennlinie.addStyleName("linie");
		// Menübar erstellen
		Command cmd1 = new Command() {
			public void execute() {
				mainPanel.clear();
				InfosVonUserReport eins = new InfosVonUserReport("");
				mainPanel.add(eins);
			}
		};

		Command cmd2 = new Command() {
			public void execute() {
				mainPanel.clear();
				InfosVonBeitragReport zwei = new InfosVonBeitragReport("");
				mainPanel.add(zwei);
			}
		};

		Command cmd3 = new Command() {
			public void execute() {
				mainPanel.clear();
				InfosVonAllenUsernReport drei = new InfosVonAllenUsernReport("");
				mainPanel.add(drei);
			}
		};

		Command cmd4 = new Command() {
			public void execute() {
				mainPanel.clear();
				InfosVonAllenBeitraegenReport vier = new InfosVonAllenBeitraegenReport(
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
		menu.addItem("LogOut", barMenu);

		// Add it to the root panel.
		RootPanel.get("Header").add(menu);

		// public void suchen(){
		final Button sendSucheButton = new Button("Suchen");
		final TextBox nameField = new TextBox();
		// nameField.setText("");
		// final Label errorLabel = new Label();

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
//					RootPanel.get("Navigator").add(aboPanel);
		   
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
					// mainPanel.add(ut.zeigeUserNameTabelle("Simpson"));

				} else {
					// boolean test = nameField.equals("");
					mainPanel.clear();
					System.out.println("Else Block, Namefeld ist: "
							+ nameField.getText());
					UserTrefferliste ut = new UserTrefferliste();
					// mainPanel.clear();
					// mainPanel.add(ut.zeigeTabelle());
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

					boolean test = nameField.equals("");
					System.out.println("Else Block, Keypress ist: "
							+ nameField.getText());
					UserTrefferliste ut = new UserTrefferliste();
					mainPanel.clear();
					mainPanel.add(ut.zeigeUserNameTabelle(text));

				}

			}
			// if (event.getCharCode() == KeyCodes.KEY_ENTER) {
			// addPost();
		});
		
		
		// Add it to the root panel.
		RootPanel.get("Navigator").add(suchePanel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();
	}

}
