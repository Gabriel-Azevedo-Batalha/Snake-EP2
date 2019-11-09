import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

public class Painel extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	private boolean running;
	private Timer timer;
	
	private boolean SpecialFruit = false;
	private Fruta fruta;
	private ArrayList<Fruta> frutas;
	private BigFruit bigFruit;
	private ArrayList<BigFruit> bigFruits;
	private BombFruit bombFruit;
	private ArrayList<BombFruit> bombFruits;
	
	
	private Random r, gamble;
	
	private String tipoSnake = "Simple";
	
	private Corpo c;
	private ArrayList<Corpo> snake;
	private int coordX = 10, coordY = 10, size = 5, points = 0 ;
	private boolean right = true, left = false, up = false, down = false;
	
	public static final int width = 500, height = 500;
	
	public Painel() {
		setFocusable(true);
		setPreferredSize(new Dimension(width, height));
		addKeyListener(this);
		
		snake = new ArrayList<Corpo>();
		frutas = new ArrayList<Fruta>();
		bigFruits = new ArrayList<BigFruit>();
		bombFruits= new ArrayList<BombFruit>();
		timer = new Timer();
		r = new Random();
		gamble = new Random();
		
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
	
	public boolean tick() {
		
		if(snake.size() == 0) {
			c = new Corpo(coordX, coordY, 10, tipoSnake);
			snake.add(c);
		}
		try {
			TimeUnit.MILLISECONDS.sleep(70);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			if(right) coordX ++;
			if(left) coordX--;
			if(down) coordY++;
			if(up) coordY--;
			
			c = new Corpo(coordX, coordY, 10, tipoSnake);
			snake.add(c);
			
			if(snake.size() > size) {
				snake.remove(0);
			}

		//Criação da SimpleFruit
		if(frutas.size() == 0) {
			int coordX = r.nextInt(45);
			int coordY = r.nextInt(45);
			
			fruta = new Fruta(coordX, coordY, 10);
			frutas.add(fruta);
		}
		
		//Colisão SimpleFruit
		for(int i=0;i<frutas.size();i++) {
			if(coordX == frutas.get(i).getCoordX() && coordY == frutas.get(i).getCoordY()) {
				size++;
				frutas.remove(i);
				i++;
			}
		}
		
		//Randomizador fruta especial
		int gambled = gamble.nextInt(10);
		
		//Geração de fruta especial
		if (SpecialFruit == false && timer.isDelay() == false) {
			//Gerou BigFruit
			if(gambled >= 0 && gambled <= 5) {
				int coordX = r.nextInt(40);
				int coordY = r.nextInt(40);
				bigFruit = new BigFruit(coordX, coordY, 10);
				bigFruits.add(bigFruit);
				SpecialFruit = true;
				timer.setSumir(true);
			}
			//Gerou BombFruit
			if(gambled >= 6 && gambled <= 10) {
				int coordX = r.nextInt(40);
				int coordY = r.nextInt(40);
				bombFruit = new BombFruit(coordX, coordY, 10);
				bombFruits.add(bombFruit);
				SpecialFruit = true;
				timer.setSumir(true);
			}
		}
		
		//Colisão BigFruit
		for(int i=0;i<bigFruits.size();i++) {
			if((coordX == bigFruits.get(i).getCoordX() && coordY == bigFruits.get(i).getCoordY())) {
				size++;
				bigFruits.remove(i);
				i++;
				SpecialFruit = false;
				timer.setDelay(true);
				timer.setSumir(false);
				break;
			}
			if(!timer.isSumir()) {
				bigFruits.remove(i);
				i++;
				SpecialFruit = false;
				timer.setDelay(true);
			}
		}
		
		//Colisão BombFruit
			for(int i=0;i<bombFruits.size();i++) {
				if((coordX == bombFruits.get(i).getCoordX() && coordY == bombFruits.get(i).getCoordY())) {
					bombFruits.remove(i);
					i++;
					SpecialFruit = false;
					timer.setDelay(true);
					timer.setSumir(false);
					System.out.println("Game over");
					stop();
					return false;
					}
				if(!timer.isSumir()) {
					bombFruits.remove(i);
					i++;
					SpecialFruit = false;
					timer.setDelay(true);
				}
			}
		
		//Colisão Corpo
		for(int i=0;i<snake.size();i++) {
			if(coordX == snake.get(i).getCoordX() && coordY == snake.get(i).getCoordY()) {
				if(i != snake.size() -1) {
					System.out.println("Game over");
					stop();
					return false;
					
				}
			}
		}
		
		//Colisão bordas
		if (coordX < 0 || coordX > 49 || coordY < 0 || coordY > 49) {
			//Temporary
			System.out.println("Game over");
			stop();
			return false;
			
		}
		return true;
	}
	
	public void paint(Graphics g2) {
		Graphics2D g;
		g = (Graphics2D) g2;
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
		for(int i=0;i<bigFruits.size();i++) {
			bigFruits.get(i).draw(g);
		}
		for(int i=0;i<bombFruits.size();i++) {
			bombFruits.get(i).draw(g);
		}
		
	}

	@Override
	public void run() {
		while(running) {
			//tick();
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
		try {
			TimeUnit.MILLISECONDS.sleep(70);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
