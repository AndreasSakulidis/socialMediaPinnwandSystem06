package de.hdm.gruppe6.itprojekt.shared.bo;

public class Kommentar extends BusinessObject {

	/**
	 * @author Ezgi Demirbilek, Özlem Gül, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
	 * In Anlehnung an Hr. Prof. Dr. Thies

	 * Realisierung einer exemplarischen Kommentarbeschreibung. 
	 
	 */
	private static final long serialVersionUID = 1L;

	private String text;
	private Textbeitrag textbeitrag;
	private int userID;

	/**Kontstruktor
	 * @param text
	 */
	public Kommentar(String text) {
		this.setText(text);
	}

	public Kommentar() {
		super();
	}

	/**
	 * Kommentar Auslesen
	 * 
	 */
	public String getText() {
		return text;
	}

	/**
	 * Setzen des Kommentars
	 * @param text
	 */
	public void setText(String text) {
		/* Eine Textbeitrag ohne (echten) Inhalt ist nicht vorgesehen */
		if (text == null) {
			throw new IllegalArgumentException("text == null");
		}
		if (text.trim().isEmpty()) {
			throw new IllegalArgumentException("String ist leer!");
		}
		this.text = text;
	}

	public Textbeitrag getTextbeitrag() {
		return textbeitrag ;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
}
