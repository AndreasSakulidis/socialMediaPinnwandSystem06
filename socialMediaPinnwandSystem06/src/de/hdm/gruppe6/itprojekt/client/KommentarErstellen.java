package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, �zlem G�l, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse KommentarErstellen erm�glicht dem angelmeldeten User einen bestehenden Textbeitrag zu kommentieren, diesen Kommentar zu bearbeiten und zu l�schen.
 */

import java.util.ArrayList;
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
import com.google.gwt.user.client.ui.Widget;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.Kommentar;

public class KommentarErstellen extends Composite {

	PinnwandVerwaltungServiceAsync pinnwandVerwaltung = GWT
			.create(PinnwandVerwaltungService.class);
	/**
	 * Hier werden die Widgets und die Panels festgelegt.
	 */
	private VerticalPanel kommentarPanel = new VerticalPanel();
	private FlexTable kommentarTable = new FlexTable();
	private Label commentL = new Label();
	private Button kbearbeiten = new Button("Bearbeiten");
	private Button kloeschen = new Button("X");
	private Label commentUpdatedLabel = new Label();
	private Label lbId = new Label();

	public Widget setComment(final String content, final int tid) {
		/**
		 * Die Klasse MeineDialogBox wird aufgerufen, indem der Textbeitrag
		 * kommentiert werden kann.
		 */
		final MeineDialogBox comment = new MeineDialogBox("Kommentieren");
		comment.center();
		comment.setText("Kommentieren");
		comment.hide(true);

		/**
		 * Die Aktion wird abgebrochen und es wird kein Kommentar gepostet.
		 */
		comment.abbrechen.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				comment.hide();


			}

		});

		/**
		 * Mit einem Klick auf ok wird ein Kommentar gepostet und in einer
		 * FlexTable angelegt.
		 */

		comment.ok.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				comment.hide(true);

				// kommentarTable.setText(1, 1, comment.getContent());
				kommentarTable.setWidget(1, 1, commentL);
				kommentarTable.setWidget(1, 2, kloeschen);
				kommentarTable.setWidget(1, 3, kbearbeiten);
				kommentarTable.setWidget(2, 1, commentUpdatedLabel);
				kommentarTable.setWidget(1, 4, lbId);

				kommentarPanel.add(kommentarTable);

				// vPanel.add(kommentarPanel);
				kommentarPanel.addStyleName("Kommentare");

				commentL.setText(comment.getContent());

				String text = commentL.getText();
				String uid = Cookies.getCookie("SocialMedia6ID");

				pinnwandVerwaltung.kommentarAnlegen(text, uid, tid,
						new AsyncCallback<Kommentar>() {

							@Override
							public void onFailure(Throwable caught) {
								System.out.println(caught.getMessage());
								Window.alert("Fehler beim Anlegen!");
							}

							@Override
							public void onSuccess(Kommentar kommentar) {
								lbId.setText(String.valueOf(kommentar.getId()));
								Window.alert("Kommentar wurde angelegt!");
								Window.Location.reload();

								
								
							}
						});

				/**
				 * Um den Kommentar bearbeiten zu k�nnen wird die Klasse
				 * MeineDialogBox aufgerufen.
				 */
				kbearbeiten.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						final MeineDialogBox comment = new MeineDialogBox(
								"Bearbeiten");
						comment.center();
						comment.setText("Bearbeiten");
						comment.setContent(commentL.getText());

						/**
						 * Die Bearbeitung des Kommentars wird abgebrochen.
						 */

						comment.abbrechen.addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								comment.hide();

							}

						});

						/**
						 * Der Kommentar wird erfolgreich bearbeitet.
						 */

						comment.ok.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								comment.hide();

								commentL.setText(comment.getContent());

								int id = Integer.parseInt(lbId.getText());
								final String text = commentL.getText();
								pinnwandVerwaltung.kommentarEditieren(text, id,
										new AsyncCallback<Kommentar>() {

											@Override
											public void onFailure(
													Throwable caught) {
												Window.alert("Fehler beim Editieren!");
											}

											@Override
											public void onSuccess(
													Kommentar result) {

												Window.alert("Textbeitrag wurde editiert!");
												RootPanel.get("Details").clear();
												PinnwandForm pinnForm = new PinnwandForm();
												pinnForm.zeigePost();
												pinnForm.anzeigen();
												comment.hide();
											}
										});

							}
						});

						comment.show();
					}

				});

				/**
				 * Der Kommentar wird gel�scht.
				 */

				kloeschen.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						int id = Integer.parseInt(lbId.getText());
						String text = commentL.getText();
						pinnwandVerwaltung.kommentarLoeschen(text, id,
								new AsyncCallback<Void>() {
									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Fehler beim loeschen!");
									}

									@Override
									public void onSuccess(Void result) {
										Window.alert("Kommentar wurde geloescht!");
										kommentarTable.removeFromParent();
									}
								});
					}

				});

			}
		});

		comment.show();

		/**
		 * Der Zeitpunkt der letzten Aktualisierung des Kommentars wird
		 * angegeben.
		 */
		commentUpdatedLabel.setText("Es wurde kommentiert am: "
				+ DateTimeFormat.getMediumDateTimeFormat().format(new Date()));

		return kommentarPanel;
	}

}