package coffee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Layout extends JPanel{
	
	private List<Coins> coins;
	
	private CoffeeMethine coffeeMechine;
	private Wallet wallet;
	private Drink drink = null;
	
	private ButtonGrid buttons;
	
	private double paid = 0.0;
	
	public Layout() throws IOException {
		coins = new ArrayList<>();
		
		setPreferredSize(new Dimension(1000,680));
		setBackground(Color.cyan);
		setLayout(new BorderLayout());
		
		addMouseMotionListener(new Mouse(this));
		addMouseListener(new Mouse(this));
		
		coffeeMechine = new CoffeeMethine(new Point(0,0));
//		mechine = new Methine();

		add(coffeeMechine, BorderLayout.EAST);
		
		var x = getPreferredSize().width;
		buttons = new ButtonGrid(new Point(x - 360,200), new Point(320,200), new Point(3,3));
		wallet = new Wallet(new Point(50,50));
		
		JButton reset = new JButton("reset");
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				coins.clear();
				repaint();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.cyan);
		panel.add(reset);
		add(panel, BorderLayout.NORTH);
	}
	
	public void paint(Graphics g) {
		super.paint(g);

		var x = getSize().width;

		buttons.setPoint(new Point(x - 360,250));
		buttons.paint(g);
		wallet.paint(g, this);
		for(Coins coin : coins) {
			coin.paint(g, this);
		}
	}
	
	private Coins coinAtached = null;
	private int walletAtached = -1;
	
	private Point difference = new Point();
	
	private double chaneg = 0;

	private class Mouse extends MouseAdapter{
		
		Component c;
		public Mouse(Component c) {
			this.c = c;
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			var p = e.getPoint();
			
			if(coinAtached != null) {
				coinAtached.move(p, difference);
				if(isInSlot(p) && coinAtached != null) {
					paid += coinAtached.value;
					if(drink != null && paid >= drink.price) {
						setChange(drink.pay(paid, c));
						paid = 0;
						drink = null;
					}
					coffeeMechine.payStr = paid;

					coins.remove(coinAtached);
					coinAtached = null;
				}
				repaint();
				return;
			}	
			repaint();
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			final var p = e.getPoint();
			
			for(int i = coins.size() - 1; i >= 0; i--) {
				if(coins.get(i).isInCoin(p)) {
					difference.x = p.x - coins.get(i).getPoint().x;
					difference.y = p.y - coins.get(i).getPoint().y;

					coinAtached = coins.get(i);
					break;
				}
			}
			walletAtached = wallet.pressed(p);
			if(walletAtached != -1) {
				coins.add(new Coins(walletAtached, p));
				coinAtached = coins.get(coins.size() - 1);
				difference = new Point(150,100);
				coinAtached.move(p, difference);
				walletAtached = -1;
			}

			try {
				if(buttons.isPressed(e.getPoint()) != null) {
					drink = buttons.isPressed(e.getPoint());
					coffeeMechine.text = "you chose " + drink.name + " it cost's " + drink.price;
				}
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			repaint();
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			coinAtached = null;
			buttons.Released();
			difference = new Point();
			repaint();
		}
	}
	
	public static Point toPoint(Dimension d) {
		return new Point(d.width, d.height);
	}
	
	public boolean isInSlot(Point p) {
		Point obgectPoint = new Point(getSize().width - 125, getSize().height - 97);
		Point bound = new Point(125,42);
		return Wallet.inBound(p, obgectPoint, bound);
	}
	
	private void setChange(double c) {
		int difference = (int)(c * 10);
		int[] coinsAmount = new int[7]; 
		int[] coinsTipes = {200,100,50,20,10,5,1};
		
		if(difference != 0) {		
			int change = difference;
			
			for(int i = 0; i < 7; i++) {
				coinsAmount[i] = change / coinsTipes[i];
				change = change % coinsTipes[i];
			}		
		}
		
		var p = new Point( +50, getSize().height - 50);
		for(int i = 0; i < coinsAmount.length; i++) {
			p.y = getSize().height -50;
			for(int j = 0; j < coinsAmount[i]; j++) {
				switch (i) {
				case 0:
					coins.add(new Coins(6, new Point(p.x, p.y - 100)));//20
					break;
				case 1:
					coins.add(new Coins(2, new Point(p.x, p.y)));//10
					break;
				case 2:
					coins.add(new Coins(5, new Point(p.x, p.y)));//5
					break;
				case 3:
					coins.add(new Coins(1, new Point(p.x, p.y)));//2
					break;
				case 4:
					coins.add(new Coins(0, new Point(p.x, p.y)));//1
					break;
				case 5:
					coins.add(new Coins(3, new Point(p.x, p.y)));//0.5
					break;
				default:
					coins.add(new Coins(4, new Point(p.x, p.y)));//0.1
					break;
				}
				p.y -= 100;
			}
			p.x += 100 ;
		}		
	}
}
