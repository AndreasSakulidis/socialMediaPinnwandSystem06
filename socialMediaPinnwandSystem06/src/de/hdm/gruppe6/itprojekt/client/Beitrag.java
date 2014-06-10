package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, �zlem G�l, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse Beitrag erm�glicht den angemeldeten User einen Textbeitrag zu posten.
 */
import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.Kommentar;
import de.hdm.gruppe6.itprojekt.shared.bo.Like;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;

public class Beitrag extends Composite {

	PinnwandVerwaltungServiceAsync pinnwandVerwaltung = GWT
			.create(PinnwandVerwaltungService.class);


	private Label lastUpdatedLabel = new Label();
	private Label lbId = new Label();
	
	public Beitrag() {

	}

	public void beitragErstellen(final String content){
		
		String id = Cookies.getCookie("SocialMedia6ID");
		pinnwandVerwaltung.textbeitragAnlegen(content, id,
				new AsyncCallback<Textbeitrag>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim Posten!");
					}

					@Override
					public void onSuccess(Textbeitrag textbeitrag) {
						lbId.setText(String.valueOf(textbeitrag.getId()));
						// user.setText()
						Window.alert("Textbeitrag wurde gepostet!");
						RootPanel.get("Details").clear();
						PinnwandForm pinnForm = new PinnwandForm();
						pinnForm.zeigePost();
						pinnForm.anzeigen();

					}
				});
		/**
		 * Die letzte Aktualisierung des Textbeitrags wird angezeigt.
		 */
		

	}

}