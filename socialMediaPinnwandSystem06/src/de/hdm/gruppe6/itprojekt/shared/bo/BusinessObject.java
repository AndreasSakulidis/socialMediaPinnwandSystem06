package de.hdm.gruppe6.itprojekt.shared.bo;

import java.io.Serializable;
import java.util.Date;

import de.hdm.gruppe6.itprojekt.shared.bo.BusinessObject;

/**
 * @author Özlem Gül, Michael Schelkle, Bharti Kumar
 */

public abstract class BusinessObject implements Serializable {

	  private static final long serialVersionUID = 1L;

private int id=0;

public int getId(){
	return id;
}

public void setId(int id){
	this.id=id;
}

private Date erstellungsZeitpunkt;

public Date getErstellungsZeitpunkt() {
	return erstellungsZeitpunkt;
	
}
	
public void setErstellungsZeitpunkt(Date erstellungsZeitpunkt) {
	this.erstellungsZeitpunkt=erstellungsZeitpunkt;
}

public String toString() {
    return this.getClass().getName() + " #" + this.id;
  }


public boolean equals(Object o) {
   //* Die Methode überprüft, ob das Objekt eexisitiert und ob es ein BusinessObject ist
  // Wenn das Objekt ein BusinessObject ist, dann wird das übergebene Objekt auch in ein BusinessObjekt umgewandelt.*/
	
    if (o != null && o instanceof BusinessObject) {
      BusinessObject bo = (BusinessObject) o;
      try {
    	  // Wenn die ID gleich ist, dann wird der Wert True zurückgegeben.
        if (bo.getId() == this.id)
          return true;
      }
      catch (IllegalArgumentException e) {
    	  // Bei einem Fehler soll false zurückgegeben werden
        return false;
      }
      //Ist die ID nicht gleich, dann soll false zurückgegeben werden
    } return false;
    }

}

	

