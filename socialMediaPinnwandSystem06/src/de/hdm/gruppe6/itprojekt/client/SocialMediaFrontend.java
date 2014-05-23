package de.hdm.gruppe6.itprojekt.client;


import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

public class SocialMediaFrontend extends Composite {
	
	
		private static final int REFRESH_INTERVAL = 5000; // ms
		private HorizontalPanel addPanel = new HorizontalPanel();
		private VerticalPanel mainPanel = new VerticalPanel();
		private TextArea ta = new TextArea();
		private Button textbeitragPosten = new Button("Add Post");
		private HorizontalPanel addNavPanel = new HorizontalPanel();
		
	
public SocialMediaFrontend (){

		
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
					addPanel.add(zwei);
				}
			};
			
			Command cmd3 = new Command() {
				public void execute() {
					addPanel.clear();
					InfosVonAllenUsernReport drei = new InfosVonAllenUsernReport("");
					addPanel.add(drei);
				}
			};
			
			Command cmd4 = new Command() {
				public void execute() {
					addPanel.clear();
					InfosVonAllenBeitraegenReport vier = new InfosVonAllenBeitraegenReport("");
					addPanel.add(vier);
				}
			};
			Command cmd5 = new Command() {
				public void execute() {
					addPanel.clear();
					PinnwandForm pF = new PinnwandForm ();
					addPanel.add(pF);
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
			fooMenu.addItem("Pinnwand",cmd5);
//			
			// Make a new menu bar, adding a few cascading menus to it.
			MenuBar menu = new MenuBar();
			menu.addItem("Pinnwand", fooMenu);
			menu.addItem("Reports", reportMenu);
			menu.addItem("Einstellungen", barMenu);
			menu.addItem("LogOut", barMenu);

			
			
			// Add it to the root panel.
			RootPanel.get("Header").add(menu);

//			public void suchen(){
			final Button sendSucheButton = new Button("Suchen");
			final TextBox nameField = new TextBox();
//			nameField.setText("");
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
						mainPanel.clear();
						mainPanel.add(ut.zeigeTabelle());	
//						mainPanel.add(ut.zeigeUserNameTabelle("Simpson"));

					}
					else {
//						boolean test = nameField.equals("");
						System.out.println("Else Block, Namefeld ist: "+nameField.getText());
						UserTrefferliste ut = new UserTrefferliste();
//						mainPanel.clear();
//						mainPanel.add(ut.zeigeTabelle());	
						mainPanel.add(ut.zeigeUserNameTabelle(text));
						
					}

				}
			});
			
			
			// Add it to the root panel.
		    RootPanel.get("Navigator").add(addNavPanel);
			
			// Focus the cursor on the name field when the app loads
			nameField.setFocus(true);
			nameField.selectAll();
			
		}
}

			
