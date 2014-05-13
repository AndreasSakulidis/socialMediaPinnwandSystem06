package de.hdm.gruppe6.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.gruppe6.itprojekt.shared.bo.User;

public class InfosVonAllenUsernReport extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable postFlexTable = new FlexTable();

	/**
	 * Konstruktor 
	 * @param content
	 */
	public InfosVonAllenUsernReport(String content){
		initWidget(this.vPanel);
		
		
		
		 // Create table for stock data.
		postFlexTable.setText(0, 0, "ID");
		postFlexTable.setText(0, 1, "Vorname");
	    postFlexTable.setText(0, 2, "Nachname");
	    postFlexTable.setText(0, 3, "Nickname");
	    postFlexTable.setText(0, 4, "Anfangszeitpunkt");
	    postFlexTable.setText(0, 4, "Endzeitpunkt");
	    
	    
	    

	    
	    vPanel.add(postFlexTable);
	    
		   
		}	
	}


