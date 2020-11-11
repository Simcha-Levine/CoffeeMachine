package coffee;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Drink {
	
	public final String name;
	public final double price;
	
	private Point buttonBound = null;
	private Point buttonPoint = null;

	private ImageIcon drinkImage;
	
	public Drink(String n, double p, int type) {
		name = n;
		price = p;
		
		switch (type) {
		case 0:
			drinkImage = new ImageIcon("/home/levine-fam/Desktop/coffeeMashine/nes1.png");
			break;
		case 1:
			drinkImage = new ImageIcon("/home/levine-fam/Desktop/coffeeMashine/blak1.png");
			break;
		case 2:
			drinkImage = new ImageIcon("/home/levine-fam/Desktop/coffeeMashine/tea1.png");
			break;
		case 3:
			drinkImage = new ImageIcon("/home/levine-fam/Desktop/coffeeMashine/shoko1.png");
			break;
		case 4:
			drinkImage = new ImageIcon("/home/levine-fam/Desktop/coffeeMashine/soop.png");
			break;
		case 5:
			drinkImage = new ImageIcon("/home/levine-fam/Desktop/coffeeMashine/kola1.png");
			break;
		case 6:
			drinkImage = new ImageIcon("/home/levine-fam/Desktop/coffeeMashine/oreng1.png");
			break;
		default:
			drinkImage = null;
			break;
		}
	}

	
	public double pay(double payment, Component c) {
		if(price == 0) {
			return payment;
		}
		JFrame frame = new JFrame() {
			@Override
			public void paint(Graphics g) {
				drinkImage.paintIcon(this, g, 0, 20);
			}
		};
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		var y = drinkImage.getIconHeight() + 20;
		var x = drinkImage.getIconWidth();
		frame.setPreferredSize(new Dimension(x,y));
		frame.setLocationRelativeTo(c);
        frame.setLocation(1000, 300);
		frame.pack();
		frame.setVisible(true);

		return payment - price;
	}
	
	public void setButton(Point point, Point size) {	
		buttonBound = new Point(size.x,size.y);
		buttonPoint = point;
	} 
	
	private boolean presed;
	
	public boolean checkPresed(Point p) {
		
		presed = Wallet.inBound(p, buttonPoint, buttonBound);
		return presed;
	}

	public void release() {
		presed = false;
	}
	
	public boolean isPressed() {
		return presed;
	}
}
