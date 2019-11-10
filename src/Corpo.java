import java.awt.Color;
import java.awt.Graphics;
import java.lang.String;
import java.util.Random;

public class Corpo {
	
	private int coordX, coordY, width, height, rx ,ry;
	private String snake;
	private Random r;

	//Construtor
	public Corpo(int coordX, int coordY, int tileSize, String snake) {
		r = new Random();
		this.coordX = coordX;
		this.coordY = coordY;
		width = tileSize;
		height = tileSize;
		this.snake = snake;
		
	}
	public void tick() {
	}
	
	//Draw
	public void draw(Graphics g) {
		//Draw Simple Snake
		if(snake == "Simple"){
			g.setColor(Color.GREEN);
			g.fillRect(coordX*width, coordY*height, width, height);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(coordX*width, coordY*height, width/2, height/2);
		}
		//Draw Kitty Snake
		if(snake == "Kitty"){
			g.setColor(Color.ORANGE);
			g.fillRect(coordX*width, coordY*height, width, height);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(coordX*width, coordY*height, width/2, height/2);
		}
		//Draw Star Snake
		if(snake == "Star"){
			g.setColor(Color.YELLOW);
			g.fillRect(coordX*width, coordY*height, width, height);
			//Brilho
			rx = r.nextInt(5);
			ry = r.nextInt(5);
			g.setColor(Color.WHITE);
			g.fillRect((coordX*width)+rx, (coordY*height)+ry, 3, 3);
		}
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
}
