package de.hdm.gruppe6.itprojekt.shared.bo;

public class Textbeitrag extends BusinessObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String text;
	private int likeId;
	
//Konstruktor	
public Textbeitrag(String text){
	this.text=text;
}

public Textbeitrag(){
	}

//Getter und Setter
public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}

public int getLikeId() {
	return likeId;
}

public void setLikeId(int likeId) {
	this.likeId = likeId;
}
}
