package de.hdm.gruppe6.itprojekt.shared.bo;
/**
 * @author Ezgi Demirbilek, �zlem G�l, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
 * In Anlehnung an Hr. Prof. Dr. Thies

 * Realisierung einer exemplarischen Textbeitragbeschreibung. 
 
 */
public class Textbeitrag extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String text;
	private int likeId;
	private String nameUser;

	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	/**
	 * Konstruktor
	 * @param text
	 */
	public Textbeitrag(String text) {
		this.text = text;
	}
/**
 * Konstruktor
 */
	public Textbeitrag() {
		super();
	}

	/** Auslesen des Textes
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}
/** Setzen des Textes
 * 
 * @param text
 */
	public void setText(String text) {

		if (text == null) {
			throw new IllegalArgumentException("text == null");
		}
		if (text.trim().isEmpty()) {
			throw new IllegalArgumentException("String ist leer");
		}
		this.text = text;
	}
/**
 * Auslesen der LikeID
 * @return
 */
	public int getLikeId() {
		return likeId;
	}
/**
 * Setzen der LikeID
 * @param likeId
 */
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
}
