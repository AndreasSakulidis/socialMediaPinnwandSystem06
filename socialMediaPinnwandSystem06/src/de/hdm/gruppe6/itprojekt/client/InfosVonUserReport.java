package de.hdm.gruppe6.itprojekt.client;


import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;


public class InfosVonUserReport extends  Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel dPanel = new HorizontalPanel();
//	private VerticalPanel d2Panel = new VerticalPanel();
//	
	private FlexTable infoFlexTable = new FlexTable();
	private Textbeitrag textb = new Textbeitrag();
//	final Button datum = new Button ("Datum auswählen");
	final Button zuruck = new Button ("zurueck");
	final Label title = new Label("Report: Informationen von einem User");
	
	public InfosVonUserReport(String content) {
		RootPanel.get("Details").add(vPanel);
		RootPanel.get("Details").add(dPanel);
//		RootPanel.get("Details").add(d2Panel);
//		
		
		
		final Button sendSucheButton = new Button("Suchen");
		final Label userSuchen = new Label ("User eingeben");
		final Label aZ = new Label ("Anfangszeitpunkt eingeben");
		final Label eZ = new Label ("Endzeitpunkt eingeben");
		final TextBox userSuchenField = new TextBox();
		final TextBox anfangszeitpunktField = new TextBox();
		anfangszeitpunktField.addStyleName("AZ");
		final TextBox endzeitpunktField = new TextBox();
		vPanel.add(title);
        title.addStyleName("title");
		sendSucheButton.addStyleName("beitragSuchen");
		vPanel.add(userSuchen);
	    vPanel.add(userSuchenField);
	    vPanel.add(aZ);
	    vPanel.add(anfangszeitpunktField);
	    System.out.println("\n");
	    System.out.println("\n");
	    DatePicker datePicker = new DatePicker();
	    final Label text = new Label();
	    
	    
	    // Set the value in the text box when the user selects a date
	  
	    datePicker.addValueChangeHandler(new ValueChangeHandler() {
	      public void onValueChange(ValueChangeEvent event) {
	        Date date = (Date) event.getValue();
	        String dateString = DateTimeFormat.getMediumDateFormat().format(date);
	        anfangszeitpunktField.setText(dateString);
	      }
	    });
	    
	    // Set the default value
	    datePicker.setValue(new Date(), true);

	  
	   
   // Add the widgets to the page
	    vPanel.add(text);
	    vPanel.add(datePicker);
	    
//	    RootPanel.get("Details").add(dPanel);
//	    
//	    vPanel.add(datum);
	    vPanel.add(eZ);
	    vPanel.add(endzeitpunktField);
	    System.out.println();
	    DatePicker date2Picker = new DatePicker();
	    final Label text2 = new Label();
	        
	    // Set the value in the text box when the user selects a date
	  
	    date2Picker.addValueChangeHandler(new ValueChangeHandler() {
	      public void onValueChange(ValueChangeEvent event) {
	        Date date = (Date) event.getValue();
	        String dateString = DateTimeFormat.getMediumDateFormat().format(date);
	        endzeitpunktField.setText(dateString);
	      }
	    });
	    
	    // Set the default value
	    datePicker.setValue(new Date(), true);

	  
	   
   // Add the widgets to the page
	    vPanel.add(text2);
	    vPanel.add(date2Picker);
	    
    
		vPanel.add(sendSucheButton);
        
		
		
        initWidget(this.vPanel);
        vPanel.add(sendSucheButton);
        sendSucheButton.addClickHandler(new ClickHandler(){
        	@Override
			public void onClick(ClickEvent event) {
        				
        			vPanel.clear();
        			vPanel.add(title);
					 // Erstelle Tabelle für Infos von einem bestimmten Beitrag in einem bestimmten Zeitraum
					infoFlexTable.setText(0, 0, "User Nickname");
					infoFlexTable.setText(0, 1, "Anzahl Beitraege");
					infoFlexTable.setText(0, 2, "Anzahl Abonnements");
				    infoFlexTable.setText(0, 3, "Anzahl Kommentare");
				  

				    
				    vPanel.add(infoFlexTable); 
				    infoFlexTable.addStyleName("flextable");
				    vPanel.add(zuruck);
				    
				    
				}
				    
			
		});
		

		    
			   	zuruck.addClickHandler(new ClickHandler(){

		    		@Override
		    		public void onClick(ClickEvent event) {
		    			vPanel.clear();
		    			dPanel.clear();
		    			InfosVonUserReport infosVonU = new InfosVonUserReport("");
		    			vPanel.add(infosVonU);
		    		
		    			}
		    			    
		    		
		    	});
		    	
	
	
	}}
	
