package de.hdm.gruppe6.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;



		public class InfosVonAllenBeitraegenReport extends Composite {
			
			private VerticalPanel vPanel = new VerticalPanel();
			private FlexTable postFlexTable = new FlexTable();

			/**
			 * Konstruktor 
			 * @param content
			 */
			public InfosVonAllenBeitraegenReport(String content){
				initWidget(this.vPanel);
				
				 // Gibt die Beiträge 
				postFlexTable.setText(0, 0, "text");
				postFlexTable.setText(0, 1, "Anfangszeitpunkt");
			    postFlexTable.setText(0, 2, "Endzeitpunkt");
			   // postFlexTable.setText(0,3,"Likes");

			    
			    vPanel.add(postFlexTable);
			    
				   
				}	
			}







