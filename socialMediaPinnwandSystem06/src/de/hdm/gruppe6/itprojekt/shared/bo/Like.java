package de.hdm.gruppe6.itprojekt.shared.bo;

public class Like extends BusinessObject{

	/**
	 * @author Özlem Gül, Michael Schelkle, Bharti Kumar
	 */
	private static final long serialVersionUID = 1L;
	private Textbeitrag textId;
	private User userId;

	// Konstruktor
	public Like(Textbeitrag textid, User userId) {
		this.setTextId(textid);
		this.setUserId(userId);
	}

	public void setTextId(Textbeitrag textId) {
		this.textId= textId;
	}
	
	public void setUserId(User userId) {
		this.userId= userId;
	}

	public Textbeitrag getTextId() {
		return textId;
	}

	public User getUserId() {
		return userId;
	}
	
}
