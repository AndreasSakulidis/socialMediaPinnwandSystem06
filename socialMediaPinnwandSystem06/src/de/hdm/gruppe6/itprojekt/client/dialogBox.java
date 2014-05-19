package de.hdm.gruppe6.itprojekt.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class dialogBox extends DialogBox{
	
	private VerticalPanel commentPanel = new VerticalPanel();
	public TextArea ta;
	public Button ok;
	public Button abbrechen;
	
	public dialogBox(String title) {
	this.setText(title);
	
	ta = new TextArea();
	
	
	ok = new Button("Posten");
	abbrechen = new Button("Abbrechen");
	commentPanel.add(ta);
	commentPanel.add(ok);
	commentPanel.add(abbrechen);
	  
	this.setWidget(commentPanel);
	}
	public String getContent() {
		// TODO Auto-generated method stub
		return ta.getText();
	}
	public void setContent(String content){
		ta.setText(content);
	}
}
