package de.hdm.gruppe6.itprojekt.server.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.gruppe6.itprojekt.server.PinnwandVerwaltungImpl;
import de.hdm.gruppe6.itprojekt.server.db.TextbeitragMapper;
import de.hdm.gruppe6.itprojekt.server.db.UserMapper;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.ReportGenerator;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;
import de.hdm.gruppe6.itprojekt.shared.report.Column;
import de.hdm.gruppe6.itprojekt.shared.report.CompositeParagraph;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonAllenBeitraegenReport;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonAllenUsernReport;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonBeitragReport;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonUserReport;
import de.hdm.gruppe6.itprojekt.shared.report.Row;
import de.hdm.gruppe6.itprojekt.shared.report.SimpleParagraph;

/**
 * @author Bharti Kumar, �zlem G�l, Michael Schelkle, Andreas Sakulidis, Gezim Krasniqi, Ezgi Demirbilek
 * Anlehnung an Professor Thies
 * 
 * Implementierung des <code>ReportGenerator</code>-Interface. Die technische
 * Realisierung bzgl. <code>RemoteServiceServlet</code> bzw. GWT RPC erfolgt
 * analog zu {@lBankAdministrationImplImpl}. Für Details zu GWT RPC siehe dort.
 * 
 * @see ReportGenerator
 * 
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
  protected UserMapper usermapper = null;
  protected TextbeitragMapper textbeitragmapper = null;

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
  public ReportGeneratorImpl() {
  }

  /**
   * Initialsierungsmethode. Siehe dazu Anmerkungen zum No-Argument-Konstruktor.
   * 
   * @see #ReportGeneratorImpl()
   */
  public void init() {
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
 * @throws IllegalArgumentException 
   */
  public InfosVonUserReport erstelleInfosVonUserReport (User user, Date anfangszeitpunkt,Date endzeitpunkt) 
	 {
	  
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
    headline.addColumn(new Column("Anzahl Abonnements"));
    headline.addColumn(new Column("Anzahl Kommentare"));

    // Hinzufügen der Kopfzeile
    result.addRow(headline);

    /*
     * Nun werden sämtliche Daten der User ausgelesen und deren Anfangszeitpunkt und
     * Endzeitpunkt in die Tabelle eingetragen.
     */
   /* User 
    int user1 = this.pinnwandverwaltung.zaehleTextbeitraegeVonUser(user);

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
    */
    Row userRow = new Row();
   
    try {
		userRow.addColumn(new Column(String.valueOf(usermapper.zaehleTextbeitraegeVonUser(user))));
		userRow.addColumn(new Column(String.valueOf(usermapper.zaehleAbosVonUser(user))));
	    userRow.addColumn(new Column(String.valueOf(usermapper.zaehleKommentareVonUser(user))));
	    
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    result.addRow(userRow);
    return result;
  }

  

    /*
     * Zum Schluss müssen wir noch den fertigen Report zurückgeben.
     */

  /**
   * Erstellen von <code>AllAccountsOfAllCustomersReport</code>-Objekten.
   * 
   * @return der fertige Report
   */
  public InfosVonBeitragReport erstelleInfosVonBeitragReport(Textbeitrag textbeitrag, Date anfangszeitpunkt, Date endzeitpunkt)
      {

    if (this.getPinnwandVerwaltungService() == null)
      return null;

    /*
     * Zunächst legen wir uns einen leeren Report an.
     */
    InfosVonBeitragReport result = new InfosVonBeitragReport();

    // Jeder Report hat einen Titel (Bezeichnung / überschrift).
    result.setTitle("Infos von Beitrag");


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
    header.addSubParagraph(new SimpleParagraph(textbeitrag.getText() + ", "
        + textbeitrag.getErstellungsZeitpunkt()));

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
    headline.addColumn(new Column("Verfasser"));
    headline.addColumn(new Column("Anzahl Kommentare"));
    headline.addColumn(new Column("Anzahl Likes"));

    // Hinzufügen der Kopfzeile
    result.addRow(headline);
    

    
  Row textbeitragRow = new Row();
 
  try {
	
	  textbeitragRow.addColumn(new Column(String.valueOf(textbeitragmapper.findeUserZuTextbeitrag(textbeitrag))));
	  textbeitragRow.addColumn(new Column(String.valueOf(textbeitragmapper.zaehleKommentareVonTextbeitrag(textbeitrag))));
	  textbeitragRow.addColumn(new Column(String.valueOf(textbeitragmapper.zaehleLikesZuTextbeitrag(textbeitrag))));
	  
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
   result.addRow(textbeitragRow);
  return result;
}
  
  public InfosVonAllenUsernReport erstelleInfosVonAllenUsernReport(Date anfangszeitpunkt, Date endzeitpunkt)
  {

	    if (this.getPinnwandVerwaltungService() == null)
	      return null;

	    /*
	     * Zunächst legen wir uns einen leeren Report an.
	     */
	    InfosVonAllenUsernReport result = new InfosVonAllenUsernReport();

	    // Jeder Report hat einen Titel (Bezeichnung / überschrift).
	    result.setTitle("Infos von allen Usern");


	    /*
	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
	    result.setCreated(new Date());
	    
	    Vector<User> alleUser;
		try {
			alleUser = this.pinnwandverwaltung.findeAlleUser();
			 for (User u : alleUser){
			    	result.addSubReport(this.erstelleInfosVonUserReport(u, anfangszeitpunkt, endzeitpunkt));
			    }
			    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	     }
  
  
  public InfosVonAllenBeitraegenReport erstelleInfosVonAllenBeitraegenReport(Date anfangszeitpunkt, Date endzeitpunkt)
	      {

	    if (this.getPinnwandVerwaltungService() == null)
	      return null;

	    /*
	     * Zunächst legen wir uns einen leeren Report an.
	     */
	    InfosVonAllenBeitraegenReport result = new InfosVonAllenBeitraegenReport();

	    // Jeder Report hat einen Titel (Bezeichnung / überschrift).
	    result.setTitle("Infos von allen Beitraegen");


	    /*
	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
	    result.setCreated(new Date());
	    
	    Vector<Textbeitrag> alleTextbeitraege;
		try {
			alleTextbeitraege = this.pinnwandverwaltung.findeAlleTextbeitraege();
			   for (Textbeitrag t : alleTextbeitraege){
			    	result.addSubReport(this.erstelleInfosVonBeitragReport(t, anfangszeitpunkt, endzeitpunkt));
			    }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	return result;
  }
}
