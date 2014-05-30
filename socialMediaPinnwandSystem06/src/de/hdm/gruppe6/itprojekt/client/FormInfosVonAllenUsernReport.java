package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, �zlem G�l, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse FormInfosVonALlenUsernReport enth�lt die Auswahlmaske.
 */

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


public class FormInfosVonAllenUsernReport extends Composite {
	/**
	 * Hier werden die Widgets und die Panels festgelegt. 
	 */
	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable postFlexTable = new FlexTable();
	private Button alleUserButton = new Button("Alle User ausgeben");
	private Label title = new Label ("Report: Informationen von allen Usern");


	/**
	 * Konstruktor 
	 * @param content
	 */
	
	
	public FormInfosVonAllenUsernReport(String content){
		initWidget(this.vPanel);
		vPanel.add(title);
		title.addStyleName("title");
		vPanel.add(alleUserButton);
		
		/**
		 * Mit einem Klick auf den alle User ausgeben Button wird eine Flextable mit allen Usern erzeugt.
		 * 
		 */
		
		alleUserButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
			    
			    vPanel.clear();
				vPanel.add(title);
	
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


