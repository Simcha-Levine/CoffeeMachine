package coffee;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;


public class CoffeeMeshineMain {
	
	public static void main(String[] args) {

		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Layout panel = new Layout();
		
//		Dimension windowSize = frame.getSize();
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        Point centerPoint = ge.getCenterPoint();
//
//        int dx = centerPoint.x - windowSize.width / 2 - 100;
//        int dy = centerPoint.y - windowSize.height / 2 - 100;    
        frame.setLocation(100, 100);

		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
