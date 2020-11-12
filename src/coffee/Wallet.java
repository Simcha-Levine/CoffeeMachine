package coffee;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Wallet {
	
	private ImageIcon wallet;
	private Point point;
	private final List<Point> coinPoints;
	private final Point coinBounds;
	private final Point notePoint;
	private final Point noteBound;


	public Wallet(Point p) {
		point = p;

		ClassLoader classLoader = getClass().getClassLoader();
		wallet = new ImageIcon(classLoader.getResource("images/wallet2.png"));

		coinPoints = new ArrayList<>();
		coinBounds = new Point(100,50);
		
		for(int i = 0; i < 300; i += 100) {
			coinPoints.add(new Point(12 + i + point.x, 25 + point.y));
		}
		
		for(int i = 0; i < 300; i += 100) {
			coinPoints.add(new Point(12 + i + point.x, 75 + point.y));
		}
		
		noteBound = new Point(50,217);
		notePoint = new Point(310 + point.x, 35 + point.y);
	}
	
	public void paint(Graphics g, Component c) {
		
		wallet.paintIcon(c, g, point.x, point.y);
	}
	
	
	public int pressed(Point p) {
		for(Point coin : coinPoints) {
			 if(inBound(p, coin, coinBounds)) {
				 return coinPoints.indexOf(coin);
			 }
		}
		if(inBound(p, notePoint, noteBound)) {
			return 6;
		}
		return -1;
	}
	
	public static boolean inBound(Point checkPoint, Point obgectPoint, Point bound) {
		return checkPoint.x < bound.x + obgectPoint.x &&
				checkPoint.y < bound.y + obgectPoint.y &&
				checkPoint.x > obgectPoint.x &&
				checkPoint.y > obgectPoint.y;
	}

	
}
