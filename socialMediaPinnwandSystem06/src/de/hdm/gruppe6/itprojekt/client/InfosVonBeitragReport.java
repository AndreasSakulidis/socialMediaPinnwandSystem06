package de.hdm.gruppe6.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;

public class InfosVonBeitragReport extends Composite {

		private VerticalPanel vPanel = new VerticalPanel();
		private FlexTable infoFlexTable = new FlexTable();
		private Textbeitrag textb = new Textbeitrag();
		
		public InfosVonBeitragReport(String content) {
			final Button sendSucheButton = new Button("Suchen");
			final TextBox beitragSuchenField = new TextBox();
			final TextBox anfangszeitpunktField = new TextBox();
			final TextBox endzeitpunktField = new TextBox();
			beitragSuchenField.setText("Beitrag suchen");
			sendSucheButton.addStyleName("beitragSuchen");
		    vPanel.add(beitragSuchenField);
		    vPanel.add(anfangszeitpunktField);
		    vPanel.add(endzeitpunktField);
			vPanel.add(sendSucheButton);
	        RootPanel.get("Details").add(vPanel);
	        initWidget(this.vPanel);
			
			 // Erstelle Tabelle für Infos von einem bestimmten Beitrag in einem bestimmten Zeitraum
			infoFlexTable.setText(0, 0, "Text");
			infoFlexTable.setText(0, 1, "Erstellungszeitpunkt");
		    infoFlexTable.setText(0, 2, "User Vorname");
		    infoFlexTable.setText(0, 3, "User Nachname");
			infoFlexTable.setText(0, 4, "Anzahl Kommentare");
		    infoFlexTable.setText(0, 5, "Anzahl Likes");

		    
		    vPanel.add(infoFlexTable);
			
			sendSucheButton.addClickHandler(new ClickHandler(){

				private VerticalPanel vPanel = new VerticalPanel();

				@Override
				public void onClick(ClickEvent event) {
				
				
						initWidget(this.vPanel);
						
						 // Erstelle Tabelle für Infos von einem bestimmten Beitrag in einem bestimmten Zeitraum
						infoFlexTable.setText(0, 0, "Text");
						infoFlexTable.setText(0, 1, "Erstellungszeitpunkt");
					    infoFlexTable.setText(0, 2, "User Vorname");
					    infoFlexTable.setText(0, 3, "User Nachname");
						infoFlexTable.setText(0, 4, "Anzahl Kommentare");
					    infoFlexTable.setText(0, 5, "Anzahl Likes");

					    
					    vPanel.add(infoFlexTable); 
					    
					}
					    
				
			});
			
	

		
	}

		protected void initWidget(VerticalPanel vPanel2) {
			// TODO Auto-generated method stub
			
		}
	}


