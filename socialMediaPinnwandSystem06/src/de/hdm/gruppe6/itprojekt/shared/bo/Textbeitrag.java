package de.hdm.gruppe6.itprojekt.shared.bo;

public class Textbeitrag extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String text;
	private int likeId;

	// Konstruktor
	public Textbeitrag(String text) {
		this.text = text;
	}

	public Textbeitrag() {
		super();
	}

	// Getter und Setter
	public String getText() {
		return text;
	}

	public void setText(String text) {

		if (text == null) {
			throw new IllegalArgumentException("text == null");
		}
		if (text.trim().isEmpty()) {
			throw new IllegalArgumentException("String ist leer");
		}
		this.text = text;
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
}
