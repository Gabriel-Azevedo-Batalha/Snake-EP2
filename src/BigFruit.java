import java.awt.Color;
import java.awt.Graphics;

public class BigFruit extends FrutasEspeciais{
	
	public BigFruit(int coordX, int coordY, int tileSize) {
		this.coordX = coordX;
		this.coordY = coordY;
		width = tileSize;
		height = tileSize;
		pontos = 20;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(coordX*width, coordY*height, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(coordX*width, coordY*height, width/2, height/2);
	}
	
}
