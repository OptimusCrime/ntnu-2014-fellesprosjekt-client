package main;

/*
 * Gui
 * 
 * Takes care of drawing the different views etc
 * 
 */

public class Gui {
	
	/*
	 * Variables
	 */
	
	private Calendar c;
	private ViewLogin login;
	private ViewMain main;
	
	/*
	 * Constructor
	 */
	
	public Gui (Calendar c) {
		// Set references
		this.c = c;
		
		// Display login-screen
		login = new ViewLogin(this);
		login.setVisible(true);
	}
	
	/*
	 * Set username and password
	 */
	
	public void setLogin(String username, String password) {
		c.setLogin(username, password);
	}
	
	/*
	 * Testing connection entered during login
	 */
	
	public boolean testConnection(String s, int port) {
		return c.testConnection(s, port);
	}
	
	/*
	 * User is logged in, display home-screen
	 */
	
	public void showHome() {
		login.setVisible(false);
		login = null;
		main = new ViewMain(this);
		main.setVisible(true);
	}
	
	/*
	 * User wishes to log out, display login-screen again
	 */
	
	public void logout() {
		main.setVisible(false);
		main = null;
		login = new ViewLogin(this);
		login.setVisible(true);
		
	}
}
