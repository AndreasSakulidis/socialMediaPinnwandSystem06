package de.hdm.gruppe6.itprojekt.client;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Beitrag extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable postFlexTable = new FlexTable();
	// private FlexTable postBeitrag = new FlexTable();

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
				final dialogBox comment = new dialogBox("Kommentieren");
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
						postFlexTable.setText(postFlexTable.getRowCount(), 0,
								comment.getContent());
						postFlexTable.setWidget(postFlexTable.getRowCount(), 0,
								kloeschen);
						postFlexTable.setWidget(postFlexTable.getRowCount(), 0,
								kbearbeiten);
						postFlexTable.setWidget(postFlexTable.getRowCount(), 0,
								commentUpdatedLabel);

						kloeschen.addClickHandler(new Loeschen());

					}
				});

				comment.show();

			}

		});

		loeschen.addClickHandler(new Loeschen());

		bearbeiten.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final dialogBox comment = new dialogBox("Bearbeiten");
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
					}
				});

				comment.show();
			}

		});
		post.setText(content);
		lastUpdatedLabel.setText("Es wurde gepostet um: "
				+ DateTimeFormat.getMediumDateFormat().format(new Date()));
		
		commentUpdatedLabel.setText("Es wurde kommentiert um: "
				+ DateTimeFormat.getMediumDateFormat().format(new Date()));
	}

	class Loeschen implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			postFlexTable.clear();

		}

	}

	class Kommentieren implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
	}
			

		// postFlexTable.setWidget(0, 0, post);
		// postFlexTable.setWidget(3, 1, loeschen);
		// postFlexTable.setWidget(3, 2, bearbeiten);
		// postFlexTable.setWidget(3, 1, kommentieren);
		// postFlexTable.setWidget(3, 2, liken);
		//
		// // kommentieren.addClickHandler(new ClickHandler() {
		// //
		// // @Override
		// // public void onClick(ClickEvent event) {
		// // String s = ta.getText();
		// // Label l = new Label(s);
		// // postBeitrag.add(l);
		// // }
		// // });
		//
		// // //TODO proxy Objrkt zum speichern
		// // if(label!=null){
		// // postBeitrag.setWidget(1,1, label);
		// // postFlexTable.setWidget(1,2,postBeitrag);
		// // postFlexTable.setWidget(2, 1, ta);
		// // }
		// //
		// // vPanel.add(postFlexTable);
		// }

		// protected void setBeitrag(String text) {

		// if (text == null) {
		// throw new IllegalArgumentException("text == null");
		// }
		// if (text.trim().isEmpty()) {
		// throw new IllegalArgumentException("Text ist leer");
		// }
		// this.text = text;
		// }
		//

	}

