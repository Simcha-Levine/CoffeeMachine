package coffee;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JFrame;


public class CoffeeMeshineMain {
	
	public static void main(String[] args) throws IOException {

		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Layout panel = new Layout();

        frame.setLocation(100, 100);

		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
