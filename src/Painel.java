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
	private DecreaseFruit decreaseFruit;
	private ArrayList<DecreaseFruit> decreaseFruits;
	
	private Wall wall;
	private ArrayList<Wall> wall1, wall2, wall3, wall4;
	
	
	private Random r, gamble;
	
	private String tipoSnake;
	
	private Corpo c;
	private ArrayList<Corpo> snake;
	private int coordX = 10, coordY = 7, size = 5, points = 0, multiplier = 1 ;
	private boolean right = true, left = false, up = false, down = false;
	
	public static final int width = 500, height = 500;
	
	//Construtor
	public Painel(String tipoSnake) {
		setFocusable(true);
		setPreferredSize(new Dimension(width, height));
		addKeyListener(this);
		this.tipoSnake = tipoSnake;
		if(tipoSnake == "Star") multiplier = 2;
		wall1 = new ArrayList<Wall>();
		wall2 = new ArrayList<Wall>();
		wall3 = new ArrayList<Wall>();
		wall4 = new ArrayList<Wall>();
		snake = new ArrayList<Corpo>();
		frutas = new ArrayList<Fruta>();
		bigFruits = new ArrayList<BigFruit>();
		bombFruits = new ArrayList<BombFruit>();
		decreaseFruits = new ArrayList<DecreaseFruit>();
		timer = new Timer();
		r = new Random();
		gamble = new Random();
		
		for(int i=0;i<16;i++) {
			wall = new Wall(10,17+i,10);
			wall1.add(wall);
		}
		for(int i=0;i<16;i++) {
			wall = new Wall(40,17+i,10);
			wall2.add(wall);
		}
		for(int i=0;i<16;i++) {
			wall = new Wall(17+i,10,10);
			wall3.add(wall);
		}
		for(int i=0;i<16;i++) {
			wall = new Wall(17+i,40,10);
			wall4.add(wall);
		}
		
		start();
	}
	
	//Start thread
	public void start() {
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	//Stop thread
	public void stop() {
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Update física
	public boolean tick() {
		
		//Criação Snake
		if(snake.size() == 0) {
			c = new Corpo(coordX, coordY, 10, tipoSnake);
			snake.add(c);
		}
		
		//Velocidade do jogo
		try {
			TimeUnit.MILLISECONDS.sleep(70);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Update direcional
		if(right) coordX ++;
		if(left) coordX--;
		if(down) coordY++;
		if(up) coordY--;
			
		//Update posição da snake
		c = new Corpo(coordX, coordY, 10, tipoSnake);
		snake.add(c);	
		if(snake.size() > size) {
			snake.remove(0);
		}
			

		//Criação da SimpleFruit
		if(frutas.size() == 0) {
			int coordX = r.nextInt(48)+1;
			int coordY = r.nextInt(48)+1;
			if (WallCollide(coordX, coordY) == 1) coordX++;
			if (WallCollide(coordX, coordY) == 2) coordX--;
			if (WallCollide(coordX, coordY) == 3) coordY++;
			if (WallCollide(coordX, coordY) == 4) coordY--;
			fruta = new Fruta(coordX, coordY, 10);
			frutas.add(fruta);
		}
		
		//Colisão SimpleFruit
		for(int i=0;i<frutas.size();i++) {
			if(coordX == frutas.get(i).getCoordX() && coordY == frutas.get(i).getCoordY()) {
				size++;
				frutas.remove(i);
				i++;
				points += 10*multiplier;
			}
		}
		
		//Randomizador fruta especial
		int gambled = gamble.nextInt(10);
		
		//Geração de fruta especial
		if (SpecialFruit == false && timer.isDelay() == false) {
			int coordX = r.nextInt(48)+1;
			int coordY = r.nextInt(48)+1;
			if (WallCollide(coordX, coordY) == 1) coordX++;
			if (WallCollide(coordX, coordY) == 2) coordX--;
			if (WallCollide(coordX, coordY) == 3) coordY++;
			if (WallCollide(coordX, coordY) == 4) coordY--;
			//Gerou BigFruit
			if(gambled >= 0 && gambled <= 4) {
				
				bigFruit = new BigFruit(coordX, coordY, 10);
				bigFruits.add(bigFruit);
				SpecialFruit = true;
				timer.setSumir(true);
			}
			//Gerou BombFruit
			if(gambled >= 5 && gambled <= 8) {
				bombFruit = new BombFruit(coordX, coordY, 10);
				bombFruits.add(bombFruit);
				SpecialFruit = true;
				timer.setSumir(true);
			}
			//Gerou DecreaseFruit
			if(gambled >= 9 && gambled <= 10) {
				decreaseFruit = new DecreaseFruit(coordX, coordY, 10);
				decreaseFruits.add(decreaseFruit);
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
				points += 20*multiplier;
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
		
		//Colisão DecreaseFruit
		for(int i=0;i<decreaseFruits.size();i++) {
			if((coordX == decreaseFruits.get(i).getCoordX() && coordY == decreaseFruits.get(i).getCoordY())) {
				size = 5;
				decreaseFruits.remove(i);
				i++;
				SpecialFruit = false;
				timer.setDelay(true);
				timer.setSumir(false);
				break;
			}
			if(!timer.isSumir()) {
				decreaseFruits.remove(i);
				i++;
				SpecialFruit = false;
				timer.setDelay(true);
			}
		}
		if(snake.size() > size) {
			snake.remove(0);
		}
		
		//Colisão Corpo
		for(int i=0;i<snake.size();i++) {
			if(coordX == snake.get(i).getCoordX() && coordY == snake.get(i).getCoordY()) {
				if(i != snake.size() -1) {
					stop();
					return false;
					
				}
			}
		}
		
		//Colisão Wall
		if (WallCollide(coordX, coordY) != 0 && tipoSnake != "Kitty") {
			stop();
			return false;
		}
			
		//Colisão bordas
		if (coordX <= -1) coordX = 49;
		if (coordX >= 50) coordX = 0;
		if (coordY <= -1) coordY = 49;
		if (coordY >= 50) coordY = 0;
			
		return true;
	}
	
	//Draw
	public void paint(Graphics g2) {
		Graphics2D g;
		g = (Graphics2D) g2;
		
		//Draw Grid
		g.clearRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		for(int i=0;i<width/10;i++) {
			g.drawLine(i*10, 0, i*10, height);
		}
		for(int i=0;i<height/10;i++) {
			g.drawLine(0, i*10, height, i*10);
		}
		
		//Draw Walls
				for(int i=0;i<wall1.size();i++) {
					wall1.get(i).draw(g);
				}
				for(int i=0;i<wall2.size();i++) {
					wall2.get(i).draw(g);
				}
				for(int i=0;i<wall3.size();i++) {
					wall3.get(i).draw(g);
				}
				for(int i=0;i<wall4.size();i++) {
					wall4.get(i).draw(g);
				}
		//Draw Snake
		for(int i=0;i<snake.size();i++) {
			snake.get(i).draw(g);
		}
		
		//Draw SimpleFruit
		for(int i=0;i<frutas.size();i++) {
			frutas.get(i).draw(g);
		}
		
		//Draw BigFruit
		for(int i=0;i<bigFruits.size();i++) {
			bigFruits.get(i).draw(g);
		}
		
		//Draw BombFruit
		for(int i=0;i<bombFruits.size();i++) {
			bombFruits.get(i).draw(g);
		}
		
		//Draw DecreaseFruit
		for(int i=0;i<decreaseFruits.size();i++) {
			decreaseFruits.get(i).draw(g);
		}
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(points), 0, 10);
	}
	
	//Colisão com os walls
	public int WallCollide(int coordX, int coordY) {
		for(int i=0;i<wall1.size();i++) {
			if((coordX == wall1.get(i).getCoordX() && coordY == wall1.get(i).getCoordY())) {
				return 1;
			}
		}
		for(int i=0;i<wall2.size();i++) {
			if((coordX == wall2.get(i).getCoordX() && coordY == wall2.get(i).getCoordY())) {
				return 2;
			}
		}
		for(int i=0;i<wall3.size();i++) {
			if((coordX == wall3.get(i).getCoordX() && coordY == wall3.get(i).getCoordY())) {
				return 3;
			}
		}
		for(int i=0;i<wall4.size();i++) {
			if((coordX == wall4.get(i).getCoordX() && coordY == wall4.get(i).getCoordY())) {
				return 4;
			}
		}
		return 0;
	}
	
	//Thread para o Draw
	@Override
	public void run() {
		while(running) {
			repaint();
		}
		
	}

	//Key Events
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
	
	public int getPoints() {
		return points;
	}
}
