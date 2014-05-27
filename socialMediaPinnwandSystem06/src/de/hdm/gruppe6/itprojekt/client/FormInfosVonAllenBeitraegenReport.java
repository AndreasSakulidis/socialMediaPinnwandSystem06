package de.hdm.gruppe6.itprojekt.client;
/**
 * @author Bharti Kumar, Özlem Gül, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse FormInfosVonALlenBeitraegenReport enthält die Auswahlmaske.
 */

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.VerticalPanel;




		public class FormInfosVonAllenBeitraegenReport extends Composite {
			
			private VerticalPanel vPanel = new VerticalPanel();
			private FlexTable postFlexTable = new FlexTable();
			private Button alleBeitraegeButton = new Button("Alle Beitraege ausgeben");
			private Label title = new Label ("Report: Informationen von allen Beitraegen");
			
			

			/**
			 * Konstruktor 
			 * @param content
			 */
			
			public FormInfosVonAllenBeitraegenReport(String content){
				initWidget(this.vPanel);
				vPanel.add(title);
				title.addStyleName("title");
				vPanel.add(alleBeitraegeButton);
				alleBeitraegeButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
				
				// entfernt den Button
				vPanel.clear();
				// fügt Titel hinzu
				vPanel.add(title);
				
				 // Gibt die Beiträge in einer Tabelle aus
				postFlexTable.setText(0, 0, "text");
				postFlexTable.setText(0, 1, "Anfangszeitpunkt");
			    postFlexTable.setText(0, 2, "Endzeitpunkt");
			   // postFlexTable.setText(0,3,"Likes");

			    
			    vPanel.add(postFlexTable);
			    postFlexTable.addStyleName("flextable");
			    
				   
				}


			});
		}
		}







