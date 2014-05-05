package de.hdm.gruppe6.itprojekt.client;

public class Post {
	  private String symbol;

	  public Post() {
	  }

	  public Post(String symbol, double price, double change) {
	    this.symbol = symbol;
	  }

	  public String getSymbol() {
	    return this.symbol;
	  }

	  public void setSymbol(String symbol) {
	    this.symbol = symbol;
	  }
}
