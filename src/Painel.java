import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Painel extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	private boolean running;
	
	private Fruta fruta;
	private ArrayList<Fruta> frutas;
	
	private Random r;
	
	private Corpo c;
	private ArrayList<Corpo> snake;
	private int coordX = 10, coordY = 10, size = 5;
	private boolean right = true, left = false, up = false, down = false;
	
	private int ticks = 0;
	
	public static final int width = 500, height = 500;
	
	public Painel() {
		setFocusable(true);
		setPreferredSize(new Dimension(width, height));
		addKeyListener(this);
		
		snake = new ArrayList<Corpo>();
		frutas = new ArrayList<Fruta>();
		
		r = new Random();
		
		start();
	}
	
	public void start() {
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public void stop() {
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void tick() {
		
		if(snake.size() == 0) {
			c = new Corpo(coordX, coordY, 10);
			snake.add(c);
		}
		
		ticks++;
		
		if(ticks > 750000) {
			if(right) coordX++;
			if(left) coordX--;
			if(down) coordY++;
			if(up) coordY--;
			
			ticks = 0;
			c = new Corpo(coordX, coordY, 10);
			snake.add(c);
			
			if(snake.size() > size) {
				snake.remove(0);
			}
		}
		if(frutas.size() == 0) {
			int coordX = r.nextInt(49);
			int coordY = r.nextInt(49);
			
			fruta = new Fruta(coordX, coordY, 10);
			frutas.add(fruta);
		}
		for(int i=0;i<frutas.size();i++) {
			if(coordX == frutas.get(i).getCoordX() && coordY == frutas.get(i).getCoordY()) {
				size++;
				frutas.remove(i);
				i++;
			}
		}
		//if (coordX)
		
	}
	
	public void paint(Graphics g) {
		
		g.clearRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		for(int i=0;i<width/10;i++) {
			g.drawLine(i*10, 0, i*10, height);
		}
		
		for(int i=0;i<height/10;i++) {
			g.drawLine(0, i*10, height, i*10);
		}
		for(int i=0;i<snake.size();i++) {
			snake.get(i).draw(g);
		}
		for(int i=0;i<frutas.size();i++) {
			frutas.get(i).draw(g);
		}
		
	}

	@Override
	public void run() {
		while(running) {
			tick();
			repaint();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent event) {

		int key = event.getKeyCode();
		if(key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_UP && !down) {
			right = false;
			left = false;
			up = true;
		}
		if(key == KeyEvent.VK_DOWN && !up) {
			right = false;
			left = false;
			down = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
