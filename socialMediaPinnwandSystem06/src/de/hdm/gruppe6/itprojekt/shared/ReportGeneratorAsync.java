package de.hdm.gruppe6.itprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.gruppe6.itprojekt.shared.bo.Pinnwand;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonAllenBeitraegenReport;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonAllenPinnwaendenReport;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonBeitragReport;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonPinnwandReport;
/**
 * @author Ezgi Demirbilek, Özlem Gül, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
 * In Anlehnung an Hr. Prof. Dr. Thies
 * Diese Klasse ist das asynchrone Gegenstück des Interface {@link ReportGeneratorAsync}. Sie wird automatisch
 * durch das Google Plugin erstellt. Für weitere Details siehe das synchrone Interface.
 */

public interface ReportGeneratorAsync {
	
	void init(AsyncCallback<Void> callback);

	void erstelleInfosVonBeitragReport(Textbeitrag textbeitrag, String anfangszeitpunkt,
			String endzeitpunkt, 
			AsyncCallback<InfosVonBeitragReport> callback);
	
	void erstelleInfosVonAllenBeitraegenReport(String anfangszeitpunkt,
			String endzeitpunkt, String sortierung, AsyncCallback<InfosVonAllenBeitraegenReport> callback);

		void erstelleInfosVonPinnwandReport(Pinnwand pinnwand,
			AsyncCallback<InfosVonPinnwandReport> callback);
	
	void erstelleInfosVonAllenPinnwaendenReport(String anfangszeitpunkt,
			String endzeitpunkt,
			AsyncCallback<InfosVonAllenPinnwaendenReport> callback);
	
}
