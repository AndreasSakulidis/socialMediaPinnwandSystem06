package de.hdm.gruppe6.itprojekt.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;

public class Warnung extends DialogBox {
	PinnwandVerwaltungServiceAsync pinnwandVerwaltung = GWT
			.create(PinnwandVerwaltungService.class);
	/**
	 * Hier werden die Panels und die Widgets festgelegt.
	 */

	private HorizontalPanel commentPanel = new HorizontalPanel();

	public Button ok;
	public Button abbrechen;

	public Warnung(String title) {
		this.setText(title);

		/**
		 * Die Widgets werden dem CommentPanel hinzugef�gt.
		 */

		ok = new Button("OK");
		abbrechen = new Button("Abbrechen");
		

		commentPanel.add(ok);
		commentPanel.add(abbrechen);
		

		this.setWidget(commentPanel);
		
		

	}

}