package coffee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CoffeeMethine extends JPanel{

	private ClassLoader classLoader = getClass().getClassLoader();
	
	ImageIcon meshine = new ImageIcon(classLoader.getResource("images/meshine.png"));
	ImageIcon screen = new ImageIcon(classLoader.getResource("images/screen1.png"));
	
	public String text = "please wait for instructions";
	public double payStr = 0.0;
	
	JPanel menu;
	
	Point point;
	
	public CoffeeMethine(Point p) {
		var x = meshine.getIconWidth();
		var y = meshine.getIconHeight();
		
		setPreferredSize(new Dimension(x,y));	

		
		point = p;
	}
	
	public Dimension getSize() {
		return new Dimension(meshine.getIconWidth(), meshine.getIconHeight());
	}
	
	public static void paintText(Graphics g, Point p, String text, int fontSize, int font, int length) {
		var x = p.x;
		var y = p.y;
		
		g.setFont(new Font(Font.SERIF, font,  fontSize));		

		if(text.length() > length) {
			String splitText = text;
			var spaceIndex = length;

			var i = 0;
			while(splitText.length() > length) {
				
						for(int j = length; j > 0; j--) {
							if(splitText.charAt(j) == ' ') {
								spaceIndex = j;
								break;
							}
						}
				g.drawString(splitText.substring(0,spaceIndex), x, y + fontSize * i);
				splitText = splitText.substring(spaceIndex + 1);

				i++;
			}
			g.drawString(splitText, x, fontSize * (i) + y);
		} else {
			g.drawString(text, x, y);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		var x = point.x;
		var y = point.y;
		final var fontSize = 40;

		g.setColor(Color.green);
		
		meshine.paintIcon(this, g, x, y);
		
		paintText(g, new Point(point.x + 30, point.y + 70), text, fontSize, Font.PLAIN, 16);

		screen.paintIcon(this, g, x, y);
		
		g.setColor(new Color(32067));
		g.fillRect(45, 465, 120, 65);
		g.setColor(Color.black);
	    DecimalFormat myFormatter = new DecimalFormat("#0.00");
	    String output = myFormatter.format(payStr);
		paintText(g, new Point(50, 465 + 30), output, 30, 1, 6);
	}
}
