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

import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;


public class InfosVonUserReport extends  Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable infoFlexTable = new FlexTable();
	private Textbeitrag textb = new Textbeitrag();
	
	public InfosVonUserReport(String content) {
		final Button sendSucheButton = new Button("Suchen");
		final TextBox userSuchenField = new TextBox();
		final TextBox anfangszeitpunktField = new TextBox();
		final TextBox endzeitpunktField = new TextBox();
		userSuchenField.setText("User suchen");
		sendSucheButton.addStyleName("beitragSuchen");
	    vPanel.add(userSuchenField);
	    vPanel.add(anfangszeitpunktField);
	    vPanel.add(endzeitpunktField);
		vPanel.add(sendSucheButton);
        RootPanel.get("Details").add(vPanel);
		
		sendSucheButton.addClickHandler(new ClickHandler(){

			private VerticalPanel vPanel = new VerticalPanel();

			@Override
			public void onClick(ClickEvent event) {
			
			
					initWidget(this.vPanel);
					
					 // Erstelle Tabelle für Infos von einem bestimmten Beitrag in einem bestimmten Zeitraum
					infoFlexTable.setText(0, 0, "Anzahl Beitraege");
					infoFlexTable.setText(0, 1, "Anzahl Abonnements");
				    infoFlexTable.setText(0, 2, "Anzahl Kommentare");

				    
				    vPanel.add(infoFlexTable); 
				    
				}
				    
			
		});
		


	
}

	protected void initWidget(VerticalPanel vPanel2) {
		// TODO Auto-generated method stub
		
	}

}
	
