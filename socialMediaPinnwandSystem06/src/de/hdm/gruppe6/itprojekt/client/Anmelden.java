package de.hdm.gruppe6.itprojekt.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.User;

public class Anmelden{

	private HorizontalPanel hPanel = new HorizontalPanel();
	private Label lbname = new Label("Name   :  ");
	private TextBox tbName = new TextBox();
	private Label lbPasswort = new Label("Passwort   :  ");
	private PasswordTextBox tbPasswort = new PasswordTextBox();
	private Button loginButton = new Button("Anmelden");
	private Label anmelden = new Label("Anmeldung");

	// Registrierung

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
	private Button regButton = new Button("Registrieren");
	private Label regi = new Label("Registrierung ");
	
	private Label lTrennWand = new Label("-");
	private static final int REFRESH_INTERVAL = 5000; // ms
	private VerticalPanel addPanel = new VerticalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();
	private TextArea ta = new TextArea();
	private Button textbeitragPosten = new Button("Add Post");
	private HorizontalPanel addNavPanel = new HorizontalPanel();
	private Button alleUserButton = new Button();
	private Button alleBeitraegeButton = new Button(); 

	private HorizontalPanel horziPanel= new HorizontalPanel();
	
	private PinnwandVerwaltungServiceAsync socialmedia = GWT.create(PinnwandVerwaltungService.class);
	
	

	public Widget anmelden() {
		
		
		addPanel.add(lbname);
		addPanel.add(tbName);
		addPanel.add(lbPasswort);
		addPanel.add(tbPasswort);
		addPanel.add(loginButton);
		
//		for(int i = 0; i<10; i++){
//			addPanel.add(lTrennWand);
//		}
//		vPanel.add(addPanel);
		
		addPanel.add(lbRname);
		addPanel.add(tbRname);
		addPanel.add(lbRs);
		addPanel.add(tbNachname);
		addPanel.add(lbNick);
		addPanel.add(tbNick);
		addPanel.add(lbRPasswort);
		addPanel.add(tbRPasswort);
		addPanel.add(lbEmail);
		addPanel.add(tbEmail);
		addPanel.add(regButton);
		addPanel.add(regi);
		
		regButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				
				socialmedia.userAnlegen(tbRname.getText(), tbNachname.getText(), tbNick.getText(), tbEmail.getText(), tbRPasswort.getText(), new AsyncCallback<User>() {
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(User result) {
						Window.alert("Anlegen erfolgreich!");
					}
				});
				
			}
		});
		
//		addPanel.add(addPanel);
		
		RootPanel.get("Details").add(addPanel);
//		RootPanel.get().add(vPanel);


//		hPanel.add(anmelden);
		loginButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				socialmedia.userAnmelden(tbName.getText(), tbPasswort.getText(), new AsyncCallback<User>() {
					
					@Override
					public void onSuccess(User result) {
//						System.out.println("Userpasswort und Name "+result.getPasswort()+" "+result.getNickname()); 
						if(result!= null){
							
							RootPanel.get("Details").clear();

					Window.alert("Erfolgreich angemeldet... Nickname: "+result.getNickname()+" und Passwort"+result.getPasswort());	
					tbName.setVisible(false);
					tbPasswort.setVisible(false);
					loginButton.setVisible(false);
					
					SocialMediaFrontend hauptseite = new SocialMediaFrontend();
					
		
		
						

//					--------------------------------------------------------------------------------------------------------
					
	
	
						}
						else{
							Window.alert("User ist leer... ");
						}
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Ein fehler ist aufgetreten: "+caught.getMessage());
						
					}
				} );
				
			}
		});
		

			
		
		
		
		regi.addStyleName("regi‹ber");
		anmelden.addStyleName("Anmelde‹ber");
		hPanel.addStyleName("Anmelden");
		vPanel.addStyleName("Regi");
		
		horziPanel.add(hPanel);
		horziPanel.add(vPanel);

		return horziPanel;
		}
}
