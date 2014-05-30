package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, Özlem Gül, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse Beitrag ermöglicht den angemeldeten User einen Textbeitrag zu posten.
 */
import java.util.Date;
import java.util.Vector;

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
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.Like;
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
	private Label label = new Label("TEST");
	private Label lastUpdatedLabel = new Label();
	private Label lbId = new Label();
	private VerticalPanel mainPanel = new VerticalPanel();
	private PinnwandVerwaltungServiceAsync socialmedia = GWT.create(PinnwandVerwaltungService.class);
	public VerticalPanel addPanel = new VerticalPanel();	
	
	public Beitrag(final String content) {

//		label.setText(content);
		initWidget(this.vPanel);
//		lbId.setText(String.valueOf(id));
		System.out.println("in Konstruktor Beitrag");
		String uid = Cookies.getCookie("SocialMedia6ID");
		int userID = Integer.parseInt(uid);
		socialmedia.findeAlleUserBeitraege(userID, new AsyncCallback<Vector<Textbeitrag>>() {
			@Override
			public void onSuccess(Vector<Textbeitrag> result) {
				System.out.println("in der Methode findeAlleUserBeitraege vor der Schleife");
				System.out.println("Werte von result: "+result.size());
//				VerticalPanel addPanel = new VerticalPanel();		
				addPanel = new VerticalPanel();
				FlexTable ft = new FlexTable();
				int i = 0;
				
				Widget[] w = new Widget[result.size()];
				for (Textbeitrag tb : result){
					System.out.println("TB Text"+tb.getText());

					postFlexTable.setText(1, 0, tb.getText());
					postFlexTable.setWidget(1, 1, loeschen);
					postFlexTable.setWidget(1, 2, bearbeiten);
					postFlexTable.setWidget(1, 3, kommentieren);
					postFlexTable.setWidget(1, 4, liken);
					postFlexTable.setText(1, 5, "ID");
					postFlexTable.setText(1, 5, String.valueOf(tb.getId()));

					postFlexTable.setText(2, 0, String.valueOf(tb.getErstellungsZeitpunkt()));
					System.out.println("postFlexTable: "+postFlexTable);
					
//					w[i]=postFlexTable;
//					ft.setWidget(i, 0, postFlexTable);
					vPanel.add(postFlexTable);
//					System.out.println("vPanel "+vPanel.toString());
					vPanel.addStyleName("Textbeitrag");
//					i++;
//					System.out.println("int i: "+i);
				}
				for (int j = 0; j < w.length; j++) {
					System.out.println("Array"+w[i]);
				}
//				vPanel.add(ft);
//				vPanel.addStyleName("Textbeitrag");
//				RootPanel.get().add(vPanel);
				
//				vPanel.add(addPanel);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("hat nicht geklappt mit den Post ausgaben: "+caught.getMessage());
			}
		});

		
//		vPanel.add(label);
//		vPanel.add(label);
//		vPanel.add(label);

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
		
		liken.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String uid = Cookies.getCookie("SocialMedia6ID");
				int tid = Integer.parseInt(lbId.getText());
				pinnwandVerwaltung.likeAnlegen(uid, tid,  new AsyncCallback<Like>(){
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim Liken!");
					}
					public void onSuccess(Like like) {
						Window.alert("Erfolgreich geliked!");
					}
				});
			}
		});
		


				lastUpdatedLabel.setText("Es wurde gepostet um : "
		        + DateTimeFormat.getMediumDateTimeFormat().format(new Date()));

	}
	
	public void setPost(String content){
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
						//TODO nach dem Anlegen aktualisieren 
						lbId.setText(String.valueOf(textbeitrag.getId()));
//						Window.alert("Textbeitrag wurde gepostet!");
						Window.Location.reload();

					}
				});
	}
	
	

}