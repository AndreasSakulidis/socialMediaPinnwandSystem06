package de.hdm.gruppe6.itprojekt.shared.bo;

public class Kommentar extends BusinessObject {

	/**
	 * @author Özlem Gül, Michael Schelkle, Bharti Kumar
	 */
	private static final long serialVersionUID = 1L;

	private String text;
	private Textbeitrag textbeitrag;

	// Kontruktor
	public Kommentar(String text) {
		this.setText(text);
	}

	public Kommentar() {
		super();
	}

	// Getter und Setter
	public String getText() {
		return text;
	}

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
}
