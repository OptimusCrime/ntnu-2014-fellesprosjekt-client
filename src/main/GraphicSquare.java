package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.JLabel;

/*
 * GraphicSquare
 * 
 * Class for drawing a square in the calendar
 * 
 */

public class GraphicSquare extends JPanel implements MouseMotionListener, MouseListener {
	
	/*
	 * Variables we need
	 */
	
	private static final long serialVersionUID = 1L;
	private Rectangle rect;
	private int space;
	private int width;
	private ArrayList<JLabel> labelsOnHover;
	
	/*
	 * Constructor
	 */
	
	public GraphicSquare(int x, int y, int width, int height, int spaceHeight) {
		super();
		
		// Set some variables we need
		this.space = spaceHeight;
		this.width = height;
		
		// Initialize list of labels
		labelsOnHover = new ArrayList<JLabel>();
		
		// Create new Rect from Swing
		rect = new Rectangle(x, y, width, height);
	}
	
	/*
	 * Add label to the square
	 */
	
	public void addLabel(JLabel j) {
		labelsOnHover.add(j);
	}
	
	/*
	 * Paint the component
	 */
	
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Cast to grapgics
		Graphics2D g2 = (Graphics2D) g;
		
		// Set color
		g2.setColor(new Color(212, 229, 245));
		
		// Fill with color
		g2.fill(rect);
		
		// Set border-color
		g2.setColor(new Color(28, 64, 148));
		
		// Draw border
		g2.draw(rect);
		
		// Draw horizontal lines
		g2.setColor(new Color(28, 64, 148));
		for (int i = 1; i <= 9; i++) {
			g2.drawLine(0, this.space*i, this.width, this.space*i);
		}
	}
	
	/*
	 * TODO
	 */
	
	@Override
	public void mouseMoved(MouseEvent e) {
		for (int i = 0; i < labelsOnHover.size(); i++) {
			labelsOnHover.get(i).setVisible(true);
		}
	}
	
	/*
	 * TODO
	 */
	
	@Override
	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < labelsOnHover.size(); i++) {
			labelsOnHover.get(i).setVisible(false);
		}
	}
	
	/*
	 * TODO
	 */
	
	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}
}