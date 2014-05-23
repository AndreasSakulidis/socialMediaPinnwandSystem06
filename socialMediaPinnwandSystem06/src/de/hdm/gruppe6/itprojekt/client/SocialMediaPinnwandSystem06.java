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

//	private static final int REFRESH_INTERVAL = 5000; // ms
//	private HorizontalPanel addPanel = new HorizontalPanel();
//	private VerticalPanel mainPanel = new VerticalPanel(); //wird nicht benötigt
//	private TextArea ta = new TextArea();
//	private Button textbeitragPosten = new Button("Add Post");
//	private HorizontalPanel addNavPanel = new HorizontalPanel();
//	private Button alleUserButton = new Button();
//	private Button alleBeitraegeButton = new Button(); 
	Anmelden an = new Anmelden();
	

	public void onModuleLoad() {
		
		an.anmelden();

}
}