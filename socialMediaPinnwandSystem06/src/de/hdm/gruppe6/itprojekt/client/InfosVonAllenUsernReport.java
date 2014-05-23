package de.hdm.gruppe6.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.gruppe6.itprojekt.shared.bo.User;

public class InfosVonAllenUsernReport extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable postFlexTable = new FlexTable();
	private Button alleUserButton = new Button("Alle User ausgeben");
	private Label title = new Label ("Report: Informationen von allen Usern");


	/**
	 * Konstruktor 
	 * @param content
	 */
	
	
	public InfosVonAllenUsernReport(String content){
		initWidget(this.vPanel);
		vPanel.add(title);
		title.addStyleName("title");
		vPanel.add(alleUserButton);
		alleUserButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
			    
			    vPanel.clear();
				vPanel.add(title);
	
	 // Create table for stock data.
	postFlexTable.setText(0, 0, "ID");
	postFlexTable.setText(0, 1, "Vorname");
    postFlexTable.setText(0, 2, "Nachname");
    postFlexTable.setText(0, 3, "Nickname");
    postFlexTable.setText(0, 4, "Anfangszeitpunkt");
    postFlexTable.setText(0, 5, "Endzeitpunkt");
    
    
    

    
    vPanel.add(postFlexTable);
    postFlexTable.addStyleName("flextable");
    
	   
	
				
			}
		});
		
		


	}
}


