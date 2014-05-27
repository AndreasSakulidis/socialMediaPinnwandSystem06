package de.hdm.gruppe6.itprojekt.client;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;

public class Beitrag extends Composite {

	PinnwandVerwaltungServiceAsync pinnwandVerwaltung = GWT
			.create(PinnwandVerwaltungService.class);

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable postFlexTable = new FlexTable();


	private Label post = new Label();
	private Button loeschen = new Button("X");
	private Button bearbeiten = new Button("Bearbeiten");
	private Button kommentieren = new Button("Kommentieren");
	private Button liken = new Button("Like");
	private Label label = new Label();
	private Label lastUpdatedLabel = new Label();
	private Label lbId = new Label();
	
	public Beitrag(final String content) {

		label.setText(content);
		initWidget(this.vPanel);
//		lbId.setText(String.valueOf(id));

		postFlexTable.setWidget(0, 0, post);
		postFlexTable.setWidget(1, 1, loeschen);
		postFlexTable.setWidget(1, 2, bearbeiten);
		postFlexTable.setWidget(1, 3, kommentieren);
		postFlexTable.setWidget(1, 4, liken);
		postFlexTable.setText(0, 5, "ID");
		postFlexTable.setWidget(1, 5, lbId);

		postFlexTable.setWidget(2, 0, lastUpdatedLabel);

		vPanel.add(postFlexTable);


		kommentieren.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Kommentieren kommentarErstellen = new Kommentieren();
				//kommentarErstellen.setComment(content);
				Textbeitrag tb = new Textbeitrag();
				vPanel.add(kommentarErstellen.setComment(content));



			}

		});

		loeschen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				int id = Integer.parseInt(lbId.getText());
				String text = post.getText();
				pinnwandVerwaltung.textbeitragLoeschen(text, id,
				new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim loeschen!");
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Textbeitrag wurde geloescht!");
						postFlexTable.removeFromParent();
					}
				});
			}

		});



		bearbeiten.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final MeineDialogBox comment = new MeineDialogBox("Bearbeiten");
				comment.setText("Bearbeiten");
				comment.setContent(post.getText());

				comment.abbrechen.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						comment.hide();

					}

				});
				
				comment.ok.addClickHandler(new ClickHandler() {


					@Override
					public void onClick(ClickEvent event) {
						comment.hide();
						post.setText(comment.getContent());	

						int id = Integer.parseInt(lbId.getText());
						final String text = post.getText();
						pinnwandVerwaltung.textbeitragEditieren(text, id,
								new AsyncCallback<Textbeitrag>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Fehler beim Editieren!");
									}

									@Override
									public void onSuccess(Textbeitrag result) {
										Window.alert("Textbeitrag wurde editiert!");
									}
								});

					}
				});

				comment.show();
			}

		});
		post.setText(content);

		String text = post.getText();
		pinnwandVerwaltung.textbeitragAnlegen(text,
				new AsyncCallback<Textbeitrag>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim Posten!");
					}

					@Override
					public void onSuccess(Textbeitrag textbeitrag) {
						lbId.setText(String.valueOf(textbeitrag.getId()));
						Window.alert("Textbeitrag wurde gepostet!");

					}
				});

				lastUpdatedLabel.setText("Es wurde gepostet um : "
		        + DateTimeFormat.getMediumDateTimeFormat().format(new Date()));

	}

}