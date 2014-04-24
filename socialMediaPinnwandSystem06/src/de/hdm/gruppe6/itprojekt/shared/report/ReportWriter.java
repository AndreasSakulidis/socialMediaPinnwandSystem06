package de.hdm.gruppe6.itprojekt.shared.report;

public abstract class ReportWriter {
	
	/**
	 * <p>
	 * Diese Klasse wird benötigt, um auf dem Client die ihm vom Server zur
	 * Verfügung gestellten <code>Report</code>-Objekte in ein menschenlesbares
	 * Format zu überführen.
	 * </p>
	 * <p>
	 * Das Zielformat kann prinzipiell beliebig sein. Methoden zum Auslesen der in
	 * das Zielformat überführten Information wird den Subklassen überlassen. In
	 * dieser Klasse werden die Signaturen der Methoden deklariert, die für die
	 * Prozessierung der Quellinformation zuständig sind.
	 * </p>
	 * 
	 * @author angelehnt an Prof. Thies
	 */
	
	public ReportWriter(){
		
	}
	
	  /**
	   * Übersetzen eines <code>InfosVonUserReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   */

	public abstract void process(InfosVonUserReport r);
	
	  /**
	   * Übersetzen eines <code>InfosVonBeitragReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   */
	
	public abstract void process(InfosVonBeitragReport r);
	
	  /**
	   * Übersetzen eines <code>InfosVonAllenBeitraegenReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   */
	
	public abstract void process(InfosVonAllenBeitraegenReport r);
	
	  /**
	   * Übersetzen eines <code>InfosVonAllenUsernReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   */
	
	public abstract void process(InfosVonAllenUsernReport r);
}
