package coffee;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Measure extends JPanel{
	
	private Point start;
	private Point lengthen;
	private int height;
	private int width;
	private int x, y;

	
	public Measure() {
		start = new Point(250,500);
		lengthen = new Point(300,550);
		
		addMouseListener(new Listener());
		addMouseMotionListener(new Listener());
	}
	
	public void setStart(Point p) {
		start = p;
		lengthen = p;
//		System.out.println("start");
	}
	
	public void setLengthen(Point p) {
		lengthen = p;
//		System.out.println(lengthen);
	}
	
	public void end() {
		lengthen = new Point();
		start = new Point();
		height = 0;
		width = 0;
//		System.out.println("end");
	}
	
	public void printMeasure() {	
		System.out.println(x + " , " + y);
		System.out.println(width + " , " + height + "\n \n");
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		var minX = Math.min(start.x, lengthen.x);
		var maxX = Math.max(start.x, lengthen.x);
		var minY = Math.min(start.y, lengthen.y);
		var maxY = Math.max(start.y, lengthen.y);
		
		x = minX;
		y = minY;
		width = maxX - minX;
		height = maxY - minY;
		
		g.setColor(new Color(0,0,0,50));
		g.fillRect(x, y, width, height);
	}
	
	private class Listener extends MouseAdapter {
		
		@Override
		public void mouseDragged(MouseEvent e) {
			setLengthen(e.getPoint());
		}

		@Override
		public void mousePressed(MouseEvent e) {
			setStart(e.getPoint());
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			printMeasure();
			repaint();
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			end();
			repaint();
		}
	}

}
