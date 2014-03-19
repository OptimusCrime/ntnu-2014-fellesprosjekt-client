package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/*
 * ViewLogin
 * 
 * The loginview
 * 
 */

public class ViewLogin extends JFrame {
	
	/*
	 * Variables
	 */
	
	protected Gui gui;
	protected Cal calendar;
	private static final long serialVersionUID = 1L;
	protected JTextField textField1;
	protected JTextField textField2;
	protected JTextField textField3;
	protected JPasswordField textField4;
	protected JButton send;
	
	/*
	 * Constructor
	 */
	
	public ViewLogin(Gui g, Cal c) {
		// Set gui
		this.gui = g;
		this.calendar = c;
		
		// Set close-operation
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set title
		super.setTitle("NTNU Calendar - Login");
		
		// Set size
		int x = 500; int y = 200;
		super.setPreferredSize(new Dimension(x, y));
		
		// Set location to center
		super.setLocation(683-x/2, 364-y/2);
		
		// Set layout TODO
		super.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		// Add all labels
		JLabel label1 = new JLabel("Server:");
		super.getContentPane().add(label1, "4, 4, right, default");
		JLabel label2 = new JLabel("Port:");
		super.getContentPane().add(label2, "4, 6, right, default");
		JLabel label3 = new JLabel("E-post");
		super.getContentPane().add(label3, "4, 10, right, default");
		JLabel label4 = new JLabel("Passord");
		super.getContentPane().add(label4, "4, 12, right, default");
		
		// Init new keylistener for all the input-fields
		KeyLoginListener enterListener = new KeyLoginListener();
		
		// Add all textfields
		textField1 = new JTextField("localhost");
		super.getContentPane().add(textField1, "6, 4, fill, default");
		textField1.setColumns(10);
		textField2 = new JTextField("9000");
		super.getContentPane().add(textField2, "6, 6, fill, default");
		textField2.setColumns(10);
		textField3 = new JTextField();
		super.getContentPane().add(textField3, "6, 10, fill, default");
		textField3.setColumns(10);
		textField4 = new JPasswordField();
		super.getContentPane().add(textField4, "6, 12, fill, default");
		textField4.setColumns(10);
		
		// Add all events
		textField1.addKeyListener(enterListener);
		textField2.addKeyListener(enterListener);
		textField3.addKeyListener(enterListener);
		textField4.addKeyListener(enterListener);
		
		// Tweak textfields
		
		// Add button
		send = new JButton("Logg inn");
		send.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Button was clicked, check input
				String errors = "";
				
				if (textField1.getText().length() == 0) {
					errors += "- Server\n";
				}
				if (textField2.getText().length() == 0) {
					errors += "- Port\n";
				}
				if (textField3.getText().length() == 0) {
					errors += "- E-post\n";
				}
				if (textField4.getPassword().length == 0) {
					errors += "- Passord\n";
				}
				
				// Check if we had any errors
				if (errors.length() > 0) {
					JOptionPane.showMessageDialog(null, "Vennligst fyll ut manglende informasjon:\n\n" + errors, "Feil", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					// Let's try to connect to the database
					if (calendar.testConnection(textField1.getText(), Integer.parseInt(textField2.getText()))) {
						calendar.setLogin(textField3.getText(), new String(textField4.getPassword()));
						
						// Send the login-info
						calendar.doLogin();
						
						// Set the button to disabled and update text
						send.setEnabled(false);
						send.setText("Laster...");
					}
					else {
						// Something's fucked up
						JOptionPane.showMessageDialog(null, "Kunne ikke koble til serveren.", "Feil", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		super.getContentPane().add(send, "6, 14");
		
		// Pack everything
		super.pack();
	}
	
	/*
	 * If the login was not successfull, send error here
	 */
	
	public void sendLoginFailedMessage() {
		send.setText("Logg inn");
		send.setEnabled(true);
		JOptionPane.showMessageDialog(null, "Brukernavn og/eller passord feil. Prøv igjen!", "Feil", JOptionPane.PLAIN_MESSAGE);
	}
	
	/*
	 * Public setter for setting visible state
	 */
	
	public void setVisible(boolean b) {
		super.setVisible(b);	
	}
	
	/*
	 * Public class that listens for press with the Enter-button
	 */
	
	public class KeyLoginListener implements KeyListener {
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == 10) {
				// Enter-button, simulate a click on the send-button
				send.doClick();
			}
		}
		
		@Override
		public void keyTyped(KeyEvent e) {}
	
		@Override
		public void keyPressed(KeyEvent e) {}
	}
}
