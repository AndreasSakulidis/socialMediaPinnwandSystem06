package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, Özlem Gül, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse PinnwandForm hat die Funktion einen Textbeitrag und Kommentare zu erstellen.
 */

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PinnwandForm extends Widget {

	private VerticalPanel addPanel = new VerticalPanel();
	private TextArea ta = new TextArea();
	private Button textbeitragPosten = new Button("Add Post");

	public PinnwandForm() {
		// Größe des Textareas

	}
	
	public Widget zeigePost() {
		ta.setCharacterWidth(60);
		ta.setVisibleLines(5);
		// Anordnung von textarea und button
		addPanel.add(ta);
		addPanel.add(textbeitragPosten);
		addPanel.addStyleName("addPanel");

		// RootPanel.get("Details").clear();
		RootPanel.get("Details").add(addPanel);
		textbeitragPosten.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String a = ta.getText();

				Beitrag b = new Beitrag(a);
				addPanel.add(b);
				ta.setText("");
			}
		});

		return addPanel;
	}

}