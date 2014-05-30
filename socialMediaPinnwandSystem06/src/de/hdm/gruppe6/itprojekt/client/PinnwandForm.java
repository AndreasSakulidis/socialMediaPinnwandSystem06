package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, Özlem Gül, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse PinnwandForm hat die Funktion einen Textbeitrag und Kommentare zu erstellen.
 */

import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;

public class PinnwandForm extends Widget {

	private VerticalPanel addPanel = new VerticalPanel();
	private TextArea ta = new TextArea();
	private Button textbeitragPosten = new Button("Add Post");
	public PinnwandForm() {
		// Größe des Textareas

	}
	
//	public Widget zeigePost() {
//		ta.setCharacterWidth(60);
//		ta.setVisibleLines(5);
//		// Anordnung von textarea und button
//		addPanel.add(ta);
//		addPanel.add(textbeitragPosten);
//		addPanel.addStyleName("addPanel");
//
//		// RootPanel.get("Details").clear();
//		RootPanel.get("Details").add(addPanel);
//		textbeitragPosten.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				String a = ta.getText();
//
//				Beitrag b = new Beitrag(a);
//				b.setPost(a);
////				addPanel.add(b);
//				ta.setText("");
//			}
//		});
//		
//		
//		
//		return addPanel; }

}