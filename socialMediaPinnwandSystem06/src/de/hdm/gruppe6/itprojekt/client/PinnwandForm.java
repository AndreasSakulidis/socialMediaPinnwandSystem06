package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, �zlem G�l, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse PinnwandForm hat die Funktion einen Textbeitr�ge zu erstellen.
 */

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.Like;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;

public class PinnwandForm extends Composite {
	/**
	 * Hier wird der Panel und die Widgets festgelegt.
	 */

	private VerticalPanel addPanel = new VerticalPanel();
	private TextArea ta = new TextArea();
	private Button textbeitragPosten = new Button("Add Post");
	private Button pinnwandAnzeigen = new Button("Pinnwand-Anzeigen");

	private PinnwandVerwaltungServiceAsync socialmedia = GWT
			.create(PinnwandVerwaltungService.class);
	
	
	
	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable postFlexTable = new FlexTable();
	private FlexTable userFlexTable = new FlexTable();
	private Label user = new Label(Cookies.getCookie("SocialMedia6"));
	private Button liken = new Button("Like");

	private Label lbId = new Label();
	private Button bePinnwand = new Button("Bearbeiten");
	private Button koPinnwand = new Button("Kommentieren");
	private Button loePinnwand = new Button("X");

	public PinnwandForm() {

	}

	public Widget zeigePost() {

		ta.setCharacterWidth(60);
		ta.setVisibleLines(5);

		/**
		 * Die Widgets werden dem addPanel hinzugef�gt.
		 */
		addPanel.add(ta);
		addPanel.add(textbeitragPosten);
		addPanel.addStyleName("addPanel");
		addPanel.add(pinnwandAnzeigen);
		addPanel.addStyleName("PinnwandAnzeigen");
	

		// RootPanel.get("Details").clear();

		RootPanel.get("Details").add(addPanel);
		ta.setFocus(true);
		/**
		 * Mit einem Klick auf den Button Add Post wird ein Textbeitrag erzeugt.
		 */
		textbeitragPosten.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String a = ta.getText();

				Beitrag b = new Beitrag(a);
				addPanel.add(b);
				ta.setText("");
			}

		});
		
		pinnwandAnzeigen.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event) {
				System.out.println("in Konstruktor Beitrag");
				String uid = Cookies.getCookie("SocialMedia6ID");
				int userID = Integer.parseInt(uid);
				
				socialmedia.findeAlleUserBeitraege(userID,
						new AsyncCallback<ArrayList<Textbeitrag>>() {
							@Override
							public void onSuccess(ArrayList<Textbeitrag> result) {
								System.out
										.println("in der Methode findeAlleUserBeitraege vor der Schleife");
								System.out.println("Werte von result: " + result.size());
								// VerticalPanel addPanel = new VerticalPanel();

								for (Textbeitrag tb : result) {
									//String pinnwand = tb.getText();
									PinnwandForm pinnwandAnzeigen = new PinnwandForm();
									pinnwandAnzeigen.beitragAnzeigen(tb);
									addPanel.add(pinnwandAnzeigen);

								}

							}

							@Override
							public void onFailure(Throwable caught) {
								System.out
										.println("hat nicht geklappt mit den Post ausgaben: "
												+ caught.getMessage());
							}
						});
				
				
			}
			
		});
		

		return addPanel;

	}
	
	// METHODE

	public void beitragAnzeigen(final Textbeitrag a) {
		
	//	bePinnwand.addClickHandler(new BearbeitenPost());

		// label.setText(content);
		initWidget(this.vPanel);
		// lbId.setText(String.valueOf(id));
		userFlexTable.setWidget(0, 0, user);
		userFlexTable.addStyleName("Userspalte");
		/**
		 * Die Textbeitr�ge werden in einer Flextable gepostet.
		 */
		postFlexTable.setText(0, 0, a.getText());
		postFlexTable.setWidget(1, 1, loePinnwand);
		postFlexTable.setWidget(1, 2, bePinnwand);
		postFlexTable.setWidget(1, 3, koPinnwand);
		postFlexTable.setWidget(1, 4, liken);
		postFlexTable.setText(0, 5, "ID");
		postFlexTable.setText(1, 5, String.valueOf(a.getId()));
		String b = a.getErstellungsZeitpunkt().toString();

		postFlexTable.setText(2, 0, b);

		/**
		 * Die Flextable wird dem vPanel zugeordnet.
		 */
		vPanel.add(userFlexTable);
		vPanel.add(postFlexTable);
		vPanel.addStyleName("Textbeitrag");

		/**
		 * Mit einem Klick auf den Kommentieren Button wird die Klasse
		 * KommentarErstellen aufgerufen und ein Kommentar wird angelegt.
		 */
		
		bePinnwand.addClickHandler(new ClickHandler(){
		
		public void onClick(ClickEvent event) {

			final MeineDialogBox comment = new MeineDialogBox("Bearbeiten");
			comment.center();
			comment.setText("Bearbeiten");
			comment.setContent(a.getText());

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
					a.setText(comment.getContent());

					int id = a.getId();
					final String text = a.getText();
					socialmedia.textbeitragEditieren(text, id,
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
		
		

		koPinnwand.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				KommentarErstellen kommentarErstellen = new KommentarErstellen();
				int tid =a.getId();
//				 kommentarErstellen.setComment();
//				 Textbeitrag tb = new Textbeitrag();
				
				vPanel.add(kommentarErstellen.setComment(a.getText(), tid));

			}

		});
		/**
		 * Mit einem Klick auf den Loeschen Button wird der Textbeitrag
		 * gel�scht.
		 * 
		 */

		loePinnwand.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				int id = a.getId();
				String text = a.getText();
				socialmedia.textbeitragLoeschen(text, id,
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
		 * Mit einem Klick auf den Bearbeiten Button wird ein Dialogbox
		 * ge�ffnet, indem der User den Textbeitrag bearbeiten kann.
		 * 
		 */
		
		liken.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String uid = Cookies.getCookie("SocialMedia6ID");
				int tid = Integer.parseInt(lbId.getText());
				socialmedia.likeAnlegen(uid, tid,
						new AsyncCallback<Like>() {
							public void onFailure(Throwable caught) {
								Window.alert("Fehler beim Liken!");
							}

							public void onSuccess(Like like) {
								Window.alert("Erfolgreich geliked!");
							}
						});
			}
		});


	}
	
}