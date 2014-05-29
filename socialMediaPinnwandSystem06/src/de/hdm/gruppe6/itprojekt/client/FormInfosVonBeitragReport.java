package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, Özlem Gül, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse FormInfosVonBeitragReport enthält die Auswahlmaske.
 */

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

public class FormInfosVonBeitragReport extends Composite {

	/**
	 * Hier werden die Widgets und die Panels festgelegt. 
	 */
		private VerticalPanel vPanel = new VerticalPanel();
		private FlexTable infoFlexTable = new FlexTable();
		private Label title = new Label("Report:Informationen von einem Beitrag"); 
		final Button zuruck = new Button("zurueck");
		
		public FormInfosVonBeitragReport(String content) {
			  RootPanel.get("Details").add(vPanel);
			  
			  /**
			   * Hier werden die Buttons und die Labels festgelegt und dem vPanel zugeordnet.
			   */
			final Button sendSucheButton = new Button("Suchen");
			final Label beitragSuchen = new Label ("Text eingeben");
			final Label aZ = new Label ("Anfangszeitpunkt eingeben");
			final Label eZ = new Label ("Endzeitpunkt eingeben");
			final TextBox beitragSuchenField = new TextBox();
			final TextBox anfangszeitpunktField = new TextBox();
			final TextBox endzeitpunktField = new TextBox();
			sendSucheButton.addStyleName("beitragSuchen");
			vPanel.add(title);
		    title.addStyleName("title");
			vPanel.add(beitragSuchen);
		    vPanel.add(beitragSuchenField);
		    vPanel.add(aZ);
		    vPanel.add(anfangszeitpunktField);
		    
			 /**
			  * Hier wird ein datePicker erzeugt und dem Anfangszeitpunkfeld zugeordnet. 
			  */
		    DatePicker datePicker = new DatePicker();
		    final Label text = new Label();
		    
		
		    datePicker.addValueChangeHandler(new ValueChangeHandler() {
		      public void onValueChange(ValueChangeEvent event) {
		        Date date = (Date) event.getValue();
		        String dateString = DateTimeFormat.getMediumDateFormat().format(date);
		        anfangszeitpunktField.setText(dateString);
		      }
		    });
		    
		    datePicker.setValue(new Date(), true);
		    vPanel.add(text);
		    vPanel.add(datePicker);
		    vPanel.add(eZ);
		    vPanel.add(endzeitpunktField);
		    
		    
			 /**
			  * Hier wird ein anderer datePicker erzeugt und dem Endzeitpunktfeld zugeordnet. 
			  */
		    DatePicker date2Picker = new DatePicker();
		    final Label text2 = new Label();
		        
		   
		    date2Picker.addValueChangeHandler(new ValueChangeHandler() {
		      public void onValueChange(ValueChangeEvent event) {
		        Date date = (Date) event.getValue();
		        String dateString = DateTimeFormat.getMediumDateFormat().format(date);
		        endzeitpunktField.setText(dateString);
		      }
		    });
		    
		    datePicker.setValue(new Date(), true);
		    vPanel.add(text2);
		    vPanel.add(date2Picker);
		    vPanel.add(sendSucheButton);
	      

	        initWidget(this.vPanel);
	        vPanel.add(sendSucheButton);
	        
	        /**
	         * Mit einem Klick auf den Suchen Button wird eine Flextable mit den Informationen zu einem Beitrag erzeugt.
	         */
			sendSucheButton.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					
					vPanel.clear();
					vPanel.add(title);
						 // Erstelle Tabelle für Infos von einem bestimmten Beitrag in einem bestimmten Zeitraum
						infoFlexTable.setText(0, 0, "Text");
						infoFlexTable.setText(0, 1, "Erstellungszeitpunkt");
					    infoFlexTable.setText(0, 2, "User Vorname");
					    infoFlexTable.setText(0, 3, "User Nachname");
						infoFlexTable.setText(0, 4, "Anzahl Kommentare");
					    infoFlexTable.setText(0, 5, "Anzahl Likes");

					    
					    vPanel.add(infoFlexTable); 
					    infoFlexTable.addStyleName("flextable");
					    vPanel.add(zuruck);
					    
					}
					    
				
			});
			
			
			/**
			 * Mit einem Klick auf zurueck Button kann man eine neue Suche beginnen.
			 */
	
	zuruck.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			vPanel.clear();
			FormInfosVonBeitragReport infosVonB = new FormInfosVonBeitragReport("");
			vPanel.add(infosVonB);
			
			    
			}
			    
		
	});
	

		
	}


	}



