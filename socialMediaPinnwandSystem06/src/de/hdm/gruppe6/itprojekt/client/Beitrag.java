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
import de.hdm.gruppe6.itprojekt.shared.bo.Kommentar;
import de.hdm.gruppe6.itprojekt.shared.bo.Like;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;

public class Beitrag extends Composite {

	PinnwandVerwaltungServiceAsync pinnwandVerwaltung = GWT
			.create(PinnwandVerwaltungService.class);

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable postFlexTable = new FlexTable();
	
	private VerticalPanel kommentarPanel = new VerticalPanel();
	private FlexTable kommentarTable = new FlexTable();
	

	private Label post = new Label();
	private Button loeschen = new Button("X");
	private Button bearbeiten = new Button("Bearbeiten");
	private Button kbearbeiten = new Button("Bearbeiten");
	private Button kloeschen = new Button("X");
	private Button kommentieren = new Button("Kommentieren");
	private Button liken = new Button("Like");
	private Label label = new Label();
	private String text = null;
	private Label lastUpdatedLabel = new Label();
	private Label commentUpdatedLabel = new Label();

	// private TextArea ta = new TextArea();

	public Beitrag(String content) {

		label.setText(content);
		initWidget(this.vPanel);

		postFlexTable.setWidget(0, 0, post);
		postFlexTable.setWidget(3, 0, loeschen);
		postFlexTable.setWidget(3, 2, bearbeiten);
		postFlexTable.setWidget(3, 1, kommentieren);
		postFlexTable.setWidget(3, 3, liken);
		postFlexTable.setWidget(4, 1, lastUpdatedLabel);

		vPanel.add(postFlexTable);

		kommentieren.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final MeineDialogBox comment = new MeineDialogBox(
						"Kommentieren");
				comment.setText("Kommentieren");

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
						
						kommentarTable.setText(kommentarTable.getRowCount(), 0,
								comment.getContent());
						kommentarTable.setWidget(kommentarTable.getRowCount(), 0,
								kloeschen);
						kommentarTable.setWidget(kommentarTable.getRowCount(), 0,
								kbearbeiten);
						kommentarTable.setWidget(kommentarTable.getRowCount(), 0,
								commentUpdatedLabel);
								
								kommentarPanel.add(kommentarTable);
								
								vPanel.add(kommentarPanel);
								kommentarPanel.addStyleName("Kommentare");
						
//						postFlexTable.setText(postFlexTable.getRowCount(), 0,
//								comment.getContent());
//						postFlexTable.setWidget(postFlexTable.getRowCount(), 0,
//								kloeschen);
//						postFlexTable.setWidget(postFlexTable.getRowCount(), 0,
//								kbearbeiten);
//						postFlexTable.setWidget(postFlexTable.getRowCount(), 0,
//								commentUpdatedLabel);
								
								String text = comment.getText();

								pinnwandVerwaltung.kommentarAnlegen(text,
										new AsyncCallback<Kommentar>() {

											@Override
											public void onFailure(Throwable caught) {
												System.out.println(caught.getMessage());
												Window.alert("Fehler beim Anlegen!");
											}

											@Override
											public void onSuccess(Kommentar kommentar) {
												Window.alert("Kommentar wurde angelegt!");
											}
										});

								kloeschen.addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {
										
										 Kommentar kommentar = null;
										pinnwandVerwaltung.kommentarLoeschen(kommentar,
										new AsyncCallback<Void>() {
											@Override
											public void onFailure(Throwable caught) {
												Window.alert("Fehler beim loeschen!");
											}
								
											@Override
											public void onSuccess(Void result) {
												Window.alert("Kommentar wurde gelöscht!");
												kommentarTable.removeFromParent();
											}
										});
									}
									
								});
								

							}
						});
				comment.show();

			}

		});

		loeschen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				 Textbeitrag textbeitrag = null;
				pinnwandVerwaltung.textbeitragLoeschen(textbeitrag,
				new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim loeschen!");
					}
		
					@Override
					public void onSuccess(Void result) {
						Window.alert("Textbeitrag wurde gelöscht!");
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
//						Textbeitrag textbeitrag = null;
//						pinnwandVerwaltung.textbeitragEditieren(textbeitrag,
//								new AsyncCallback<Textbeitrag>() {
//
//									@Override
//									public void onFailure(Throwable caught) {
//										Window.alert("Fehler beim Editieren!");
//									}
//
//									@Override
//									public void onSuccess(Textbeitrag result) {
//										Window.alert("Textbeitrag wurde editiert!");
//									}
//								});
					}
				});

				comment.show();
			}

		});
		

		liken.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				pinnwandVerwaltung.likeAnlegen(new AsyncCallback<Like>(){
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

		pinnwandVerwaltung.textbeitragAnlegen(text,
				new AsyncCallback<Textbeitrag>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim posten!");
					}

					@Override
					public void onSuccess(Textbeitrag textbeitrag) {
						Window.alert("Der Textbeitrag wurde gepostet!");

					}
				});

		lastUpdatedLabel.setText("Es wurde gepostet um: "
				+ DateTimeFormat.getMediumDateFormat().format(new Date()));

		commentUpdatedLabel.setText("Es wurde kommentiert um: "
				+ DateTimeFormat.getMediumDateFormat().format(new Date()));
	}

	

}
