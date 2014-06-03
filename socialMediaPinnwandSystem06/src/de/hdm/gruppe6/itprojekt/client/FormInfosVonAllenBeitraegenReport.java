package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, Özlem Gül, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 *
 * Die Klasse FormInfosVonALlenBeitraegenReport enthält die Auswahlmaske.
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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

public class FormInfosVonAllenBeitraegenReport extends Composite {
	/**
	 * Hier werden die Widgets und die Panels festgelegt.
	 */
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private FlexTable postFlexTable = new FlexTable();
	private Button alleBeitraegeButton = new Button("Alle Beitraege ausgeben");
	private Label title = new Label(
			"Report: Informationen von allen Beitraegen");
	private Button ak = new Button("Sortieren nach Anzahl der Kommentare");
	private Button al = new Button("Sortieren nach Anzahl der Likes");
	private Button zuruck = new Button("zurueck");

	/**
	 * Konstruktor
	 * 
	 * @param content
	 */

	public FormInfosVonAllenBeitraegenReport(String content) {
		initWidget(this.vPanel);
		vPanel.add(title);
		title.addStyleName("title");

		// RadioButton rb1 = new
		// RadioButton("Sortieren nach Anzahl der Likes","Sortieren nach Anzahl der Likes");
		// RadioButton rb2 = new RadioButton
		// ("Sortieren nach Anzahl der Kommentare",
		// "Sortieren nach Anzahl der Kommentare");
		// vPanel.add(rb1);
		// vPanel.add(rb2);
		//
		// rb1.setChecked(true);
		final Label aZ = new Label("Anfangszeitpunkt eingeben");
		final Label eZ = new Label("Endzeitpunkt eingeben");
		final TextBox anfangszeitpunktField = new TextBox();
		final TextBox endzeitpunktField = new TextBox();
		vPanel.add(title);
		title.addStyleName("title");
		vPanel.add(aZ);
		vPanel.add(anfangszeitpunktField);

		/**
		 * Hier wird ein datePicker erzeugt und dem Anfangszeitpunkfeld
		 * zugeordnet.
		 */
		DatePicker datePicker = new DatePicker();
		final Label text = new Label();

		datePicker.addValueChangeHandler(new ValueChangeHandler() {
			public void onValueChange(ValueChangeEvent event) {
				Date date = (Date) event.getValue();
				String dateString = DateTimeFormat.getMediumDateFormat()
						.format(date);
				anfangszeitpunktField.setText(dateString);
			}
		});

		datePicker.setValue(new Date(), true);
		vPanel.add(text);
		vPanel.add(datePicker);
		vPanel.add(eZ);
		vPanel.add(endzeitpunktField);

		/**
		 * Hier wird ein anderer datePicker erzeugt und dem Endzeitpunktfeld
		 * zugeordnet.
		 */
		DatePicker date2Picker = new DatePicker();
		final Label text2 = new Label();

		date2Picker.addValueChangeHandler(new ValueChangeHandler() {
			public void onValueChange(ValueChangeEvent event) {
				Date date = (Date) event.getValue();
				String dateString = DateTimeFormat.getMediumDateFormat()
						.format(date);
				endzeitpunktField.setText(dateString);
			}
		});

		datePicker.setValue(new Date(), true);
		vPanel.add(text2);
		vPanel.add(date2Picker);

		RootPanel.get("Details").add(hPanel);

		hPanel.add(ak);
		hPanel.add(al);

		/**
		 * Mit einem Klick auf den alle Beitraege ausgeben Button wird eine
		 * Flextable mit allen Beitraegen erzeugt.
		 * 
		 */
		ak.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				hPanel.clear();
				vPanel.clear();
				vPanel.add(title);

				postFlexTable.setText(0, 0, "text");
				postFlexTable.setText(0, 1, "Anfangszeitpunkt");
				postFlexTable.setText(0, 2, "Endzeitpunkt");
				postFlexTable.setText(0, 3, "Anzahl der Kommentare");

				vPanel.add(postFlexTable);
				vPanel.add(zuruck);
				postFlexTable.addStyleName("flextable");

			}

		});

		/**
		 * Mit einem Klick auf den alle Beitraege ausgeben Button wird eine
		 * Flextable mit allen Beitraegen erzeugt.
		 * 
		 */
		al.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				hPanel.clear();
				vPanel.clear();
				vPanel.add(title);

				postFlexTable.setText(0, 0, "text");
				postFlexTable.setText(0, 1, "Anfangszeitpunkt");
				postFlexTable.setText(0, 2, "Endzeitpunkt");
				postFlexTable.setText(0, 3, " Anzahl der Likes");

				vPanel.add(postFlexTable);
				vPanel.add(zuruck);
				postFlexTable.addStyleName("flextable");

			}

		});

		zuruck.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				vPanel.clear();
				FormInfosVonAllenBeitraegenReport infosVonB = new FormInfosVonAllenBeitraegenReport(
						"");
				vPanel.add(infosVonB);

			}

		});
	}

}
