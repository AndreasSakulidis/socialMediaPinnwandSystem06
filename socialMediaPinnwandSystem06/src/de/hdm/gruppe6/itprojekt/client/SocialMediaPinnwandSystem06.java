package de.hdm.gruppe6.itprojekt.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.i18n.client.DateTimeFormat;

import de.hdm.gruppe6.itprojekt.shared.FieldVerifier;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungService;
import de.hdm.gruppe6.itprojekt.shared.PinnwandVerwaltungServiceAsync;
import de.hdm.gruppe6.itprojekt.shared.bo.Textbeitrag;

import java.util.Date;


public class SocialMediaPinnwandSystem06 implements EntryPoint {

  private static final int REFRESH_INTERVAL = 5000; // ms
  private HorizontalPanel addPanel = new HorizontalPanel();
  private Button addPostButton = new Button("Add");
  private TextArea newSymbolTextBox = new TextArea(); 
  private VerticalPanel mainPanel = new VerticalPanel();
  private FlexTable postFlexTable = new FlexTable();
  private Label lastUpdatedLabel = new Label();
  private ArrayList<String> posts = new ArrayList<String>();
  private HorizontalPanel addNavPanel = new HorizontalPanel();

  private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
  /**
   * Entry point method.
   */
  public void onModuleLoad() {
	      
	// Add Textbox panel
	    TextArea ta = new TextArea();
	    ta.setCharacterWidth(150);
	    ta.setVisibleLines(70);
	  
	  // Assemble Add Stock panel.
	    addPanel.add(newSymbolTextBox);
	    addPanel.add(addPostButton);
	    addPanel.addStyleName("addPanel");
  	  
	    // Create table for stock data.
	    postFlexTable.setText(0, 0, "Post");
	    postFlexTable.setText(0, 1, "Loeschen");
	    postFlexTable.setText(0, 2, "Bearbeiten");
	    postFlexTable.setText(0, 3, "");
	    postFlexTable.setText(0, 4, "Kommentieren");
	    postFlexTable.setText(0, 5, "Like");

	    // Add styles to elements in the stock list table.
	    postFlexTable.setCellPadding(6);
	    postFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
	    postFlexTable.addStyleName("watchList");
	    postFlexTable.getCellFormatter().addStyleName(0, 1, "watchListRemoveColumn");
  
	   
	    // Assemble Main panel.
	    mainPanel.add(addPanel);
	    mainPanel.add(lastUpdatedLabel);
	    mainPanel.add(postFlexTable);
	    
	    
	    // Associate the Main panel with the HTML host page.
	    RootPanel.get("Details").add(mainPanel);
	    
	    //Menübar
	    
	    Command cmd = new Command() {
	    	public void execute() {
	    		Window.alert("You selecetd a menu item");
	    	}
	    };
	    
	    MenuBar fooMenu = new MenuBar(true);
	    
	    MenuBar barMenu = new MenuBar(true);

	    MenuBar reportMenu = new MenuBar(true);
	    reportMenu.addItem("Infoausgabe von User", cmd);
	    reportMenu.addItem("Infoausgabe von Beitraeg", cmd);
	    reportMenu.addItem("Infosausgabe von allen Usern", cmd);
	    reportMenu.addItem("Infosausgabe von allen Beitreagen", cmd);

	    // Make a new menu bar, adding a few cascading menus to it.
	    MenuBar menu = new MenuBar();
	    menu.addItem("Pinnwand", fooMenu);
	    menu.addItem("Reports", reportMenu);
	    menu.addItem("Einstellungen", barMenu);
	    menu.addItem("LogOut", barMenu);

	    // Add it to the root panel.
	    RootPanel.get("Header").add(menu);	    

	    // Move cursor focus to the input box.
	    newSymbolTextBox.setFocus(true);
	    
	    // Setup timer to refresh list automatically.
	    Timer refreshTimer = new Timer() {
	      @Override
	      public void run() {
	        refreshWatchList();
	      }
	    };
	    refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
	  
	    final Button sendSucheButton = new Button("Suchen");
		final TextBox nameField = new TextBox();
		nameField.setText("User suchen");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendSucheButton.addStyleName("sendSucheButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		
		addNavPanel.add(nameField);
		addNavPanel.add(sendSucheButton);
		addNavPanel.add(errorLabel);
		
		// Add it to the root panel.
	    RootPanel.get("Navigator").add(addNavPanel);
		
		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Nach User suchen");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
	/*	dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		*/
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendSucheButton.setEnabled(true);
				sendSucheButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Bitte geben Sie mind. 1 Buchstaben ein!");
					return;
				}

				// Then, we send the input to the server.
				sendSucheButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox.setText("Trefferliste");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendSucheButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
			
		 
	    
	  // Listen for mouse events on the Add button.
	  addPostButton.addClickHandler(new ClickHandler() {
	    public void onClick(ClickEvent event) {
	      addPost();
	    }});
	  
	    // Listen for keyboard events in the input box.
	    newSymbolTextBox.addKeyPressHandler(new KeyPressHandler() {
	      public void onKeyPress(KeyPressEvent event) {
	        if (event.getCharCode() == KeyCodes.KEY_ENTER) {
	          addPost();
	        }
	      }
	    });


   }


	/**
	 * Add stock to FlexTable. Executed when the user clicks the addStockButton or
	 * presses enter in the newSymbolTextBox.
	 */
	private void addPost() {
	    final String symbol = newSymbolTextBox.getText().trim();
	    newSymbolTextBox.setFocus(true);
	    newSymbolTextBox.setText("");

	/*    // Don't add the stock if it's already in the table.
	    if (posts.contains(symbol))
	      return;
	*/    
	   	    
	   String text = newSymbolTextBox.getText();
	   PinnwandVerwaltungServiceAsync pinnwandVerwaltung = GWT
				.create(PinnwandVerwaltungService.class);
	    
	    pinnwandVerwaltung.textbeitragAnlegen(text,
				new AsyncCallback<Textbeitrag>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim posten!");
					}

					public void onSuccess(Textbeitrag textbeitrag) {
						Window.alert("Dein Textbeitrag wurde gepostet!");
						//clearFields();
					}
				});
	    
	    // Add the stock to the table.
	    int row = postFlexTable.getRowCount();
	    posts.add(symbol);
	    postFlexTable.setText(row, 0, symbol);
	    postFlexTable.setWidget(row, 2, new Label());

	    postFlexTable.getCellFormatter().addStyleName(row, 1, "watchListRemoveColumn");
	    
	    // Add a button to remove this stock from the table.
	    Button removePostButton = new Button("x");
	    removePostButton.addStyleDependentName("remove");
	    removePostButton.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        int removedIndex = posts.indexOf(symbol);
	        posts.remove(removedIndex);        postFlexTable.removeRow(removedIndex + 1);  
	        
	      }
	    });
	    postFlexTable.setWidget(row, 1, removePostButton);
	    
	    // Get the stock price.
	    refreshWatchList();
	    
	    
	    Button bearbeitePostButton = new Button("Bearbeiten");
	    bearbeitePostButton.addStyleDependentName("Bearbeiten");
	    bearbeitePostButton.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        int editIndex = posts.indexOf(symbol);
	      //  posts.bearbeite(editIndex);        postFlexTable.bearbeiteRow(editIndex + 1);  
	        
	      }
	    });
	    postFlexTable.setWidget(row, 2, bearbeitePostButton);
	    
	    // Get the stock price.
	    refreshWatchList();
	    
	    Button kommentierePostButton = new Button("Kommentieren");
	    kommentierePostButton.addStyleDependentName("Kommentieren");
	    kommentierePostButton.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        int commentIndex = posts.indexOf(symbol);
	      //  posts.kommentiere(editIndex);        postFlexTable.kommentiereRow(editIndex + 1);  
	        
	      }
	    });
	    postFlexTable.setWidget(row, 4, kommentierePostButton);
	    
	    // Get the stock price.
	    refreshWatchList();
	    
	    Button likePostButton = new Button("Like");
	    likePostButton.addStyleDependentName("Like");
	    likePostButton.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        int likeIndex = posts.indexOf(symbol);
	      //  posts.kommentiere(editIndex);        postFlexTable.kommentiereRow(editIndex + 1);  
	        
	      }
	    });
	    postFlexTable.setWidget(row, 5, likePostButton);
	    
	    // Get the stock price.
	    refreshWatchList();
	    
	  }
	
	
	  /**
	   * Generate random stock prices.
	   */
	  private void refreshWatchList() {
		  final double MAX_PRICE = 100.0; // $100.00
	 final double MAX_PRICE_CHANGE = 0.02; // +/- 2%

	    Post[] textbeitrag = new Post[posts.size()];
	    for (int i = 0; i < posts.size(); i++) {
	      double price = Random.nextDouble() * MAX_PRICE;
	      double change = price * MAX_PRICE_CHANGE
	          * (Random.nextDouble() * 2.0 - 1.0);

	      textbeitrag[i] = new Post(posts.get(i), price, change);
	    }

	  
	    updateTable(textbeitrag);
	  }
	  
	  /**
	   * Update the Price and Change fields all the rows in the stock table.
	   *
	   * @param prices Stock data for all rows.
	   */
 @SuppressWarnings("deprecation")
private void updateTable(Post[] textbeitrag) {
	    for (int i = 0; i < textbeitrag.length; i++) {
	      updateTable(textbeitrag[i]);
	    }
	  
	    // Display timestamp showing last refresh.
	    lastUpdatedLabel.setText("Last update : "
	        + DateTimeFormat.getMediumDateTimeFormat().format(new Date()));
	  }
   
	    /**
		   * Update a single row in the stock table.
		   *
		   * @param price Stock data for a single row.
		   */
		  private void updateTable(Post post) {
		    // Make sure the stock is still in the stock table.
		    if (!posts.contains(post.getSymbol())) {
		      return;
		    }

		    int row = posts.indexOf(post.getSymbol()) + 1;

		  }	 
}