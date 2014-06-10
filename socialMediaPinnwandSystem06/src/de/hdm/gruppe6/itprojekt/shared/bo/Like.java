package de.hdm.gruppe6.itprojekt.shared.bo;

public class Like extends BusinessObject{

	/**
	 * @author Ezgi Demirbilek, Özlem Gül, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
	 * In Anlehnung an Hr. Prof. Dr. Thies

	 * Realisierung einer exemplarischen Likebeschreibung. 
	 
	 */
	private static final long serialVersionUID = 1L;
	private int tid;
	private String uid;

	/** 
	 * Konstruktor
	 * @param textid
	 * @param userId
	 */
	public Like(String uid, int tid) {
		this.setTextId(tid);
		this.setUserId(uid);
	}
	
	public Like(){
		
	}
/**
 * Setzen der ID
 * @param textId
 */
	public void setTextId(int tid) {
		this.tid= tid;
	}
	
	/** Auslesen der ID
	 * *
	 * @param userId
	 */
	public void setUserId(String uid) {
		this.uid= uid;
	}

	/** Setzen der TextbeitragID
	 * 
	 * @return
	 */
	public int getTextId() {
		return tid;
	}
	/**
	 * Auslesen der TextbeitragID
	 * @return
	 */

	public String getUserId() {
		return uid;
	}
	
}
