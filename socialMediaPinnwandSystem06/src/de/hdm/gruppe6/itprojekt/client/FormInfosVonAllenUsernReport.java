
package de.hdm.gruppe6.itprojekt.client;

/**
* @author Bharti Kumar, ?zlem G?l, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
*
* Die Klasse FormInfosVonALlenUsernReport enth?lt die Auswahlmaske.
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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;


public class FormInfosVonAllenUsernReport extends Composite {
/**
* Hier werden die Widgets und die Panels festgelegt.
*/
private VerticalPanel vPanel = new VerticalPanel();
private HorizontalPanel hPanel = new HorizontalPanel();
private FlexTable postFlexTable = new FlexTable();
private Button alleUserButton = new Button("Alle User ausgeben");
private Label title = new Label ("Report: Informationen von allen Usern");
private Button ak = new Button ("Sortieren nach Anzahl der Kommentare");
private Button al = new Button("Sortieren nach Anzahl der Likes");
private Button aa = new Button ("Sortieren nach Anzahl der Abonnenten");
private Button zuruck = new Button ("zurueck");


/**
* Konstruktor
* @param content
*/


public FormInfosVonAllenUsernReport(String content){
initWidget(this.vPanel);
vPanel.add(title);
title.addStyleName("title");

final Label aZ = new Label ("Anfangszeitpunkt eingeben");
final Label eZ = new Label ("Endzeitpunkt eingeben");
final TextBox anfangszeitpunktField = new TextBox();
final TextBox endzeitpunktField = new TextBox();
vPanel.add(title);
title.addStyleName("title");
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

RootPanel.get("Details").add(hPanel);


hPanel.add(ak);
hPanel.add(al);
hPanel.add(aa);


ak.addClickHandler(new ClickHandler(){
@Override
public void onClick(ClickEvent event) {

	hPanel.clear();
vPanel.clear();
vPanel.add(title);

postFlexTable.setText(0, 0, "ID");
postFlexTable.setText(0, 1, "Vorname");
postFlexTable.setText(0, 2, "Nachname");
postFlexTable.setText(0, 3, "Nickname");
postFlexTable.setText(0, 4, "Anfangszeitpunkt");
postFlexTable.setText(0, 5, "Endzeitpunkt");
postFlexTable.setText(0,6, "Anzahl der Kommentare");
    
vPanel.add(postFlexTable);
vPanel.add(zuruck);
postFlexTable.addStyleName("flextable");
    




}
});


/**
* Mit einem Klick auf den alle User ausgeben Button wird eine Flextable mit allen Usern erzeugt.
*
*/

al.addClickHandler(new ClickHandler(){
@Override
public void onClick(ClickEvent event) {

	hPanel.clear();
vPanel.clear();
vPanel.add(title);

postFlexTable.setText(0, 0, "ID");
postFlexTable.setText(0, 1, "Vorname");
postFlexTable.setText(0, 2, "Nachname");
postFlexTable.setText(0, 3, "Nickname");
postFlexTable.setText(0, 4, "Anfangszeitpunkt");
postFlexTable.setText(0, 5, "Endzeitpunkt");
postFlexTable.setText(0,6, "Anzahl der Likes");
    
vPanel.add(postFlexTable);
vPanel.add(zuruck);
postFlexTable.addStyleName("flextable");
    




}
});

aa.addClickHandler(new ClickHandler(){
@Override
public void onClick(ClickEvent event) {

	hPanel.clear();
vPanel.clear();
vPanel.add(title);

postFlexTable.setText(0, 0, "ID");
postFlexTable.setText(0, 1, "Vorname");
postFlexTable.setText(0, 2, "Nachname");
postFlexTable.setText(0, 3, "Nickname");
postFlexTable.setText(0, 4, "Anfangszeitpunkt");
postFlexTable.setText(0, 5, "Endzeitpunkt");
postFlexTable.setText(0,6, "Anzahl der Abonnenten");
    
vPanel.add(postFlexTable);
vPanel.add(zuruck);
postFlexTable.addStyleName("flextable");
    




}
});

zuruck.addClickHandler(new ClickHandler(){

@Override
public void onClick(ClickEvent event) {
vPanel.clear();
FormInfosVonAllenUsernReport infosVonU = new FormInfosVonAllenUsernReport("");
vPanel.add(infosVonU);


}


});

}
}