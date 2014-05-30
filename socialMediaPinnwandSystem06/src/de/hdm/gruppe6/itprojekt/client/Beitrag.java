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
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.Like;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;

public class Beitrag extends Composite {

	PinnwandVerwaltungServiceAsync pinnwandVerwaltung = GWT
			.create(PinnwandVerwaltungService.class);

	/**
	 * Hier werden die Widgets und die Panels festgelegt. 
	 */
	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable postFlexTable = new FlexTable();
	private FlexTable userFlexTable = new FlexTable();
	private Label user = new Label(Cookies.getCookie("SocialMedia6"));
	private Label post = new Label();
	private Button loeschen = new Button("X");
	private Button bearbeiten = new Button("Bearbeiten");
	private Button kommentieren = new Button("Kommentieren");
	private Button liken = new Button("Like");
	private Label label = new Label();
	private Label lastUpdatedLabel = new Label();
	private Label lbId = new Label();
	//private VerticalPanel mainPanel = new VerticalPanel();
	
	public Beitrag(final String content) {

		label.setText(content);
		initWidget(this.vPanel);
//		lbId.setText(String.valueOf(id));
		userFlexTable.setWidget(0, 0, user);
		userFlexTable.addStyleName("Userspalte");
/**
 * Die Textbeitr�ge werden in einer Flextable gepostet.
 */
		postFlexTable.setWidget(0, 0, post);
		postFlexTable.setWidget(1, 1, loeschen);
		postFlexTable.setWidget(1, 2, bearbeiten);
		postFlexTable.setWidget(1, 3, kommentieren);
		postFlexTable.setWidget(1, 4, liken);
		postFlexTable.setText(0, 5, "ID");
		postFlexTable.setWidget(1, 5, lbId);

		postFlexTable.setWidget(2, 0, lastUpdatedLabel);
		
/**
 * Die Flextable wird dem vPanel zugeordnet.
 */
		vPanel.add(userFlexTable);
		vPanel.add(postFlexTable);
		vPanel.addStyleName("Textbeitrag");
		
/**
 * Mit einem Klick auf den Kommentieren Button wird die Klasse KommentarErstellen aufgerufen und ein Kommentar wird
 * angelegt.
 */

		kommentieren.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				KommentarErstellen kommentarErstellen = new KommentarErstellen();
				int tid = Integer.parseInt(lbId.getText());
				//kommentarErstellen.setComment(content);
//				Textbeitrag tb = new Textbeitrag();
				vPanel.add(kommentarErstellen.setComment(content, tid));



			}

		});
		/**
		 * Mit einem Klick auf den Loeschen Button wird der Textbeitrag gel�scht.
		 * 
		 */
		
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


		/**
		 * Mit einem Klick auf den Bearbeiten Button wird ein Dialogbox ge�ffnet, indem der User den Textbeitrag bearbeiten kann.
		 * 
		 */
		bearbeiten.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final MeineDialogBox comment = new MeineDialogBox("Bearbeiten");
				comment.center();
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
		/**
		 * Mit einem Klick auf den Liken Button wird ein Like erstellt.
		 * 
		 */
		
		liken.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String uid = Cookies.getCookie("SocialMedia6ID");
				int tid = Integer.parseInt(lbId.getText());
				pinnwandVerwaltung.likeAnlegen(uid, tid, new AsyncCallback<Like>(){
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim Liken!");
					}
					public void onSuccess(Like like) {
						Window.alert("Erfolgreich geliked!");
					}
				});
			}
		});
		
		post.setText(content);

		String text = post.getText();
		String id = Cookies.getCookie("SocialMedia6ID");
		pinnwandVerwaltung.textbeitragAnlegen(text, id,
				new AsyncCallback<Textbeitrag>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim Posten!");
					}

					@Override
					public void onSuccess(Textbeitrag textbeitrag) {
						lbId.setText(String.valueOf(textbeitrag.getId()));
//						user.setText()
						Window.alert("Textbeitrag wurde gepostet!");

					}
				});

 /**
  * Die letzte Aktualisierung des Textbeitrags wird angezeigt.
  */
				lastUpdatedLabel.setText("Es wurde gepostet um : "
		        + DateTimeFormat.getMediumDateTimeFormat().format(new Date()));

	}

}