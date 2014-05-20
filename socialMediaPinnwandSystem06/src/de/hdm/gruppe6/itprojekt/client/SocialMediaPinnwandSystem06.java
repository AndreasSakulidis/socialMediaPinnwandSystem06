package de.hdm.gruppe6.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SocialMediaPinnwandSystem06 implements EntryPoint {

	private static final int REFRESH_INTERVAL = 5000; // ms
	private HorizontalPanel addPanel = new HorizontalPanel();
//	private VerticalPanel mainPanel = new VerticalPanel(); //wird nicht benötigt
	private TextArea ta = new TextArea();
	private Button textbeitragPosten = new Button("Add Post");
	private HorizontalPanel addNavPanel = new HorizontalPanel();
	private Button alleUserButton = new Button();
	private Button alleBeitraegeButton = new Button(); 

	public void onModuleLoad() {

		// Größe des Textareas
		ta.setCharacterWidth(60);
		ta.setVisibleLines(5);

		// Anordnung von textarea und button
		addPanel.add(ta);
		addPanel.add(textbeitragPosten);
		addPanel.addStyleName("addPanel");

		RootPanel.get("Details").add(addPanel);

		// Menübar erstellen
		Command cmd1 = new Command() {
			public void execute() {
				addPanel.clear();
				InfosVonUserReport eins = new InfosVonUserReport("");
				addPanel.add(eins);
			}
		};
		
		Command cmd2 = new Command() {
			public void execute() {
				addPanel.clear();
				InfosVonBeitragReport zwei = new InfosVonBeitragReport("");
			}
		};
		
		Command cmd3 = new Command() {
			public void execute() {
				addPanel.clear();
				InfosVonAllenUsernReport drei = new InfosVonAllenUsernReport("");
			}
		};
		
		Command cmd4 = new Command() {
			public void execute() {
				addPanel.clear();
				InfosVonAllenBeitraegenReport vier = new InfosVonAllenBeitraegenReport("");
			}
		};
		
		MenuBar fooMenu = new MenuBar(true);

		MenuBar barMenu = new MenuBar(true);

		MenuBar reportMenu = new MenuBar(true);
		reportMenu.addItem("Infoausgabe von User", cmd1);
		reportMenu.addItem("Infoausgabe von Beitraeg", cmd2);
		reportMenu.addItem("Infosausgabe von allen Usern", cmd3);
		reportMenu.addItem("Infosausgabe von allen Beitreagen", cmd4);

		// Make a new menu bar, adding a few cascading menus to it.
		MenuBar menu = new MenuBar();
		menu.addItem("Pinnwand", fooMenu);
		menu.addItem("Reports", reportMenu);
		menu.addItem("Einstellungen", barMenu);
		menu.addItem("LogOut", barMenu);

		// Add it to the root panel.
		RootPanel.get("Header").add(menu);

		final Button sendSucheButton = new Button("Suchen");
		final TextBox nameField = new TextBox();
//		nameField.setText("");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendSucheButton.addStyleName("sendSucheButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		
		addNavPanel.add(nameField);
		addNavPanel.add(sendSucheButton);
		addNavPanel.add(errorLabel);
		
		sendSucheButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				System.out.println("On Click in der User Trefferliste ");
				System.out.println(nameField.getText());
				String text = nameField.getText();
				if (text.isEmpty()) {
					
					UserTrefferliste ut = new UserTrefferliste();
					addPanel.clear();
					addPanel.add(ut.zeigeTabelle());	
//					mainPanel.add(ut.zeigeUserNameTabelle("Simpson"));

				}
				else {
					boolean test = nameField.equals("");
					System.out.println("Else Block, Namefeld ist: "+nameField.getText());
					UserTrefferliste ut = new UserTrefferliste();
					addPanel.clear();
//					mainPanel.add(ut.zeigeTabelle());	
					addPanel.add(ut.zeigeUserNameTabelle(text));
					
				}

			}
		});
		
	    nameField.addKeyPressHandler(new KeyPressHandler() {
		      public void onKeyPress(KeyPressEvent event) {
		    	  System.out.println("Keypress ");
					System.out.println(nameField.getText());
					String text = nameField.getText();
					System.out.println(("Event von Key: "+event.getCharCode()));
					if (text.isEmpty() && event.getCharCode() == KeyCodes.KEY_ENTER) {
																																				
						UserTrefferliste ut = new UserTrefferliste();
						addPanel.clear();
						addPanel.add(ut.zeigeTabelle());	

					}
					else if (event.getCharCode() == KeyCodes.KEY_ENTER){
						
						boolean test = nameField.equals("");
						System.out.println("Else Block, Keypress ist: "+nameField.getText());
						UserTrefferliste ut = new UserTrefferliste();
						addPanel.clear();
						addPanel.add(ut.zeigeUserNameTabelle(text));
						
					}

		      }
//		        if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		         // addPost();
	    });
		
		// Add it to the root panel.
	    RootPanel.get("Navigator").add(addNavPanel);
		
		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();


		
		textbeitragPosten.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String a= ta.getText();
				
				Beitrag b = new Beitrag(a);
				RootPanel.get("Details").add(b);
				ta.setText("");
			}
		});

		
		

	
	
	/* Report zur Ausgabe von allen Usern 
	 * 
	 */
			alleUserButton.addStyleName("alleUserAusgeben");

			// Add the nameField and sendButton to the RootPanel
			// Use RootPanel.get() to get the entire body element
			
			RootPanel.get().add(alleUserButton);

			alleUserButton.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					InfosVonAllenUsernReport infoAlleU = new InfosVonAllenUsernReport("");
					RootPanel.get().add(infoAlleU);
					
				}
				
			});
	
	
	/* Report zur Ausgabe von allen Beitraegen 
	 * 
	 */
	
	
	// We can add style names to widgets
	alleBeitraegeButton.addStyleName("alleBeitraegeAusgeben");

	// Add the nameField and sendButton to the RootPanel
	// Use RootPanel.get() to get the entire body element
	
	RootPanel.get().add(alleBeitraegeButton);

	alleBeitraegeButton.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			InfosVonAllenBeitraegenReport iaab = new InfosVonAllenBeitraegenReport("");
			RootPanel.get().add(iaab);
			
		}
		
	});
}
}