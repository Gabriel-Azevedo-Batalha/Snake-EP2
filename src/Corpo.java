import java.awt.Color;
import java.awt.Graphics;

public class Corpo {
	
	private int coordX, coordY, width, height;

	public Corpo(int coordX, int coordY, int tileSize) {
		
		this.coordX = coordX;
		this.coordY = coordY;
		width = tileSize;
		height = tileSize;
		
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillRect(coordX*width, coordY*height, width, height);
		
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
