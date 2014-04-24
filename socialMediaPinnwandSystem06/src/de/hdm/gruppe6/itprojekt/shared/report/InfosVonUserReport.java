package de.hdm.gruppe6.itprojekt.shared.report;

import java.util.Date;



public class InfosVonUserReport extends SimpleReport {
	
	/**
	 * Report, der die Information von einem User darstellt.
	 * Die Klasse traegt keine weiteren Attribute- und Methoden-Implementierungen,
	 * da alles Notwendige schon in den Superklassen vorliegt. Ihre Existenz ist 
	 * dennoch wichtig, um bestimmte Typen von Reports deklarieren und mit ihnen 
	 * objektorientiert umgehen zu können.
	 * 
	 * @author Anlehnung an Prof. Thies
	 */
	
	private static final long serialVersionUID = 1L;
	private Date anfangszeitpunkt;
	private Date endzeitpunkt;
	
	
	public Date getAnfangszeitpunkt() {
		return anfangszeitpunkt;
	}
	public void setAnfangszeitpunkt(Date anfangszeitpunkt) {
		this.anfangszeitpunkt = anfangszeitpunkt;
	}
	public Date getEndzeitpunkt() {
		return endzeitpunkt;
	}
	public void setEndzeitpunkt(Date endzeitpunkt) {
		this.endzeitpunkt = endzeitpunkt;
	}
}
