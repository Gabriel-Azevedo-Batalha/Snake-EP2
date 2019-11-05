import java.awt.Color;
import java.awt.Graphics;
import java.lang.String;

public class Corpo {
	
	private int coordX, coordY, width, height;
	private String snake;

	public Corpo(int coordX, int coordY, int tileSize, String snake) {
		
		this.coordX = coordX;
		this.coordY = coordY;
		width = tileSize;
		height = tileSize;
		this.snake = snake;
		
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillRect(coordX*width, coordY*height, width, height);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(coordX*width, coordY*height, width/2, height/2);
		
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
