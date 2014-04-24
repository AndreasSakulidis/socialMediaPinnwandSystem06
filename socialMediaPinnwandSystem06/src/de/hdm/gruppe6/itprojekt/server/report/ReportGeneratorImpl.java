package de.hdm.gruppe6.itprojekt.server.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.gruppe6.itprojekt.server.PinnwandVerwaltungImpl;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.ReportGenerator;
import de.hdm.gruppe6.itprojekt.shared.bo.User;
import de.hdm.gruppe6.itprojekt.shared.report.Column;
import de.hdm.gruppe6.itprojekt.shared.report.CompositeParagraph;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonUserReport;
import de.hdm.gruppe6.itprojekt.shared.report.Row;
import de.hdm.gruppe6.itprojekt.shared.report.SimpleParagraph;

/**
 * Implementierung des <code>ReportGenerator</code>-Interface. Die technische
 * Realisierung bzgl. <code>RemoteServiceServlet</code> bzw. GWT RPC erfolgt
 * analog zu {@lBankAdministrationImplImpl}. Für Details zu GWT RPC siehe dort.
 * 
 * @see ReportGenerator
 * @author Anlehnung an Professor Thies
 */
@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet
    implements ReportGenerator {

  /**

   * Ein ReportGenerator benötigt Zugriff auf die BankAdministration, da diese die
   * essentiellen Methoden für die Koexistenz von Datenobjekten (vgl.
   * bo-Package) bietet.
   */
  protected PinnwandVerwaltungService pinnwandverwaltung = null;

  /**
   * <p>
   * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
   * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
   * ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines anderen
   * Konstruktors ist durch die Client-seitige Instantiierung durch
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
   * Initialsierungsmethode. Siehe dazu Anmerkungen zum No-Argument-Konstruktor.
   * 
   * @see #ReportGeneratorImpl()
   */
  public void init() throws IllegalArgumentException {
    /*
     * Ein ReportGeneratorImpl-Objekt instantiiert für seinen Eigenbedarf eine
     * BankVerwaltungImpl-Instanz.
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

  /**
   * Erstellen von <code>InfosVonUserReport</code>-Objekten.
   * 
   * @param user das Userobjekt bzgl. dessen der Report erstellt werden soll.
   * @return der fertige Report
   */
  public InfosVonUserReport erstelleInfosVonUserReport (User user, Date anfangszeitpunkt,Date endzeitpunkt) 
       throws IllegalArgumentException {

    if (this.getPinnwandVerwaltungService() == null)
      return null;

    /*
     * Zunächst legen wir uns einen leeren Report an.
     */
    InfosVonUserReport result = new InfosVonUserReport();

    // Jeder Report hat einen Titel (Bezeichnung / Überschrift).
    result.setTitle("Infos von User");

    /*
     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
     */
    result.setCreated(new Date());

    /*
     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
     * auf dem Report stehen) des Reports. Die Kopfdaten sind mehrzeilig, daher
     * die Verwendung von CompositeParagraph.
     */
    CompositeParagraph header = new CompositeParagraph();

    // Name und Vorname des Users aufnehmen
    header.addSubParagraph(new SimpleParagraph(user.getNachname() + ", "
        + user.getVorname()));

    // Anfangszeitpunkt aufnehmen
    header.addSubParagraph(new SimpleParagraph("Anfangszeitpunkt: " + result.getAnfangszeitpunkt()));
 
    
    // Endzeitpunkt aufnehmen
    header.addSubParagraph(new SimpleParagraph("Endzeitpunkt: " + result.getEndzeitpunkt()));
    
    
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
     * Spalte schreiben wir den jeweiligen User, in die zweite Spalte schreiben wir den Anfangszeitpunkt und 
     * in die letzte Zeile schreiben wir den Endzeitpunkt.
     *  In der Kopfzeile legen wir also entsprechende
     * Überschriften ab.
     */
    headline.addColumn(new Column("Anzahl Beitraege"));
    headline.addColumn(new Column("Anzahl Likes"));
    headline.addColumn(new Column("Anzahl Abonnements"));
    headline.addColumn(new Column("Anzahl Kommentare"));

    // Hinzufügen der Kopfzeile
    result.addRow(headline);

    /*
     * Nun werden sämtliche Daten der User ausgelesen und deren Anfangszeitpunkt und
     * Endzeitpunkt in die Tabelle eingetragen.
     */
    Vector<User> user1 = this.pinnwandverwaltung.erstelleInfosVonUserReport(user1);

    for (User user : user) {
      // Eine leere Zeile anlegen.
      Row accountRow = new Row();

      // Erste Spalte: Kontonummer hinzufügen
      accountRow.addColumn(new Column(String.valueOf(a.getId())));

      // Zweite Spalte: Anfangszeitpunkt hinzuf�gen
      accountRow.addColumn(new Column(String.valueOf(this.InfosVonUserA
          .getAnfangszeitpunkt(a))));

      // und schließlich die Zeile dem Report hinzufügen.
      result.addRow(accountRow);
    }

    /*
     * Zum Schluss müssen wir noch den fertigen Report zurückgeben.
     */
    return result;
  }

  /**
   * Erstellen von <code>AllAccountsOfAllCustomersReport</code>-Objekten.
   * 
   * @return der fertige Report
   */
  public InfosVonUserReport erstelleInfosVonUserReport()
      throws IllegalArgumentException {

    if (this.getPinnwandVerwaltung() == null)
      return null;

    /*
     * Zunächst legen wir uns einen leeren Report an.
     */
    AllAccountsOfAllCustomersReport result = new AllAccountsOfAllCustomersReport();

    // Jeder Report hat einen Titel (Bezeichnung / überschrift).
    result.setTitle("Alle Konten aller Kunden");

    // Imressum hinzufügen
    this.addImprint(result);

    /*
     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
     */
    result.setCreated(new Date());

    /*
     * Da AllAccountsOfAllCustomersReport-Objekte aus einer Sammlung von
     * AllAccountsOfCustomerReport-Objekten besteht, benötigen wir keine
     * Kopfdaten für diesen Report-Typ. Wir geben einfach keine Kopfdaten an...
     */

    /*
     * Nun müssen sämtliche Kunden-Objekte ausgelesen werden. Anschließend wir
     * für jedes Kundenobjekt c ein Aufruf von
     * createAllAccountsOfCustomerReport(c) durchgeführt und somit jeweils ein
     * AllAccountsOfCustomerReport-Objekt erzeugt. Diese Objekte werden
     * sukzessive der result-Variable hinzugefügt. Sie ist vom Typ
     * AllAccountsOfAllCustomersReport, welches eine Subklasse von
     * CompositeReport ist.
     */
    Vector<Customer> allCustomers = this.administration.getAllCustomers();

    for (Customer c : allCustomers) {
      /*
       * Anlegen des jew. Teil-Reports und Hinzufügen zum Gesamt-Report.
       */
      result.addSubReport(this.createAllAccountsOfCustomerReport(c));
    }

    /*
     * Zu guter Letzt müssen wir noch den fertigen Report zurückgeben.
     */
    return result;
  }

}
