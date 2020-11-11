package coffee;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class Coins {
	
	private ImageIcon coine;
	
	private Point point;
	
	public final double radius = 50;
	private final int type;
	public final double value;
	
	public Coins(int tipe, Point p) {
		this.type = tipe;
		switch (tipe) {
		case 0:
			coine = new ImageIcon("/home/levine-fam/Desktop/coffeeMashine/1shekel.png");
			value = 1;
			break;
		case 1:
			coine = new ImageIcon("/home/levine-fam/Desktop/coffeeMashine/2shekel.png");
			value = 2;
			break;
		case 2:
			coine = new ImageIcon("/home/levine-fam/Desktop/coffeeMashine/10shekel.png");
			value = 10;
			break;
		case 3:
			coine = new ImageIcon("/home/levine-fam/Desktop/coffeeMashine/half.png");
			value = 0.5;
			break;
		case 4:
			coine = new ImageIcon("/home/levine-fam/Desktop/coffeeMashine/10agorot.png");
			value = 0.1;
			break;
		case 5:
			coine = new ImageIcon("/home/levine-fam/Desktop/coffeeMashine/5shekel.png");
			value = 5;
			break;
		default:
			coine = new ImageIcon("/home/levine-fam/Desktop/coffeeMashine/20shekel.png");
			value = 20;
			break;
		}
		point = p;
		
		
		
	}
	
	public void paint(Graphics g, Component c) {
		if(type != 6) {
			coine.paintIcon(c, g, point.x - 50, point.y - 50);
		} else {
			coine.paintIcon(c, g, point.x, point.y);
		}
	}
	
	public void move(Point p, Point dif) {
		if(type == 6) {
			point = new Point(p.x - dif.x, p.y - dif.y);
		} else {
			point = p;
		}
	}
	
	public Point getPoint() {
		return point;
	}
	public boolean isInCoin(Point p) {
		if(type == 6) {
			return Wallet.inBound(p, point, new Point(300, 165));
		}
		return getDictans(p, point) <= radius;
	}
	
	private double getDictans(Point p1, Point p2) {		
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}
}
