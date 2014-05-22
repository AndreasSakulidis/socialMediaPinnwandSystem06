package de.hdm.gruppe6.itprojekt.shared.bo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Özlem Gül, Michael Schelkle, Bharti Kumar
 */

public abstract class BusinessObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private Timestamp erstellungsZeitpunkt;

	// private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// private Date erstellungsZeitpunktFormattiert =
	// format.parseObject(erstellungsZeitpunkt);
	// private String erstellungsZeitpunktFormattiert =
	// sdf.format(erstellungsZeitpunkt);
	// private Date datum = sdf.parse(erstellungsZeitpunktFormattiert);

	public Timestamp getErstellungsZeitpunkt() {
		return erstellungsZeitpunkt;

	}

	public void setErstellungsZeitpunkt(Timestamp erstellungsZeitpunkt) {
		this.erstellungsZeitpunkt = erstellungsZeitpunkt;
	}

	public String toString() {
		return this.getClass().getName() + " #" + this.id;
	}

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
