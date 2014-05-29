package de.hdm.gruppe6.itprojekt.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;
import de.hdm.gruppe6.itprojekt.shared.bo.User;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonAllenBeitraegenReport;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonAllenUsernReport;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonBeitragReport;
import de.hdm.gruppe6.itprojekt.shared.report.InfosVonUserReport;
/**
 * @author Ezgi Demirbilek, Özlem Gül, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
 * In Anlehnung an Hr. Prof. Dr. Thies
 * Diese Klasse ist das asynchrone Gegenstück des Interface {@link ReportGeneratorAsync}. Sie wird automatisch
 * durch das Google Plugin erstellt. Für weitere Details siehe das synchrone Interface.
 */

public interface ReportGeneratorAsync {

	void erstelleInfosVonAllenBeitraegenReport(Date anfangszeitpunkt,
			Date endzeitpunkt,
			AsyncCallback<InfosVonAllenBeitraegenReport> callback);

	void erstelleInfosVonAllenUsernReport(Date anfangszeitpunkt,
			Date endzeitpunkt, AsyncCallback<InfosVonAllenUsernReport> callback);

	void erstelleInfosVonBeitragReport(Textbeitrag textbeitrag,
			Date anfangszeitpunkt, Date endzeitpunkt,
			AsyncCallback<InfosVonBeitragReport> callback);

	void erstelleInfosVonUserReport(User user, Date anfangszeitpunkt,
			Date endzeitpunkt, AsyncCallback<InfosVonUserReport> callback);

	void init(AsyncCallback<Void> callback);

}
