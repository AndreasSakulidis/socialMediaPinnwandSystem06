package de.hdm.gruppe6.itprojekt.client;

/**
 * @author Bharti Kumar, Özlem Gül, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * 
 * Die Klasse FormInfosVonBeitragReport enthält die Auswahlmaske.
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
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.ReportGenerator;
import de.hdm.gruppe6.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.report.HTMLReportWriter;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonBeitragReport;

public class FormInfosVonBeitragReport extends Composite {
	/**
	 * Hier werden die Widgets und die Panels festgelegt.
	 */
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private Label title = new Label("Report: Informationen ueber einen Beitrag");
	final Button zurueck = new Button("zurueck");
	protected PinnwandVerwaltungService pinnwandverwaltung = null;

	PinnwandVerwaltungServiceAsync pinnwandVerwaltung = GWT
			.create(PinnwandVerwaltungService.class);
	ReportGeneratorAsync reportGenerator = GWT.create(ReportGenerator.class);

	public FormInfosVonBeitragReport(String content) {
		RootPanel.get("Details").add(vPanel);
		RootPanel.get("Details").add(hPanel);
		/**
		 * Hier werden die Buttons und die Labels festgelegt und dem vPanel
		 * zugeordnet.
		 */
		final Button anzeigeButton = new Button("Report anzeigen");
		final Label lblSortieren = new Label("Beliebtester Beitrag: ");
		final RadioButton rbKommentare = new RadioButton("RadioBeitrag",
				"anhand Anzahl der Kommentare");
		final RadioButton rbLikes = new RadioButton("RadioBeitrag",
				"anhand Anzahl der Likes");
		final Label aZ = new Label("Anfangszeitpunkt auswaehlen");
		final Label eZ = new Label("Endzeitpunkt auswaehlen");
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

		rbKommentare.setChecked(true);
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

		/**
		 * Hier wird ein anderer datePicker erzeugt und dem Endzeitpunktfeld
		 * zugeordnet.
		 */
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

		final String anfangsZeitpunkt = anfangszeitpunktField.getText();
		final String endZeitpunkt = endzeitpunktField.getText();

		vPanel.add(lblSortieren);
		vPanel.add(rbKommentare);
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
				if (anfangszeitpunktField.getText().isEmpty()
						| endzeitpunktField.getText().isEmpty()) {
					Window.alert("Bitte einen Anfangs- und/oder einen Enzeitpunkt auswaehlen!");
					vPanel.clear();
					String anfangsZeitpunkt = anfangszeitpunktField.getText();
					String endZeitpunkt = endzeitpunktField.getText();
					hPanel.clear();
					FormInfosVonBeitragReport formInfosVonBeitragReport = new FormInfosVonBeitragReport(
							"");
					vPanel.add(formInfosVonBeitragReport);
				}
				vPanel.clear();
				vPanel.add(title);
				if (rbLikes.isChecked()) {
					pinnwandVerwaltung.findeTextbeitragMitMeistenLikes(
							anfangszeitpunktField.getText(),
							endzeitpunktField.getText(),
							new AsyncCallback<Textbeitrag>() {
								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Fehler bei der Suche nach Textbeitrag");
								}

								@Override
								public void onSuccess(Textbeitrag textbeitrag) {
									if(textbeitrag == null){
										Window.alert("Textbeitrag existiert nicht");
										vPanel.clear();
										hPanel.clear();
										FormInfosVonBeitragReport formInfosVonBeitragReport = new FormInfosVonBeitragReport(
												"");
										vPanel.add(formInfosVonBeitragReport);
									}

									reportGenerator
											.erstelleInfosVonBeitragReport(
													textbeitrag,
													anfangszeitpunktField
															.getText(),
													endzeitpunktField.getText(),
													new AsyncCallback<InfosVonBeitragReport>() {
														//
														@Override
														public void onFailure(
																Throwable caught) {
															System.out
																	.println(caught
																			.getMessage());
															Window.alert("Fehler beim Erstellen des Reports");
															vPanel.clear();
															hPanel.clear();
															FormInfosVonBeitragReport formInfosVonBeitragReport = new FormInfosVonBeitragReport(
																	"");
															vPanel.add(formInfosVonBeitragReport);

														}

														@Override
														public void onSuccess(
																InfosVonBeitragReport report) {
															System.out
																	.println("onSUCUCUCUCUUCUC");
															if (report != null) {
																HTMLReportWriter writer = new HTMLReportWriter();
																writer.process(report);
																System.out
																		.println("Report:::: "
																				+ writer.getReportText());
																HTMLPanel htmlPanel = new HTMLPanel(
																		writer.getReportText());
																vPanel.add(htmlPanel);
																vPanel.add(zurueck);
																zurueck.addClickHandler(new ClickHandler() {

																	@Override
																	public void onClick(
																			ClickEvent event) {
																		vPanel.clear();
																		hPanel.clear();
																		FormInfosVonBeitragReport formInfosVonBeitragReport = new FormInfosVonBeitragReport(
																				"");
																		vPanel.add(formInfosVonBeitragReport);

																	}
																});

															}

														}

													});

								}

							});

				}

				else {
					pinnwandVerwaltung
							.findeTextbeitragMitMeistenKommentarenJeZeitraum(
									anfangszeitpunktField.getText(),
									endzeitpunktField.getText(),
									new AsyncCallback<Textbeitrag>() {
										

										@Override
										public void onFailure(Throwable caught) {
											Window.alert("Fehler bei der Suche nach Textbeitrag");
											vPanel.clear();
											hPanel.clear();
											FormInfosVonBeitragReport formInfosVonBeitragReport = new FormInfosVonBeitragReport(
													"");
											vPanel.add(formInfosVonBeitragReport);
										}

										@Override
										public void onSuccess(
												Textbeitrag textbeitrag) {
											if(textbeitrag == null){
												Window.alert("Textbeitrag existiert nicht");
												vPanel.clear();
												hPanel.clear();
												FormInfosVonBeitragReport formInfosVonBeitragReport = new FormInfosVonBeitragReport(
														"");
												vPanel.add(formInfosVonBeitragReport);
												
											}

											reportGenerator
													.erstelleInfosVonBeitragReport(
															textbeitrag,
															anfangszeitpunktField
																	.getText(),
															endzeitpunktField
																	.getText(),
															new AsyncCallback<InfosVonBeitragReport>() {
																//
																@Override
																public void onFailure(
																		Throwable caught) {
																	Window.alert("Fehler beim Erstellen des Reports");
																	vPanel.clear();
																	hPanel.clear();
																	FormInfosVonBeitragReport formInfosVonBeitragReport = new FormInfosVonBeitragReport(
																			"");
																	vPanel.add(formInfosVonBeitragReport);

																}

																@Override
																public void onSuccess(
																		InfosVonBeitragReport report) {
																	System.out
																			.println("onSUCUCUCUCUUCUC");
																	if (report != null) {
																		HTMLReportWriter writer = new HTMLReportWriter();
																		writer.process(report);
																		System.out
																				.println("Report:::: "
																						+ writer.getReportText());
																		HTMLPanel htmlPanel = new HTMLPanel(
																				writer.getReportText());
																		vPanel.add(htmlPanel);

																		vPanel.add(zurueck);
																		zurueck.addClickHandler(new ClickHandler() {

																			@Override
																			public void onClick(
																					ClickEvent event) {
																				vPanel.clear();
																				hPanel.clear();
																				FormInfosVonBeitragReport formInfosVonBeitragReport = new FormInfosVonBeitragReport(
																						"");
																				vPanel.add(formInfosVonBeitragReport);

																			}
																		});

																	}

																}

															});
										}

									});
				}

			}

		});

	}
}
