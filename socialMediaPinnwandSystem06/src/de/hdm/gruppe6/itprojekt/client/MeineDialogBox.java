package de.hdm.gruppe6.itprojekt.client;
/**
 * @author Bharti Kumar, Özlem Gül, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse MeineDialogBox ruft eine DialogBox auf, in der man Textbeiträge und Kommentare editieren und hinzufügen kann.
 */

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;

public class MeineDialogBox extends DialogBox {
	PinnwandVerwaltungServiceAsync pinnwandVerwaltung = GWT
			.create(PinnwandVerwaltungService.class);

	private HorizontalPanel commentPanel = new HorizontalPanel();
	public TextArea ta;
	public Button ok;
	public Button abbrechen;

	public MeineDialogBox(String title) {
		this.setText(title);

		ta = new TextArea();

		ok = new Button("Add");
		abbrechen = new Button("Abbrechen");
		commentPanel.add(ta);
		commentPanel.add(ok);
		commentPanel.add(abbrechen);

		this.setWidget(commentPanel);
	}

	public String getContent() {
		// TODO Auto-generated method stub
		return ta.getText();
	}

	public void setContent(String content) {
		ta.setText(content);
	}
}
