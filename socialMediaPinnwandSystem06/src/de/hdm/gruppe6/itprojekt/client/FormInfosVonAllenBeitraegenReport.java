package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, �zlem G�l, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 *
 * Die Klasse FormInfosVonALlenBeitraegenReport enth�lt die Auswahlmaske.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.ReportGenerator;
import de.hdm.gruppe6.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.gruppe6.itprojekt.shared.report.HTMLReportWriter;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonAllenBeitraegenReport;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonAllenPinnwaendenReport;

public class FormInfosVonAllenBeitraegenReport extends Composite {
	/**
	 * Hier werden die Widgets und die Panels festgelegt.
	 */
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private Label title = new Label(
			"Report: Informationen ueber alle Beitraege");
	final Button zurueck = new Button("zurueck");
	protected PinnwandVerwaltungService pinnwandverwaltung = null;

	PinnwandVerwaltungServiceAsync pinnwandVerwaltung = GWT
			.create(PinnwandVerwaltungService.class);
	ReportGeneratorAsync reportGenerator = GWT.create(ReportGenerator.class);

	public FormInfosVonAllenBeitraegenReport(String content) {
		RootPanel.get("Details").add(vPanel);
		RootPanel.get("Details").add(hPanel);
		/**
		 * Hier werden die Buttons und die Labels festgelegt und dem vPanel
		 * zugeordnet.
		 */
		final Button anzeigeButton = new Button("Report anzeigen");
		final Label lblSortieren = new Label("Sortieren nach: ");
		final Label aZ = new Label("Anfangszeitpunkt auswaehlen");
		final Label eZ = new Label("Endzeitpunkt auswaehlen");
		final RadioButton rbKommentare = new RadioButton("RadioBeitrag",
				"Anzahl der Kommentare");
		final RadioButton rbLikes = new RadioButton("RadioBeitrag",
				"Anzahl der Likes");
		final TextBox anfangszeitpunktField = new TextBox();
		final TextBox endzeitpunktField = new TextBox();

		anfangszeitpunktField.setReadOnly(true);
		anfangszeitpunktField.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Bitte Kalenderfunktion verwenden");

			}
		});

		endzeitpunktField.setReadOnly(true);
		endzeitpunktField.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Bitte Kalenderfunktion verwenden");

			}
		});

		anzeigeButton.addStyleName("ReportAnzeigen");
		vPanel.add(title);
		title.addStyleName("title");
		vPanel.add(aZ);

		/**
		 * Hier wird ein datePicker erzeugt und dem Anfangszeitpunkfeld
		 * zugeordnet.
		 */
		DatePicker datePicker = new DatePicker();
		final Label text = new Label();

		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				DateTimeFormat sdf = DateTimeFormat.getFormat("yyyy-MM-dd");
				String dateString = sdf.format(date);
				anfangszeitpunktField.setText(dateString);
			}
		});

		vPanel.add(text);

		vPanel.add(datePicker);
		vPanel.add(anfangszeitpunktField);
		vPanel.add(eZ);
		DatePicker date2Picker = new DatePicker();
		final Label text2 = new Label();

		date2Picker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				DateTimeFormat sdf = DateTimeFormat.getFormat("yyyy-MM-dd");
				String dateString = sdf.format(date);
				endzeitpunktField.setText(dateString);
			}
		});

		vPanel.add(text2);
		vPanel.add(date2Picker);
		vPanel.add(endzeitpunktField);
		vPanel.add(lblSortieren);
		vPanel.add(rbKommentare);
		rbLikes.setChecked(true);
		vPanel.add(rbLikes);
		vPanel.add(anzeigeButton);

		initWidget(this.vPanel);
		vPanel.add(anzeigeButton);

		/**
		 * Mit einem Klick auf den Suchen Button wird eine Flextable mit den
		 * Informationen zu einem Beitrag erzeugt.
		 */
		anzeigeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				vPanel.clear();
				vPanel.add(title);
				String anfangsZeitpunkt = anfangszeitpunktField.getText();
				String endZeitpunkt = endzeitpunktField.getText();
				if (anfangszeitpunktField.getText().isEmpty()
						| endzeitpunktField.getText().isEmpty()) {
					Window.alert("Bitte einen Anfangs- und/oder einen Enzeitpunkt auswaehlen!");
					vPanel.clear();
					hPanel.clear();
					FormInfosVonAllenBeitraegenReport formInfosVonAllenBeitraegenReport = new FormInfosVonAllenBeitraegenReport(
							"");
					vPanel.add(formInfosVonAllenBeitraegenReport);
				}
				if (rbLikes.isChecked()) {

					String sortierung = "likes";

					reportGenerator.erstelleInfosVonAllenBeitraegenReport(
							anfangsZeitpunkt, endZeitpunkt, sortierung,
							new AsyncCallback<InfosVonAllenBeitraegenReport>() {

								@Override
								public void onFailure(Throwable caught) {
									System.out.println(caught.getMessage());
									Window.alert("Fehler beim Erstellen des Reports");
									vPanel.clear();
									hPanel.clear();
									FormInfosVonAllenBeitraegenReport formInfosVonAllenBeitraegenReport = new FormInfosVonAllenBeitraegenReport(
											"");
									vPanel.add(formInfosVonAllenBeitraegenReport);
								}

								@Override
								public void onSuccess(
										InfosVonAllenBeitraegenReport report) {
									if (report != null) {
										HTMLReportWriter writer = new HTMLReportWriter();
										writer.process(report);
										System.out.println("Report:::: "
												+ writer.getReportText());
										HTMLPanel htmlPanel = new HTMLPanel(
												writer.getReportText());
										vPanel.add(htmlPanel);

										vPanel.add(zurueck);
										zurueck.addClickHandler(new ClickHandler() {

											@Override
											public void onClick(ClickEvent event) {
												vPanel.clear();
												hPanel.clear();
												FormInfosVonAllenBeitraegenReport formInfosVonAllenBeitraegenReport = new FormInfosVonAllenBeitraegenReport(
														"");
												vPanel.add(formInfosVonAllenBeitraegenReport);

											}
										});
									}

								}

							});
					//
				}
				
				else{
					String sortierung = "kommentare";
					reportGenerator.erstelleInfosVonAllenBeitraegenReport(
							anfangsZeitpunkt, endZeitpunkt, sortierung,
							new AsyncCallback<InfosVonAllenBeitraegenReport>() {

								@Override
								public void onFailure(Throwable caught) {
									System.out.println(caught.getMessage());
									Window.alert("Fehler beim Erstellen des Reports");
									vPanel.clear();
									hPanel.clear();
									FormInfosVonAllenBeitraegenReport formInfosVonAllenBeitraegenReport = new FormInfosVonAllenBeitraegenReport(
											"");
									vPanel.add(formInfosVonAllenBeitraegenReport);
								}

								@Override
								public void onSuccess(
										InfosVonAllenBeitraegenReport report) {
									if (report != null) {
										HTMLReportWriter writer = new HTMLReportWriter();
										writer.process(report);
										System.out.println("Report:::: "
												+ writer.getReportText());
										HTMLPanel htmlPanel = new HTMLPanel(
												writer.getReportText());
										vPanel.add(htmlPanel);

										vPanel.add(zurueck);
										zurueck.addClickHandler(new ClickHandler() {

											@Override
											public void onClick(ClickEvent event) {
												vPanel.clear();
												hPanel.clear();
												FormInfosVonAllenBeitraegenReport formInfosVonAllenBeitraegenReport = new FormInfosVonAllenBeitraegenReport(
														"");
												vPanel.add(formInfosVonAllenBeitraegenReport);

											}
										});
									}

								}

							});
					
					
				}
			}

		});

	}

}
