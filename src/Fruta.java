import java.awt.Color;
import java.awt.Graphics;

public class Fruta {
	
	private int coordX, coordY, width, height;
	
	public Fruta(int coordX, int coordY, int tileSize) {
		
		this.coordX = coordX;
		this.coordY = coordY;
		width = tileSize;
		height = tileSize;
		
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.RED);
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
