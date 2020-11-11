package coffee;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.sun.jdi.event.Event;

public class ButtonGrid {
	

	private Point point;
	private Point size;
	private Point grid;
	private List<Drink> buttons;

	private Layout panel;
	
	public final String[] names = {" נס קפה"," קפה שחור"," תה"," שוקו"," מרק"," קולה"," מיץ תפוזים"};
	public final double[] prices = {2.4, 2.1, 1.6, 2.5, 3.1, 3.3, 3.2};
	
	public ButtonGrid(Point p, Point s, Point g) {
		point = p;
		size = s;
		grid = g;
		
		buttons = new ArrayList<>();


		for(int i = 0; i < grid.x * grid.y; i++) {
			if(i < names.length) {
				buttons.add(new Drink(names[i], prices[i], i));
			} else {
				buttons.add(new Drink("ריק", 0, 7));
			}
		}
	}
	
	private final int gap = 12;
	
	public void paint(Graphics g) {
		int k = 0;
		for(int i = 0; i < grid.x; i++) {
			for(int j = 0; j < grid.y; j++) { 
				var realLength = new Point(size.x / grid.x, size.y / grid.y);
				var length = new Point();
				var place = new Point(point.x,point.y);
				if(i == 0) {
					length.x = realLength.x - gap / 2;
				} else if(i == grid.x - 1) {
					length.x = realLength.x - gap / 2;
					place.x += realLength.x * (grid.x - 1) + gap / 2;
				} else {
					length.x = realLength.x - gap / 2;
					place.x += realLength.x * (i) + gap / 4;
				}
				
				if(j == 0) {
					length.y = realLength.y - gap / 2;
				} else if(j == grid.y - 1) {
					length.y = realLength.y - gap / 2;
					place.y += realLength.y * (grid.y - 1) + gap / 2;
				} else {
					length.y = realLength.y - gap / 2;
					place.y += realLength.y * (j) + gap / 4;
				}
				if(buttons.get(k).isPressed()) {
					color = 1;
				}
				setColor(g);
				color = 0;
				buttons.get(k).release();
				g.fillRect(place.x, place.y, length.x, length.y);
				buttons.get(k).setButton(place, length);
				g.setColor(Color.black);
				place = new Point(place.x + 10, place.y + 20);
				String str =  buttons.get(k).name + " " + buttons.get(k).price;
				CoffeeMethine.paintText(g, place, str, 20, Font.BOLD, 9);
				k++;
			}
		}
	}
	
	private int color = 0;
	private void setColor(Graphics g) {
		switch (color) {
		case 0:
			g.setColor(Color.pink);
			break;

		default:
			g.setColor(Color.magenta);
			break;
		}
	}
			
	
	public Drink isPressed(Point e) {
		int i = 0;
		for(Drink b : buttons) {
			if(b.checkPresed(e)) {
				return new Drink(b.name, b.price, i);
			}
			i++;
		}
		return null;
	}
	
	public void Released() {
		color = 0;
	}
	
	public void setPoint(Point p) {
		point = p;
	}
}
