package de.hdm.gruppe6.itprojekt.shared.bo;
/**
 * @author Ezgi Demirbilek, Özlem Gül, Gezim Krasniqi, Bharti Kumar, Andreas Sakulidis, Michael Schelkle
 * In Anlehnung an Hr. Prof. Dr. Thies

 * Realisierung einer exemplarischen Loginbeschreibung. 
 
 */
public class LoginInfo extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	
	private boolean loggedIn = false;
	  private String loginUrl;
	  private String logoutUrl;
	  private String emailAddress;
	  private String nickname;

/**
 * Prüfen der Anmeldung
 * @return
 */
	  public boolean isLoggedIn() {
	    return loggedIn;
	  }
/**
 * Setzen der Anmeldunge
 * @param loggedIn
 */

	  public void setLoggedIn(boolean loggedIn) {
	    this.loggedIn = loggedIn;
	  }
/**
 * Auslesen der LoginUrl
 * @return
 */
	  public String getLoginUrl() {
	    return loginUrl;
	  }
/**
 * Setzen der LoginUrl
 * @param loginUrl
 */
	  public void setLoginUrl(String loginUrl) {
	    this.loginUrl = loginUrl;
	  }
/**
 * Auslesen der LogoutUrl
 * @return
 */
	  public String getLogoutUrl() {
	    return logoutUrl;
	  }
/** 
 * Setzen der LogOutUrl
 * @param logoutUrl
 */

	  public void setLogoutUrl(String logoutUrl) {
	    this.logoutUrl = logoutUrl;
	  }
/**
 * Auslesen der Emailadresse
 * @return
 */
	  public String getEmailAddress() {
	    return emailAddress;
	  }
/**
 * Setzen der email-adresse
 * @param emailAddress
 */
	  public void setEmailAddress(String emailAddress) {
	    this.emailAddress = emailAddress;
	  }

/** 
 * Auslesen der Nickname
 * @return
 */
	  public String getNickname() {
	    return nickname;
	  }
/**
 * Setzen der Nickname 
 * @param nickname
 */
	  public void setNickname(String nickname) {
	    this.nickname = nickname;
	  }
	}
