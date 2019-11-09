import java.awt.Color;
import java.awt.Graphics;

public class BombFruit extends FrutasEspeciais{

	public BombFruit(int coordX, int coordY, int tileSize) {
		
		this.coordX = coordX;
		this.coordY = coordY;
		width = tileSize;
		height = tileSize;
		
	}

	public void draw(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(coordX*width, coordY*height, width, height);
		g.fillRect((coordX*width)+5, (coordY*height)-3, 2, 3);
		g.setColor(Color.BLACK);
		g.fillRect((coordX*width)+1, (coordY*height)+1, width-2, height-2);
		g.setColor(Color.RED);
		g.fillRect((coordX*width)+5, (coordY*height)-5, 3, 2);
		
	}
	
}
