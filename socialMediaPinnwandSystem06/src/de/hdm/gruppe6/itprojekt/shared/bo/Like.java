package de.hdm.gruppe6.itprojekt.shared.bo;

public class Like extends BusinessObject{

	/**
	 * @author Ezgi Demirbilek, Özlem Gül, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
	 * In Anlehnung an Hr. Prof. Dr. Thies

	 * Realisierung einer exemplarischen Likebeschreibung. 
	 
	 */
	private static final long serialVersionUID = 1L;
	private Textbeitrag textId;
	private User userId;

	/** 
	 * Konstruktor
	 * @param textid
	 * @param userId
	 */
	public Like(Textbeitrag textid, User userId) {
		this.setTextId(textid);
		this.setUserId(userId);
	}
	
	public Like(){
		
	}
/**
 * Setzen der ID
 * @param textId
 */
	public void setTextId(Textbeitrag textId) {
		this.textId= textId;
	}
	
	/** Auslesen der ID
	 * *
	 * @param userId
	 */
	public void setUserId(User userId) {
		this.userId= userId;
	}

	/** Setzen der TextbeitragID
	 * 
	 * @return
	 */
	public Textbeitrag getTextId() {
		return textId;
	}
	/**
	 * Auslesen der TextbeitragID
	 * @return
	 */

	public User getUserId() {
		return userId;
	}
	
}
