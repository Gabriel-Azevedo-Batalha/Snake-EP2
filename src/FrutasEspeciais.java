import java.awt.Graphics;

public abstract class FrutasEspeciais {
	protected int coordX, coordY, width, height, pontos;
	
	public abstract void draw(Graphics g);
	
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


