package de.hdm.gruppe6.itprojekt.shared.bo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Ezgi Demirbilek, Özlem Gül, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
 * In Anlehnung an Hr. Prof. Dr. Thies
 * Die Klasse <code>BusinessObject</code> stellt die Basisklasse aller in diesem
 * Projekt aller Klassen des Social-Media-Pinnwand-Systems dar.
 * Jedes <code>BusinessObject</code> hat eine ID und Erstellungszeitpunkt-
 */
 
public abstract class BusinessObject implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Primärschlüssel
	 */
	private int id = 0;
	
	/**
	 * Auslesen der ID
	 */

	public int getId() {
		return id;
	}
	/**
	 * Setzen der ID
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Erstellungszeitpunkt
	 */
	private Timestamp erstellungsZeitpunkt;


/**
 * Auslesen des Erstellungszeitpunkts
 */
	public Timestamp getErstellungsZeitpunkt() {
		return erstellungsZeitpunkt;

	}
	/**
	 * Setzen des Erstellungszeitpunkts
	 */

	public void setErstellungsZeitpunkt(Timestamp erstellungsZeitpunkt) {
		this.erstellungsZeitpunkt = erstellungsZeitpunkt;
	}
	/** textuelle Darstellung der Instanz erzeugen
	 */

	public String toString() {
		return this.getClass().getName() + " #" + this.id;
	}
/**
 * Feststellung inhaltlicher Gleichheit der BusinessObjekte
 */
	public boolean equals(Object o) {
		// * Die Methode überprüft, ob das Objekt eexisitiert und ob es ein
		// BusinessObject ist
		// Wenn das Objekt ein BusinessObject ist, dann wird das übergebene
		// Objekt auch in ein BusinessObjekt umgewandelt.*/

		if (o != null && o instanceof BusinessObject) {
			BusinessObject bo = (BusinessObject) o;
			try {
				// Wenn die ID gleich ist, dann wird der Wert True
				// zurückgegeben.
				if (bo.getId() == this.id)
					return true;
			} catch (IllegalArgumentException e) {
				// Bei einem Fehler soll false zurückgegeben werden
				return false;
			}
			// Ist die ID nicht gleich, dann soll false zurückgegeben werden
		}
		return false;
	}

}
