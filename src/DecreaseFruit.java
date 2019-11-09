import java.awt.Color;
import java.awt.Graphics;

public class DecreaseFruit extends FrutasEspeciais{
	public DecreaseFruit(int coordX, int coordY, int tileSize) {
		
		this.coordX = coordX;
		this.coordY = coordY;
		width = tileSize;
		height = tileSize;
		
	}

	public void draw(Graphics g) {
		
		g.setColor(Color.BLUE);
		g.fillRect(coordX*width, coordY*height, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(coordX*width, coordY*height, width/2, height/2);
		
	}
}
