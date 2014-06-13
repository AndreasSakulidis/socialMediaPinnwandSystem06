package de.hdm.gruppe6.itprojekt.server.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.gruppe6.itprojekt.server.PinnwandVerwaltungImpl;
import de.hdm.gruppe6.itprojekt.server.db.TextbeitragMapper;
import de.hdm.gruppe6.itprojekt.server.db.UserMapper;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.ReportGenerator;
import de.hdm.gruppe6.itprojekt.shared.bo.Pinnwand;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.report.Column;
import de.hdm.gruppe6.itprojekt.shared.report.CompositeParagraph;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonAllenBeitraegenReport;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonAllenPinnwaendenReport;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonBeitragReport;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonPinnwandReport;
import de.hdm.gruppe6.itprojekt.shared.report.Report;
import de.hdm.gruppe6.itprojekt.shared.report.Row;
import de.hdm.gruppe6.itprojekt.shared.report.SimpleParagraph;

/**
 * @author Bharti Kumar, �zlem G�l, Michael Schelkle, Andreas Sakulidis, Gezim
 *         Krasniqi, Ezgi Demirbilek Anlehnung an Professor Thies
 * 
 *         Implementierung des <code>ReportGenerator</code>-Interface. Die
 *         technische Realisierung bzgl. <code>RemoteServiceServlet</code> bzw.
 *         GWT RPC erfolgt analog zu {@lBankAdministrationImplImpl}. Für
 *         Details zu GWT RPC siehe dort.
 * 
 * @see ReportGenerator
 * 
 */
@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements
		ReportGenerator {

	/**
	 * 
	 * Ein ReportGenerator benötigt Zugriff auf die PinnwandVerwaltung, da
	 * diese die essentiellen Methoden für die Koexistenz von Datenobjekten
	 * (vgl. bo-Package) bietet.
	 */

	private PinnwandVerwaltungService pinnwandverwaltung = null;
	protected UserMapper usermapper = null;
	protected TextbeitragMapper textbeitragmapper = null;

	/**
	 * <p>
	 * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
	 * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
	 * ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines
	 * anderen Konstruktors ist durch die Client-seitige Instantiierung durch
	 * <code>GWT.create(Klassenname.class)</code> nach derzeitigem Stand nicht
	 * möglich.
	 * </p>
	 * <p>
	 * Es bietet sich also an, eine separate Instanzenmethode zu erstellen, die
	 * Client-seitig direkt nach <code>GWT.create(Klassenname.class)</code>
	 * aufgerufen wird, um eine Initialisierung der Instanz vorzunehmen.
	 * </p>
	 */
	public ReportGeneratorImpl() throws IllegalArgumentException {
	}

	/**
	 * Initialsierungsmethode. Siehe dazu Anmerkungen zum
	 * No-Argument-Konstruktor.
	 * 
	 * @see #ReportGeneratorImpl()
	 */
	public void init() throws IllegalArgumentException {
		/*
		 * Ein ReportGeneratorImpl-Objekt instantiiert für seinen Eigenbedarf
		 * eine BankVerwaltungImpl-Instanz.
		 */
		PinnwandVerwaltungImpl p = new PinnwandVerwaltungImpl();
		p.init();
		this.pinnwandverwaltung = p;
	}

	/**
	 * Auslesen der zugehörigen PinnwandVerwaltung (interner Gebrauch).
	 * 
	 * @return das PinnwandVerwaltungServiceobjekt
	 */
	protected PinnwandVerwaltungService getPinnwandVerwaltungService() {
		return this.pinnwandverwaltung;
	}

	protected void addImprint(Report r) {
		/*
		 * Das Impressum soll wesentliche Informationen �ber die Bank enthalten.
		 */

		CompositeParagraph imprint = new CompositeParagraph();

		// Das eigentliche Hinzuf�gen des Impressums zum Report.
		r.setImprint(imprint);

	}

	/**
	 * Erstellen von <code>InfosVonUserReport</code>-Objekten.
	 * 
	 * @param pinnwand
	 *            das Pinnwandobjekt bzgl. dessen der Report erstellt werden
	 *            soll.
	 * @return der fertige Report
	 * @throws IllegalArgumentException
	 */
	@Override
	public InfosVonPinnwandReport erstelleInfosVonPinnwandReport(
			Pinnwand pinnwand) {
		if (this.getPinnwandVerwaltungService() == null)
			return null;

		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		InfosVonPinnwandReport result = new InfosVonPinnwandReport();

		// Jeder Report hat einen Titel (Bezeichnung / Überschrift).
		result.setTitle("Infos von PinnwandNr. " + pinnwand.getId());
		this.addImprint(result);
		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * mehrzeilig, daher die Verwendung von CompositeParagraph.
		 */
		CompositeParagraph header = new CompositeParagraph();

		// Name und Vorname des Users aufnehmen
		header.addSubParagraph(new SimpleParagraph("Eigentuemer: "
				+ pinnwand.getEigentuemer()));

		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.setHeaderData(header);

		/*
		 * Ab hier erfolgt ein zeilenweises Hinzufügen von Konto-Informationen.
		 */

		/*
		 * Zunächst legen wir eine Kopfzeile für die Kundeninfos-Tabelle an.
		 */
		Row headline = new Row();

		/*
		 * Wir wollen Zeilen mit 3 Spalten in der Tabelle erzeugen. In die erste
		 * Spalte schreiben wir den jeweiligen User, in die zweite Spalte
		 * schreiben wir den Anfangszeitpunkt und in die letzte Zeile schreiben
		 * wir den Endzeitpunkt. In der Kopfzeile legen wir also entsprechende
		 * Überschriften ab.
		 */
		headline.addColumn(new Column("Anzahl Abonnenten"));
		headline.addColumn(new Column("Anzahl eigener Beitraege"));
		headline.addColumn(new Column("Anzahl Likes"));
		// Hinzufügen der Kopfzeile
		result.addRow(headline);

		/*
		 * Nun werden sämtliche Daten der User ausgelesen und deren
		 * Anfangszeitpunkt und Endzeitpunkt in die Tabelle eingetragen.
		 */

		// Eine leere Zeile anlegen.
		Row accountRow = new Row();

		// Erste Spalte: Anzahl der Abonnennten der Pinnwand hinzuf�gen
		accountRow.addColumn(new Column(String.valueOf(pinnwandverwaltung
				.zaehleAbosVonPinnwandAnhandEigentuemer(pinnwand
						.getEigentuemer()))));

		// Zweite Spalte: Anzahl der Beitr�ge die der Eigent�mer der Pinnwand
		// verfasst hat hinzuf�gen
		accountRow.addColumn(new Column(String.valueOf(pinnwandverwaltung
				.zaehleEigeneBeitraegeVonPinnwandAnhandPinnwandID(pinnwand
						.getId()))));

		// Dritte Spalte: Anzahl der Likes die, die Textbeitr�ge des
		// Pinnwandeigent�mers bekommen hat hinz�f�gen
		accountRow.addColumn(new Column(String.valueOf(pinnwandverwaltung
				.zaehleLikesVonPinnwandAnhandPinnwandID(pinnwand.getId()))));

		// und schlie�lich die Zeile dem Report hinzuf�gen.
		result.addRow(accountRow);

		return result;

	}

	@Override
	public InfosVonAllenPinnwaendenReport erstelleInfosVonAllenPinnwaendenReport(
			String anfangsZeitpunkt, String endZeitpunkt) {
		if (this.getPinnwandVerwaltungService() == null)
			return null;

		/*
		 * Zun�chst legen wir uns einen leeren Report an.
		 */
		InfosVonAllenPinnwaendenReport result = new InfosVonAllenPinnwaendenReport();

		// Jeder Report hat einen Titel (Bezeichnung / �berschrift).
		result.setTitle("Infos aller Pinnwaende");

		// Imressum hinzuf�gen
		this.addImprint(result);

		/*
		 * Datum der Erstellung hinzuf�gen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		/*
		 * Da AllAccountsOfAllCustomersReport-Objekte aus einer Sammlung von
		 * AllAccountsOfCustomerReport-Objekten besteht, ben�tigen wir keine
		 * Kopfdaten f�r diesen Report-Typ. Wir geben einfach keine Kopfdaten
		 * an...
		 */

		/*
		 * Nun m�ssen s�mtliche Kunden-Objekte ausgelesen werden. Anschlie�end
		 * wir f�r jedes Kundenobjekt c ein Aufruf von
		 * createAllAccountsOfCustomerReport(c) durchgef�hrt und somit jeweils
		 * ein AllAccountsOfCustomerReport-Objekt erzeugt. Diese Objekte werden
		 * sukzessive der result-Variable hinzugef�gt. Sie ist vom Typ
		 * AllAccountsOfAllCustomersReport, welches eine Subklasse von
		 * CompositeReport ist.
		 */
		Vector<Pinnwand> allePinnwaende = this.pinnwandverwaltung
				.findeAllePinnwaendeJeZeitraum(anfangsZeitpunkt, endZeitpunkt);

		for (Pinnwand p : allePinnwaende) {
			/*
			 * Anlegen des jew. Teil-Reports und Hinzuf�gen zum Gesamt-Report.
			 */
			result.addSubReport(this.erstelleInfosVonPinnwandReport(p));
		}

		/*
		 * Zu guter Letzt m�ssen wir noch den fertigen Report zur�ckgeben.
		 */
		return result;
	}

	/**
	 * Erstellen von <code>InfosVonBeitragReport</code>-Objekten.
	 * 
	 * @param textbeitrag
	 *            , anfangsZeitpunkt, endZeitpunkt das Textbeitragobjekt bzgl.
	 *            dessen der Report erstellt werden soll.
	 * @return der fertige Report
	 * @throws IllegalArgumentException
	 */
	public InfosVonBeitragReport erstelleInfosVonBeitragReport(
			Textbeitrag textbeitrag, String anfangsZeitpunkt,
			String endZeitpunkt) {

		if (this.getPinnwandVerwaltungService() == null)
			return null;

		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		InfosVonBeitragReport result = new InfosVonBeitragReport();

		// Jeder Report hat einen Titel (Bezeichnung / überschrift).
		result.setTitle("Infos von Beitrag" + textbeitrag.getId());
		this.addImprint(result);

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * mehrzeilig, daher die Verwendung von CompositeParagraph.
		 */
		CompositeParagraph header = new CompositeParagraph();

		// Verfasser (Nickname) des Textbeitrags aufnehmen
		header.addSubParagraph(new SimpleParagraph("Verfasser: "
				+ pinnwandverwaltung.findeUserZuTextbeitrag(textbeitrag)
						.getNickname()));
		// Text des Textbeitrags aufnehmen
		header.addSubParagraph(new SimpleParagraph("Text: "
				+ textbeitrag.getText()));
		// Erstellungszeitpunkt des Textbeitrags aufnehmen
		header.addSubParagraph(new SimpleParagraph("Verfasst am: "
				+ textbeitrag.getErstellungsZeitpunkt()));

		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.setHeaderData(header);

		/*
		 * Ab hier erfolgt ein zeilenweises Hinzufügen von Konto-Informationen.
		 */

		/*
		 * Zunächst legen wir eine Kopfzeile für die Beitragsinfos-Tabelle an.
		 */
		Row headline = new Row();

		/*
		 * Wir wollen Zeilen mit 2 Spalten in der Tabelle erzeugen. In die erste
		 * Spalte schreiben wir die Anzahl der Kommentare, die ein Textbeitrag
		 * �ber einen bestimmten Zeitraum erhalten hat. In die zweite Spalte
		 * schreiben wir die Anzahl der Likes, die ein Textbeitrag �ber einen
		 * bestimmten Zeitraum erhalten hat In der Kopfzeile legen wir also
		 * entsprechende Überschriften ab.
		 */
		headline.addColumn(new Column("Anzahl Kommentare"));
		headline.addColumn(new Column("Anzahl Likes"));

		// Hinzufügen der Kopfzeile
		result.addRow(headline);

		Row textbeitragRow = new Row();
		textbeitragRow.addColumn(new Column(String.valueOf(pinnwandverwaltung
				.zaehleKommentareZuTextbeitragJeZeitraum(textbeitrag,
						anfangsZeitpunkt, endZeitpunkt))));
		textbeitragRow.addColumn(new Column(String.valueOf(pinnwandverwaltung
				.zaehleLikesZuTextbeitragJeZeitraum(textbeitrag,
						anfangsZeitpunkt, endZeitpunkt))));

		result.addRow(textbeitragRow);
		return result;
	}

	public InfosVonAllenBeitraegenReport erstelleInfosVonAllenBeitraegenReport(
			String anfangsZeitpunkt, String endZeitpunkt, String sortierung) {

		if (this.getPinnwandVerwaltungService() == null)
			return null;

		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		InfosVonAllenBeitraegenReport result = new InfosVonAllenBeitraegenReport();
		String istKommentare = "kommentare";
		String istLikes = "likes";

		// Jeder Report hat einen Titel (Bezeichnung / überschrift).
		result.setTitle("Infos von allen Beitraegen");
		this.addImprint(result);

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		if (sortierung.equals(istLikes)) {
			Vector<Textbeitrag> alleTextbeitraege = this.pinnwandverwaltung
					.findeAlleJeZeitraumSortiertNachAnzahlLikes();
			for (Textbeitrag t : alleTextbeitraege) {
				result.addSubReport(this.erstelleInfosVonBeitragReport(t,
						anfangsZeitpunkt, endZeitpunkt));
			}

		}
		Vector<Textbeitrag> alleTextbeitraege = this.pinnwandverwaltung
				.findeAlleJeZeitraumSortiertNachAnzahlKommentaren();
		for (Textbeitrag t : alleTextbeitraege) {
			result.addSubReport(this.erstelleInfosVonBeitragReport(t,
					anfangsZeitpunkt, endZeitpunkt));
		}
		System.out.println("SOOOOOOOOOORTIierung" + sortierung);
		return result;
	}

}
